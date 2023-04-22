package org.example.controller.api;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.ws.rs.core.Response;
import jakarta.xml.ws.WebServiceException;
import org.example.controller.response.ResponseMessage;
import org.example.dto.PaymentDto;
import org.example.dto.customer.CustomerDto;
import org.example.dto.customer.CustomerSummaryDto;
import org.example.dto.rental.RentalDto;
import org.example.dto.rental.RentalSummaryDto;
import org.example.mapper.customer.CustomerMapper;
import org.example.repository.CustomerRepository;
import org.example.service.customer.CustomerService;
import org.example.service.customer.CustomerServiceImpl;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)

@WebService(name = "CustomerService")
public class CustomerController {

    private final CustomerService customerService = new CustomerService(
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
    public List<RentalSummaryDto> getRentalsByCustomerId(@WebParam(name = "customerId") Integer customerId) {
        return customerService.getRentalsByCustomerId(customerId);
    }

    @WebMethod(operationName = "getCustomerById")
    @WebResult(name = "customer")
    public CustomerSummaryDto getCustomerById(@WebParam(name = "id") int id) {
        CustomerSummaryDto customerSummaryDto = customerService.getCustomerSummariesById(id);
        if (customerSummaryDto != null) {
            return customerSummaryDto;
        }
        throw new WebServiceException("Customer with id " + id + " not found");
    }

    @WebMethod(operationName = "createCustomer")
    @WebResult(name = "customerAdded")
    public CustomerDto createCustomer(@WebParam(name = "customer") CustomerDto customerDto) {
        customerDto.setLastUpdate(Instant.now());
        customerDto.setCreateDate(Instant.now());
        Optional<CustomerSummaryDto> optionalCustomerSummaryDto = Optional.ofNullable(customerService.createCustomerByEmail(customerDto));
        if (optionalCustomerSummaryDto.isPresent()){
            optionalCustomerSummaryDto.get();
        }
        throw new WebServiceException("Can't create customer");
    }

    @WebMethod(operationName = "updateCustomer")
    @WebResult(name = "customerUpdated")
    public CustomerDto updateCustomer(@WebParam(name = "id") Integer id, @WebParam(name = "customer") CustomerDto customerDto) {
        customerDto.setId(id);
        CustomerDto res = customerService.update(id, customerDto);
        if (res != null) {
            return res;
        } else
            throw new WebServiceException("Failed to update customer");
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
