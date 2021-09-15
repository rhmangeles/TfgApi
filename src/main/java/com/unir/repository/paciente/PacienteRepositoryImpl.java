package com.unir.repository.paciente;

import com.unir.entity.pacientes.PacienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PacienteRepositoryImpl implements PacienteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * NOs devuelve el numero de doctores registrados en la APP.
     * @return
     */
    @Override
    public int count() {
        return jdbcTemplate
                .queryForObject("select count(*) from paciente", Integer.class);
    }

    @Override
    public int save(PacienteEntity paciente) {
        String sql = "insert into paciente (nombre, apellidos, fecha_nacimiento, sexo, ciudad, email, password) values(?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id_rel_paciente_medico" });
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellidos());
            ps.setString(3, paciente.getFechaNacimiento());
            ps.setString(4, paciente.getSexo());
            ps.setString(5, paciente.getCiudad());
            ps.setString(6, paciente.getEmail());
            ps.setString(7, paciente.getPassword());

            return ps;
        }, keyHolder );

        return keyHolder.getKey().intValue();
    }


    @Override
    public int update(PacienteEntity paciente) {
        return jdbcTemplate.update(
                "update paciente set nombre = ? ," +
                        " set apellido = ?, " +
                        " set fecha_nacimiento = ? , " +
                        " set sexo = ? , " +
                        " set ciudad = ? , " +
                        "where id_paciente = ?",
                paciente.getNombre(), paciente.getApellidos(), paciente.getFechaNacimiento(),
                paciente.getSexo(), paciente.getCiudad());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update(
                "delete paciente where id_paciente = ?",
                id);
    }

    @Override
    public List<PacienteEntity> findAll() {
        return jdbcTemplate.query(
                "select * from paciente",
                (rs, rowNum) ->
                        new PacienteEntity(
                                rs.getLong("id_paciente"),
                                rs.getString("nombre"),
                                rs.getString("apellidos"),
                                rs.getString("fecha_nacimiento"),
                                rs.getString("sexo"),
                                rs.getString("ciudad"),
                                rs.getString("email"),
                                rs.getString("password")
                        )
        );
    }

    @Override
    public List<PacienteEntity> findByNombreAndApellidos(String email, String apellidos) {
        return jdbcTemplate.query(
                "select * from paciente where email like ? and password like ?",
                new Object[]{"%" + email + "%", "%" + apellidos + "%"},
                (rs, rowNum) ->
                        new PacienteEntity(
                                rs.getLong("id_paciente"),
                                rs.getString("nombre"),
                                rs.getString("apellidos"),
                                rs.getString("fecha_nacimiento"),
                                rs.getString("sexo"),
                                rs.getString("ciudad"),
                                rs.getString("email"),
                                rs.getString("password")
                        )
        );
    }

    @Override
    public Optional<PacienteEntity> findById(Long idPaciente) {
        return jdbcTemplate.queryForObject(
                "select * from paciente where id_paciente = ?",
                new Object[]{idPaciente},
                (rs, rowNum) ->
                        Optional.of( new PacienteEntity(
                                rs.getLong("id_paciente"),
                                rs.getString("nombre"),
                                rs.getString("apellidos"),
                                rs.getString("fecha_nacimiento"),
                                rs.getString("sexo"),
                                rs.getString("ciudad"),
                                rs.getString("email"),
                                rs.getString("password")
                        ))
        );
    }

    @Override
    public Optional<PacienteEntity> findByEmail(String email) {
        return jdbcTemplate.queryForObject(
                "select * from paciente where email = ?",
                new Object[]{email},
                (rs, rowNum) ->
                        Optional.of( new PacienteEntity(
                                rs.getLong("id_paciente"),
                                rs.getString("nombre"),
                                rs.getString("apellidos"),
                                rs.getString("fecha_nacimiento"),
                                rs.getString("sexo"),
                                rs.getString("ciudad"),
                                rs.getString("email"),
                                rs.getString("password")
                        ))
        );
    }

    @Override
    public String getNameById(Long idPaciente) {
        return jdbcTemplate.queryForObject(
                "select name from paciente where id_paciente = ?",
                new Object[]{idPaciente},
                String.class);
    }

    @Override
    public List<PacienteEntity> findAllWhereDoctorId(long doctorId) {
        //TODO: Tenemos que ver como almacenar las queries fuera del codigo.
        String query = "SELECT p.* " +
                "FROM servidormedicamentos.paciente p " +
                "INNER JOIN servidormedicamentos.rel_paciente_medico rpm " +
                "ON p.id_paciente = rpm.id_paciente " +
                "where rpm.id_medico = ? ";

        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(query, doctorId);

        List<PacienteEntity> ret = mapList.stream().map( d -> {
            PacienteEntity pe = new PacienteEntity();
            pe.setIdPaciente(Long.parseLong(String.valueOf(d.get("id_paciente"))));
            pe.setNombre(String.valueOf(d.get("nombre")));
            pe.setApellidos(String.valueOf(d.get("apellidos")));
            pe.setSexo(String.valueOf(d.get("sexo")));
            pe.setFechaNacimiento(String.valueOf(d.get("fecha_nacimiento")));
            pe.setCiudad(String.valueOf(d.get("ciudad")));
            pe.setEmail(String.valueOf(d.get("email")));
            pe.setPassword(String.valueOf(d.get("password")));
            return pe;
        }).collect(Collectors.toList());

        return ret;
    }
}
