package com.unir.services.consultas;

import com.unir.dtos.consulta.ConsultaDto;
import com.unir.entity.consultas.ConsultaEntity;
import com.unir.repository.consulta.ConsultaRepository;
import com.unir.services.medicos.MedicosService;
import com.unir.services.pacientes.PacientesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para la logica de las consultas.
 */

@Service
public class ConsultasService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacientesServices pacientesServices;

    @Autowired
    private MedicosService medicosService;

    /**
     * Devuelve todas las consultas que ha tenido un determinado paciente.
     * @param idPaciente
     * @return
     */
    public List<ConsultaDto> getAllConsultasByIdPaciente(long idPaciente) {
        List<ConsultaEntity> consultas = consultaRepository.findAllByIdPaciente(idPaciente);
        List<ConsultaDto> consultasDto = new ArrayList<ConsultaDto>();

        //TODO: Devolvemos las consultas con sus objetos resueltos.
        ConsultaDto c = null;
        for (ConsultaEntity consulta : consultas) {
            c = new ConsultaDto();
            c.setPaciente(pacientesServices.getPacienteById(consulta.getIdPaciente())); //Nos traemos el paciente.
            c.setFechaConsulta(consulta.getFechaDeConsulta()); // INcluimos la fecha de consulta
            c.setMedico(medicosService.findDoctorById(consulta.getIdDoctor())); // Incluimos el medico
            consultasDto.add(c);
        }
        return consultasDto;
    }

    /**
     * Devuelve la ultima consulta de un paciente.
     * @param idPaciente
     * @return
     */
    public ConsultaEntity getLastConsultaByIdPaciente(long idPaciente) {
        return consultaRepository.findLastConsultaByIdPaciente(idPaciente)
                .orElseGet(null);
    }

    /**
     * Guarda nueva consulta.
     * @param consulta
     * @return
     */
    public int saveNuevaConsulta(ConsultaEntity consulta) {
        return consultaRepository.save(consulta);
    }

}
