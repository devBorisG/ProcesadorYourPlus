package com.uco.yourplus.serviceyourplus.usecase.laboratorio.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.repository.RepositoryCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.entityyourplus.LaboratorioEntity;
import com.uco.yourplus.repositoryyourplus.laboratorio.LaboratorioRepository;
import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.usecase.laboratorio.ConsultarLaboratorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ConsultarLaboratorioImpl implements ConsultarLaboratorio {

    @Autowired
    private LaboratorioRepository laboratorioRepository;
    @Override
    public List<LaboratorioDomain> execute(Optional<LaboratorioDomain> domain) {
        List<LaboratorioEntity> laboratorioEntities;
        List<LaboratorioDomain> laboratorioDomains = new ArrayList<>();
        if (domain.isPresent()){
            LaboratorioEntity laboratorioEntity = new LaboratorioEntity();
            BeanUtils.copyProperties(domain.get(), laboratorioEntity);
            try {
                laboratorioEntities = laboratorioRepository.findCustom(laboratorioEntity);
            }catch (RepositoryCustomException e){
                throw ServiceCustomException.createTechnicalException(e, "no funca x2");
            }
        }else{
            try {
                laboratorioEntities = laboratorioRepository.findAll();
            }catch (RepositoryCustomException e){
                throw ServiceCustomException.createTechnicalException(e, "no funca x2");
            }
        }
        laboratorioEntities.forEach(value ->{
            LaboratorioDomain laboratorioDomain = new LaboratorioDomain();
            BeanUtils.copyProperties(value, laboratorioDomain);
            laboratorioDomains.add(laboratorioDomain);

        });
        return laboratorioDomains;
    }
}
