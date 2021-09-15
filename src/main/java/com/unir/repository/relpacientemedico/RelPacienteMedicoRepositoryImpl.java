package com.unir.repository.relpacientemedico;

import com.unir.entity.pacientes.PacienteEntity;
import com.unir.entity.relpacientemedico.RelPacienteMedicoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la tabla intermedia entre pacientes y medicos relacionados.
 */

@Repository
public class RelPacienteMedicoRepositoryImpl implements RelPacienteMedicoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<RelPacienteMedicoEntity> getIdMedicosFromIdPaciente(long idPaciente) {
        return jdbcTemplate.query(
                "select * from rel_paicente_medico where id_paciente = ?",
                new Object[]{idPaciente},
                (rs, rowNum) ->
                        new RelPacienteMedicoEntity(
                                rs.getLong("id_rel_paciente_medico"),
                                rs.getLong("id_medico"),
                                rs.getLong("id_paciente")
                        )
        );
    }

    @Override
    public List<RelPacienteMedicoEntity> getIdPacientesFromIdMedico(long idMedico) {
        return jdbcTemplate.query(
                "select * from rel_paciente_medico where id_medico = ?",
                new Object[]{idMedico},
                (rs, rowNum) ->
                        new RelPacienteMedicoEntity(
                                rs.getLong("id_rel_paciente_medico"),
                                rs.getLong("id_medico"),
                                rs.getLong("id_paciente")
                        )
        );
    }

    @Override
    public int save(RelPacienteMedicoEntity relPacienteMedicoEntity) {
        return jdbcTemplate.update(
                "insert into rel_paciente_medico (id_medico, id_paciente) values(?,?)",
                relPacienteMedicoEntity.getIdMedico(), relPacienteMedicoEntity.getIdPaciente());
    }
}
