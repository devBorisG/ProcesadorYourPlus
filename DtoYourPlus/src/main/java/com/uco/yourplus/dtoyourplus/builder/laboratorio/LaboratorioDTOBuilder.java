package com.uco.yourplus.dtoyourplus.builder.laboratorio;

import com.uco.yourplus.dtoyourplus.LaboratorioDTO;

import java.util.UUID;

public class LaboratorioDTOBuilder implements LaboratorioBuilder {
    private UUID id;
    private String nombre;
    private String descripcion;

    private LaboratorioDTOBuilder() {
        super();
    }

    public static LaboratorioDTOBuilder getLaboratorioDTOBuilder() {
        return new LaboratorioDTOBuilder();
    }

    @Override
    public LaboratorioDTOBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    @Override
    public LaboratorioDTOBuilder setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    @Override
    public LaboratorioDTOBuilder setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public LaboratorioDTO build() {
        return LaboratorioDTO.create(id, nombre, descripcion);
    }
}
