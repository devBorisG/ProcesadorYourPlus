package com.uco.yourplus.dtoyourplus;

import java.util.UUID;

import static com.uco.yourplus.crosscuttingyourplus.helper.IntegerHelper.ZERO;
import static com.uco.yourplus.crosscuttingyourplus.helper.IntegerHelper.getDefaultInteger;
import static com.uco.yourplus.crosscuttingyourplus.helper.StringHelper.EMPTY;
import static com.uco.yourplus.crosscuttingyourplus.helper.StringHelper.getDefaultString;
import static com.uco.yourplus.crosscuttingyourplus.helper.UUIDHelper.getDefaultUUID;
import static com.uco.yourplus.crosscuttingyourplus.helper.UUIDHelper.getNewUUID;

public class ProductoDTO {

    private UUID id;
    private String nombre;
    private int precio;
    private String descripcion;
    private String imagen;

    //TODO: crear los atributos de laboratorio y categoria

    public ProductoDTO() {
        setId(getNewUUID());
        setNombre(EMPTY);
        setPrecio(ZERO);
        setDescripcion(EMPTY);
        setImagen(EMPTY);
        //TODO: Agregar el laboratorio y categoria al constructor
    }

    public ProductoDTO(final UUID id, final String nombre, final int precio, final String descripcion, final String imagen) {
        setId(id);
        setNombre(nombre);
        setPrecio(precio);
        setDescripcion(descripcion);
        setImagen(imagen);
        //TODO: Agregar el laboratorio y categoria al constructor
    }

    public static ProductoDTO create(final UUID id, final String nombre, final int precio, final String descripcion, final String imagen) {
        //TODO: Agregar los objetos de laboratorio y categoria
        return new ProductoDTO(id, nombre, precio, descripcion, imagen);
    }

    public static ProductoDTO create(final UUID id) {
        //TODO: Agregar los objetos de laboratorio y categoria
        return new ProductoDTO(id, EMPTY, ZERO, EMPTY, EMPTY);
    }

    //TODO: Crear los getters y setters para laboratorio y categoria
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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = getDefaultInteger(precio);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = getDefaultString(descripcion);
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = getDefaultString(imagen);
    }
}
