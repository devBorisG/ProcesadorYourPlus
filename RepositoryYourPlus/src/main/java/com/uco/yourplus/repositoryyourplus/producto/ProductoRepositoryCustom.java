package com.uco.yourplus.repositoryyourplus.producto;

import com.uco.yourplus.entityyourplus.ProductoEntity;

import java.util.List;

public interface ProductoRepositoryCustom {

    List<ProductoEntity> findCustom(ProductoEntity productoEntity);

}
