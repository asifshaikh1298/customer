package com.customer.customer_service.controller;

import com.customer.customer_service.TestUtils;
import com.customer.customer_service.dto.Customer;
import com.customer.customer_service.dto.CustomerResponse;
import com.customer.customer_service.service.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    private CustomerResponse customerResponse;

    private Customer customer;

    private String customerId;

    @BeforeEach
    void setUp() {
        customerResponse = TestUtils.createSuccessCustomerResponse();
        customer = TestUtils.createCustomer();
        customerId = UUID.randomUUID().toString();
    }

    @AfterEach
    void tearDown() {
        customerResponse = null;
        customer = null;
    }

    @Test
    void testCreateCustomer(){
        when(customerService.createCustomer(customer)).thenReturn(customerResponse);
        ResponseEntity<CustomerResponse> customerResponseEntity = customerController.createCustomer(customer);
        assertEquals(customerResponseEntity.getBody(), customerResponse);
        assertEquals(HttpStatus.OK, customerResponseEntity.getStatusCode());
    }

    @Test
    void testGetCustomer(){
        when(customerService.getCustomer(customerId)).thenReturn(customer);
        ResponseEntity<Customer> customerEntity = customerController.getCustomer(customerId);
        assertEquals(customerEntity.getBody(), customer);
        assertEquals(HttpStatus.OK, customerEntity.getStatusCode());
    }


}