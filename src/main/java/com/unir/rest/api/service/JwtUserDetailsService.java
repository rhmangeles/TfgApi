package com.unir.rest.api.service;


import com.unir.entity.pacientes.PacienteEntity;
import com.unir.services.pacientes.PacientesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Servicio que da soporte a la generacion del token y login para el paciente.
 */

@Service
public class JwtUserDetailsService implements UserDetailsService {

    // Importamos el servicio de Pacientes
    @Autowired
    private PacientesServices pacientesServices;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Recuperamos el usuario de la BBDD.
        //NOTA: Aunque se use email en vez de username , es que este es por standar de Spring Security.
        PacienteEntity paciente = pacientesServices.getPacienteByEmail(username);

        if (paciente != null) {
            return new User(paciente.getEmail(), paciente.getPassword(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("Paciente no encontrado: " + username);
        }
    }

}
