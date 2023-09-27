package com.uco.yourplus.serviceyourplus.usecase.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.repository.RepositoryCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.entityyourplus.ProductoEntity;
import com.uco.yourplus.repositoryyourplus.ProductoRepository;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.usecase.producto.RegistrarProducto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrarProductoImpl implements RegistrarProducto {

    private ProductoRepository repository;

    @Autowired
    public RegistrarProductoImpl(ProductoRepository repository){
        this.repository = repository;
    }

    @Override
    public void execute(ProductoDomain domain) {
        try {
            ProductoEntity productoEntity = new ProductoEntity();
            BeanUtils.copyProperties(domain, productoEntity);
            repository.save(productoEntity);
        }catch (ServiceCustomException exception){
            throw exception;
        }catch (RepositoryCustomException exception){
            throw ServiceCustomException.createTechnicalException(exception, "No se logro usar la integracion de JPA");
        }catch (Exception exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrió un error inesperado");
        }
    }
}
