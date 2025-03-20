package com.customer.customer_service.builder;

import com.customer.customer_service.dto.Customer;
import com.customer.customer_service.entity.CustomerEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class CustomerEntityBuilder {

    public CustomerEntity buildCustomerEntity(Customer customer){
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerId(UUID.randomUUID().toString());
        customerEntity.setFirstName(customer.getFirstName());
        customerEntity.setLastName(customer.getLastName());
        customerEntity.setDateOfBirth(customer.getDateOfBirth());
        return customerEntity;
    }
}
