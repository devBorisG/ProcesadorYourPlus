package com.uco.yourplus.crosscuttingyourplus.exceptions;

import com.uco.yourplus.crosscuttingyourplus.exceptions.enumeration.LayerException;

import java.io.Serial;

import static com.uco.yourplus.crosscuttingyourplus.helper.ObjectHelper.getDefaultIfNull;
import static com.uco.yourplus.crosscuttingyourplus.helper.StringHelper.isEmpty;

public class YourPlusCustomException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;
    private final String userMessage;
    private final LayerException layerException;

    /**
     * Crea una nueva instancia de YourPlusCustomException.
     *
     * @param rootException    La excepción raíz que causó este error, o null si no hay una excepción raíz.
     * @param technicalMessage El mensaje técnico que describe la causa del error.
     * @param userMessage      El mensaje amigable para el usuario que se muestra en la interfaz de usuario.
     * @param layerException   La capa de la aplicación en la que ocurrió el error.
     */
    protected YourPlusCustomException(final Throwable rootException, final String technicalMessage, final String userMessage, final LayerException layerException) {
        super(technicalMessage, getDefaultIfNull(rootException, new Exception()));
        this.userMessage = userMessage;
        this.layerException = layerException;
    }

    /**
     * Obtiene el mensaje amigable para el usuario asociado con esta excepción.
     *
     * @return El mensaje amigable para el usuario.
     */
    public String getUserMessage() {
        return userMessage;
    }

    /**
     * Obtiene la capa de la aplicación en la que ocurrió el error.
     *
     * @return La capa de la aplicación.
     */
    public LayerException getLayerException() {
        return layerException;
    }

    /**
     * Comprueba si esta excepción es una excepción técnica sin un mensaje de usuario.
     *
     * @return true si es una excepción técnica, false si tiene un mensaje de usuario.
     */
    public final boolean isTechnicalException() {
        return isEmpty(getUserMessage());
    }
}
