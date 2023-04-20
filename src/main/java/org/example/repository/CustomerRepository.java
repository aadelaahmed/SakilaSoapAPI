package org.example.repository;

import org.example.dto.PaymentDto;
import org.example.dto.rental.RentalDto;
import org.example.dto.customer.CustomerSummaryDto;
import org.example.mapper.rental.RentalMapper;
import org.example.mapper.customer.CustomerSummaryMapper;
import org.example.mapper.PaymentMapper;
import org.example.model.*;
import org.example.util.Database;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository extends BaseRepository<Customer, Integer> {
    public CustomerRepository() {
        super(Customer.class);
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
                        throw new EntityNotFoundException("Can't get category with id: "+categoryId);
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

    public List<RentalDto> getRentalsByCustomerId(Integer customerId) {
        return Database.doInTransaction(
                entityManager -> {
                    Customer customer = entityManager.find(Customer.class,customerId);
                    if (customer == null)
                        throw new EntityNotFoundException("Can't get Customer with id: "+customerId);
                    RentalMapper rentalMapper = RentalMapper.INSTANCE;
                    return rentalMapper.toDto(new ArrayList<>(customer.getRentals()));
                    /*CriteriaBuilder builder = entityManager.getCriteriaBuilder();
                    CriteriaQuery<Rental> query = builder.createQuery(Rental.class);
                    Root<Rental> rentalRoot = query.from(Rental.class);
                    Predicate customerIdPredicate = builder.equal(rentalRoot.get("customer").get("id"), customerId);
                    query.where(customerIdPredicate);
                    List<Rental> payments = entityManager.createQuery(query).getResultList();
                    return rentalMapper.toDto(payments);*/
                }
        );
    }

    /*public List<Rental> getRentalsByCustomerId(Integer categoryId) {

    }*/
}
