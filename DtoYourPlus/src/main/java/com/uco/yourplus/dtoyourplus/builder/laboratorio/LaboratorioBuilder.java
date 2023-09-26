package com.uco.yourplus.dtoyourplus.builder.laboratorio;

import java.util.UUID;

public interface LaboratorioBuilder {
    LaboratorioDTOBuilder setId(UUID id);

    LaboratorioDTOBuilder setNombre(String nombre);

    LaboratorioDTOBuilder setDescripcion(String descripcion);
}