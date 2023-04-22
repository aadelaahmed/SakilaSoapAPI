package org.example.repository;

import jakarta.xml.ws.WebServiceException;
import org.example.dto.PaymentDto;
import org.example.dto.customer.CustomerDto;
import org.example.dto.rental.RentalDto;
import org.example.dto.customer.CustomerSummaryDto;
import org.example.dto.rental.RentalSummaryDto;
import org.example.exception.EntityAlreadyExistException;
import org.example.mapper.customer.CustomerMapper;
import org.example.mapper.rental.RentalMapper;
import org.example.mapper.customer.CustomerSummaryMapper;
import org.example.mapper.PaymentMapper;
import org.example.mapper.rental.RentalSummaryMapper;
import org.example.model.*;
import org.example.util.Database;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerRepository extends BaseRepository<Customer, Integer> {
    CustomerMapper customerMapper;
    CustomerSummaryMapper customerSummaryMapper;
    public CustomerRepository() {
        super(Customer.class);
        this.customerMapper = CustomerMapper.INSTANCE;
        this.customerSummaryMapper = CustomerSummaryMapper.INSTANCE;
    }

    public Store getStoreByCustomerId(Integer customerId) {
        //already checked that the customer is existed in db.
        return Database.doInTransaction(entityManager -> entityManager.find(Customer.class,customerId).getStore());
    }

    public List<PaymentDto> getPaymentsByCustomerId(Integer categoryId) {
        return Database.doInTransaction(
                entityManager -> {
                    Category category = entityManager.find(Category.class,categoryId);
                    if (category == null)
                        throw new WebServiceException("Can't get category with id: "+categoryId);
                    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
                    CriteriaQuery<Payment> query = builder.createQuery(Payment.class);
                    Root<Payment> paymentRoot = query.from(Payment.class);
                    Predicate customerIdPredicate = builder.equal(paymentRoot.get("customer").get("id"), categoryId);
                    query.where(customerIdPredicate);
                    List<Payment> payments = entityManager.createQuery(query).getResultList();
                    PaymentMapper paymentMapper = PaymentMapper.INSTANCE;
                    //PaymentMapperImpl paymentMapper1 = new PaymentMapperImpl();
                    return paymentMapper.toDto(payments);
                }
        );
    }

    public List<CustomerSummaryDto> getCustomerSummaries() {
        return Database.doInTransaction(
                entityManager -> {
                    List<Customer> customers = getAll(entityManager);
                    CustomerSummaryMapper customerSummaryMapper = CustomerSummaryMapper.INSTANCE;
                    return customerSummaryMapper.toDto(customers);
                }
        );
    }
    public CustomerSummaryDto getCustomerSummariesById(Integer customerId) {
        return Database.doInTransaction(
                entityManager -> {
                    Customer customer = entityManager.find(Customer.class,customerId);
                    CustomerSummaryMapper customerSummaryMapper = CustomerSummaryMapper.INSTANCE;
                    return customerSummaryMapper.toDto(customer);
                }
        );
    }


    public List<RentalSummaryDto> getRentalsByCustomerId(Integer customerId) {
        return Database.doInTransaction(
                entityManager -> {
                    Customer customer = entityManager.find(Customer.class,customerId);
                    if (customer == null)
                        throw new WebServiceException("Can't get Customer with id: "+customerId);
                    RentalSummaryMapper rentalSummaryMapper = RentalSummaryMapper.INSTANCE;
                    return rentalSummaryMapper.toDto(new ArrayList<>(customer.getRentals()));
                }
        );
    }

    public CustomerSummaryDto createCustomerByEmail(CustomerDto customerDto) {
        return Database.doInTransaction(
                entityManager -> {
                    if (customerDto.getEmail() != null && customerDto != null) {
                        Optional<Customer> optionalEntity = getByName("email",customerDto.getEmail());
                        if (optionalEntity.isPresent()) {
                            throw new EntityAlreadyExistException("Customer is already existed");
                        }
                    }else
                        throw new WebServiceException("Can't create this customer");
                    Customer customer = customerMapper.toEntity(customerDto);
                    //get store first and set it to new created customer
                    Store store = entityManager.find(Store.class,customerDto.getStoreId());
                    if (store==null)
                        throw new WebServiceException("Can't find store with id: "+customerDto.getStoreId());
                    customer.setStore(store);
                    customer = save(customer);
                    return customerSummaryMapper.toDto(customer);
                }
        );
    }

    /*public List<Rental> getRentalsByCustomerId(Integer categoryId) {

    }*/
}
