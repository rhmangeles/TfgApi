package com.unir.repository.paciente;

import com.unir.entity.medicos.DoctorEntity;
import com.unir.entity.pacientes.PacienteEntity;

import java.util.List;
import java.util.Optional;

/**
 * REpositorio de los doctores.
 */
public interface PacienteRepository {

    int count();

    int save(PacienteEntity book);

    int update(PacienteEntity book);

    int deleteById(Long id);

    List<PacienteEntity> findAll();

    List<PacienteEntity> findAllWhereDoctorId(long doctorId);

    List<PacienteEntity> findByNombreAndApellidos(String email, String password);

    Optional<PacienteEntity> findById(Long id);

    Optional<PacienteEntity> findByEmail(String email);

    String getNameById(Long id);

}
