package com.unir.controller.consultas;

import com.unir.controller.BaseAbstractController;
import com.unir.dtos.fichapaciente.FichaPacienteFormDto;
import com.unir.entity.consultas.ConsultaEntity;
import com.unir.entity.medicamentos.MedicamentoEntity;
import com.unir.entity.medicos.DoctorEntity;
import com.unir.entity.pacientes.PacienteEntity;
import com.unir.entity.relmedicamentoconsulta.RelMedicamentoConsultaEntity;
import com.unir.services.consultas.ConsultasService;
import com.unir.services.medicamentos.MedicamentosService;
import com.unir.services.pacientes.PacientesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Transient;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Controlador que prepara el formulario para dar de alta una nueva consulta.
 */

@Controller
public class ConsultasController extends BaseAbstractController {

    @Autowired
    private PacientesServices pacientesServices;

    @Autowired
    private MedicamentosService medicamentosService;

    @Autowired
    private ConsultasService consultasService;

    @GetMapping(value = "/nueva_consulta")
    public ModelAndView creaFormNuevaConsulta(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView ret = new ModelAndView("index"); //main
        FichaPacienteFormDto consultaForm = null;

        if (verifySession(request)) {
            ret = new ModelAndView("medicos/nueva_consulta");
            consultaForm = new FichaPacienteFormDto();
            DoctorEntity user = (DoctorEntity) request.getSession().getAttribute("user");
            long idPaciente = Long.parseLong(request.getParameter("idpaciente"));

            // Creamos los datos iniciales para dar de alta la nueva consulta.
            String dateNow = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
            consultaForm.setFechaConsulta(dateNow);

            // Incluimos la lista de pacientes que estan asoc. al medico.
            consultaForm.setPacientesAsociados(pacientesServices.getAllPacientesForDoctorFrom(user));

            //Incluimos los medicamentos que estan disponibles en la BBDD.
            consultaForm.setMedicamentosDisponibles(medicamentosService.getAllMedicamentos());

            ret.addObject("formData", consultaForm);
        }

        return ret;
    }

    /**
     * Guardamos en la BBDD el nuevo registro de consulta.
     * @param request
     * @param response
     * @return
     */
    @Transactional //Ojo en un futuro debe ir en el servicio correspondiente.
    @PostMapping(value = "/save_consulta")
    public ModelAndView guardaNuevaConsulta(HttpServletRequest request, HttpServletResponse response) {
        String idsMedicamentos = request.getParameter("idsmedicamentos"); // Medicamentos y hora de toma.
        String idPaciente = request.getParameter("paciente"); //id del paciente.
        DoctorEntity doctor = (DoctorEntity) request.getSession().getAttribute("user");
        PacienteEntity paciente = pacientesServices.getPacienteById(Long.parseLong(idPaciente));

        //Se guarda la consulta
        ConsultaEntity consulta = new ConsultaEntity();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:s");
        consulta.setFechaDeConsulta(sdf.format(new Date()));
        consulta.setIdPaciente(Long.parseLong(idPaciente));
        consulta.setIdDoctor(doctor.getIdDoctor());
        int consultaId = consultasService.saveNuevaConsulta(consulta);

        // Parseamos la hora.
        String[] idsMedicamentosTemp = idsMedicamentos.split(",");
        for (String temp : idsMedicamentosTemp) {
            if (!temp.isEmpty()) {
                long medId = Long.parseLong(temp.split(";")[0]);
                String hora  = temp.split(";")[1];
                // Recuperamos el id del medicamento.
                MedicamentoEntity med = medicamentosService.getMedicamentoPorId(medId);
                MedicamentoEntity newMed = new MedicamentoEntity();
                newMed.setToma(hora);
                newMed.setNombre(med.getNombre());
                newMed.setConsideraciones(med.getConsideraciones());
                newMed.setPC(med.getPC());
                newMed.setEvery(med.getEvery());
                System.out.println("Id Medicamento: "+medId);
                System.out.println("Hora : "+hora);

                // Se guarda el nuevo medicamento
                int idMedicamento = medicamentosService.guardaMedicamentoNuevo(newMed);

                // Se guarda la relacion.
                RelMedicamentoConsultaEntity relacion = new RelMedicamentoConsultaEntity();
                relacion.setIdConsulta(consultaId);
                relacion.setIdMedicamento(idMedicamento);

                medicamentosService.guardaNuevaRelacionMedicamentoConsulta(relacion);
                System.out.println("REgistrada la consulta.");
            }

        }


        return new ModelAndView("medicos/main");
    }
}
