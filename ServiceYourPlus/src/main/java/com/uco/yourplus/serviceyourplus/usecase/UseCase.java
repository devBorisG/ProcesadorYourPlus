package com.uco.yourplus.serviceyourplus.usecase;

/**
 * Esta interfaz define un caso de uso genérico en el sistema YourPlus.
 * Los casos de uso representan operaciones específicas que se pueden realizar en el sistema,
 * y toman un objeto de dominio de tipo D como entrada para su ejecución.
 *
 * @param <D> El tipo de objeto de dominio que se utiliza como entrada para el caso de uso.
 */
public interface UseCase <D>{
    /**
     * Ejecuta el caso de uso con un objeto de dominio de tipo D como entrada.
     *
     * @param domain El objeto de dominio que contiene los datos necesarios para ejecutar el caso de uso.
     */
    void execute(D domain);

}
