package com.unir.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Clase base con utilizades para los controladores.
 */
public class BaseAbstractController {

    /**
     * Comprueba que existe un usuario valido en la sesion.
     * @param request
     * @return
     */
    protected boolean verifySession(HttpServletRequest request) {
        boolean ret = Boolean.FALSE; // POr defecto es false.
        // Verificamos si existe usuario en la sesion.
        if (request.getSession().getAttribute("user") != null) {
            ret = Boolean.TRUE;
        }
        return ret;
    }
}
