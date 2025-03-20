package com.customer.customer_service.service;

import com.customer.customer_service.TestUtils;
import com.customer.customer_service.builder.CustomerBuilder;
import com.customer.customer_service.builder.CustomerEntityBuilder;
import com.customer.customer_service.builder.CustomerResponseBuilder;
import com.customer.customer_service.constant.ErrorMapping;
import com.customer.customer_service.dto.Customer;
import com.customer.customer_service.dto.CustomerResponse;
import com.customer.customer_service.entity.CustomerEntity;
import com.customer.customer_service.exception.CustomerNotFoundException;
import com.customer.customer_service.respository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerResponseBuilder customerResponseBuilder;

    @Mock
    private CustomerEntityBuilder customerEntityBuilder;

    @Mock
    private CustomerBuilder customerBuilder;

    private Customer customer;
    private CustomerEntity customerEntity;
    private CustomerResponse customerResponse;
    private String customerId;

    @BeforeEach
    void setUp() {
        customer = TestUtils.createCustomer();
        customerEntity = TestUtils.createCustomerEntity();
        customerResponse = TestUtils.createSuccessCustomerResponse();
        customerId = UUID.randomUUID().toString();
    }

    @AfterEach
    void tearDown() {
        customer = null;
        customerEntity = null;
        customerResponse = null;
        customerId = null;
    }

    @Test
    void testCreateCustomer() {
        when(customerEntityBuilder.buildCustomerEntity(customer)).thenReturn(customerEntity);
        when(customerResponseBuilder.buildCustomerResponse(customerEntity.getCustomerId())).thenReturn(customerResponse);
        CustomerResponse result = customerService.createCustomer(customer);
        assertEquals(result,customerResponse);
        verify(customerRepository,times(1)).save(customerEntity);
    }

    @Test
    void testGetCustomerFound() {
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customerEntity));
        when(customerBuilder.buildCustomer(customerEntity)).thenReturn(customer);
        Customer result = customerService.getCustomer(customerId);
        assertEquals(result,customer);
    }

    @Test
    void testGetCustomerNotFound() {
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
        CustomerNotFoundException customerNotFoundException = assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomer(customerId));
        assertEquals(customerNotFoundException.getErrorCode(), ErrorMapping.CUSTOMER_NOT_FOUND.getErrorCode());
        assertEquals(customerNotFoundException.getErrorMessage(), ErrorMapping.CUSTOMER_NOT_FOUND.getErrorMessage());
        verify(customerBuilder,times(0)).buildCustomer(customerEntity);
    }
}