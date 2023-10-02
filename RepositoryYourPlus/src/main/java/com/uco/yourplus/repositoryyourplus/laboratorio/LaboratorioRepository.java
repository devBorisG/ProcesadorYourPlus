package com.uco.yourplus.repositoryyourplus.laboratorio;

import com.uco.yourplus.entityyourplus.LaboratorioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LaboratorioRepository extends JpaRepository<LaboratorioEntity, UUID>,LaboratorioRepositoryCustom {

}
