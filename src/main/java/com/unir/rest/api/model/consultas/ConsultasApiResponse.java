package com.unir.rest.api.model.consultas;

import com.unir.dtos.consulta.ConsultaDto;
import com.unir.entity.consultas.ConsultaEntity;
import com.unir.services.consultas.ConsultasService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Dto que devuelve la lista de consultas desde el servidor.
 */
public class ConsultasApiResponse implements Serializable {

    private List<ConsultaDto> consultas = new ArrayList<ConsultaDto>();

    public ConsultasApiResponse() {
    }

    public List<ConsultaDto> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<ConsultaDto> consultas) {
        this.consultas = consultas;
    }
}
