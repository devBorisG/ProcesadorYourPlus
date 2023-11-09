package com.uco.yourplus.serviceyourplus.usecase.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.repository.RepositoryCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.entityyourplus.ProductoEntity;
import com.uco.yourplus.repositoryyourplus.ProductoRepository;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.usecase.producto.ConsultarProducto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultarProductoImpl implements ConsultarProducto {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<ProductoDomain> execute(Optional<ProductoDomain> domain) {
        List<ProductoEntity> productoEntities;
        List<ProductoDomain> productoDomains = new ArrayList<>();
        if(domain.isPresent()){
            ProductoEntity productoEntity = new ProductoEntity();
            BeanUtils.copyProperties(domain.get(),productoEntity);
            try{
                productoEntities = productoRepository.findCustom(productoEntity);
            } catch(RepositoryCustomException exception){
                throw ServiceCustomException.createTechnicalException(exception,"Ocurrio un error buscando, intente de nuevo");
            }
        } else{
            try{
                productoEntities = productoRepository.findAll();
            } catch(RepositoryCustomException e){
                throw ServiceCustomException.createTechnicalException(e,"Ocurrio un error buscando, intente de nuevo");
            }
        }
        productoEntities.forEach(value ->{
            ProductoDomain productoDomain = new ProductoDomain();
            BeanUtils.copyProperties(value,productoDomain);
            productoDomains.add(productoDomain);
        });
        return productoDomains;
    }
}
