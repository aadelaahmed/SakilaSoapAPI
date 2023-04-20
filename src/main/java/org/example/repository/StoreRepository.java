package org.example.repository;

import org.example.dto.customer.CustomerSummaryDto;
import org.example.dto.store.StoreSummaryDto;
import org.example.mapper.customer.CustomerSummaryMapper;
import org.example.mapper.store.StoreSummaryMapper;
import org.example.model.Actor;
import org.example.model.Customer;
import org.example.model.Store;
import org.example.util.Database;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StoreRepository extends BaseRepository<Store, Integer> {
    StoreSummaryMapper storeSummaryMapper;
    CustomerSummaryMapper customerSummaryMapper;
    public StoreRepository() {
        super(Store.class);
        this.storeSummaryMapper = StoreSummaryMapper.INSTANCE;
        this.customerSummaryMapper = CustomerSummaryMapper.INSTANCE;
    }


    public List<StoreSummaryDto> getStoreSummaries() {
        return Database.doInTransaction(
                entityManager -> {
                    List<Store> stores = getAll(entityManager);

                    return storeSummaryMapper.toDto(stores);
                }
        );
    }

    public StoreSummaryDto getStoreSummaryById(Integer id){
        return Database.doInTransaction(
                entityManager -> {
                    Store store = entityManager.find(Store.class, id);
                    return storeSummaryMapper.toDto(store);
                }
        );
    }

    public List<CustomerSummaryDto> getAllCustomersByStoreId(Integer storeId) {
        return Database.doInTransaction(
                entityManager -> {
                     Store store = entityManager.find(Store.class,storeId);
                     return customerSummaryMapper.toDto(new ArrayList<>(store.getCustomers()));
                }
        );
    }
}