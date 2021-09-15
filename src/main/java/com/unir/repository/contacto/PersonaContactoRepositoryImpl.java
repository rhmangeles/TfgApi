package com.unir.repository.contacto;

import com.unir.entity.medicamentos.MedicamentoEntity;
import com.unir.entity.medicos.DoctorEntity;
import com.unir.entity.personacontacto.PersonaContactoEntity;
import com.unir.repository.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonaContactoRepositoryImpl implements PersonaContactoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * NOs devuelve el numero de doctores registrados en la APP.
     * @return
     */
    @Override
    public int count() {
        return jdbcTemplate
                .queryForObject("select count(*) from contacto", Integer.class);
    }

    @Override
    public int save(PersonaContactoEntity contacto) {
        return jdbcTemplate.update(
                "insert into contacto (nombre, apellidos, telefono, email) values(?,?,?,?)",
                contacto.getNombre(), contacto.getApellidos(), contacto.getTelefono(), contacto.getEmail());
    }

    @Override
    public int update(PersonaContactoEntity contacto) {
        return jdbcTemplate.update(
                "update contacto set nombre = ? ," +
                        " set apellido = ?, " +
                        " set telefono = ? , " +
                        " set email = ? , " +
                        "where id_contacto = ?",
                contacto.getNombre(), contacto.getApellidos(), contacto.getTelefono(), contacto.getEmail());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update(
                "delete contacto where id_contacto = ?",
                id);
    }

    @Override
    public List<PersonaContactoEntity> findAll() {
        return jdbcTemplate.query(
                "select * from contacto",
                (rs, rowNum) ->
                        new PersonaContactoEntity(
                                rs.getLong("id_contacto"),
                                rs.getString("nombre"),
                                rs.getString("apellidos"),
                                rs.getString("telefono"),
                                rs.getString("email")
                        )
        );
    }

    @Override
    public List<PersonaContactoEntity> findByIdPaciente(List<Long> ids) {
        String parameters = String.join(",", Collections.nCopies(ids.size(), "?"));
        return jdbcTemplate.query(
                String.format("select * from contacto where id_contacto in (%s) ", parameters),
                ids.toArray(),
                (rs, rowNum) ->
                        new PersonaContactoEntity(
                                rs.getLong("id_contacto"),
                                rs.getString("nombre"),
                                rs.getString("apellidos"),
                                rs.getString("telefono"),
                                rs.getString("email")
                        )
        );
    }



    @Override
    public Optional<PersonaContactoEntity> findById(Long idContacto) {
        return jdbcTemplate.queryForObject(
                "select * from contacto where id_contacto = ? ",
                new Object[]{idContacto},
                (rs, rowNum) ->
                        Optional.of( new PersonaContactoEntity(
                                rs.getLong("id_contacto"),
                                rs.getString("nombre"),
                                rs.getString("apellidos"),
                                rs.getString("telefono"),
                                rs.getString("email")
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
