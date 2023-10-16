package com.uco.yourplus.entityyourplus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Producto", schema = "public")
public class ProductoEntity  {

    @Id
    UUID id;
    String nombre;
    int precio;
    String descripcion;
    String imagen;

    @ManyToOne
    @JoinColumn(name = "id_laboratorio")
    LaboratorioEntity laboratorioEntity;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    CategoriaEntity categoriaEntity;

}
