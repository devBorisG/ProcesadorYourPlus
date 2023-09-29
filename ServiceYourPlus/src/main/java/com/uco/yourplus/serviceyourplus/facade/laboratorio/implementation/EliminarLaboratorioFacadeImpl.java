package com.uco.yourplus.serviceyourplus.facade.laboratorio.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.dtoyourplus.LaboratorioDTO;
import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.facade.laboratorio.EliminarLaboratorioFacade;
import com.uco.yourplus.serviceyourplus.usecase.laboratorio.EliminarLaboratorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EliminarLaboratorioFacadeImpl implements EliminarLaboratorioFacade {

    @Autowired
    private EliminarLaboratorio eliminarLaboratorio;
    @Override
    public void execute(LaboratorioDTO dto) {
        try {
            LaboratorioDomain laboratorioDomain = new LaboratorioDomain();
            BeanUtils.copyProperties(dto, laboratorioDomain);
            eliminarLaboratorio.execute(laboratorioDomain);
        }catch (ServiceCustomException serviceCustomException) {
            // Si ocurre una excepción personalizada, se relanza tal cual.
            throw serviceCustomException;
        } catch (Exception exception) {
            // Si ocurre otra excepción, se crea una ServiceCustomException con un mensaje personalizado.
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrió un error eliminando la persona: " + exception.getMessage());
        }

    }
}
