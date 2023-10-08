package com.uco.yourplus.repositoryyourplus.producto;

import com.uco.yourplus.crosscuttingyourplus.exceptions.repository.RepositoryCustomException;
import com.uco.yourplus.crosscuttingyourplus.helper.StringHelper;
import com.uco.yourplus.entityyourplus.ProductoEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductoRepositoryCustomImpl implements ProductoRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProductoEntity> findCustom(ProductoEntity productoEntity) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<ProductoEntity> query = criteriaBuilder.createQuery(ProductoEntity.class);
            Root<ProductoEntity> productosEntityRoot = query.from(ProductoEntity.class);
            List<Predicate> predicates = new ArrayList<>();

            if (!Objects.isNull(productoEntity)) {
                if (!StringHelper.isEmpty(productoEntity.getNombre())) {
                    predicates.add(criteriaBuilder.equal(productosEntityRoot.get("nombre"), productoEntity.getNombre()));
                }
                if (productoEntity.getPrecio() > 0) {
                    predicates.add(criteriaBuilder.equal(productosEntityRoot.get("precio"), productoEntity.getPrecio()));
                }
                if (!StringHelper.isEmpty(productoEntity.getDescripcion())) {
                    predicates.add(criteriaBuilder.equal(productosEntityRoot.get("descripcion"), productoEntity.getDescripcion()));
                }
                if (!StringHelper.isEmpty(productoEntity.getImagen())) {
                    predicates.add(criteriaBuilder.equal(productosEntityRoot.get("imagen"), productoEntity.getImagen()));
                }
            }

            query.select(productosEntityRoot).where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
            return entityManager.createQuery(query).getResultList();
        } catch (Exception exception) {
            throw RepositoryCustomException.createTechnicalException(exception, "Ocurrió un error creando el query para la consulta personalizada");
        }
    }

}

