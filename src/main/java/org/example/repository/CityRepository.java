package org.example.repository;

import jakarta.xml.ws.WebServiceException;
import org.example.dto.city.CityDto;
import org.example.mapper.city.CityMapper;
import org.example.model.City;
import org.example.util.Database;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

public class CityRepository extends BaseRepository<City,Integer>{
    CityMapper cityMapper;
    public CityRepository() {
        super(City.class);
        this.cityMapper = CityMapper.INSTANCE;
    }

    public CityDto getCityById(Integer id){
        return Database.doInTransaction(
                entityManager -> {
                    City city = entityManager.find(City.class,id);
                    if (city == null)
                        throw new WebServiceException("Can't find city with id: "+id);
                    return cityMapper.toDto(city);
                }
        );
    }

    public Optional<City> getByName(String name) {
        return Database.doInTransaction(em -> {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<City> query = cb.createQuery(City.class);
            Root<City> root = query.from(City.class);
            query.select(root).where(cb.equal(root.get("city"), name));
            TypedQuery<City> typedQuery = em.createQuery(query);
            List<City> resultList = typedQuery.getResultList();
            return Optional.of(resultList.get(0));
        });
    }

}
