package com.uco.yourplus.serviceyourplus.usecase;

import java.util.List;
import java.util.Optional;

/**
 * Esta interfaz define un caso de uso genérico que devuelve una lista de objetos de dominio.
 * Los casos de uso representan operaciones específicas que se pueden realizar en el sistema YourPlus,
 * y toman un objeto de dominio opcional de tipo D como entrada para su ejecución.
 * El resultado de la ejecución es una lista de objetos de dominio de tipo D.
 *
 * @param <D> El tipo de objeto de dominio que se utiliza como entrada y salida del caso de uso.
 */
public interface UseCaseList<D> {
    /**
     * Ejecuta el caso de uso con un objeto de dominio opcional de tipo D como entrada.
     *
     * @param domain El objeto de dominio opcional que contiene los datos necesarios para ejecutar el caso de uso.
     * @return Una lista de objetos de dominio de tipo D que representan el resultado de la ejecución.
     */
    List<D> execute(Optional<D> domain);
}
