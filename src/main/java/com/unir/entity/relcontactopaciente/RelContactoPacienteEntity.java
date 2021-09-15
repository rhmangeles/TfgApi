package com.unir.entity.relcontactopaciente;

/**
 * Relacion entre Contacto de emergencia y paciente.
 */
public class RelContactoPacienteEntity {

    private long idRelContactoPaciente;

    private long idContacto;

    private long idPaciente;

    public RelContactoPacienteEntity(long idRelContactoPaciente, long idContacto, long idPaciente) {
        this.idRelContactoPaciente = idRelContactoPaciente;
        this.idContacto = idContacto;
        this.idPaciente = idPaciente;
    }

    public RelContactoPacienteEntity() {
    }

    public long getIdRelContactoPaciente() {
        return idRelContactoPaciente;
    }

    public void setIdRelContactoPaciente(long idRelContactoPaciente) {
        this.idRelContactoPaciente = idRelContactoPaciente;
    }

    public long getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(long idContacto) {
        this.idContacto = idContacto;
    }

    public long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(long idPaciente) {
        this.idPaciente = idPaciente;
    }

}
