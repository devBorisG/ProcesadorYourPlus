package com.uco.yourplus.dtoyourplus.builder.producto;

import com.uco.yourplus.dtoyourplus.CategoriaDTO;
import com.uco.yourplus.dtoyourplus.LaboratorioDTO;
import com.uco.yourplus.dtoyourplus.ProductoDTO;

import java.util.UUID;

public class ProductoDTOBuilder implements ProductoBuilder{

    private UUID id;
    private String nombre;
    private int precio;
    private String descripcion;
    private String imagen;
    private CategoriaDTO categoria;
    private LaboratorioDTO laboratorio;

    private ProductoDTOBuilder(){
        super();
    }

    public static ProductoDTOBuilder getProductoDTOBuilder(){
        return new ProductoDTOBuilder();
    }

    @Override
    public ProductoDTOBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    @Override
    public ProductoDTOBuilder setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    @Override
    public ProductoDTOBuilder setPrecio(int precio) {
        this.precio = precio;
        return this;
    }

    @Override
    public ProductoDTOBuilder setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    @Override
    public ProductoDTOBuilder setImagen(String imagen) {
        this.imagen = imagen;
        return this;
    }

    @Override
    public ProductoDTOBuilder setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
        return this;
    }

    @Override
    public ProductoDTOBuilder setLaboratorio(LaboratorioDTO laboratorio) {
        this.laboratorio = laboratorio;
        return this;
    }

    public ProductoDTO build(){
        return ProductoDTO.create(id,nombre,precio,descripcion,imagen,laboratorio,categoria);
    }
}
