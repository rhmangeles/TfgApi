package com.unir.rest.api.model;

import java.io.Serializable;

public class JwtPacienteLoginApiResponse implements Serializable {

    private final String jwttoken;

    public JwtPacienteLoginApiResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }



}
