package org.example.controller.api;


import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import org.example.dto.customer.CustomerSummaryDto;
import org.example.dto.store.StoreDto;
import org.example.dto.store.StoreSummaryDto;
import org.example.mapper.store.StoreMapper;
import org.example.repository.StoreRepository;
import org.example.service.StoreService;

import java.util.List;
import java.util.Optional;

@WebService(name = "StoreService")
public class StoreController {

    private final StoreService service = new StoreService(
            new StoreRepository(), StoreMapper.INSTANCE
    );

    @WebMethod(operationName = "getAllStores")
    @WebResult(name = "stores")
    public List<StoreSummaryDto> getAllStores() {
        return service.getStoreSummaries();
    }

    @WebMethod(operationName = "getStoreById")
    @WebResult(name = "store")
    public StoreSummaryDto getStoreById(@WebParam(name = "id") Integer id) {
        Optional<StoreSummaryDto> optionalStore = Optional.ofNullable(service.getStoreSummaryById(id));
        if (optionalStore.isPresent()) {
            return optionalStore.get();
        } else {
            throw new WebServiceException("Store with id " + id + " not found");
        }
    }

    @WebMethod(operationName = "createStore")
    @WebResult(name = "storeAdded")
    public StoreDto createStore(@WebParam(name = "store") StoreSummaryDto storeSummaryDto) {
        Optional<StoreDto> optionalStoreDto = Optional.ofNullable(service.createStore(storeSummaryDto));
        if (optionalStoreDto.isPresent()) {
            return optionalStoreDto.get();
        } else {
            throw new WebServiceException("Can't create store");
        }
    }

    @WebMethod(operationName = "deleteStoreById")
    @WebResult(name = "storeDeleted")
    public void deleteStoreById(@WebParam(name = "id") Integer id) {
        service.deleteById(id);
    }

    @WebMethod(operationName = "getAllCustomersByStoreId")
    @WebResult(name = "customers")
    public List<CustomerSummaryDto> getAllCustomersByStoreId(@WebParam(name = "storeId") Integer storeId) {
        return service.getAllCustomersByStoreId(storeId);
    }
}
