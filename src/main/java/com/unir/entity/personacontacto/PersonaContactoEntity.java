package com.unir.entity.personacontacto;

/**
 * Entidad para las personas de contacto de emergencia.
 */
public class PersonaContactoEntity {

    private long idContacto;

    private String Nombre;

    private String Apellidos;

    private String Telefono;

    private String Email;

    public PersonaContactoEntity(long idContacto, String nombre, String apellidos, String telefono, String email) {
        this.idContacto = idContacto;
        Nombre = nombre;
        Apellidos = apellidos;
        Telefono = telefono;
        Email = email;
    }

    public PersonaContactoEntity() {
    }

    public long getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(long idContacto) {
        this.idContacto = idContacto;
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

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

}
