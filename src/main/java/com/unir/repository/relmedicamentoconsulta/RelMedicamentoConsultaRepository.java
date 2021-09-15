package com.unir.repository.relmedicamentoconsulta;

import com.unir.entity.relmedicamentoconsulta.RelMedicamentoConsultaEntity;

import java.util.List;
import java.util.Optional;

public interface RelMedicamentoConsultaRepository {

    int count();

    int save(RelMedicamentoConsultaEntity relMedicamentoConsulta);

    int update(RelMedicamentoConsultaEntity relMedicamentoConsulta);

    int deleteById(Long id);

    List<RelMedicamentoConsultaEntity> findAllByIdConsulta(long idConsulta);

    List<RelMedicamentoConsultaEntity> findAllByIdPaciente(long idPaciente);

}
