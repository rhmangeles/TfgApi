package com.unir.repository.consulta;

import com.unir.entity.consultas.ConsultaEntity;

import java.util.List;
import java.util.Optional;

public interface ConsultaRepository {

    int save(ConsultaEntity consulta);

    int update(ConsultaEntity consulta);

    int deleteById(Long id);

    List<ConsultaEntity> findAllByIdDoctor(long idDoctor);

    List<ConsultaEntity> findAllByIdPaciente(long idPaciente);

    Optional<ConsultaEntity> findLastConsultaByIdPaciente(long idPaciente);

}
