package org.example.service.customer;

import org.example.dto.rental.RentalDto;
import org.example.dto.customer.CustomerDto;
import org.example.dto.PaymentDto;
import org.example.dto.customer.CustomerSummaryDto;
import org.example.dto.rental.RentalSummaryDto;
import org.example.mapper.customer.CustomerMapper;
import org.example.mapper.PaymentMapper;
import org.example.model.Customer;
import org.example.repository.*;
import org.example.service.BaseService;

import java.util.List;

public class CustomerServiceImpl extends BaseService<Customer, CustomerDto> {
    CustomerRepository customerRepository;
    CustomerMapper customerMapper;
    PaymentMapper paymentMapper;
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
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

    public List<CustomerSummaryDto> getAllCustomers() {
        List<CustomerSummaryDto> summaries = customerRepository.getCustomerSummaries();
        return summaries;
    }

    public List<RentalSummaryDto> getRentalsByCustomerId(Integer categoryId) {
        List<RentalSummaryDto> rentals = customerRepository.getRentalsByCustomerId(categoryId);
        return rentals;
//        Customer customer = customerRepository.getById(id).orElse(null);
        //return customer != null ? rentalRepository.findAllByCustomer(customer).stream().map(RentalDto::fromEntity).collect(Collectors.toList()) : null;
    }

    public CustomerSummaryDto getCustomerSummariesById(Integer customerId) {
        return customerRepository.getCustomerSummariesById(customerId);
    }

    /*public List<PaymentDto> getPaymentsByCustomerId(Integer categoryId) {
        customerRepository.getPaymentsByCustomerId(categoryId);
        Customer customer = customerRepository.getById(id).orElse(null);
        return customer != null ? paymentRepository.findAllByCustomer(customer).stream().map(PaymentDto::fromEntity).collect(Collectors.toList()) : null;
    }*/
    public List<PaymentDto> getPaymentsByCustomerId(Integer categoryId) {
        List<PaymentDto> payments = customerRepository.getPaymentsByCustomerId(categoryId);
        System.out.println("start map the payment of category id to payment dtos");
        return payments;
//        return paymentMapper.toDto(payments);
    }

    /*private final CustomerRepository customerRepository;
    private final RentalRepository rentalRepository;
    private final PaymentRepository paymentRepository;
    private CustomerMapper customerMapper;
    public CustomerServiceImpl() {
        this.customerRepository = new CustomerRepository();
        this.rentalRepository = new RentalRepository();
        this.paymentRepository = new PaymentRepository();
        this.customerMapper = CustomerMapper.INSTANCE;
    }*/

   /* @Override
    public List<CustomerDto> getAllCustomers() {
        return customerMapper.toDto(customerRepository.getAll());
    }


    @Override
    public CustomerDto getCustomerById(Integer id) {
        Optional<Customer> optionalCustomer = customerRepository.getById(id);
        if (!optionalCustomer.isPresent())
            throw new WebServiceException("Can't fetch customer with id: " + id);
        else {
            System.out.println("get the customer object successfully in customer service -> " + optionalCustomer.get());
            return customerMapper.toDto(optionalCustomer.get());
        }
    }

    @Override
    public CustomerDto updateCustomer(Integer id, CustomerDto customerDto) {
        Optional<Customer> optionalCustomer = customerRepository.getById(id);
        if (!optionalCustomer.isPresent())
            throw new WebServiceException("Can't get the cutomer with id: " + id);
        else {
            Customer customer = customerMapper.toEntity(customerDto);
            customer.setId(id);
            return customerMapper.toDto(customerRepository.update(customer));
        }
    }
    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        //firstly,ensures that there is no actor with this id in db.
        Optional<Customer> optionalExistedCustomer = null;
        if (customerDto.getId() != null) {
            optionalExistedCustomer = customerRepository.getById(customerDto.getId());
            if (optionalExistedCustomer.isPresent())
                throw new EntityAlreadyExistException("Customer already exists with id: " + customerDto.getId());
        }
        Customer customer = customerMapper.toEntity(customerDto);
        customer.setId(null);
        System.out.println("customer obj which is saved in db -> " + customer.toString());
        return customerMapper.toDto(customerRepository.save(customer));
    }
    @Override
    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }
*/
   /* @Override
    public List<RentalDto> getRentalsByCustomerId(Integer id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        return customer != null ? rentalRepository.findAllByCustomer(customer).stream().map(RentalDto::fromEntity).collect(Collectors.toList()) : null;
    }

    @Override
    public List<PaymentDto> getPaymentsByCustomerId(Integer id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        return customer != null ? paymentRepository.findAllByCustomer(customer).stream().map(PaymentDto::fromEntity).collect(Collectors.toList()) : null;
    }*/
}

