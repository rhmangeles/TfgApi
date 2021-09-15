package com.unir.entity;

public class UserEntity {

    private String UserId;

    private String Nombre;

    private String Apellidos;

    private String Password;

    private String Email;

    public UserEntity() {}

    public String getPassword() {
        return Password;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getEmail() {
        return Email;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public String getUserId() {
        return UserId;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

}
