package com.uco.yourplus.repositoryyourplus.categoria;

import com.uco.yourplus.entityyourplus.CategoriaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepositoryCustom {
    List<CategoriaEntity> findCustom(CategoriaEntity categoriaEntity);
}
