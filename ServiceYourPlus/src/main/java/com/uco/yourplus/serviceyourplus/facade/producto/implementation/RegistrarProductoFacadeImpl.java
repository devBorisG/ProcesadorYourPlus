package com.uco.yourplus.serviceyourplus.facade.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.dtoyourplus.ProductoDTO;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.facade.producto.RegistrarProductoFacade;
import com.uco.yourplus.serviceyourplus.usecase.producto.RegistrarProducto;
import com.uco.yourplus.serviceyourplus.usecase.producto.implementation.RegistrarProductoImpl;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RegistrarProductoFacadeImpl implements RegistrarProductoFacade {

    private RegistrarProducto useCase;
    @Autowired
    public RegistrarProductoFacadeImpl(RegistrarProductoImpl useCase){
        this.useCase = useCase;
    }

    @Override
    public void execute(ProductoDTO dto) {
        try {
            ProductoDomain productoDomain = new ProductoDomain();
            BeanUtils.copyProperties(dto, productoDomain);
            useCase.execute(productoDomain);
        }catch (ServiceCustomException exception){
            throw exception;
        }catch (BeanInstantiationException exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error mapeando de dto a domain");
        }catch (Exception exception){
            throw ServiceCustomException.createTechnicalException("Ocurrió un error inesperado");
        }
    }
}
