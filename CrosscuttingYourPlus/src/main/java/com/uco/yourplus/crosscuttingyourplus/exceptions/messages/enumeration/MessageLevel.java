package com.uco.yourplus.crosscuttingyourplus.exceptions.messages.enumeration;

public enum MessageLevel {

    /**
     * Nivel de mensaje FATAL, que indica un error grave que generalmente resulta en la terminación de la aplicación.
     */
    FATAL,

    /**
     * Nivel de mensaje ERROR, que indica un error que afecta negativamente el funcionamiento de la aplicación.
     */
    ERROR,

    /**
     * Nivel de mensaje WARNING, que indica una advertencia o una condición potencialmente problemática.
     */
    WARNING,

    /**
     * Nivel de mensaje INFO, que proporciona información general sobre el estado o el progreso de la aplicación.
     */
    INFO,

    /**
     * Nivel de mensaje SUCCESS, que indica un mensaje de éxito o confirmación.
     */
    SUCCESS
}
