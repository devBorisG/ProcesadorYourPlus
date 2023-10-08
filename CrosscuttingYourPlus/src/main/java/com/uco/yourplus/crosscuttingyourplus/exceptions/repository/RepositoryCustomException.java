package com.uco.yourplus.crosscuttingyourplus.exceptions.repository;

import com.uco.yourplus.crosscuttingyourplus.exceptions.YourPlusCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.enumeration.LayerException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;

import java.io.Serial;

import static com.uco.yourplus.crosscuttingyourplus.helper.StringHelper.EMPTY;

public class RepositoryCustomException extends YourPlusCustomException {

    @Serial
    private static final long serialVersionUID = -7581138972968503560L;

    /**
     * Crea una nueva instancia de RepositoryCustomException con un mensaje técnico y un mensaje amigable para el usuario.
     *
     * @param rootException    La excepción raíz que causó este error, o null si no hay una excepción raíz.
     * @param technicalMessage El mensaje técnico que describe la causa del error.
     * @param userMessage      El mensaje amigable para el usuario que se muestra en la interfaz de usuario.
     */
    private RepositoryCustomException(Exception rootException, String technicalMessage, String userMessage) {
        super(rootException, technicalMessage, userMessage, LayerException.REPOSITORY);
    }

    /**
     * Crea una excepción de repositorio con un mensaje técnico y un mensaje amigable para el usuario.
     *
     * @param userMesssage     El mensaje amigable para el usuario que se muestra en la interfaz de usuario.
     * @param technicalMessage El mensaje técnico que describe la causa del error.
     * @return Una nueva instancia de RepositoryCustomException.
     */
    public static YourPlusCustomException create(final String userMesssage, final String technicalMessage) {
        return new RepositoryCustomException(new Exception(), technicalMessage, userMesssage);
    }

    /**
     * Crea una excepción de repositorio con una excepción raíz, un mensaje técnico y un mensaje amigable para el usuario.
     *
     * @param rootException    La excepción raíz que causó este error.
     * @param userMessage      El mensaje amigable para el usuario que se muestra en la interfaz de usuario.
     * @param technicalMessage El mensaje técnico que describe la causa del error.
     * @return Una nueva instancia de RepositoryCustomException.
     */
    public static YourPlusCustomException create(final Exception rootException, final String userMessage,
                                                 final String technicalMessage) {
        return new RepositoryCustomException(rootException, technicalMessage, userMessage);
    }

    /**
     * Crea una excepción de repositorio con un mensaje técnico y sin mensaje amigable para el usuario.
     *
     * @param technicalMessage El mensaje técnico que describe la causa del error.
     * @return Una nueva instancia de RepositoryCustomException.
     */
    public static YourPlusCustomException createTechnicalException(final String technicalMessage) {
        return new RepositoryCustomException(new Exception(), technicalMessage, EMPTY);
    }

    /**
     * Crea una excepción de repositorio con una excepción raíz, un mensaje técnico y sin mensaje amigable para el usuario.
     *
     * @param rootException    La excepción raíz que causó este error.
     * @param technicalMessage El mensaje técnico que describe la causa del error.
     * @return Una nueva instancia de RepositoryCustomException.
     */
    public static YourPlusCustomException createTechnicalException(final Exception rootException, final String technicalMessage) {
        return new RepositoryCustomException(rootException, technicalMessage, EMPTY);
    }

    /**
     * Envuelve una excepción personalizada en una excepción de negocio si no tiene un mensaje técnico.
     *
     * @param message   El mensaje de negocio que describe la causa del error.
     * @param exception La excepción personalizada existente que se va a envolver si no tiene un mensaje técnico.
     * @return Una excepción de negocio envuelta o la excepción personalizada original si ya tiene un mensaje técnico.
     */
    public static YourPlusCustomException wrapException(final String message, final YourPlusCustomException exception) {
        if (exception.isTechnicalException()) {
            return ServiceCustomException.createBusinessException(message, exception);
        }
        return exception;
    }
}
