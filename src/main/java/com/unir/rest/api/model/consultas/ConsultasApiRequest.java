package com.unir.rest.api.model.consultas;

import java.io.Serializable;

/**
 * Dto que contiene el modelo para consultar una a
 */
public class ConsultasApiRequest implements Serializable {

    /**
     * Id del paciente logado.
     */
    private String idPaciente;

    public ConsultasApiRequest(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public ConsultasApiRequest() {
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }
}
