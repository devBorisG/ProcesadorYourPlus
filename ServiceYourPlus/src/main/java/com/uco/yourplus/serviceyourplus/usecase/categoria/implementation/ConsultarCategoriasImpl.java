package com.uco.yourplus.serviceyourplus.usecase.categoria.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.repository.RepositoryCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.entityyourplus.CategoriaEntity;
import com.uco.yourplus.repositoryyourplus.CategoriaRepository;
import com.uco.yourplus.serviceyourplus.domain.CategoriaDomain;
import com.uco.yourplus.serviceyourplus.usecase.categoria.ConsultarCategorias;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultarCategoriasImpl implements ConsultarCategorias {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaDomain> execute(Optional<CategoriaDomain> domain) {
        List<CategoriaEntity> result;
        List<CategoriaDomain> convertResult = new ArrayList<>();

        if(domain.isPresent()){
            CategoriaEntity categoriaEntity = new CategoriaEntity();
            BeanUtils.copyProperties(domain.get(),categoriaEntity);
            try{
                result = categoriaRepository.findCustom(categoriaEntity);
            } catch (RepositoryCustomException exception){
                throw ServiceCustomException.createTechnicalException(exception,"Ocurrio un error, intente de nuevo");
            }
        } else{
            try{
                result = categoriaRepository.findAll();
            } catch (RepositoryCustomException exception){
                throw ServiceCustomException.createTechnicalException(exception,"Ocurrio un error intente de nuevo");
            }
        }
        result.forEach(value ->{
            CategoriaDomain categoriaDomain = new CategoriaDomain();
            BeanUtils.copyProperties(value,categoriaDomain);
            convertResult.add(categoriaDomain);
        });
        return convertResult;
    }
}
