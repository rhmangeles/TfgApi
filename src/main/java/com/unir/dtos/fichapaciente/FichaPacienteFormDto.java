package com.unir.dtos.fichapaciente;

import com.unir.entity.medicamentos.MedicamentoEntity;
import com.unir.entity.pacientes.PacienteEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Datos para dar de alta un nuevo usuario.
 */
public class FichaPacienteFormDto {

    // LIsta de pacientes asociados al medico.
    private List<PacienteEntity> pacientesAsociados = new ArrayList<PacienteEntity>();

    // Lista de medicamentos registrados.
    private List<MedicamentoEntity> medicamentosDisponibles = new ArrayList<MedicamentoEntity>();

    private String fechaConsulta;

    public FichaPacienteFormDto() {
    }

    public List<PacienteEntity> getPacientesAsociados() {
        return pacientesAsociados;
    }

    public void setPacientesAsociados(List<PacienteEntity> pacientesAsociados) {
        this.pacientesAsociados = pacientesAsociados;
    }

    public List<MedicamentoEntity> getMedicamentosDisponibles() {
        return medicamentosDisponibles;
    }

    public void setMedicamentosDisponibles(List<MedicamentoEntity> medicamentosDisponibles) {
        this.medicamentosDisponibles = medicamentosDisponibles;
    }

    public String getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(String fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }
}
