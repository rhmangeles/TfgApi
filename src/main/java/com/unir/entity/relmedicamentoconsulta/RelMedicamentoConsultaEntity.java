package com.unir.entity.relmedicamentoconsulta;

/**
 * Clase que contiene la relacion entre un medicamento preescrito en una consulta y esta consulta.
 */
public class RelMedicamentoConsultaEntity {

    private long idRelMedicamentoConsulta;

    private long idConsulta;

    private long idMedicamento;

    public RelMedicamentoConsultaEntity() {}

    public RelMedicamentoConsultaEntity(long idRelMedicamentoConsulta, long idConsulta, long idMedicamento) {
        this.idRelMedicamentoConsulta = idRelMedicamentoConsulta;
        this.idConsulta = idConsulta;
        this.idMedicamento = idMedicamento;
    }

    public long getIdRelMedicamentoConsulta() {
        return idRelMedicamentoConsulta;
    }

    public void setIdRelMedicamentoConsulta(long idRelMedicamentoConsulta) {
        this.idRelMedicamentoConsulta = idRelMedicamentoConsulta;
    }

    public long getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(long idConsulta) {
        this.idConsulta = idConsulta;
    }

    public long getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(long idMedicamento) {
        this.idMedicamento = idMedicamento;
    }
}
