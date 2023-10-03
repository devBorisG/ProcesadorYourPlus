/**
  La interfaz EliminarPersona define un contrato para el caso de uso de eliminación de personas en
  la aplicación YourPlus. Esta interfaz extiende la interfaz UseCase, que proporciona un método genérico
 para ejecutar casos de uso.

 @param <PersonaDomain> Tipo genérico que representa el dominio de la persona a eliminar.
 */
package com.uco.yourplus.serviceyourplus.usecase.laboratorio;


import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.usecase.UseCase;


public interface EliminarLaboratorio extends UseCase<LaboratorioDomain> {
}
