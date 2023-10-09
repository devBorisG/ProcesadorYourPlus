package com.uco.yourplus.crosscuttingyourplus.helper;

import java.util.Objects;

import static com.uco.yourplus.crosscuttingyourplus.helper.ObjectHelper.getDefaultIfNull;

public class StringHelper {

    /**
     * Valor constante que representa una cadena de texto vacía.
     */
    public static final String EMPTY = "";

    // Evita la instanciación de la clase, ya que es de utilidad y no debe crearse instancias.
    private StringHelper() {
        super();
    }

    /**
     * Obtiene la cadena de texto proporcionada o una cadena de texto predeterminada si la cadena es nula.
     *
     * @param value        La cadena de texto que se verificará.
     * @param defaultValue La cadena de texto predeterminada a utilizar si la cadena es nula.
     * @return La cadena de texto proporcionada o la cadena de texto predeterminada si es nula.
     */
    public static String getDefaultString(String value, String defaultValue) {
        return getDefaultIfNull(value, defaultValue);
    }

    /**
     * Obtiene la cadena de texto proporcionada o una cadena de texto vacía si la cadena es nula.
     *
     * @param value La cadena de texto que se verificará.
     * @return La cadena de texto proporcionada o una cadena de texto vacía si es nula.
     */
    public static String getDefaultString(String value) {
        return getDefaultIfNull(value, EMPTY);
    }

    /**
     * Aplica el método trim a la cadena de texto para eliminar espacios en blanco al principio y al final.
     *
     * @param value La cadena de texto a la que se aplicará trim.
     * @return La cadena de texto con espacios en blanco eliminados al principio y al final.
     */
    public static String applyTrim(String value) {
        return getDefaultString(value).trim();
    }

    /**
     * Verifica si una cadena de texto está vacía (nula o sin contenido).
     *
     * @param value La cadena de texto que se verificará.
     * @return true si la cadena de texto está vacía, false de lo contrario.
     */
    public static boolean isEmpty(String value) {
        return Objects.equals(value, EMPTY);
    }


    /**
     * Verifica si una cadena de texto contiene solo letras y espacios.
     *
     * @param value La cadena de texto que se verificará.
     * @return true si la cadena de texto contiene solo letras y espacios, false de lo contrario.
     */
    public static boolean isOnlyWordsAndSpace(String value) {
        return value.matches("[a-zA-Z ]+");
    }

    /**
     * Verifica si una cadena de texto contiene solo letras.
     *
     * @param value La cadena de texto que se verificará.
     * @return true si la cadena de texto contiene solo letras, false de lo contrario.
     */
    public static boolean isOnlyWords(String value) {
        return value.matches("^[a-zA-Z]+$");
    }

    /**
     * Verifica si una cadena de texto es un número de celular válido con el formato "+123456789012".
     *
     * @param number El número de celular que se verificará.
     * @return true si el número de celular es válido, false de lo contrario.
     */
    public static boolean verifyCelNumber(String number) {
        return number.matches("\\+\\d{12}");
    }

    /**
     * Verifica si una cadena de texto es una dirección de correo electrónico válida.
     *
     * @param email La dirección de correo electrónico que se verificará.
     * @return true si la dirección de correo electrónico es válida, false de lo contrario.
     */
    public static boolean verifyEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
}
