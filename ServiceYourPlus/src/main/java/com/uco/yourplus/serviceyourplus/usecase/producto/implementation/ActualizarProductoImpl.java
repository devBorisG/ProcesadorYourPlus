package com.uco.yourplus.serviceyourplus.usecase.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.repository.RepositoryCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.entityyourplus.ProductoEntity;
import com.uco.yourplus.repositoryyourplus.producto.ProductoRepository;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.specification.producto.ActualizarProductoSpecification;
import com.uco.yourplus.serviceyourplus.usecase.producto.ActualizarProducto;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActualizarProductoImpl implements ActualizarProducto {

    private final ProductoRepository repository;
    private final ActualizarProductoSpecification specification;

    @Autowired
    public ActualizarProductoImpl(ProductoRepository repository, ActualizarProductoSpecification specification) {
        this.repository = repository;
        this.specification = specification;
    }

    @Override
    public void execute(ProductoDomain domain) {
        ProductoEntity productoEntity = new ProductoEntity();
        try {
            specification.isSatisfied(domain);
            BeanUtils.copyProperties(domain, productoEntity);
            repository.save(productoEntity);
        } catch (ServiceCustomException exception) {
            throw exception;
        } catch (RepositoryCustomException exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error accediendo a JPA");
        } catch (BeanInstantiationException exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error mapeando el producto de domain a entity");
        } catch (Exception exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error inesperado");
        }
    }
}
