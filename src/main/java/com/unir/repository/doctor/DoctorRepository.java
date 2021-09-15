package com.unir.repository.doctor;

import com.unir.entity.medicos.DoctorEntity;

import java.util.List;
import java.util.Optional;

/**
 * REpositorio de los doctores.
 */
public interface DoctorRepository {

    int count();

    int save(DoctorEntity book);

    int update(DoctorEntity book);

    int deleteById(Long id);

    List<DoctorEntity> findAll();



    List<DoctorEntity> findByEmailAndPassword(String email, String password);

    Optional<DoctorEntity> findById(Long id);

    String getNameById(Long id);

}
