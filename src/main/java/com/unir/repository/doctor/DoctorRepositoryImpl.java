package com.unir.repository.doctor;

import com.unir.entity.medicos.DoctorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DoctorRepositoryImpl implements DoctorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * NOs devuelve el numero de doctores registrados en la APP.
     * @return
     */
    @Override
    public int count() {
        return jdbcTemplate
                .queryForObject("select count(*) from doctor", Integer.class);
    }

    @Override
    public int save(DoctorEntity doctor) {
        return jdbcTemplate.update(
                "insert into doctor (nombre, apellidos, email, password, telf) values(?,?,?,?,?,?)",
                doctor.getNombre(), doctor.getApellidos(), doctor.getEmail(), doctor.getPassword(), doctor.getTelf());
    }

    @Override
    public int update(DoctorEntity doctor) {
        return jdbcTemplate.update(
                "update doctor set nombre = ? ," +
                        " set apellido = ?, " +
                        " set email = ? , " +
                        " set password = ? , " +
                        " set telf = ? , " +
                        "where id_doctor = ?",
                doctor.getNombre(), doctor.getApellidos(), doctor.getEmail(), doctor.getPassword(),
                doctor.getTelf(), doctor.getIdDoctor());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update(
                "delete doctor where id_doctor = ?",
                id);
    }

    @Override
    public List<DoctorEntity> findAll() {
        return jdbcTemplate.query(
                "select * from doctor",
                (rs, rowNum) ->
                        new DoctorEntity(
                                rs.getLong("id_doctor"),
                                rs.getString("nombre"),
                                rs.getString("apellidos"),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("telf")
                        )
        );
    }

    @Override
    public List<DoctorEntity> findByEmailAndPassword(String email, String apellidos) {
        return jdbcTemplate.query(
                "select * from doctor where email like ? and password like ?",
                new Object[]{"%" + email + "%", "%" + apellidos + "%"},
                (rs, rowNum) ->
                        new DoctorEntity(
                                rs.getLong("id_doctor"),
                                rs.getString("nombre"),
                                rs.getString("apellidos"),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("telf")
                        )
        );
    }

    @Override
    public Optional<DoctorEntity> findById(Long idDoctor) {
        return jdbcTemplate.queryForObject(
                "select * from doctor where id_doctor = ?",
                new Object[]{idDoctor},
                (rs, rowNum) ->
                        Optional.of( new DoctorEntity(
                                rs.getLong("id_doctor"),
                                rs.getString("nombre"),
                                rs.getString("apellidos"),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("telf")
                        ))
        );
    }

    @Override
    public String getNameById(Long idDoctor) {
        return jdbcTemplate.queryForObject(
                "select name from doctor where id_doctor = ?",
                new Object[]{idDoctor},
                String.class);
    }
}
