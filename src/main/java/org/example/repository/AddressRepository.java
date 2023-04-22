package org.example.repository;

import org.example.model.Address;
import org.example.util.Database;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public class AddressRepository extends BaseRepository<Address,Integer>{

    public AddressRepository() {
        super(Address.class);
    }

    public Address getAddressByCityDetails(String address,String phone){
        return Database.doInTransaction(
                entityManager -> {
                    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
                    CriteriaQuery<Address> query = criteriaBuilder.createQuery(Address.class);
                    Root<Address> root = query.from(Address.class);
                    List<Predicate> predicates = new ArrayList<>();
                    if (address != null) {
                        predicates.add(criteriaBuilder.equal(root.get("address"), address));
                    }
                    if (phone != null) {
                        predicates.add(criteriaBuilder.equal(root.get("phone"), phone));
                    }
                    query.select(root).distinct(true).where(predicates.toArray(new Predicate[0]));
                    TypedQuery<Address> typedQuery = entityManager.createQuery(query);
                    return typedQuery.getResultList().get(0);
                }
        );
    }
}
