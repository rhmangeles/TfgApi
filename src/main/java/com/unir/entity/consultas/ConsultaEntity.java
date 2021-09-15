package com.unir.entity.consultas;

/**
 * Clase que contiene la información sobre la consulta en la que se expiden y programan los medicamentos.
 */

public class ConsultaEntity {

    // En tabla intermedia se encuentran los medicamentos recetados en esta consulta apuntando
    // a este id de consulta.
    private long idConsulta;

    /* https://stackoverflow.com/questions/2400955/how-to-store-java-date-to-mysql-datetime-with-jpa */
    private String fechaDeConsulta;

    private long idPaciente;

    private long idDoctor;

    //TODO: Lista de medicamentos recetados en la consulta

    public ConsultaEntity() {
    }

    public ConsultaEntity(long idConsulta, String fechaDeConsulta, long idPaciente, long idDoctor) {
        this.idConsulta = idConsulta;
        this.fechaDeConsulta = fechaDeConsulta;
        this.idPaciente = idPaciente;
        this.idDoctor = idDoctor;
    }

    public long getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(long idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getFechaDeConsulta() {
        return fechaDeConsulta;
    }

    public void setFechaDeConsulta(String fechaDeConsulta) {
        this.fechaDeConsulta = fechaDeConsulta;
    }

    public long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public long getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(long idDoctor) {
        this.idDoctor = idDoctor;
    }

}
