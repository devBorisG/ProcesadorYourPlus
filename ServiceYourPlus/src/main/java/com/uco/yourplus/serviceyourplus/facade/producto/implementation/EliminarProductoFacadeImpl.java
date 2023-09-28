package com.uco.yourplus.serviceyourplus.facade.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.dtoyourplus.ProductoDTO;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.facade.producto.EliminarProductoFacade;
import com.uco.yourplus.serviceyourplus.usecase.producto.EliminarProducto;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EliminarProductoFacadeImpl implements EliminarProductoFacade {

    private EliminarProducto eliminarProducto;

    @Autowired
    public EliminarProductoFacadeImpl(EliminarProducto eliminarProducto){
        this.eliminarProducto = eliminarProducto;
    }

    @Override
    public void execute(ProductoDTO dto) {
        ProductoDomain productoDomain = new ProductoDomain();
        try{
            BeanUtils.copyProperties(dto,productoDomain);
            eliminarProducto.execute(productoDomain);
        } catch (BeanInstantiationException exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error mapeando de dto a domain");
        } catch (ServiceCustomException exception){
            throw exception;
        } catch (Exception exception){
            throw ServiceCustomException.createTechnicalException(exception,"Ocurrio un error inesperado");
        }
    }
}
