package com.uco.yourplus.repositoryyourplus;

import com.uco.yourplus.entityyourplus.CategoriaEntity;
import com.uco.yourplus.repositoryyourplus.categoria.CategoriaRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, UUID>, CategoriaRepositoryCustom {
}
