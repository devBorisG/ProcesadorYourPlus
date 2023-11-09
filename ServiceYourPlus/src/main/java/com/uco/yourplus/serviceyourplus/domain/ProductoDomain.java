package com.uco.yourplus.serviceyourplus.domain;

import java.util.UUID;

public class ProductoDomain {
    UUID id;
    String nombre;
    int precio;
    String descripcion;
    String imagen;
    CategoriaDomain categoriaDomain;
    LaboratorioDomain laboratorioDomain;


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

    public CategoriaDomain getCategoriaDomain() {
        return categoriaDomain;
    }

    public void setCategoriaDomain(CategoriaDomain categoriaDomain) {
        this.categoriaDomain = categoriaDomain;
    }

    public LaboratorioDomain getLaboratorioDomain() {
        return laboratorioDomain;
    }

    public void setLaboratorioDomain(LaboratorioDomain laboratorioDomain) {
        this.laboratorioDomain = laboratorioDomain;
    }

}
