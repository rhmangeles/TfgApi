package com.unir.controller.medicamentos;

import com.unir.controller.BaseAbstractController;
import com.unir.entity.medicamentos.MedicamentoEntity;
import com.unir.services.medicamentos.MedicamentosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MedicamentoController extends BaseAbstractController {

    @Autowired
    private MedicamentosService medicamentosService;

    /**
     * Devuelve todos los medicamentos que existen en la BBDD.
     * @param request
     * @return
     */
    @GetMapping("/lista_medicamentos")
    public ModelAndView getAllMedicamentos(HttpServletRequest request) {
        ModelAndView ret = new ModelAndView("index");

        //Verificamos la sesion.
        if (verifySession(request)) {
            // Definimos la vista de los medicamentos.
            ret = new ModelAndView("medicos/lista_medicamentos");
            ret.addObject("medicamentos", medicamentosService.getAllMedicamentos());
        }

        return ret;
    }

    /**
     * Redirige a un nuevo formulario para registrar un medicamento.
     * @param request Request de la peticion.
     * @param response Response de la peticion
     * @return ModelAndView
     */
    @GetMapping("/new_form_medicamento")
    public ModelAndView showFormNewMedicamentos(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView ret = new ModelAndView("index");

        if (verifySession(request)) {
            ret = new ModelAndView("medicos/form_new_medicamento");
        }

        return ret;
    }

    /**
     * Guardamos un nuevo medicamento en la BBDD.
     * @param request request.
     * @param response response.
     * @return ModelAndView
     */
    @PostMapping("/save_form_nuevo_medicamento")
    public ModelAndView saveNewMedicamento(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView ret = new ModelAndView("index");
        if (verifySession(request)) {
            ret = new ModelAndView("medicos/main");

            /* Recuperamos los valores para el nuevo medicamento */
            String nombre          = request.getParameter("nombreInput");
            String PC              = request.getParameter("pcInput");
            String consideraciones = request.getParameter("consideracionesInput");

            /* Parseamos los valores en una entidad de medicamento */
            MedicamentoEntity medicamento = new MedicamentoEntity();
            medicamento.setNombre(nombre);
            medicamento.setPC(PC);
            medicamento.setConsideraciones(consideraciones);
            medicamento.setToma("dia"); // POr defecto es dia, aunque debe ir en la relacon.
            String hoy = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS").format(new Date());
            medicamento.setEvery(hoy);

            /* Guardamos en la BBDD el nuevo registro. */
            medicamentosService.guardaMedicamentoNuevo(medicamento);

        }
        return  ret;
    }

}
