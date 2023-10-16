package com.uco.yourplus.serviceyourplus.specification.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.entityyourplus.CategoriaEntity;
import com.uco.yourplus.entityyourplus.LaboratorioEntity;
import com.uco.yourplus.repositoryyourplus.categoria.CategoriaRepository;
import com.uco.yourplus.repositoryyourplus.producto.ProductoRepository;
import com.uco.yourplus.repositoryyourplus.laboratorio.LaboratorioRepository;
import com.uco.yourplus.serviceyourplus.domain.CategoriaDomain;
import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.specification.producto.ConsultarProductoSpecification;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultarProductoSpecificationImpl implements ConsultarProductoSpecification {

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    LaboratorioRepository laboratorioRepository;

    @Autowired
    CategoriaRepository categoriaRepository;


    @Override
    public void isSatisfied(ProductoDomain data) {
        verifyProductIntegrity(data);
    }

    private void verifyProductIntegrity(ProductoDomain productoDomain) {
        if (productoRepository.findById(productoDomain.getId()).isPresent()) {
            throw ServiceCustomException.createUserException("El producto ya existe");
        }

        // Verificar campos obligatorios que no pueden ser nulos
        if (productoDomain.getNombre() == null || productoDomain.getNombre().isEmpty()) {
            throw ServiceCustomException.createUserException("El nombre del producto es obligatorio");
        }

        if (productoDomain.getPrecio() <= 0) {
            throw ServiceCustomException.createUserException("El precio del producto debe ser mayor que cero");
        }

        // Otras verificaciones de campos obligatorios
        if (productoDomain.getDescripcion() == null || productoDomain.getDescripcion().isEmpty()) {
            throw ServiceCustomException.createUserException("La descripción del producto es obligatoria");
        }

        if (productoDomain.getImagen() == null || productoDomain.getImagen().isEmpty()) {
            throw ServiceCustomException.createUserException("La imagen del producto es obligatoria");
        }

        if (verifyCategoria(productoDomain)) {
            throw ServiceCustomException.createUserException("No existe la categoria");
        }

        if (verifyLaboratorio(productoDomain)) {
            throw ServiceCustomException.createUserException("No existe el laboratorio");
        }


    }

    private boolean verifyCategoria(ProductoDomain productoDomain) {

        CategoriaDomain categoriaDomain = new CategoriaDomain();
        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaDomain.setNombre(productoDomain.getCategoriaDomain().getNombre());
        categoriaDomain.setDescripcion(productoDomain.getCategoriaDomain().getDescripcion());
        BeanUtils.copyProperties(categoriaDomain, categoriaEntity);
        return categoriaRepository.findCustom(categoriaEntity).isEmpty();
    }

    private boolean verifyLaboratorio(ProductoDomain productoDomain) {

        LaboratorioDomain laboratorioDomain = new LaboratorioDomain();
        LaboratorioEntity laboratorioEntity = new LaboratorioEntity();
        laboratorioDomain.setNombre(productoDomain.getLaboratorioDomain().getNombre());
        laboratorioDomain.setDescripcion(productoDomain.getLaboratorioDomain().getDescripcion());
        BeanUtils.copyProperties(laboratorioDomain, laboratorioEntity);
        return laboratorioRepository.findCustom(laboratorioEntity).isEmpty();
    }

}





