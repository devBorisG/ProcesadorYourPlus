package com.uco.yourplus.serviceyourplus.usecase.laboratorio.implementation;

import com.uco.yourplus.repositoryyourplus.laboratorio.LaboratorioRepository;
import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.usecase.laboratorio.EliminarLaboratorio;
import org.springframework.beans.factory.annotation.Autowired;

public class EliminarLaboratorioImpl implements EliminarLaboratorio {
    @Autowired
    private LaboratorioRepository laboratorioRepository;


    @Override
    public void execute(LaboratorioDomain domain) {

    }
}
