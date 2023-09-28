package com.uco.yourplus.dtoyourplus;

import java.util.UUID;

import static com.uco.yourplus.crosscuttingyourplus.helper.StringHelper.EMPTY;
import static com.uco.yourplus.crosscuttingyourplus.helper.StringHelper.getDefaultString;
import static com.uco.yourplus.crosscuttingyourplus.helper.UUIDHelper.getDefaultUUID;

public class LaboratorioDTO {
    private UUID id;
    private String nombre;
    private String descripcion;

    public LaboratorioDTO(){
        setId(id);
        setNombre( EMPTY);
        setDescripcion(EMPTY);
    }

    public LaboratorioDTO(final UUID id, final String nombre, final String descripcion){
        setId(id);
        setNombre(nombre);
        setDescripcion(descripcion);
    }

    public static LaboratorioDTO create(final UUID id, final String nombre, final String descripcion){
        return new LaboratorioDTO(id,nombre,descripcion);
    }

    public static LaboratorioDTO create(final UUID id){
        return new LaboratorioDTO(id, EMPTY,EMPTY);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = getDefaultUUID(id);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = getDefaultString(nombre);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = getDefaultString(descripcion);
    }


}
