package com.uco.yourplus.serviceyourplus.facade.laboratorio.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.crosscuttingyourplus.helper.StringHelper;
import com.uco.yourplus.dtoyourplus.LaboratorioDTO;
import com.uco.yourplus.repositoryyourplus.laboratorio.LaboratorioRepository;
import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.facade.laboratorio.ConsultarLaboratorioFacade;
import com.uco.yourplus.serviceyourplus.usecase.laboratorio.ConsultarLaboratorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ConsultarLaboratorioFacadeImpl implements ConsultarLaboratorioFacade {
    @Autowired
    private ConsultarLaboratorio consultarLaboratorio;
    @Override
    public List<LaboratorioDTO> execute(Optional<LaboratorioDTO> dto) {
        try {
            List<LaboratorioDomain> laboratorioDomains;
            if (dto.isPresent()&&(!Objects.equals(dto.get().getNombre(), StringHelper.EMPTY))) {
                LaboratorioDomain laboratorioDomain = new LaboratorioDomain();
                BeanUtils.copyProperties(dto.get(), laboratorioDomain);
                laboratorioDomains = consultarLaboratorio.execute(Optional.of(laboratorioDomain));
            }else {
                laboratorioDomains =consultarLaboratorio.execute(Optional.empty());
            }
            List<LaboratorioDTO> laboratorioDTOS = new ArrayList<>();
            laboratorioDomains.forEach(value ->{
                LaboratorioDTO laboratorioDTO = new LaboratorioDTO();
                BeanUtils.copyProperties(value, laboratorioDTO);
                laboratorioDTOS.add(laboratorioDTO);

            });
            return laboratorioDTOS;
        }catch (ServiceCustomException serviceCustomException){
            throw serviceCustomException;
        }catch (Exception exception){
            throw  ServiceCustomException.createTechnicalException("Ocurrio un error intentando implementar el caso de uso de registrar usuarios"+":"+exception.getMessage());
        }
    }
}
