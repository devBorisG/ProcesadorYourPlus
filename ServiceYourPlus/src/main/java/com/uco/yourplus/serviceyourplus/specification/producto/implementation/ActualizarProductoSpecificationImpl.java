package com.uco.yourplus.serviceyourplus.specification.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.repositoryyourplus.producto.ProductoRepository;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.specification.producto.ActualizarProductoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.uco.yourplus.crosscuttingyourplus.helper.IntegerHelper.isDefaultInteger;
import static com.uco.yourplus.crosscuttingyourplus.helper.ObjectHelper.isNull;
import static com.uco.yourplus.crosscuttingyourplus.helper.StringHelper.isEmpty;
import static com.uco.yourplus.crosscuttingyourplus.helper.UUIDHelper.isDefaultUUID;

@Service
public class ActualizarProductoSpecificationImpl implements ActualizarProductoSpecification {

    private final ProductoRepository repository;

    @Autowired
    public ActualizarProductoSpecificationImpl(ProductoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void isSatisfied(ProductoDomain domain) {
        verifyProductIntegrity(domain);
        verifyLaboratoryIntegrity();
        verifyCategoryIntegrirty();
    }

    private void verifyProductIntegrity(ProductoDomain domain) {
        //Que el objeto no venga nulo
        if (isNull(domain)) {
            throw ServiceCustomException.createUserException("No se esta recibiendo ningun producto");
        }
        //Que el producto exista en la base de datos
        if (repository.findById(domain.getId()).isEmpty()) {
            throw ServiceCustomException.createUserException("El producto que desea actualizar no existe");
        }
        //Que el nombre no exista en la base de datos
        if (repository.findAll().stream().filter(entidad -> domain.getNombre()
                        .equals(entidad.getNombre()))
                .findFirst()
                .isPresent()) {
            throw ServiceCustomException.createUserException("Este nombre ya existe, intente nuevamente");
        }
        //Que todos los datos esten presentes
        if (verifyObligatoryAttributes(domain)) {
            throw ServiceCustomException.createUserException("Algunos datos del producto estan vacios");
        }
        //Que el precio no sea negativo
        if (domain.getPrecio() < 0) {
            throw ServiceCustomException.createUserException("El precio no puede ser negativo");
        }
    }

    private boolean verifyObligatoryAttributes(ProductoDomain domain) {
        return isDefaultUUID(domain.getId()) || isEmpty(domain.getNombre())
                || isDefaultInteger(domain.getPrecio()) || isEmpty(domain.getDescripcion())
                || isEmpty(domain.getImagen());
    }

    //TODO: Completar el codigo para aceptacion
    private void verifyCategoryIntegrirty() {
        //Verificar que el objeto de la categoria no sea nulo
        //Vertificar que el id de la categoria exista
    }

    private void verifyLaboratoryIntegrity() {
        //Verificar que el objeto de laboratorio no sea nulo
        //Vertificar que el id del laboratorio exista
    }
}
