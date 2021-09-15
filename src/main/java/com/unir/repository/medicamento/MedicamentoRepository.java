package com.unir.repository.medicamento;

import com.unir.entity.medicamentos.MedicamentoEntity;
import com.unir.entity.medicos.DoctorEntity;

import java.util.List;
import java.util.Optional;

public interface MedicamentoRepository {

    int count();

    int save(MedicamentoEntity medicamento);

    int update(MedicamentoEntity medicamento);

    int deleteById(Long id);

    List<MedicamentoEntity> findAll();

    List<MedicamentoEntity> findByPCOrName(String pc, String nombre);

    Optional<MedicamentoEntity> findById(Long id);

    List<MedicamentoEntity> findByIds(List<Long> ids);

    String getNameById(Long id);

}
