package com.uco.yourplus.crosscuttingyourplus.helper;

public class ObjectHelper {

    // Evita la instanciación de la clase, ya que es de utilidad y no debe crearse instancias.
    private ObjectHelper(){
        super();
    }

    /**
     * Verifica si un objeto es nulo.
     *
     * @param value El objeto que se verificará.
     * @param <T>   El tipo de objeto.
     * @return true si el objeto es nulo, false de lo contrario.
     */
    public static <T> boolean isNull(T value){
        return value == null;
    }

    /**
     * Obtiene el valor proporcionado o un valor predeterminado si el valor es nulo.
     *
     * @param value        El valor que se verificará.
     * @param defaultValue El valor predeterminado a utilizar si el valor es nulo.
     * @param <T>          El tipo de valor.
     * @return El valor proporcionado o el valor predeterminado si es nulo.
     */
    public static <T> T getDefaultIfNull(T value, T defaultValue){
        return (isNull(value))?defaultValue : value;
    }
}
