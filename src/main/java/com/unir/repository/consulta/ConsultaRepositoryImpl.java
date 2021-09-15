package com.unir.repository.consulta;

import com.unir.entity.consultas.ConsultaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class ConsultaRepositoryImpl implements ConsultaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(ConsultaEntity consulta) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update( connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("insert into consulta (fecha_consulta, id_medico, id_paciente) values(?,?,?)", new String[] {"id_consulta"});
            ps.setString(1, consulta.getFechaDeConsulta());
            ps.setLong(2, consulta.getIdDoctor());
            ps.setLong(3, consulta.getIdPaciente());
            return ps;

        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public int update(ConsultaEntity consulta) {
        return jdbcTemplate.update(
                "update consulta set fecha_consulta = ? ," +
                        " set id_medico = ?, " +
                        " set id_paciente = ? , " +
                        " where id_consulta = ?",
                consulta.getFechaDeConsulta(), consulta.getIdDoctor(), consulta.getIdPaciente(),
                consulta.getIdConsulta());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update(
                "delete consulta where id_consulta = ?",
                id);
    }

    @Override
    public List<ConsultaEntity> findAllByIdDoctor(long idDoctor) {
        return jdbcTemplate.query(
                "select * from consulta where id_medico = ?",
                new Object[]{idDoctor},
                (rs, rowNum) ->
                        new ConsultaEntity(
                                rs.getLong("id_consulta"),
                                rs.getString("fecha_consulta"),
                                rs.getLong("id_medico"),
                                rs.getLong("id_paciente")
                        )
        );
    }

    @Override
    public List<ConsultaEntity> findAllByIdPaciente(long idPaciente) {
        return jdbcTemplate.query(
                "select * from consulta where id_paciente = ?",
                new Object[]{idPaciente},
                (rs, rowNum) ->
                        new ConsultaEntity(
                                rs.getLong("id_consulta"),
                                rs.getString("fecha_consulta"),
                                rs.getLong("id_medico"),
                                rs.getLong("id_paciente")
                        )
        );
    }

    @Override
    public Optional<ConsultaEntity> findLastConsultaByIdPaciente(long idPaciente) {
        return jdbcTemplate.queryForObject(
                "select * from consulta where id_paciente = ? order by fecha_consulta DESC limit 1",
                new Object[]{idPaciente},
                (rs, rowNum) ->
                        Optional.of( new ConsultaEntity(
                            rs.getLong("id_consulta"),
                            rs.getString("fecha_consulta"),
                            rs.getLong("id_medico"),
                            rs.getLong("id_paciente")
                    ))
        );
    }

}
