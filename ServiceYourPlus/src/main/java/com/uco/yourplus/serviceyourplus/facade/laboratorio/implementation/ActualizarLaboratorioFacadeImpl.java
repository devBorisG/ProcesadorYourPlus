package com.uco.yourplus.serviceyourplus.facade.laboratorio.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.dtoyourplus.LaboratorioDTO;
import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.facade.laboratorio.ActualizarLaboratorioFacada;
import com.uco.yourplus.serviceyourplus.usecase.laboratorio.ActualizarLaboratorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class ActualizarLaboratorioFacadeImpl implements ActualizarLaboratorioFacada {
    @Autowired
    private ActualizarLaboratorio actualizarLaboratorio;
    @Override
    public void execute(UUID id, LaboratorioDTO patch) {
        try {
            LaboratorioDomain laboratorioDomain = new LaboratorioDomain();
            BeanUtils.copyProperties(patch, laboratorioDomain);
            actualizarLaboratorio.execute(id, laboratorioDomain);
        }catch (ServiceCustomException exception) {
            throw exception;
        } catch (Exception exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrió un error intentando ejecutar el caso de uso: " + exception.getMessage());
        }

    }
}
