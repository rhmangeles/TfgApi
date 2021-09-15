package com.unir.controller.medicos;

import com.unir.controller.BaseAbstractController;
import com.unir.entity.medicos.DoctorEntity;
import com.unir.entity.pacientes.PacienteEntity;
import com.unir.services.medicos.MedicosService;
import com.unir.services.pacientes.PacientesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Controlador para las operaciones de los medicos.
 */

@Controller
public class MedicoController extends BaseAbstractController {

    @Autowired
    private MedicosService medicosService;

    @Autowired
    private PacientesServices pacientesServices;

    /**
     * Devolvemos todos los medicos que existen en la app y lo pasamos a la vista de lista de medicos.
     * @param request request de la peticion
     * @param response response de peticion
     * @return
     */
    @GetMapping("/lista_medicos")
    public ModelAndView getAllMedicos(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView ret = new ModelAndView("lista_medicos");
        ret.addObject("medicos", medicosService.getAllMedicos());
        return ret;
    }

    /**
     * Metodo encargado del login de un medico.
     * @param response
     * @param request
     * @return
     */
    @PostMapping("/verify_doctor")
    public ModelAndView verifyDoctor(HttpServletResponse response, HttpServletRequest request) {
        ModelAndView ret = new ModelAndView("index");
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        DoctorEntity doctor = medicosService.verifyLogin(email, password);
        if (doctor != null) {

            //Llamamos al modelo con la template que necesitamos.
            ret = new ModelAndView("medicos/main");

            // Si existe usuario lo incluimos en la sesión.
            List<PacienteEntity> listaPaciente = pacientesServices.getAllPacientesForDoctorFrom(doctor);

            // Incluimos el doctor en la sesion.
            request.getSession().setAttribute("user", doctor);

            // Incluimos la lista de pacientes en la llamada al front.
            ret.addObject("pacientes", listaPaciente);

        } else {
            ret.addObject("msg","Usuario o password incorrecto");
        }
        return ret;
    }


    @GetMapping("/closeSession")
    public ModelAndView closeSession(HttpServletRequest request) {
        ModelAndView ret = new ModelAndView("index");
        request.getSession().invalidate();
        return ret;
    }
}
