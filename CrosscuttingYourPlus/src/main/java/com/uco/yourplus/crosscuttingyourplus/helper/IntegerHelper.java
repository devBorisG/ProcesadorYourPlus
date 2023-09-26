package com.uco.yourplus.crosscuttingyourplus.helper;

import static com.uco.yourplus.crosscuttingyourplus.helper.ObjectHelper.getDefaultIfNull;

public class IntegerHelper {

    // Evita la instanciación de la clase, ya que es de utilidad y no debe crearse instancias.
    private IntegerHelper(){
        super();
    }

    /**
     * Valor constante que representa el entero cero (0).
     */
    public static final int ZERO = 0;

    /**
     * Obtiene el valor entero proporcionado o el valor predeterminado si es nulo.
     *
     * @param value El valor entero que se verificará.
     * @return El valor entero proporcionado o el valor predeterminado si es nulo.
     */
    public static int getDefaultInteger(int value){
        return getDefaultIfNull(value,ZERO);
    }
}
