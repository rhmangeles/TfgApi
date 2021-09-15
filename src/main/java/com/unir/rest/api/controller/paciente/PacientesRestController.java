package com.unir.rest.api.controller.paciente;

import com.unir.dtos.notificaciontoma.NotificacionTomaDto;
import com.unir.entity.personacontacto.PersonaContactoEntity;
import com.unir.repository.relcontactopaciente.RelContactoPacienteRepository;
import com.unir.rest.api.ApiBaseRestController;
import com.unir.rest.api.model.JwtPacienteLoginApiResponse;
import com.unir.rest.api.model.PacienteLoginApiRequest;
import com.unir.rest.api.model.consultas.ConsultasApiRequest;
import com.unir.rest.api.model.consultas.ConsultasApiResponse;
import com.unir.rest.api.model.medicamentos.MedicamentosApiRequest;
import com.unir.rest.api.model.medicamentos.MedicamentosApiResponse;
import com.unir.rest.api.service.JwtUserDetailsService;
import com.unir.rest.api.utils.JwtTokenUtil;
import com.unir.services.consultas.ConsultasService;
import com.unir.services.contactos.ContactosPacienteService;
import com.unir.services.medicamentos.MedicamentosService;
import com.unir.services.medicos.MedicosService;
import com.unir.services.pacientes.PacientesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Api Rest paciente.
 */

@RestController
public class PacientesRestController extends ApiBaseRestController {

    @Autowired
    private ConsultasService consultasService;

    @Autowired
    private MedicamentosService medicamentosService;

    @Autowired
    private MedicosService medicosService;

    @Autowired
    private PacientesServices pacientesServices;

    @Autowired
    private ContactosPacienteService contactosPacienteService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JavaMailSender javaMail;

    /**
     * SI las credenciales son correctas se devuelve un token con el que se pueden continuar haciendo peticiones.
     * @return ResponseEntity<JwtPacienteLoginApiResponse>
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = {"/api/v1/login"}, method = RequestMethod.POST)
    public ResponseEntity<JwtPacienteLoginApiResponse> loginPaciente(@RequestBody PacienteLoginApiRequest pacienteLoginRequest) {

        //Autenticamos al paciente con la BBDD.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(pacienteLoginRequest.getEmail(), pacienteLoginRequest.getPassword())
        );

        final UserDetails userDetail = jwtUserDetailsService.loadUserByUsername(pacienteLoginRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetail);

        return ResponseEntity.ok(new JwtPacienteLoginApiResponse(token));
    }

    /**
     * Devuelve la lista de consultas historicas que tiene el paciente con las notas.
     * @param request
     * @return
     */
    @RequestMapping(value = {"/api/v1/consultas"}, method = RequestMethod.GET)
    public ResponseEntity<ConsultasApiResponse> getConsultas(@RequestBody ConsultasApiRequest request) {
        ConsultasApiResponse ret = new ConsultasApiResponse();
        ret.setConsultas(consultasService
                .getAllConsultasByIdPaciente(Long.valueOf(request.getIdPaciente())));
        return ResponseEntity.ok(ret);
    }

    /**
     * Devuelve la lista de medicamentos que esta tomando actualmente  el paciente.
     * @param request
     * @return
     */
    @RequestMapping(value = "/api/v1/medicamentos", method = RequestMethod.POST)
    public ResponseEntity<MedicamentosApiResponse> getMedicamentosActivos(@RequestBody MedicamentosApiRequest request) {
        MedicamentosApiResponse ret = new MedicamentosApiResponse();
        ret.setMedicamentos(medicamentosService
                .getAllMedicamentosActivosByIdPaciente(Long.parseLong(request.getIdPaciente())));
        return ResponseEntity.ok(ret);
    }

    /**
     * Envia una notificación a los emails provistos para indicar que se pulso en la app la notificacion.
     * @return ResponseEntity
     */
    @RequestMapping(value = "/api/v1/controltoma", method = RequestMethod.POST)
    public ResponseEntity<Boolean> checkeoDeToma(@RequestBody NotificacionTomaDto notificacion) {

        Boolean ret = Boolean.TRUE;

        MimeMessage message = javaMail.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        PersonaContactoEntity[] contactos = contactosPacienteService.getPersonasDeContacto(notificacion.getIdPaciente());
        List<String> emails = new ArrayList<>();
        Arrays.stream(contactos).collect(Collectors.toList()).forEach(c -> {
            try {
                emails.add(c.getEmail());
                helper.setTo(c.getEmail()); // Array con los emails a los que se le tiene que enviar la notificacion.
                helper.setText("<h1>Medicación tomada</h1> <br/>", true); // Texto del mensaje
                helper.setSubject("Notificacion de medicacion"); // Asunto del mensaje.

                //Se envia el mensaje
                javaMail.send(message); //Mensaje

            } catch (MessagingException e) {
                // EN caso de error marcamos como false.
                System.out.println("Error enviando email.");
                e.printStackTrace();
            }
        });

        return ResponseEntity.ok(ret);

    }

}
