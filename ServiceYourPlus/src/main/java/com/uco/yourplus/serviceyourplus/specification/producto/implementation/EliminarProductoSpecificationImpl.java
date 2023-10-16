package com.uco.yourplus.serviceyourplus.specification.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.repositoryyourplus.producto.ProductoRepository;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.specification.producto.EliminarProductoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.uco.yourplus.crosscuttingyourplus.helper.ObjectHelper.isNull;

@Service
public class EliminarProductoSpecificationImpl implements EliminarProductoSpecification {

    private final ProductoRepository repository;

    @Autowired
    public EliminarProductoSpecificationImpl(ProductoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void isSatisfied(ProductoDomain domain) {
        if (isNull(domain)) {
            throw ServiceCustomException.createUserException("No se esta enviando ningun producto para eliminar");
        }
        if (repository.findById(domain.getId()).isEmpty()) {
            throw ServiceCustomException.createUserException("El producto que desea eliminar no existe");
        }
    }
}
