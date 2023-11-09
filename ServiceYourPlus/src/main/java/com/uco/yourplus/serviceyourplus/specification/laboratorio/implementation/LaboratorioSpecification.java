package com.uco.yourplus.serviceyourplus.specification.laboratorio.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.dtoyourplus.LaboratorioDTO;
import com.uco.yourplus.repositoryyourplus.LaboratorioRepository;
import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.specification.laboratorio.SpecificationLaboratorio;
import com.uco.yourplus.serviceyourplus.usecase.laboratorio.ConsultarLaboratorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.uco.yourplus.crosscuttingyourplus.helper.StringHelper.isEmpty;
import static com.uco.yourplus.crosscuttingyourplus.helper.StringHelper.isOnlyWordsAndSpace;
import static com.uco.yourplus.crosscuttingyourplus.helper.UUIDHelper.isDefaultUUID;
import static com.uco.yourplus.dtoyourplus.builder.laboratorio.LaboratorioDTOBuilder.getLaboratorioDTOBuilder;


@Service
public class LaboratorioSpecification implements SpecificationLaboratorio {

    @Autowired
    LaboratorioRepository laboratorioRepository;

    @Autowired
    ConsultarLaboratorio consultarLaboratorio;

    @Override
    public void isSatisfied(LaboratorioDomain data) {
        verifyUserIntegrity(data);
    }

    public void verifyUserIntegrity(LaboratorioDomain laboratorioDomain) {
        if (laboratorioRepository.findById(laboratorioDomain.getId()).isPresent()) {
            throw ServiceCustomException.createUserException("El usuario ya existe");
        }
        if (!verifyMandatoryPersonaAttributes(laboratorioDomain)) {
            throw ServiceCustomException.createUserException("No pueden haber campos vacios");
        }
        if (!verifyIsLetters(laboratorioDomain)) {
            throw ServiceCustomException.createUserException("No son letricas");
        }
        if (!verifyNombreDoesNotExist(laboratorioDomain.getNombre())) {
            throw ServiceCustomException.createUserException("ya te registraste prro");
        }
    }

    private boolean verifyMandatoryPersonaAttributes(LaboratorioDomain laboratorioDomain) {
        return isDefaultUUID(laboratorioDomain.getId()) || isEmpty(laboratorioDomain.getNombre()) || isEmpty(laboratorioDomain.getDescripcion());
    }

    private boolean verifyIsLetters(LaboratorioDomain laboratorioDomain) {
        return isOnlyWordsAndSpace(laboratorioDomain.getNombre() + laboratorioDomain.getDescripcion());
    }

    private boolean verifyNombreDoesNotExist(String nombre) {
        LaboratorioDTO dto = getLaboratorioDTOBuilder().build();
        LaboratorioDomain laboratorioDomain = new LaboratorioDomain();
        dto.setNombre(nombre);
        BeanUtils.copyProperties(dto, laboratorioDomain);
        return consultarLaboratorio.execute(Optional.of(laboratorioDomain)).isEmpty();
    }
}
