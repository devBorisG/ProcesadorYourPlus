package com.uco.yourplus.repositoryyourplus.laboratorio;

import com.uco.yourplus.entityyourplus.LaboratorioEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaboratorioRepositoryCustom {
    List<LaboratorioEntity> findCustom(LaboratorioEntity laboratorioEntity);
}
