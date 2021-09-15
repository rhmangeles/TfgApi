package com.unir.entity.medicamentos;

import java.io.Serializable;

/**
 * Entidad que representa un medicamento.
 *
 */
public class MedicamentoEntity implements Serializable {

    private long idMedicamento;

    private String Nombre;

    private String PC; //Identificador del medicamento.

    private String Consideraciones;

    private String toma; //Intervalo de cuando empieza y cada cuanto.

    private String every; // INdica cada cuanto tiempo nos salta la notificacion.

    public MedicamentoEntity() {
    }

    public MedicamentoEntity(long idMedicamento, String nombre, String PC, String consideraciones, String toma, String every) {
        this.idMedicamento = idMedicamento;
        this.Nombre = nombre;
        this.PC = PC;
        this.Consideraciones = consideraciones;
        this.toma = toma;
        this.every = every;
    }

    public long getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(long idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPC() {
        return PC;
    }

    public void setPC(String PC) {
        this.PC = PC;
    }

    public String getConsideraciones() {
        return Consideraciones;
    }

    public void setConsideraciones(String consideraciones) {
        Consideraciones = consideraciones;
    }

    public String getToma() {
        return toma;
    }

    public void setToma(String toma) {
        this.toma = toma;
    }

    public String getEvery() {
        return every;
    }

    public void setEvery(String every) {
        this.every = every;
    }
}
