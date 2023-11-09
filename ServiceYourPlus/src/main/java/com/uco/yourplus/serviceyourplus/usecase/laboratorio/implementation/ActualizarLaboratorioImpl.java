package com.uco.yourplus.serviceyourplus.usecase.laboratorio.implementation;


import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.entityyourplus.LaboratorioEntity;
import com.uco.yourplus.repositoryyourplus.LaboratorioRepository;
import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.usecase.laboratorio.ActualizarLaboratorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class ActualizarLaboratorioImpl implements ActualizarLaboratorio {

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    @Override
    public void execute(UUID id, LaboratorioDomain patch) {
        try {
            Optional<LaboratorioEntity> laboratorioEntity = laboratorioRepository.findById(id);
            if (laboratorioEntity.isPresent()) {
                LaboratorioEntity laboratorio = laboratorioEntity.get();
                BeanUtils.copyProperties(patch, laboratorio);
                laboratorioRepository.save(laboratorio);
            } else {
                throw new EntityNotFoundException("El Laboratorio no existe");
            }
        } catch (Exception exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrió un error inesperado al actualizar la entidad: " + exception.getMessage());
        }
    }
}
