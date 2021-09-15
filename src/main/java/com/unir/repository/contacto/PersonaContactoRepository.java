package com.unir.repository.contacto;

import com.unir.entity.medicos.DoctorEntity;
import com.unir.entity.personacontacto.PersonaContactoEntity;

import java.util.List;
import java.util.Optional;

/**
 * REpositorio de los doctores.
 */
public interface PersonaContactoRepository {

    int count();

    int save(PersonaContactoEntity contacto);

    int update(PersonaContactoEntity contacto);

    int deleteById(Long id);

    List<PersonaContactoEntity> findAll();

    List<PersonaContactoEntity> findByIdPaciente(List<Long> idPaciente);

    Optional<PersonaContactoEntity> findById(Long id);

    String getNameById(Long id);

}
