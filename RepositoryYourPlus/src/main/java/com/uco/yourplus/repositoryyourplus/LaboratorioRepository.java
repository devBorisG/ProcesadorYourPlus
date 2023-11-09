package com.uco.yourplus.repositoryyourplus;

import com.uco.yourplus.entityyourplus.LaboratorioEntity;
import com.uco.yourplus.repositoryyourplus.laboratorio.LaboratorioRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LaboratorioRepository extends JpaRepository<LaboratorioEntity, UUID>, LaboratorioRepositoryCustom {

}
