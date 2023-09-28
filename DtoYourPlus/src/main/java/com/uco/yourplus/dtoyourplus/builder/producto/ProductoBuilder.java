package com.uco.yourplus.dtoyourplus.builder.producto;

import java.util.UUID;

public interface ProductoBuilder {

    //TODO: Agregar los metodos para laboratorio y categoria

    ProductoDTOBuilder setId(UUID id);
    ProductoDTOBuilder setNombre(String nombre);
    ProductoDTOBuilder setPrecio(int precio);
    ProductoDTOBuilder setDescripcion(String descripcion);
    ProductoDTOBuilder setImagen(String imagen);
}
