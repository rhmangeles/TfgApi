package com.unir.controller.pacientes;

import com.unir.controller.BaseAbstractController;
import com.unir.dtos.fichapaciente.FichaPacienteDto;
import com.unir.entity.consultas.ConsultaEntity;
import com.unir.entity.medicos.DoctorEntity;
import com.unir.entity.pacientes.PacienteEntity;
import com.unir.entity.relpacientemedico.RelPacienteMedicoEntity;
import com.unir.services.consultas.ConsultasService;
import com.unir.services.medicamentos.MedicamentosService;
import com.unir.services.pacientes.PacientesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.print.Doc;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class PacienteController extends BaseAbstractController {

    @Autowired
    private PacientesServices pacientesServices;

    @Autowired
    private ConsultasService consultasService;

    @Autowired
    private MedicamentosService medicamentosService;

    @GetMapping("/ficha_paciente")
    public ModelAndView showFichaPaciente(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView ret = new ModelAndView("index");
        FichaPacienteDto fichaPaciente = null;

        // Verificamos la sesion del usuario.
        if (verifySession(request)) {

            ret = new ModelAndView("medicos/ficha_paciente");
            fichaPaciente = new FichaPacienteDto();

            long idPaciente = Long.parseLong(request.getParameter("idpaciente"));

            fichaPaciente.setFichaPaciente(pacientesServices.getPacienteById(idPaciente));
            fichaPaciente.setConsultas(consultasService.getAllConsultasByIdPaciente(idPaciente));

            // Si dispone de historial de consultas procedemos a cargar los datos de la misma.
            if (fichaPaciente.getConsultas().size() > 0) {
                System.out.println("Dentro");

                //Buscamos la ultima consulta si esta existe y pasamos el id de consulta.
                ConsultaEntity ultimaConsulta = consultasService.getLastConsultaByIdPaciente(idPaciente);
                System.out.println("Ultima consulta id: "+ultimaConsulta.getIdConsulta());

                //En caso de que la ultima consulta exista incluimos la información en la ficha.
                if (ultimaConsulta != null) {
                    fichaPaciente.setMedicamentosActuales(medicamentosService
                            .getAllMedicamentosByIdConsulta(ultimaConsulta.getIdConsulta()));
                    System.out.println("Medicamentos Actuales: "+fichaPaciente.getMedicamentosActuales().size());
                }
            }

            ret.addObject("ficha_paciente", fichaPaciente);

        }

        return ret;
    }

    @GetMapping("/lista_pacientes")
    public ModelAndView getListaPacientes(HttpServletRequest request) {
        //Por defecto redireccionamos a login.
        ModelAndView ret = new ModelAndView("index");

        //Recuperamos al medico de la sesion.
        DoctorEntity medicoActual = (DoctorEntity) request.getSession().getAttribute("user");

        //Verificamos si el usuario logado es nulo, en caso de serlo redireccionamos a login.
        if (medicoActual != null) {
            ret = new ModelAndView("medicos/main");

            // Recuperamos todos los pacientes asociados al doctor.
            List<PacienteEntity> listaPaciente = pacientesServices.getAllPacientesForDoctorFrom(medicoActual);

            //Incluimos la lista de pacientes asociados al doctor en el model.
            ret.addObject("pacientes", listaPaciente);
        }

        return ret;
    }

    /**
     * REdirige al formulario para dar de alta un nuevo paciente.
     * @param request REquest de la peticion.
     * @param response REsposne de la peticion.
     * @return ModelAndView
     */
    @GetMapping("/form_new_paciente")
    public ModelAndView goToFormNewPaciente(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView ret = new ModelAndView("index");

        // Comprobamos que el usuario dispone de sesion.
        if (verifySession(request)) {
            ret = new ModelAndView("medicos/form_new_paciente"); // Formulario de alta para nuevo paciente.

        }
        return ret;
    }

    /**
     * Guarda un nuevo paciente en la BBDD.
     * @param request request de la peticion.
     * @param response response de la peticion.
     * @return
     */
    @PostMapping("/save_form_nuevo_paciente")
    public ModelAndView saveNewPaciente(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView ret = new ModelAndView("index");

        // Verificamos si la sesion es correcta o no.
        if (verifySession(request)) {
            ret = new ModelAndView("medicos/main");
            DoctorEntity user = (DoctorEntity) request.getSession().getAttribute("user");
            PacienteEntity paciente = new PacienteEntity();

            paciente.setNombre(request.getParameter("nombreInput"));
            paciente.setApellidos(request.getParameter("apellidosInput"));
            paciente.setCiudad(request.getParameter("ciudadInput"));
            paciente.setEmail(request.getParameter("emailInput"));
            paciente.setFechaNacimiento(request.getParameter("fechaNacInput"));

            //TODO: Valor fijo ya que es el usuario quien desde la app movil lo modificará en un futuro.
            paciente.setPassword("$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6");
            paciente.setSexo(request.getParameter("sexoInput"));

            // Guardamos el nuevo paciente en la BBDD.
            int idPaciente = pacientesServices.saveNewPaciente(paciente);

            //Guardamos la nueva relacion en la BBDD.
            RelPacienteMedicoEntity relacion = new RelPacienteMedicoEntity();
            relacion.setIdPaciente(idPaciente);
            relacion.setIdMedico(user.getIdDoctor());
            pacientesServices.newRelPacienteMedido(relacion);
        }

        return ret;
    }

}
