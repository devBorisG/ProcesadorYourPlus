package com.uco.yourplus.serviceyourplus.usecase.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.repository.RepositoryCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.entityyourplus.ProductoEntity;
import com.uco.yourplus.repositoryyourplus.producto.ProductoRepository;
import com.uco.yourplus.serviceyourplus.domain.CategoriaDomain;
import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
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
    public List<ProductoDomain> execute(Optional<ProductoDomain> domain){

        List<ProductoEntity> result;
        List<ProductoDomain> convertResult = new ArrayList<>();

        if (domain.isPresent()){

            ProductoEntity productoEntity = new ProductoEntity();
            BeanUtils.copyProperties(domain.get(), productoEntity);

            try {

                result = productoRepository.findCustom(productoEntity);

            } catch (RepositoryCustomException e ){

                throw ServiceCustomException.createTechnicalException(e, "Consulta de producto no realizada");

            }

        } else {

            try {

                result = productoRepository.findAll();

            } catch (RepositoryCustomException e){

                throw ServiceCustomException.createTechnicalException(e, "Consulta de productos no realizada");
            }
        }

        result.forEach(value -> {

            ProductoDomain productoDomain = new ProductoDomain();
            LaboratorioDomain laboratorioDomain = new LaboratorioDomain();
            CategoriaDomain categoriaDomain = new CategoriaDomain();

            BeanUtils.copyProperties(value.getCategoriaEntity(), categoriaDomain);
            BeanUtils.copyProperties(value.getLaboratorioEntity(), laboratorioDomain);
            BeanUtils.copyProperties(value,productoDomain);

            productoDomain.setCategoriaDomain(categoriaDomain);
            productoDomain.setLaboratorioDomain(laboratorioDomain);

            convertResult.add(productoDomain);
        });

        return convertResult;

    }
}
