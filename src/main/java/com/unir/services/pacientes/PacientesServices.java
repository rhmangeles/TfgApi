package com.unir.services.pacientes;

import com.unir.entity.medicos.DoctorEntity;
import com.unir.entity.pacientes.PacienteEntity;
import com.unir.entity.relpacientemedico.RelPacienteMedicoEntity;
import com.unir.repository.paciente.PacienteRepository;
import com.unir.repository.relpacientemedico.RelPacienteMedicoRepository;
import com.unir.rest.api.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacientesServices {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private RelPacienteMedicoRepository relPacienteMedicoRepository;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    /**
     * Recuperamos todos los
     * @return
     */
    public List<PacienteEntity> getAllPacientesForDoctorFrom(DoctorEntity doctor) {
        return pacienteRepository.findAllWhereDoctorId(doctor.getIdDoctor());
    }

    public PacienteEntity getPacienteById(long idPaciente) {
        return pacienteRepository.findById(idPaciente).get();
    }

    /**
     * Metodo empleado para hacer login de un paciente empleando el email.
     * @param email Email del paciente a logar
     * @return PacienteEntity
     */
    public PacienteEntity getPacienteByEmail(String email) {
        //Si no hay usuario seteamos null.
        return pacienteRepository.findByEmail(email).orElse(null);
    }

    public int saveNewPaciente(PacienteEntity paciente) {
        return pacienteRepository.save(paciente);
    }

    public int newRelPacienteMedido(RelPacienteMedicoEntity relPacienteMedicoEntity) {
        return relPacienteMedicoRepository.save(relPacienteMedicoEntity);
    }
}
