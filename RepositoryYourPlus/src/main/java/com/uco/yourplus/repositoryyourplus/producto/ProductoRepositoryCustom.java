package com.uco.yourplus.repositoryyourplus.producto;

import com.uco.yourplus.entityyourplus.ProductoEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepositoryCustom {
    List<ProductoEntity> findCustom(ProductoEntity productoEntity);
}
