package com.uco.yourplus.serviceyourplus.domain;

import java.util.UUID;

public class ProductoDomain {
    UUID id;
    String nombre;
    int precio;
    String descripcion;
    String imagen;
    CategoriaDomain categoria;
    LaboratorioDomain laboratorio;

    public CategoriaDomain getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDomain categoria) {
        this.categoria = categoria;
    }

    public LaboratorioDomain getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(LaboratorioDomain laboratorio) {
        this.laboratorio = laboratorio;
    }

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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(final int precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(final String imagen) {
        this.imagen = imagen;
    }
}
