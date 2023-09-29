package com.uco.yourplus.serviceyourplus.usecase.laboratorio.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.repository.RepositoryCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.entityyourplus.LaboratorioEntity;
import com.uco.yourplus.repositoryyourplus.laboratorio.LaboratorioRepository;
import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.specification.Specification;
import com.uco.yourplus.serviceyourplus.usecase.laboratorio.RegistrarLaboratorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrarLaboratorioImpl implements RegistrarLaboratorio {

    @Autowired
    LaboratorioRepository laboratorioRepository;

    @Autowired
    Specification specification;


    @Override
    public String execute(LaboratorioDomain domain) {
        try {
            specification.isSatisfied(domain);
            LaboratorioEntity laboratorioEntity = new LaboratorioEntity();
            BeanUtils.copyProperties(domain, laboratorioEntity);
            laboratorioRepository.save(laboratorioEntity);
            return ("Registro de el Laboratorio fue exitoso");
        } catch (ServiceCustomException exception) {
            throw exception;
        } catch (RepositoryCustomException exception) {
            throw ServiceCustomException.createTechnicalException(exception, "No se ha logrado ingresar con exito al nuevo usuario, por favor intente de nuevo");
        } catch (Exception e) {
            throw ServiceCustomException.createTechnicalException(e, "Error");
        }
    }
}
