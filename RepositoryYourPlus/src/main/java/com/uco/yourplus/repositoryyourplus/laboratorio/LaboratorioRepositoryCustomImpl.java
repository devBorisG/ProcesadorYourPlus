package com.uco.yourplus.repositoryyourplus.laboratorio;

import com.uco.yourplus.crosscuttingyourplus.exceptions.repository.RepositoryCustomException;
import com.uco.yourplus.crosscuttingyourplus.helper.StringHelper;
import com.uco.yourplus.entityyourplus.LaboratorioEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LaboratorioRepositoryCustomImpl implements LaboratorioRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<LaboratorioEntity> findCustom(LaboratorioEntity laboratorioEntity) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<LaboratorioEntity> query = criteriaBuilder.createQuery(LaboratorioEntity.class);
            Root<LaboratorioEntity> laboratorioEntityRoot = query.from(LaboratorioEntity.class);
            List<Predicate> predicates = new ArrayList<>();
            if (!Objects.isNull(laboratorioEntity)) {
                if (!StringHelper.isEmpty(laboratorioEntity.getNombre())) {
                    predicates.add((criteriaBuilder.equal(laboratorioEntityRoot.get("nombre"), laboratorioEntity.getNombre())));
                }
                if (!StringHelper.isEmpty(laboratorioEntity.getDescripcion())) {
                    predicates.add(criteriaBuilder.equal(laboratorioEntityRoot.get("descripcion"), laboratorioEntity.getDescripcion()));
                }
            }
            query.select(laboratorioEntityRoot).where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
            return entityManager.createQuery(query).getResultList();
        } catch (Exception exception) {
            throw RepositoryCustomException.createTechnicalException(exception, "Ocurrio un error crenado el query para la consulta customizada");
        }
    }
}

