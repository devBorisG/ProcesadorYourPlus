package com.uco.yourplus.crosscuttingyourplus.exceptions.enumeration;

public enum LayerException {

    /**
     * Nivel de excepción SERVICE (servicio).
     * Este nivel se utiliza para excepciones relacionadas con la capa de servicio de la aplicación.
     */
    SERVICE,

    /**
     * Nivel de excepción CROSSCUTTING (aspectos transversales).
     * Este nivel se utiliza para excepciones relacionadas con aspectos transversales de la aplicación, como la gestión de excepciones.
     */
    CROSSCUTTING,

    /**
     * Nivel de excepción REPOSITORY (repositorio).
     * Este nivel se utiliza para excepciones relacionadas con la capa de repositorio de la aplicación.
     */
    REPOSITORY
}
