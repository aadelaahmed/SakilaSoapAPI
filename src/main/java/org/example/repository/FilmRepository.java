package org.example.repository;

import org.example.model.*;
import org.example.util.Database;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FilmRepository extends BaseRepository<Film, Integer> {
    public FilmRepository() {
        super(Film.class);
    }

    public Film searchFilmByTitle(String title) {
        try {
            return Database.doInTransaction(entityManager -> {
                CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
                CriteriaQuery<Film> query = criteriaBuilder.createQuery(Film.class);
                Root<Film> root = query.from(Film.class);
                query.select(root).where(criteriaBuilder.equal(root.get("title"), title));
                TypedQuery<Film> typedQuery = entityManager.createQuery(query);
                return typedQuery.getSingleResult();
            });
        } catch (NoResultException e) {
            return null;
        }
    }

    /*public List<Film> searchFilms(Integer releaseYear,  List<Integer> categoryIds) {
        return Database.doInTransaction(
                entityManager -> {
                    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
                    CriteriaQuery<Film> query = criteriaBuilder.createQuery(Film.class);
                    Root<Film> root = query.from(Film.class);
                    List<Predicate> predicates = new ArrayList<>();
                    if (releaseYear != null) {
                        predicates.add(criteriaBuilder.equal(root.get("releaseYear"), releaseYear));
                    }
                    if (categoryIds != null && !categoryIds.isEmpty()) {
                        Join<Film, FilmCategory> joinCategory = root.join("filmCategories").join("category");
                        predicates.add(joinCategory.get("categoryId").in(categoryIds));
                    }
                    query.select(root).distinct(true).where(predicates.toArray(new Predicate[0]));
                    TypedQuery<Film> typedQuery = entityManager.createQuery(query);
                    return typedQuery.getResultList();
                }
        );
    }*/

    public List<Film> findFilmsByActorId(Integer actorId) {
        return Database.doInTransaction(
                entityManager -> {
                    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
                    CriteriaQuery<Film> cq = cb.createQuery(Film.class);
                    Root<Film> film = cq.from(Film.class);
                    Join<Film, FilmActor> filmActor = film.join("filmActors");
                    Predicate actorIdPredicate = cb.equal(filmActor.get("actor").get("id"), actorId);
                    cq.where(actorIdPredicate);
                    cq.distinct(true);
                    return entityManager.createQuery(cq).getResultList();
                }
        );
    }

    public List<Film> findFilmsByCategoryId(Integer categoryId) {
        return Database.doInTransaction(
                entityManager -> {
                    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
                    CriteriaQuery<Film> cq = cb.createQuery(Film.class);
                    Root<Film> film = cq.from(Film.class);
                    Join<Film, FilmCategory> filmCategory = film.join("filmCategories");
                    Predicate categoryIdPredicate = cb.equal(filmCategory.get("category").get("id"), categoryId);
                    cq.where(categoryIdPredicate);
                    cq.distinct(true);
                    return entityManager.createQuery(cq).getResultList();
                }
        );
    }



}
