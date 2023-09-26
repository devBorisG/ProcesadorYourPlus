package com.uco.yourplus.serviceyourplus.usecase;

import java.util.UUID;

/**
 * Interfaz genérica que define un caso de uso para aplicar operaciones de parcheo (por ejemplo, JsonPatch)
 * a una entidad o recurso identificado por su UUID.
 *
 * @param <D> El tipo de objeto que representa las operaciones de parcheo (por ejemplo, JsonPatch).
 */
public interface UseCasePatch<D>{
    /**
     * Ejecuta un caso de uso para aplicar operaciones de parcheo a una entidad o recurso identificado por su UUID.
     *
     * @param id    El UUID que identifica la entidad o recurso a ser parcheado.
     * @param patch El objeto que contiene las operaciones de parcheo a aplicar.
     */
    void execute(UUID id, D patch);
}
