package org.example.service.customer;

import jakarta.xml.ws.WebServiceException;
import org.example.dto.rental.RentalDto;
import org.example.dto.customer.CustomerDto;
import org.example.dto.PaymentDto;
import org.example.dto.customer.CustomerSummaryDto;
import org.example.dto.rental.RentalSummaryDto;
import org.example.mapper.customer.CustomerMapper;
import org.example.mapper.PaymentMapper;
import org.example.mapper.customer.CustomerSummaryMapper;
import org.example.model.Customer;
import org.example.repository.*;
import org.example.service.BaseService;
import org.example.util.Database;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public class CustomerService extends BaseService<Customer, CustomerDto> {
    CustomerRepository customerRepository;
    CustomerMapper customerMapper;
    PaymentMapper paymentMapper;
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        super(customerRepository, customerMapper);
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.paymentMapper = PaymentMapper.INSTANCE;
    }

    @Override
    protected Class<Customer> getEntityClass() {
        return Customer.class;
    }

    @Override
    protected Class<CustomerDto> getDtoClass() {
        return CustomerDto.class;
    }
    public CustomerDto getCustomerById(Integer id) {
        Optional<Customer> optionalCustomer = customerRepository.getById(id);
        if (!optionalCustomer.isPresent())
            throw new WebServiceException("Can't fetch customer with id: " + id);
        else {
            System.out.println("get the customer object successfully in customer service -> " + optionalCustomer.get());
            return customerMapper.toDto(optionalCustomer.get());
        }
    }
    public List<CustomerSummaryDto> getAllCustomers() {
        List<CustomerSummaryDto> summaries = customerRepository.getCustomerSummaries();
        return summaries;
    }
    public CustomerSummaryDto getCustomerSummariesById(Integer customerId) {
        return customerRepository.getCustomerSummariesById(customerId);
    }

    public List<RentalSummaryDto> getRentalsByCustomerId(Integer categoryId) {
        List<RentalSummaryDto> rentals = customerRepository.getRentalsByCustomerId(categoryId);
        return rentals;
    }


    public List<PaymentDto> getPaymentsByCustomerId(Integer categoryId) {
        List<PaymentDto> payments = customerRepository.getPaymentsByCustomerId(categoryId);
        System.out.println("start map the payment of category id to payment dtos");
        return payments;
    }


    public CustomerSummaryDto createCustomerByEmail(CustomerDto customerDto) {
        return customerRepository.createCustomerByEmail(customerDto);
    }
}

