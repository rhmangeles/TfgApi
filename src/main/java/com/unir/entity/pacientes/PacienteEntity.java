package com.unir.entity.pacientes;

import java.io.Serializable;

/**
 * Entidad que representa los datos de un paciente.
 */
public class PacienteEntity implements Serializable {

    private long idPaciente;

    private String Nombre;

    private String Apellidos;

    private String FechaNacimiento;

    private String Sexo;

    private String Ciudad;

    private String Email;

    private String Password;

    public PacienteEntity() {
    }

    public PacienteEntity(long idPaciente, String nombre, String apellidos, String fechaNacimiento, String sexo, String ciudad,
                          String email, String password) {
        this.idPaciente = idPaciente;
        Nombre = nombre;
        Apellidos = apellidos;
        FechaNacimiento = fechaNacimiento;
        Sexo = sexo;
        Ciudad = ciudad;
        Email = email;
        Password = password;

    }

    public long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String ciudad) {
        Ciudad = ciudad;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
