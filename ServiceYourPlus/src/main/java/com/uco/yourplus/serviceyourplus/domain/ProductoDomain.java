package com.uco.yourplus.serviceyourplus.domain;

import com.uco.yourplus.dtoyourplus.CategoriaDTO;
import com.uco.yourplus.dtoyourplus.LaboratorioDTO;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ProductoDomain {

    UUID id;
    String nombre;
    int precio;
    String descripcion;
    String imagen;
    CategoriaDTO categoria;
    LaboratorioDTO laboratorio;

    public void setId(final UUID id) {
        this.id = id;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(final int precio) {
        this.precio = precio;
    }

    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }

    public void setImagen(final String imagen) {
        this.imagen = imagen;
    }

    public void setCategoria(final CategoriaDTO categoria) {
        this.categoria = categoria;
    }

    public void setLaboratorio(final LaboratorioDTO laboratorio) {
        this.laboratorio = laboratorio;
    }
}
