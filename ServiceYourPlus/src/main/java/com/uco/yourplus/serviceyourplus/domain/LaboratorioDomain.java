package com.uco.yourplus.serviceyourplus.domain;

import java.util.UUID;

public class LaboratorioDomain {

    private UUID id;
    private String nombre;
    private String descripcion;

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
