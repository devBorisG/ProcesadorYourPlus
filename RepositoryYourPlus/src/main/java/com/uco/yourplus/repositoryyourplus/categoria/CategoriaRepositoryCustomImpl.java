package com.uco.yourplus.repositoryyourplus.categoria;

import com.uco.yourplus.crosscuttingyourplus.exceptions.repository.RepositoryCustomException;
import com.uco.yourplus.crosscuttingyourplus.helper.StringHelper;
import com.uco.yourplus.entityyourplus.CategoriaEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoriaRepositoryCustomImpl implements CategoriaRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CategoriaEntity> findCustom(CategoriaEntity categoriaEntity) {
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<CategoriaEntity> query = criteriaBuilder.createQuery(CategoriaEntity.class);
            Root<CategoriaEntity> categoriaEntityRoot = query.from(CategoriaEntity.class);
            List<Predicate> predicates = new ArrayList<>();

            if(Objects.isNull(categoriaEntity)){
                if(!StringHelper.isEmpty(categoriaEntity.getNombre())){
                    predicates.add(criteriaBuilder.equal(categoriaEntityRoot.get("nombre"),categoriaEntity.getNombre()));
                }
                if(!StringHelper.isEmpty(categoriaEntity.getDescripcion())){
                    predicates.add(criteriaBuilder.equal(categoriaEntityRoot.get("descripcion"),categoriaEntity.getDescripcion()));
                }
            }
            query.select(categoriaEntityRoot).where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
            return entityManager.createQuery(query).getResultList();
        } catch(Exception exception){
            throw RepositoryCustomException.createTechnicalException(exception,"ocurrio un error listando las categorias");
        }
    }
}
