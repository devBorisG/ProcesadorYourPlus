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
@Table(name = "Categoria")
public class CategoriaEntity {

    @Id
    UUID id;
    String nombre;
    String descripcion;

}
