package org.example.service.customer;

import org.example.dto.customer.CustomerDto;
import org.example.dto.PaymentDto;
import org.example.dto.rental.RentalDto;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllCustomers();
    CustomerDto getCustomerById(Integer id);
    CustomerDto createCustomer(CustomerDto customerDto);
    CustomerDto updateCustomer(Integer id, CustomerDto customerDto);
    void deleteCustomer(Integer id);
    List<RentalDto> getAllRentalsForCustomer(Integer customerId);
    List<PaymentDto> getAllPaymentsForCustomer(Integer customerId);
}

