package com.unir.repository.relpacientemedico;

import com.unir.entity.relpacientemedico.RelPacienteMedicoEntity;

import java.util.List;

/**
 * Tabla intermedia que contiene la relacion entre el paciente y el medico.
 */
public interface RelPacienteMedicoRepository {

    /**
     * Devuelve la lista de medicos a los que un usuario está asociado.
     * @param idPaciente id del paciente.
     * @return
     */
    List<RelPacienteMedicoEntity> getIdMedicosFromIdPaciente(long idPaciente);


    /**
     * Devuelve todos los pacientes que tiene un medico asignado
     * @param idMedico id del medico.
     * @return
     */
    List<RelPacienteMedicoEntity> getIdPacientesFromIdMedico(long idMedico);

    int save(RelPacienteMedicoEntity relPacienteMedicoEntity);

}
