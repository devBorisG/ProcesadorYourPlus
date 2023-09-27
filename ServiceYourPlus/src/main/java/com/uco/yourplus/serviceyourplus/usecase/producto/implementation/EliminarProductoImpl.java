package com.uco.yourplus.serviceyourplus.usecase.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.repository.RepositoryCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.entityyourplus.ProductoEntity;
import com.uco.yourplus.repositoryyourplus.ProductoRepository;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.specification.producto.EliminarProductoSpecification;
import com.uco.yourplus.serviceyourplus.usecase.producto.EliminarProducto;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EliminarProductoImpl implements EliminarProducto {

    private final ProductoRepository repository;
    private final EliminarProductoSpecification specification;

    @Autowired
    public EliminarProductoImpl(ProductoRepository repository, EliminarProductoSpecification specification){
        this.repository = repository;
        this.specification = specification;
    }

    @Override
    public void execute(ProductoDomain domain) {
        ProductoEntity productoEntity = new ProductoEntity();
        try{
            specification.isSatisfied(domain);
            BeanUtils.copyProperties(domain, productoEntity);
            repository.delete(productoEntity);
        }catch (ServiceCustomException exception){
            throw exception;
        }catch (RepositoryCustomException exception){
            throw ServiceCustomException.createTechnicalException(exception,"Ocurrio un error utilizando JPA");
        }catch (BeanInstantiationException exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error mapeando el obejto de domain a entity");
        }catch (Exception exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error inesperado");
        }
    }
}
