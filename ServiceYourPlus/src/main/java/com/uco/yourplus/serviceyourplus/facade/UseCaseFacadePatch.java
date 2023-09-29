package com.uco.yourplus.serviceyourplus.facade;

import java.util.UUID;

/**
 * Interfaz genérica que define un patrón de diseño de fachada para la ejecución de casos de uso
 * que implican actualizaciones parciales (parcheo) en entidades o recursos identificados por UUID.
 *
 * @param <T> El tipo de objeto que representa las operaciones de parcheo (por ejemplo, JsonPatch).
 */
public interface UseCaseFacadePatch<T>{
    /**
     * Ejecuta un caso de uso para aplicar operaciones de parcheo a una entidad o recurso identificado por su UUID.
     *
     * @param id    El UUID que identifica la entidad o recurso a ser parcheado.
     * @param patch El objeto que contiene las operaciones de parcheo a aplicar.
     */
    void execute(UUID id, T patch);
}
