package com.unir.repository.relcontactopaciente;

import com.unir.entity.relcontactopaciente.RelContactoPacienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RelContactoPacienteRepositoryImpl implements RelContactoPacienteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate
                .queryForObject("select count(*) from rel_contacto_paciente", Integer.class);
    }

    @Override
    public int save(RelContactoPacienteEntity rel) {
        return jdbcTemplate.update(
                "insert into rel_contacto_paciente (id_contacto, id_paciente) values(?,?)",
                rel.getIdContacto(), rel.getIdPaciente());
    }

    @Override
    public int update(RelContactoPacienteEntity rel) {
        return jdbcTemplate.update(
                "update rel_contacto_paciente set id_contacto = ? ," +
                        " set id_paciente = ?, " +
                        "where id_rel_contacto_paciente = ?",
                rel.getIdContacto(), rel.getIdPaciente(), rel.getIdRelContactoPaciente());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update(
                "delete rel_contacto_paciente where id_rel_contacto_paciente = ?",
                id);
    }

    @Override
    public List<RelContactoPacienteEntity> findAllWherePacienteId(long idPaciente) {
        return jdbcTemplate.query(
                "select * from rel_contacto_paciente where id_paciente = ?",
                new Object[]{idPaciente},
                (rs, rowNum) ->
                        new RelContactoPacienteEntity(
                                rs.getLong("id_rel_contacto_paciente"),
                                rs.getLong("id_paciente"),
                                rs.getLong("id_contacto")
                        )
        );
    }
}
