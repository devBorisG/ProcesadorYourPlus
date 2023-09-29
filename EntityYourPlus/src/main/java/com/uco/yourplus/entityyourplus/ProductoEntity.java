package com.uco.yourplus.entityyourplus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Producto",schema = "public")
public class ProductoEntity {
    //TODO: agregar los atributos de laboratorio y categoria
    @Id
    UUID id;
    String nombre;
    int precio;
    String descripcion;
    String imagen;
}
