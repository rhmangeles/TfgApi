package com.unir.dtos.consulta;

import com.unir.entity.medicos.DoctorEntity;
import com.unir.entity.pacientes.PacienteEntity;

/**
 * Dto de consulta.
 */
public class ConsultaDto {

    private String fechaConsulta;

    private DoctorEntity medico;

    private PacienteEntity paciente;

    public ConsultaDto() {}


    public String getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(String fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public DoctorEntity getMedico() {
        return medico;
    }

    public void setMedico(DoctorEntity medico) {
        this.medico = medico;
    }

    public PacienteEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
    }
}
