package com.uco.yourplus.serviceyourplus.usecase.laboratorio.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.repository.RepositoryCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.entityyourplus.LaboratorioEntity;
import com.uco.yourplus.repositoryyourplus.laboratorio.LaboratorioRepository;
import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.usecase.laboratorio.EliminarLaboratorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EliminarLaboratorioImpl implements EliminarLaboratorio {
    @Autowired
    private LaboratorioRepository laboratorioRepository;

    @Autowired
    private ConsultarLaboratorio consultarLaboratorio;


    @Override
    public void execute(LaboratorioDomain domain) {
        try {
            Optional<LaboratorioDomain> laboratorioDomain =consultarLaboratorio.execute(Optional.of(domain)).stream().findFirst();
            if (laboratorioDomain.isPresent()){
                LaboratorioEntity laboratorioEntity = new LaboratorioEntity();
                BeanUtils.copyProperties(laboratorioDomain.get(), domain);
                laboratorioRepository.delete(laboratorioEntity);
            }else {
                throw ServiceCustomException.createTechnicalException("No se ha logrado encontrar el laboratorio.");
            }
        }catch (ServiceCustomException exception){
            throw exception;
        } catch (RepositoryCustomException exception){
            throw ServiceCustomException.createTechnicalException("No se ha logrado consultar el repositorio.");
        } catch (Exception e){
            throw ServiceCustomException.createTechnicalException("Ha ocurrido un error inesperado durante la eliminación.");
        }
    }
}
