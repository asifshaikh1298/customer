package com.customer.customer_service.builder;

import com.customer.customer_service.dto.Customer;
import com.customer.customer_service.entity.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerBuilder {

    public Customer buildCustomer(CustomerEntity customerEntity){
        Customer customer = new Customer();
        customer.setFirstName(customerEntity.getFirstName());
        customer.setLastName(customerEntity.getLastName());
        customer.setDateOfBirth(customerEntity.getDateOfBirth());
        return customer;
    }
}
