package com.uco.yourplus.crosscuttingyourplus.exceptions.crosscutting;

import com.uco.yourplus.crosscuttingyourplus.exceptions.YourPlusCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.enumeration.LayerException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;

import java.io.Serial;

import static com.uco.yourplus.crosscuttingyourplus.helper.StringHelper.EMPTY;

public class CrosscuttingCustomException extends YourPlusCustomException{
    @Serial
    private static final long serialVersionUID = -7581138972968503560L;

    /**
     * Crea una nueva instancia de ServiceCustomException con un mensaje técnico y un mensaje amigable para el usuario.
     *
     * @param rootException   La excepción raíz que causó este error, o null si no hay una excepción raíz.
     * @param technicalMessage El mensaje técnico que describe la causa del error.
     * @param userMessage     El mensaje amigable para el usuario que se muestra en la interfaz de usuario.
     */
    private CrosscuttingCustomException(Exception rootException, String technicalMessage, String userMessage) {
        super(rootException, technicalMessage, userMessage, LayerException.CROSSCUTTING);
    }

    /**
     * Crea una excepción de servicio con un mensaje técnico y un mensaje amigable para el usuario.
     *
     * @param userMesssage     El mensaje amigable para el usuario que se muestra en la interfaz de usuario.
     * @param technicalMessage El mensaje técnico que describe la causa del error.
     * @return Una nueva instancia de ServiceCustomException.
     */
    public static YourPlusCustomException create(final String userMesssage, final String technicalMessage) {
        return new CrosscuttingCustomException(new Exception(), technicalMessage, userMesssage);
    }

    /**
     * Crea una excepción de servicio con una excepción raíz, un mensaje técnico y un mensaje amigable para el usuario.
     *
     * @param rootException   La excepción raíz que causó este error.
     * @param userMessage     El mensaje amigable para el usuario que se muestra en la interfaz de usuario.
     * @param technicalMessage El mensaje técnico que describe la causa del error.
     * @return Una nueva instancia de ServiceCustomException.
     */
    public static YourPlusCustomException create(final Exception rootException, final String userMessage,
                                                 final String technicalMessage) {
        return new CrosscuttingCustomException(rootException, technicalMessage, userMessage);
    }

    /**
     * Crea una excepción de servicio con un mensaje técnico y sin mensaje amigable para el usuario.
     *
     * @param technicalMessage El mensaje técnico que describe la causa del error.
     * @return Una nueva instancia de ServiceCustomException.
     */
    public static YourPlusCustomException createTechnicalException(final String technicalMessage) {
        return new CrosscuttingCustomException(new Exception(), technicalMessage, EMPTY);
    }

    /**
     * Crea una excepción de servicio con una excepción raíz, un mensaje técnico y sin mensaje amigable para el usuario.
     *
     * @param rootException    La excepción raíz que causó este error.
     * @param technicalMessage El mensaje técnico que describe la causa del error.
     * @return Una nueva instancia de ServiceCustomException.
     */
    public static YourPlusCustomException createTechnicalException(final Exception rootException, final String technicalMessage){
        return new CrosscuttingCustomException(rootException, technicalMessage, EMPTY);
    }

    /**
     * Crea una excepción de servicio con un mensaje amigable para el usuario y sin mensaje técnico.
     *
     * @param userMessage El mensaje amigable para el usuario que se muestra en la interfaz de usuario.
     * @return Una nueva instancia de ServiceCustomException.
     */
    public static YourPlusCustomException createUserException(final String userMessage){
        return new CrosscuttingCustomException(null, userMessage, userMessage);
    }

    /**
     * Crea una excepción de servicio con un mensaje de negocio y una excepción raíz.
     *
     * @param businessMessage El mensaje de negocio que describe la causa del error.
     * @param rootException   La excepción raíz que causó este error.
     * @return Una nueva instancia de ServiceCustomException.
     */
    public static YourPlusCustomException createBusinessException(final String businessMessage, final Exception rootException){
        return new CrosscuttingCustomException(rootException, businessMessage, EMPTY);
    }

    /**
     * Envuelve una excepción personalizada en una excepción de negocio si no tiene un mensaje técnico.
     *
     * @param message    El mensaje de negocio que describe la causa del error.
     * @param exception  La excepción personalizada existente que se va a envolver si no tiene un mensaje técnico.
     * @return Una excepción de negocio envuelta o la excepción personalizada original si ya tiene un mensaje técnico.
     */
    public static YourPlusCustomException wrapException(final String message, final YourPlusCustomException exception){
        if(exception.isTechnicalException()){
            return CrosscuttingCustomException.createBusinessException(message, exception);
        }
        return exception;
    }
}
