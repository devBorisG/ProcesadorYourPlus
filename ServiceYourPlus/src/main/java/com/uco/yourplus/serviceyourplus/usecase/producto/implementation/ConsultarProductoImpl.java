package com.uco.yourplus.serviceyourplus.usecase.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.repository.RepositoryCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.entityyourplus.ProductoEntity;
import com.uco.yourplus.repositoryyourplus.producto.ProductoRepository;
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
        List<ProductoEntity> result;
        List<ProductoDomain> convertResult = new ArrayList<>();

        if (domain.isPresent()) {
            // Si se proporciona un ProductoDomain, copia sus propiedades a una ProductoEntity
            ProductoEntity productoEntity = new ProductoEntity();
            BeanUtils.copyProperties(domain.get(), productoEntity);

            try {
                // Realiza una consulta personalizada utilizando la ProductoEntity proporcionada
                result = productoRepository.findCustom(productoEntity);
            } catch (RepositoryCustomException e) {
                // Maneja excepciones y lanza una ServiceCustomException si ocurre un error
                throw ServiceCustomException.createTechnicalException(e, "Error en la consulta de productos.");
            }
        } else {
            try {
                // Si no se proporciona un ProductoDomain, obtiene todos los productos
                result = productoRepository.findAll();
            } catch (RepositoryCustomException e) {
                // Maneja excepciones y lanza una ServiceCustomException si ocurre un error
                throw ServiceCustomException.createTechnicalException(e, "Error en la consulta de productos.");
            }
        }

        // Convierte los resultados de ProductoEntity a ProductoDomain
        result.forEach(value -> {
            ProductoDomain productoDomain = new ProductoDomain();
            BeanUtils.copyProperties(value, productoDomain);
            convertResult.add(productoDomain);
        });

        return convertResult;
    }


}
