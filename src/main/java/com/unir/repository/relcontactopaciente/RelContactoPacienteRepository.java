package com.unir.repository.relcontactopaciente;

import com.unir.entity.relcontactopaciente.RelContactoPacienteEntity;

import java.util.List;

public interface RelContactoPacienteRepository {

    int count();

    int save(RelContactoPacienteEntity rel);

    int update(RelContactoPacienteEntity rel);

    int deleteById(Long id);

    List<RelContactoPacienteEntity> findAllWherePacienteId(long idPaciente);

}
