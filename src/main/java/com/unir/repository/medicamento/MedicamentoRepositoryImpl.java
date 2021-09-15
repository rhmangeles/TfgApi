package com.unir.repository.medicamento;

import com.unir.entity.medicamentos.MedicamentoEntity;
import com.unir.entity.medicos.DoctorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MedicamentoRepositoryImpl implements MedicamentoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate
                .queryForObject("select count(*) from medicamento", Integer.class);
    }

    @Override
    public int save(MedicamentoEntity medicamento) {
        String sql = "insert into medicamento (nombre, pc, consideraciones, toma , every) values(?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id_medicamento" });
            ps.setString(1, medicamento.getNombre());
            ps.setString(2, medicamento.getPC());
            ps.setString(3, medicamento.getConsideraciones());
            ps.setString(4, medicamento.getToma());
            ps.setString(5, medicamento.getEvery());
            return ps;
        }, keyHolder );

        return keyHolder.getKey().intValue();
    }

    @Override
    public int update(MedicamentoEntity medicamento) {
        return jdbcTemplate.update(
                "update medicamento set nombre = ? ," +
                        " set pc = ?, " +
                        " set consideraciones = ? " +
                        " set toma = ? " +
                        " set every = ? ",
                medicamento.getNombre(), medicamento.getPC(), medicamento.getConsideraciones(),
                medicamento.getToma(), medicamento.getEvery());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update(
                "delete medicamento where id_doctor = ?",
                id);
    }

    @Override
    public List<MedicamentoEntity> findAll() {
        return jdbcTemplate.query(
                "select * from medicamento",
                (rs, rowNum) ->
                        new MedicamentoEntity(
                                rs.getLong("id_medicamento"),
                                rs.getString("nombre"),
                                rs.getString("pc"),
                                rs.getString("consideraciones"),
                                rs.getString("toma"),
                                rs.getString("every")
                        )
        );
    }

    @Override
    public List<MedicamentoEntity> findByPCOrName(String pc, String nombre) {
        return jdbcTemplate.query(
                "select * from medicamento where pc like ? or pc = ?",
                new Object[]{"%" + pc + "%", nombre},
                (rs, rowNum) ->
                        new MedicamentoEntity(
                                rs.getLong("id_medicamento"),
                                rs.getString("nombre"),
                                rs.getString("pc"),
                                rs.getString("consideraciones"),
                                rs.getString("toma"),
                                rs.getString("every")
                        )
        );
    }

    @Override
    public Optional<MedicamentoEntity> findById(Long id) {
        return jdbcTemplate.queryForObject(
                "select * from medicamento where id_medicamento = ? ",
                new Object[]{id},
                (rs, rowNum) ->
                        Optional.of( new MedicamentoEntity(
                                rs.getLong("id_medicamento"),
                                rs.getString("nombre"),
                                rs.getString("pc"),
                                rs.getString("consideraciones"),
                                rs.getString("toma"),
                                rs.getString("every")
                        ))
        );
    }

    @Override
    public List<MedicamentoEntity> findByIds(List<Long> ids) {
        String parameters = String.join(",", Collections.nCopies(ids.size(), "?"));
        System.out.println("parameters: "+parameters);
        return jdbcTemplate.query(
                String.format("select * from medicamento where id_medicamento in (%s) ", parameters),
                ids.toArray(),
                (rs, rowNum) ->
                        new MedicamentoEntity(
                                rs.getLong("id_medicamento"),
                                rs.getString("nombre"),
                                rs.getString("pc"),
                                rs.getString("consideraciones"),
                                rs.getString("toma"),
                                rs.getString("every")
                        )
        );

    }

    @Override
    public String getNameById(Long id) {
        return null;
    }
}
