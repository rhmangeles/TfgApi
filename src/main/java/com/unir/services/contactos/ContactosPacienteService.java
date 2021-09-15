package com.unir.services.contactos;

import com.unir.entity.personacontacto.PersonaContactoEntity;
import com.unir.entity.relcontactopaciente.RelContactoPacienteEntity;
import com.unir.repository.contacto.PersonaContactoRepository;
import com.unir.repository.relcontactopaciente.RelContactoPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio que gestiona las personas de contacto para un paciente.
 */
@Service
public class ContactosPacienteService {

    @Autowired
    private RelContactoPacienteRepository relContactoPacienteRepository;

    @Autowired
    private PersonaContactoRepository contactoRepository;

    public PersonaContactoEntity[] getPersonasDeContacto(long idPaciente) {
        List<Long> idContactos = relContactoPacienteRepository.findAllWherePacienteId(idPaciente).stream().map(x -> x.getIdContacto())
                .collect(Collectors.toList());
        return contactoRepository.findByIdPaciente(idContactos).stream().toArray(PersonaContactoEntity[]::new);
    }

}
