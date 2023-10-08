package com.uco.yourplus.entityyourplus;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Producto",schema = "public")

public class ProductoEntity {

    @Id
    UUID id;
    String nombre;
    int precio;
    String descripcion;
    String imagen;

    @ManyToOne
    @JoinColumn(name = "id_laboratorio")
    transient LaboratorioEntity laboratorioEntity;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    transient CategoriaEntity categoriaEntity;

    @JsonProperty("id")
    public void setId(UUID id) {
        this.id = id;
    }

    @JsonProperty("nombre")
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @JsonProperty("precio")
    public void setPrecio(int precio){
        this.precio = precio;
    }

    @JsonProperty("descripcion")
    public void setdescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    @JsonProperty("imagen")
    public void setimagen(String imagen){
        this.imagen = imagen;
    }

    @JsonProperty("laboratorioEntity")
    public void setLaboratorioEntity(LaboratorioEntity laboratorioEntity) {
        this.laboratorioEntity = laboratorioEntity;

    }

    @JsonProperty("categoriaEntity")
    public void setCategoriaEntity(CategoriaEntity categoriaEntity) {
        this.categoriaEntity = categoriaEntity;

    }

}
