package org.example.repository;


import org.example.model.Actor;
import org.example.util.Database;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class ActorRepository extends BaseRepository<Actor, Integer> {
    public ActorRepository() {
        super(Actor.class);
    }

    public List<Actor> findByLastName(String lastName) {
        return Database.doInTransaction(
               entityManager -> {
                   CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
                   CriteriaQuery<Actor> query = criteriaBuilder.createQuery(Actor.class);
                   Root<Actor> root = query.from(Actor.class);
                   query.select(root).where(criteriaBuilder.equal(root.get("lastName"), lastName));
                   TypedQuery<Actor> typedQuery = entityManager.createQuery(query);
                   return typedQuery.getResultList();
               }
        );
    }
}

