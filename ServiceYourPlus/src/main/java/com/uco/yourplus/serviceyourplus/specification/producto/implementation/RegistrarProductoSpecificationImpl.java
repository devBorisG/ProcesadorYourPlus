package com.uco.yourplus.serviceyourplus.specification.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import static com.uco.yourplus.crosscuttingyourplus.helper.UUIDHelper.isDefaultUUID;
import static com.uco.yourplus.crosscuttingyourplus.helper.StringHelper.isEmpty;
import static com.uco.yourplus.crosscuttingyourplus.helper.IntegerHelper.isDefaultInteger;
import com.uco.yourplus.repositoryyourplus.ProductoRepository;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.specification.ISpecification;
import com.uco.yourplus.serviceyourplus.specification.producto.RegistrarProductoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrarProductoSpecificationImpl implements RegistrarProductoSpecification {

    private ProductoRepository repository;

    @Autowired
    public RegistrarProductoSpecificationImpl(ProductoRepository repository){
        this.repository = repository;
    }

    @Override
    public void isSatisfied(ProductoDomain domain) {
        verifyProductIntegrity(domain);
    }

    private void verifyProductIntegrity(ProductoDomain domain){
        //Que no exista el producto
        if (repository.findById(domain.getId()).isPresent()){
            throw ServiceCustomException.createUserException("El producto ya existe");
        }
        //Que los atributos que son obligatorios esten presentes (todos)
        if (verifyObligatoryAttributes(domain)){
            throw ServiceCustomException.createUserException("Los datos del producto estan vacios");
        }
        //Que el precio no sea negativo
        if (domain.getPrecio()<0){
            throw ServiceCustomException.createUserException("El precio no puede ser negativo");
        }
        //Que el nombre del producto no exista
        if (repository.findAll().contains(domain.getNombre())){
            throw ServiceCustomException.createUserException("El nombre del producto ya existe");
        }
    }

    private boolean verifyObligatoryAttributes(ProductoDomain domain) {
        return isDefaultUUID(domain.getId()) || isEmpty(domain.getNombre())
                || isDefaultInteger(domain.getPrecio()) || isEmpty(domain.getDescripcion())
                || isEmpty(domain.getImagen());
    }

    //TODO: Verificar integridad de laboratorio y categoria
}
