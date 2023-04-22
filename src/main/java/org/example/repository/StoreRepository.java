package org.example.repository;

import jakarta.xml.ws.WebServiceException;
import org.example.dto.customer.CustomerSummaryDto;
import org.example.dto.staff.StaffSummaryDto;
import org.example.dto.store.StoreDto;
import org.example.dto.store.StoreSummaryDto;
import org.example.exception.EntityAlreadyExistException;
import org.example.mapper.customer.CustomerSummaryMapper;
import org.example.mapper.staff.StaffSummaryMapper;
import org.example.mapper.store.StoreMapper;
import org.example.mapper.store.StoreSummaryMapper;
import org.example.model.*;
import org.example.util.Database;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StoreRepository extends BaseRepository<Store, Integer> {
    StoreSummaryMapper storeSummaryMapper;
    CustomerSummaryMapper customerSummaryMapper;
    AddressRepository addressRepository;
    StaffRepository staffRepository;
    StoreMapper storeMapper;
    StaffSummaryMapper staffSummaryMapper;

    public StoreRepository() {
        super(Store.class);
        this.storeSummaryMapper = StoreSummaryMapper.INSTANCE;
        this.customerSummaryMapper = CustomerSummaryMapper.INSTANCE;
        this.addressRepository = new AddressRepository();
        this.staffRepository = new StaffRepository();
        this.storeMapper = StoreMapper.INSTANCE;
        this.staffSummaryMapper = StaffSummaryMapper.INSTANCE;
    }


    public List<StoreSummaryDto> getStoreSummaries() {
        return Database.doInTransaction(
                entityManager -> {
                    List<Store> stores = getAll(entityManager);
                    return storeSummaryMapper.toDto(stores);
                }
        );
    }

    public StoreSummaryDto getStoreSummaryById(Integer id) {
        return Database.doInTransaction(
                entityManager -> {
                    Store store = entityManager.find(Store.class, id);
                    if (store == null)
                        throw new WebServiceException("Can't find store with id: " + id);
                    return storeSummaryMapper.toDto(store);
                }
        );
    }

    public List<CustomerSummaryDto> getAllCustomersByStoreId(Integer storeId) {
        return Database.doInTransaction(
                entityManager -> {
                    Store store = entityManager.find(Store.class, storeId);
                    return customerSummaryMapper.toDto(new ArrayList<>(store.getCustomers()));
                }
        );
    }

    public StoreDto createStore(StoreSummaryDto storeSummaryDto) {
        return Database.doInTransaction(
                entityManager -> {
                    //we need to check first there is no store in this address, fetch store object using address specifics.
                    boolean isExist = isExistUsingAddress(storeSummaryDto.getAddress(),storeSummaryDto.getCity(),storeSummaryDto.getPhone());
                    if (isExist)
                        throw new EntityAlreadyExistException("Store is already existed with these address specefications ");
                    Store store = new Store();
                    store.setLastUpdate(Instant.now());
                    //first, we need to get address using postalcode,city,addressDescription in storeSummaryDto
                    Address address = addressRepository.getAddressByCityDetails(storeSummaryDto.getAddress(), storeSummaryDto.getPhone());
                    if (address == null)
                        throw new WebServiceException("Can't find address with these specifications");
                    //second,we need to get managerStaff using first name and last name
                    //check on returned objects values
                    System.out.println("returned address ->"+address.toString());
                    Staff staffManager = staffRepository.getStaffManagerByName(storeSummaryDto.getManagerFirstName(), storeSummaryDto.getManagerLastName());
                    //we need to check first this staff member is not a manager in another store.
                    if (staffManager.getStoreMng() != null)
                        throw new EntityAlreadyExistException("This staff member is already a manager in store with id: "+staffManager.getStoreMng().getId());
                    System.out.println("return staff manager ->"+staffManager.getId());
                    store.setManagerStaff(staffManager);
                    store.setAddress(address);
                    store = entityManager.merge(store);
                    return storeMapper.toDto(store);
                }
        );
    }

    private boolean isExistUsingAddress(String address,String city,String phone) {
        return Database.doInTransaction(
                entityManager -> {
                    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
                    CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class); // using Long return type for count query
                    Root<Store> root = query.from(Store.class);
                    Predicate addressPredicate = criteriaBuilder.equal(root.get("address").get("address"), address);
                    Predicate cityPredicate = criteriaBuilder.equal(root.get("address").get("city").get("city"), city);
                    Predicate phonePredicate = criteriaBuilder.equal(root.get("address").get("phone"), phone);
                    query.select(criteriaBuilder.count(root));
                    query.where(criteriaBuilder.and(addressPredicate, cityPredicate, phonePredicate));
                    Long count = entityManager.createQuery(query).getSingleResult();
                    return count > 0;
                }
        );

    }

    public List<StaffSummaryDto> getAllStaffsByStoreId(Integer storeId) {
        return Database.doInTransaction(
                entityManager -> {
                    Store store = entityManager.find(Store.class, storeId);
                    return staffSummaryMapper.toDto(new ArrayList<>(store.getStaff()));
                }
        );
    }
}