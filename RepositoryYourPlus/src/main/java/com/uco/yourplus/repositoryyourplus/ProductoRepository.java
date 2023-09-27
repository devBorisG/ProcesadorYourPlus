package com.uco.yourplus.repositoryyourplus;

import com.uco.yourplus.entityyourplus.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, UUID> {
}
