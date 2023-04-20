package org.example.service;

import org.example.dto.ActorDto;
import org.example.dto.customer.CustomerSummaryDto;
import org.example.dto.store.StoreDto;
import org.example.dto.store.StoreSummaryDto;
import org.example.mapper.ActorMapper;
import org.example.mapper.store.StoreMapper;
import org.example.model.Actor;
import org.example.model.Store;
import org.example.repository.ActorRepository;
import org.example.repository.StoreRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public class StoreService extends BaseService<Store, StoreDto> {
    StoreRepository storeRepository;
    StoreMapper storeMapper;
    public StoreService(StoreRepository storeRepository, StoreMapper storeMapper) {
        super(storeRepository, storeMapper);
        this.storeRepository = storeRepository;
        this.storeMapper = storeMapper;
    }

    @Override
    protected Class<Store> getEntityClass() {
        return Store.class;
    }

    @Override
    protected Class<StoreDto> getDtoClass() {
        return StoreDto.class;
    }

    public List<StoreSummaryDto> getStoreSummaries() {
        List<StoreSummaryDto> summaries = storeRepository.getStoreSummaries();
        return summaries;
    }
    public StoreSummaryDto getStoreSummaryById(Integer id){
        return storeRepository.getStoreSummaryById(id);
    }

    public List<CustomerSummaryDto> getAllCustomersByStoreId(Integer storeId) {
        boolean isStoreExist = storeRepository.isExist(storeId);
        if (!isStoreExist)
            throw new EntityNotFoundException("Can't find store with id: "+storeId);
        List<CustomerSummaryDto> customers = storeRepository.getAllCustomersByStoreId(storeId);
        return customers;
    }
}
