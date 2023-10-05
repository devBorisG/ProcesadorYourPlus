package com.uco.yourplus.serviceyourplus.domain;

import java.util.UUID;

public class CategoriaDomain {

    UUID id;
    String nombre;
    String descripcion;

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }
}
