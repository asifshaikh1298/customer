package com.customer.customer_service.service;

import com.customer.customer_service.builder.CustomerBuilder;
import com.customer.customer_service.builder.CustomerEntityBuilder;
import com.customer.customer_service.builder.CustomerResponseBuilder;
import com.customer.customer_service.dto.Customer;
import com.customer.customer_service.dto.CustomerResponse;
import com.customer.customer_service.entity.CustomerEntity;
import com.customer.customer_service.exception.CustomerNotFoundException;
import com.customer.customer_service.respository.CustomerRepository;
import com.customer.customer_service.constant.ErrorMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerBuilder customerBuilder;
    private final CustomerEntityBuilder customerEntityBuilder;
    private final CustomerResponseBuilder customerResponseBuilder;

    @Autowired
    private CustomerService(CustomerRepository customerRepository,
                            CustomerBuilder customerBuilder,
                            CustomerEntityBuilder customerEntityBuilder,
                            CustomerResponseBuilder customerResponseBuilder) {
        this.customerRepository = customerRepository;
        this.customerBuilder = customerBuilder;
        this.customerEntityBuilder = customerEntityBuilder;
        this.customerResponseBuilder = customerResponseBuilder;
    }

    public CustomerResponse createCustomer(Customer customer){
        log.info("CustomerService : Inside createCustomer ");
        CustomerEntity customerEntity = customerEntityBuilder.buildCustomerEntity(customer);
        customerRepository.save(customerEntity);
        return customerResponseBuilder.buildCustomerResponse(customerEntity.getCustomerId());
    }

    public Customer getCustomer(String customerId){
        Optional<CustomerEntity> customerEntity =  customerRepository.findById(customerId);
        if (customerEntity.isEmpty()){
            throw new CustomerNotFoundException(ErrorMapping.CUSTOMER_NOT_FOUND.getErrorCode(), ErrorMapping.CUSTOMER_NOT_FOUND.getErrorMessage());
        }
        return customerBuilder.buildCustomer(customerEntity.get());
    }
}
