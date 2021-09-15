package com.unir.rest.api.model.medicamentos;

import com.unir.entity.medicamentos.MedicamentoEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Dto que contiene la respuesta para devolver la lista de medicamentos activos que debe tomar el paciente.
 */
public class MedicamentosApiResponse implements Serializable {

    private List<MedicamentoEntity> medicamentos = new ArrayList<MedicamentoEntity>();


    public MedicamentosApiResponse(List<MedicamentoEntity> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public MedicamentosApiResponse() {
    }

    public List<MedicamentoEntity> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<MedicamentoEntity> medicamentos) {
        this.medicamentos = medicamentos;
    }
}
