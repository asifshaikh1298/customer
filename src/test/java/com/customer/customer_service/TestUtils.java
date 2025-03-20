package com.customer.customer_service;

import com.customer.customer_service.constant.ErrorMapping;
import com.customer.customer_service.dto.Customer;
import com.customer.customer_service.dto.CustomerResponse;
import com.customer.customer_service.entity.CustomerEntity;

import java.time.LocalDate;
import java.util.UUID;

public class TestUtils {

    public static CustomerEntity createCustomerEntity(){
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerId(UUID.randomUUID().toString());
        customerEntity.setLastName("LastName");
        customerEntity.setFirstName("FirstName");
        customerEntity.setDateOfBirth(LocalDate.parse("1990-10-10"));
        return customerEntity;
    }

    public static Customer createCustomer(){
        Customer customer = new Customer();
        customer.setFirstName("FirstName");
        customer.setLastName("LastName");
        customer.setDateOfBirth(LocalDate.parse("1990-10-10"));
        return customer;
    }

    public static CustomerResponse createSuccessCustomerResponse(){
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setCustomerId(UUID.randomUUID().toString());
        return customerResponse;
    }

    public static CustomerResponse createErrorCustomerResponseCustomerNotFound(){
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setErrorCode(ErrorMapping.CUSTOMER_NOT_FOUND.getErrorCode());
        customerResponse.setErrorMessage(ErrorMapping.CUSTOMER_NOT_FOUND.getErrorMessage());
        return customerResponse;
    }

    public static CustomerResponse createErrorCustomerResponseForValidation(){
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setErrorCode(ErrorMapping.VALIDATION_ERROR.getErrorCode());
        customerResponse.setErrorMessage("FirstName is invalid");
        return customerResponse;
    }

    public static CustomerResponse createErrorCustomerResponseForOtherException(){
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setErrorCode(ErrorMapping.SERVER_ERROR.getErrorCode());
        customerResponse.setErrorMessage(ErrorMapping.SERVER_ERROR.getErrorMessage());
        return customerResponse;
    }
}
