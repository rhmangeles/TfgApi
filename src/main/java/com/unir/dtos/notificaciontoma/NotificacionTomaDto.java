package com.unir.dtos.notificaciontoma;

import java.io.Serializable;

/**
 * Dto que contiene la informacion sobe la toma de un medicamento.
 */
public class NotificacionTomaDto implements Serializable {

    private long idPaciente;

    private long idMedicamento;

    private String datetime;

    public NotificacionTomaDto() {
    }

    public long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public long getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(long idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
