package com.uco.yourplus.dtoyourplus.builder.categoria;

import java.util.UUID;

public interface CategoriaBuilder {
    CategoriaDTOBuilder setId(UUID id);
    CategoriaDTOBuilder setNombre(String nombre);
    CategoriaDTOBuilder setDescripcion(String descripcion);
}
