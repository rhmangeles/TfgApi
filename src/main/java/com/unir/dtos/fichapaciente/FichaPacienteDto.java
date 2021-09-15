package com.unir.dtos.fichapaciente;

import com.unir.dtos.consulta.ConsultaDto;
import com.unir.entity.consultas.ConsultaEntity;
import com.unir.entity.medicamentos.MedicamentoEntity;
import com.unir.entity.pacientes.PacienteEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO para la informacion de la ficha de paciente
 */
public class FichaPacienteDto implements Serializable {

    private PacienteEntity fichaPaciente;

    private List<ConsultaDto> consultas = new ArrayList<ConsultaDto>();

    private List<MedicamentoEntity> medicamentosActuales = new ArrayList<MedicamentoEntity>();

    public FichaPacienteDto() {}

    public PacienteEntity getFichaPaciente() {
        return fichaPaciente;
    }

    public void setFichaPaciente(PacienteEntity fichaPaciente) {
        this.fichaPaciente = fichaPaciente;
    }

    public List<ConsultaDto> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<ConsultaDto> consultas) {
        this.consultas = consultas;
    }

    public List<MedicamentoEntity> getMedicamentosActuales() {
        return medicamentosActuales;
    }

    public void setMedicamentosActuales(List<MedicamentoEntity> medicamentosActuales) {
        this.medicamentosActuales = medicamentosActuales;
    }
}
