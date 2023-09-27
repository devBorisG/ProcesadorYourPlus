package com.uco.yourplus.serviceyourplus.usecase.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.repository.RepositoryCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.entityyourplus.ProductoEntity;
import com.uco.yourplus.repositoryyourplus.ProductoRepository;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.usecase.producto.ActualizarProducto;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActualizarProductoImpl implements ActualizarProducto {

    private ProductoRepository repository;

    @Autowired
    public ActualizarProductoImpl(ProductoRepository repository){
        this.repository = repository;
    }

    @Override
    public void execute(ProductoDomain domain) {
        ProductoEntity productoEntity = new ProductoEntity();
        try{
            //TODO: verificar que el nombre del producto no exista en la base de datos y la integridad de los datos
            //TODO: Quitar la consulta y poner en el specification
            Optional<ProductoEntity> query = repository.findById(domain.getId());
            if (query.isPresent()){
                BeanUtils.copyProperties(domain, productoEntity);
                repository.save(productoEntity);
            }else{
                throw ServiceCustomException.createUserException("El producto que deseas actualizar no existe");
            }
        }catch (ServiceCustomException exception){
            throw exception;
        }catch (RepositoryCustomException exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error accediendo a JPA");
        }catch (BeanInstantiationException exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error mapeando el producto de domain a entity");
        }catch (Exception exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error inesperado");
        }
    }
}
