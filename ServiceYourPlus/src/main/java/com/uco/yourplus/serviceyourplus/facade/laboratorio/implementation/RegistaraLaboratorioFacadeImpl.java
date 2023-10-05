package com.uco.yourplus.serviceyourplus.facade.laboratorio.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.dtoyourplus.LaboratorioDTO;
import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.facade.laboratorio.RegistrarLaboratorioFacade;
import com.uco.yourplus.serviceyourplus.usecase.laboratorio.RegistrarLaboratorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistaraLaboratorioFacadeImpl implements RegistrarLaboratorioFacade {

    @Autowired
    private RegistrarLaboratorio registrarLaboratorio;
    @Override
    public void execute(LaboratorioDTO dto) {
        try {
            LaboratorioDomain laboratorioDomain = new LaboratorioDomain();
            BeanUtils.copyProperties(dto, laboratorioDomain);
            registrarLaboratorio.execute(laboratorioDomain);
        }catch(ServiceCustomException serviceCustomException){
            throw serviceCustomException;
        } catch(Exception exception){
            throw ServiceCustomException.createTechnicalException(exception,"Ha ocurrido un error registrando el laboratorio");
        }
    }
}
