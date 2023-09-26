package com.uco.yourplus.serviceyourplus.facade.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.dtoyourplus.ProductoDTO;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.facade.producto.ActualizarProductoFacade;
import com.uco.yourplus.serviceyourplus.usecase.producto.ActualizarProducto;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ActualizarProductoFacadeImpl implements ActualizarProductoFacade {

    private ActualizarProducto useCase;

    @Autowired
    public ActualizarProductoFacadeImpl(ActualizarProducto useCase){
        this.useCase = useCase;
    }

    @Override
    public void execute(ProductoDTO dto) {
        ProductoDomain productoDomain = new ProductoDomain();
        try {
            BeanUtils.copyProperties(dto, productoDomain);
            useCase.execute(productoDomain);
        }catch (ServiceCustomException exception){
            throw exception;
        }catch (BeanInstantiationException exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error mapeando el objeto prodcuto de dto a domain");
        }catch (Exception exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error inesperado");
        }
    }
}
