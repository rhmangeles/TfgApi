package com.unir.repository.relmedicamentoconsulta;

import com.unir.entity.pacientes.PacienteEntity;
import com.unir.entity.relmedicamentoconsulta.RelMedicamentoConsultaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

/**
 * REpositorio que contiene las consultas a la BBDD para recuperar los medicamentos preescritos en una consulta.
 */

@Repository
public class RelMedicamentoConsultaRepositoryImpl implements RelMedicamentoConsultaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate
                .queryForObject("select count(*) from rel_medicamento_consulta", Integer.class);
    }

    @Override
    public int save(RelMedicamentoConsultaEntity relMedicamentoConsulta) {
        String sql = "insert into rel_medicamento_consulta (id_consulta, id_medicamento) values(?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id_rel_medicamento_consulta" });
            ps.setLong(1, relMedicamentoConsulta.getIdConsulta());
            ps.setLong(2, relMedicamentoConsulta.getIdMedicamento());
            return ps;
        }, keyHolder );

        return keyHolder.getKey().intValue();
    }

    @Override
    public int update(RelMedicamentoConsultaEntity relMedicamentoConsulta) {
        return jdbcTemplate.update(
                "update rel_medicamento_consulta set id_consulta = ? ," +
                        " set id_medicamento = ?, " +
                        "where id_paciente = ?",
                relMedicamentoConsulta.getIdConsulta(), relMedicamentoConsulta.getIdMedicamento(),
                relMedicamentoConsulta.getIdRelMedicamentoConsulta());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update(
                "delete rel_medicamento_consulta where id_rel_medicamento_consulta = ?",
                id);
    }

    @Override
    public List<RelMedicamentoConsultaEntity> findAllByIdConsulta(long idConsulta) {
        return jdbcTemplate.query(
                "select * from rel_medicamento_consulta where id_consulta = ?",
                new Object[]{idConsulta},
                (rs, rowNum) ->
                        new RelMedicamentoConsultaEntity(
                                rs.getLong("id_rel_medicamento_consulta"),
                                rs.getLong("id_consulta"),
                                rs.getLong("id_medicamento")
                        )
        );
    }

    public List<RelMedicamentoConsultaEntity> findAllByIdPaciente(long idPaciente) {
        return jdbcTemplate.query(
                "select " +
                        " rmc.* "+
                        "from rel_medicamento_consulta rmc " +
                        "inner join consulta c on c.id_consulta = rmc.id_consulta " +
                        "where c.id_paciente = ? " +
                        "order by c.id_consulta DESC",
                new Object[]{idPaciente},
                (rs, rowNum) ->
                        new RelMedicamentoConsultaEntity(
                                rs.getLong("id_rel_medicamento_consulta"),
                                rs.getLong("id_consulta"),
                                rs.getLong("id_medicamento")
                        )
        );
    }

}
