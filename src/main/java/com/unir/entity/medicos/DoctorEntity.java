package com.unir.entity.medicos;

import java.io.Serializable;

/**
 * Entidad para la BBDD que contiene los doctores.
 */
public class DoctorEntity implements Serializable {

    private long IdDoctor;

    private String Nombre;

    private String Apellidos;

    private String Email;

    private String Password;

    private String Telf;

    public DoctorEntity() {}

    public DoctorEntity(long idDoctor, String nombre, String apellidos, String email, String password, String telf) {
        IdDoctor = idDoctor;
        Nombre = nombre;
        Apellidos = apellidos;
        Email = email;
        Password = password;
        Telf = telf;
    }

    public void setIdDoctor(long idDoctor) {
        IdDoctor = idDoctor;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setTelf(String telf) {
        Telf = telf;
    }

    public long getIdDoctor() {
        return IdDoctor;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getTelf() {
        return Telf;
    }
}
