package com.icodeap._proyectospring.model;

public class Autor {
    private Long id;
    private String nombre;
    private String apellido;
    private String phone;

    public Autor() {

    }

    public Autor(Long id, String nombre, String apellido, String phone) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
