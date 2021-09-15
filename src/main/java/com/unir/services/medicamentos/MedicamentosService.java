package com.unir.services.medicamentos;

import com.unir.entity.medicamentos.MedicamentoEntity;
import com.unir.entity.relmedicamentoconsulta.RelMedicamentoConsultaEntity;
import com.unir.repository.medicamento.MedicamentoRepository;
import com.unir.repository.relmedicamentoconsulta.RelMedicamentoConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicamentosService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private RelMedicamentoConsultaRepository relMedicamentoConsultaRepository;

    /**
     * Devuelve todos los medicamentos que estan registrado en la APP.
     * @return
     */
    public List<MedicamentoEntity> getAllMedicamentos() {
        return medicamentoRepository.findAll();
    }

    /**
     * Devuelve una lista de medicamentos asociados a una consulta.
     * @return
     */
    public List<MedicamentoEntity> getAllMedicamentosByIdConsulta(long idConsulta) {
        List<RelMedicamentoConsultaEntity> relaciones = relMedicamentoConsultaRepository.findAllByIdConsulta(idConsulta);
        return medicamentoRepository.findByIds(relaciones.stream()
                .map(x -> Long.valueOf(x.getIdMedicamento())).collect(Collectors.toList()));
    }

    /**
     * Metodo que devuelve la lista de meticamentos activos para un determinado paciente.
     * @param idPaciente
     * @return
     */
    public List<MedicamentoEntity> getAllMedicamentosActivosByIdPaciente(long idPaciente) {
        List<RelMedicamentoConsultaEntity> relMedicamentosConsultas = relMedicamentoConsultaRepository
                .findAllByIdPaciente(idPaciente);

        //Encontramos la primera consulta
        long idFirstConsulta = relMedicamentosConsultas.get(0).getIdConsulta();

        // Medicamentos filtrados
        List<RelMedicamentoConsultaEntity> medicamentos = relMedicamentosConsultas.stream()
                .filter(x -> x.getIdConsulta() == idFirstConsulta).collect(Collectors.toList());

        //Hacemos la consulta por los id's correspondientes.
        return medicamentoRepository.findByIds(medicamentos.stream()
                .map(x -> x.getIdMedicamento()).collect(Collectors.toList()));
    }

    /**
     * Devuelve un medicamento.
     * @param idMedicamento
     * @return
     */
    public MedicamentoEntity getMedicamentoPorId(long idMedicamento) {
        return medicamentoRepository.findByIds(List.of(idMedicamento)).get(0);
    }

    /**
     * GUarda medicamento nuevo en BBDD.
     * @param medicamento
     * @return
     */
    public int guardaMedicamentoNuevo(MedicamentoEntity medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    /**
     * GUarda una relacion entre medicamento y cnsulta.
     * @param relacion
     * @return
     */
    public int guardaNuevaRelacionMedicamentoConsulta(RelMedicamentoConsultaEntity relacion) {
        return relMedicamentoConsultaRepository.save(relacion);
    }
}
