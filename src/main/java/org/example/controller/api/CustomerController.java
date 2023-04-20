package org.example.controller.api;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.WebServiceException;
import org.example.controller.response.ResponseMessage;
import org.example.dto.PaymentDto;
import org.example.dto.customer.CustomerDto;
import org.example.dto.customer.CustomerSummaryDto;
import org.example.dto.rental.RentalDto;
import org.example.mapper.customer.CustomerMapper;
import org.example.repository.CustomerRepository;
import org.example.service.customer.CustomerServiceImpl;

import java.util.List;
import java.util.Optional;

@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)

@WebService(name = "CustomerService")
public class CustomerController {

    private final CustomerServiceImpl customerService = new CustomerServiceImpl(
            new CustomerRepository(),
            CustomerMapper.INSTANCE
    );

    @WebMethod(operationName = "getAllCustomers")
    @WebResult(name = "customers")
    public List<CustomerSummaryDto> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @WebMethod(operationName = "getRentalsByCustomerId")
    @WebResult(name = "rentals")
    public List<RentalDto> getRentalsByCustomerId(@WebParam(name = "customerId") Integer customerId) {
        return customerService.getRentalsByCustomerId(customerId);
    }

    @WebMethod(operationName = "getCustomerById")
    @WebResult(name = "customer")
    public CustomerDto getCustomerById(@WebParam(name = "id") int id) {
        CustomerDto customerDto = customerService.getById(id);
        if (customerDto == null) {
            throw new WebServiceException("Customer with id " + id + " not found");
        }
        return customerDto;
    }

    @WebMethod(operationName = "createCustomer")
    @WebResult(name = "customerAdded")
    public CustomerDto createCustomer(@WebParam(name = "customer") CustomerDto customerDto) {
        Optional<CustomerDto> optionalCustomerDto = Optional.ofNullable(customerService.create(customerDto, customerDto.getId()));
        if (optionalCustomerDto.isPresent()) {
            return optionalCustomerDto.get();
        }
        throw new WebServiceException("Can't create customer");
    }

    @WebMethod(operationName = "updateCustomer")
    @WebResult(name = "customerUpdated")
    public ResponseMessage updateCustomer(@WebParam(name = "id") Integer id, @WebParam(name = "customer") CustomerDto customerDto) {
        customerDto.setId(id);
        boolean res = customerService.update(id, customerDto);
        if (res) {
            return new ResponseMessage("Customer was updated successfully");
        } else {
            throw new WebServiceException("Failed to update customer");
        }
    }

    @WebMethod(operationName = "deleteCustomerById")
    @WebResult(name = "customerDeleted")
    public ResponseMessage deleteCustomerById(@WebParam(name = "id") Integer id) {
        customerService.deleteById(id);
        return new ResponseMessage("Customer was deleted successfully");
    }

    @WebMethod(operationName = "getPaymentsByCustomerId")
    @WebResult(name = "payments")
    public List<PaymentDto> getPaymentsByCustomerId(@WebParam(name = "id") Integer id) {
        return customerService.getPaymentsByCustomerId(id);
    }
}
