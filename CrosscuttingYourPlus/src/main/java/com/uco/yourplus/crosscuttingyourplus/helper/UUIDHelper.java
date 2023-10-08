package com.uco.yourplus.crosscuttingyourplus.helper;

import java.util.UUID;

public class UUIDHelper {


    /**
     * Valor constante que representa el UUID predeterminado como una cadena de texto.
     */
    private static final String DEFAULT_UUID_AS_STRING = "00000000-0000-0000-0000-000000000000";
    /**
     * Valor constante que representa el UUID predeterminado como un objeto UUID.
     */
    private static final UUID DEFAULT_UUID = UUID.fromString(DEFAULT_UUID_AS_STRING);

    // Evita la instanciación de la clase, ya que es de utilidad y no debe crearse instancias.
    private UUIDHelper() {
        super();
    }

    /**
     * Obtiene el UUID proporcionado o el UUID predeterminado si el valor es nulo.
     *
     * @param value El UUID que se verificará.
     * @return El UUID proporcionado o el UUID predeterminado si es nulo.
     */
    public static UUID getDefaultUUID(final UUID value) {
        return ObjectHelper.getDefaultIfNull(value, DEFAULT_UUID);
    }

    /**
     * Genera un nuevo UUID único, excluyendo el UUID predeterminado.
     *
     * @return Un nuevo UUID único generado.
     */
    public static UUID getNewUUID() {
        UUID uuid;
        do {
            uuid = UUID.randomUUID();
        } while (isDefaultUUID(uuid));
        return uuid;
    }

    /**
     * Verifica si un UUID es igual al UUID predeterminado.
     *
     * @param value El UUID que se verificará.
     * @return true si el UUID es igual al UUID predeterminado, false de lo contrario.
     */
    public static boolean isDefaultUUID(final UUID value) {
        return DEFAULT_UUID.equals(value);
    }
}
