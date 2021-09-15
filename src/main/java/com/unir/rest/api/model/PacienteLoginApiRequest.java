package com.unir.rest.api.model;

import java.io.Serializable;

public class PacienteLoginApiRequest implements Serializable {

    private String email;

    private String password;

    public PacienteLoginApiRequest() {
    }

    public PacienteLoginApiRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
