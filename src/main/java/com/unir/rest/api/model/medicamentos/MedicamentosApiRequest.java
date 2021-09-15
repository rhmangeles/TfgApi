package com.unir.rest.api.model.medicamentos;

import java.io.Serializable;

/**
 * Dto que contiene el id de paciente para la consulta de medicamentos activos que debe tomar.
 */
public class MedicamentosApiRequest implements Serializable {

    private String idPaciente;

    public MedicamentosApiRequest(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public MedicamentosApiRequest() {
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }
}
