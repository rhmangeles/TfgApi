package com.unir.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController extends BaseAbstractController{

    /**
     * Devuelve ok si el servicio esta levantado.
     * @return String - codigo de estado
     */
    @GetMapping(path = "/status")
    public ResponseEntity<String> getStatus() {
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }

    @GetMapping("/")
    public ModelAndView login() {
        ModelAndView ret = new ModelAndView("index");
        ret.addObject("mensaje","Esta es la pantalla de login.");
        return ret;
    }
}
