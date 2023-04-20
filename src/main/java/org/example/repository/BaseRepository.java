package org.example.repository;

import jakarta.xml.ws.WebServiceException;
import org.example.mapper.BaseMapper;
import org.example.model.Actor;
import org.example.util.Database;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepository<E extends Serializable,ID> {
    private final Class<E> entityType;
    public BaseRepository(Class<E> entityClass) {
        this.entityType = entityClass;
    }

    public Optional<E> getById(ID id) {
        E entity = Database.doInTransaction(em -> em.find(entityType, id));
        return Optional.ofNullable(entity);
    }


    public Optional<E> getByName(String attributeName, String name) {
        return Database.doInTransaction(em -> {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<E> query = cb.createQuery(entityType);
            Root<E> root = query.from(entityType);
            query.select(root).where(cb.equal(root.get(attributeName), name));
            TypedQuery<E> typedQuery = em.createQuery(query);
            List<E> resultList = typedQuery.getResultList();
            return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
        });
    }


    public E save(E entity) {
        return Database.doInTransaction(em -> {
            em.persist(entity);
            return entity;
        });
    }
    public boolean isExist(ID id){
        return Database.doInTransaction(
                entityManager -> {
                    E entity = entityManager.find(entityType,id);
                    if (entity == null)
                        return false;
                    else
                        return true;
                }
        );
    }
    public List<E> getAll(EntityManager entityManager) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> query = builder.createQuery(entityType);
        Root<E> root = query.from(entityType);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }

/*   public boolean update(E entity) {
       return Database.doInTransaction(
               entityManager -> {
                   Optional<E> optionalEntity = Optional.ofNullable(entityManager.find(getEntityClass(), id));
                   if (optionalEntity.isPresent()) {
                       E entity = optionalEntity.get();
                       baseMapper.partialUpdate(entity, dto);
                       return mapper.toDto(entityManager.merge(entity));
//                        return mapper.toDto(repository.update(entity));
                   } else {
                       throw new EntityNotFoundException("Can't get "+getEntityClass().getSimpleName()+" with id: " + id);
                   }
               }
       );
        
//        return Database.doInTransaction(em -> em.merge(entity));
    }*/

    public void delete(E entity) {
        Database.doInTransactionWithoutResult(em -> em.remove(entity));
    }
    public boolean deleteById(ID id) {
        return Database.doInTransaction(em -> {
            E entity = em.find(entityType, id);
            if (entity != null) {
                em.remove(entity);
                return true;
            }else
                throw new WebServiceException("There is no "+entityType.getSimpleName()+" with this id");
        });
    }


    /*public <D> boolean update(ID id, D dto) {
        return Database.doInTransaction(
                entityManager -> {
                    Optional<E> optionalEntity = Optional.ofNullable(entityManager.find(entityType, id));
                    if (optionalEntity.isPresent()) {
                        E entity = optionalEntity.get();
                        //baseMapper.partialUpdate(entity, dto);
                        return true;
                        //return baseMapper.toDto(entityManager.merge(entity));
//                        return mapper.toDto(repository.update(entity));
                    } else {
                        throw new EntityNotFoundException("Can't get "+entityType.getSimpleName()+" with id: " + id);
                    }
                }
        );
    }*/
}
