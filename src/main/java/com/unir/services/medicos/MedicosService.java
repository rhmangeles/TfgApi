package com.unir.services.medicos;

import com.unir.entity.medicos.DoctorEntity;
import com.unir.repository.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicosService {

    @Autowired
    private DoctorRepository doctorRepository;

    /**
     * Devolvemos todos los medicos que están dandos de alta en la aplicacion.
     * @return
     */
    public List<DoctorEntity> getAllMedicos() {
        return doctorRepository.findAll();
    }

    /**
     * Verifica si el login es correcto.
     * @param email
     * @param password
     * @return
     */
    public DoctorEntity verifyLogin(String email, String password) {
        DoctorEntity ret = null;
        List<DoctorEntity> login = doctorRepository.findByEmailAndPassword(email, password);
        if (login.size() > 0) ret = login.get(0);
        return ret;
    }

    /**
     * Devuelve una entidad de Medico/Doctor en funcion del id que se pasa por argumentos.
     * @param idDoctor
     * @return
     */
    public DoctorEntity findDoctorById(long idDoctor) {
        return doctorRepository.findById(idDoctor).orElseGet(null);
    }
}
