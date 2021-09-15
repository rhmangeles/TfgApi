package com.unir.entity.relpacientemedico;

/**
 * Representación de la tabla intermedia que relaciona a los medicos y los pacientes.
 */
public class RelPacienteMedicoEntity {

    private long idRelPacienteMedico;

    private long idMedico;

    private long idPaciente;

    public RelPacienteMedicoEntity() {
    }

    public RelPacienteMedicoEntity(long idRelPacienteMedico, long idMedico, long idPaciente) {
        this.idRelPacienteMedico = idRelPacienteMedico;
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
    }

    public long getIdRelPacienteMedico() {
        return idRelPacienteMedico;
    }

    public void setIdRelPacienteMedico(long idRelPacienteMedico) {
        this.idRelPacienteMedico = idRelPacienteMedico;
    }

    public long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(long idMedico) {
        this.idMedico = idMedico;
    }

    public long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(long idPaciente) {
        this.idPaciente = idPaciente;
    }
}
