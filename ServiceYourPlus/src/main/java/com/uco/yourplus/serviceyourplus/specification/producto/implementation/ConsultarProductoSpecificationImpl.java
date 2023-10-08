package com.uco.yourplus.serviceyourplus.specification.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.repositoryyourplus.producto.ProductoRepository;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.specification.producto.ConsultarProductoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ConsultarProductoSpecificationImpl implements ConsultarProductoSpecification {

    @Autowired
    ProductoRepository productoRepository;

    @Override
    public void isSatisfied(ProductoDomain data) {
        verifyProductIntegrity(data);
    }

    private void verifyProductIntegrity(ProductoDomain productoDomain) {
        if (productoRepository.findById(productoDomain.getId()).isPresent()) {
            throw ServiceCustomException.createProductException("El producto ya existe");
        }

        // Verificar campos obligatorios que no pueden ser nulos
        if (productoDomain.getNombre() == null || productoDomain.getNombre().isEmpty()) {
            throw ServiceCustomException.createProductException("El nombre del producto es obligatorio");
        }

        if (productoDomain.getPrecio() <= 0) {
            throw ServiceCustomException.createProductException("El precio del producto debe ser mayor que cero");
        }

        // Otras verificaciones de campos obligatorios
        if (productoDomain.getDescripcion() == null || productoDomain.getDescripcion().isEmpty()) {
            throw ServiceCustomException.createProductException("La descripción del producto es obligatoria");
        }

        if (productoDomain.getImagen() == null || productoDomain.getImagen().isEmpty()) {
            throw ServiceCustomException.createProductException("La imagen del producto es obligatoria");
        }

    }

}
