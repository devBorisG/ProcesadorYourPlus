package com.uco.yourplus.serviceyourplus.usecase.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.repository.RepositoryCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.entityyourplus.ProductoEntity;
import com.uco.yourplus.repositoryyourplus.producto.ProductoRepository;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.specification.producto.RegistrarProductoSpecification;
import com.uco.yourplus.serviceyourplus.usecase.producto.RegistrarProducto;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrarProductoImpl implements RegistrarProducto {

    private final ProductoRepository repository;
    private final RegistrarProductoSpecification specification;

    @Autowired
    public RegistrarProductoImpl(ProductoRepository repository, RegistrarProductoSpecification specification) {
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
        } catch (BeanInstantiationException exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error mapeando el obejto de domain a entity");
        } catch (RepositoryCustomException exception) {
            throw ServiceCustomException.createTechnicalException(exception, "No se logro usar la integracion de JPA");
        } catch (Exception exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrió un error inesperado");
        }
    }
}
