package com.customer.customer_service.builder;

import com.customer.customer_service.TestUtils;
import com.customer.customer_service.dto.Customer;
import com.customer.customer_service.entity.CustomerEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerEntityBuilderTest {

    @InjectMocks
    private CustomerEntityBuilder customerEntityBuilder;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = TestUtils.createCustomer();
    }

    @AfterEach
    void tearDown() {
        customer = null;
    }

    @Test
    public void testBuildCustomerEntity(){
        CustomerEntity customerEntity = customerEntityBuilder.buildCustomerEntity(customer);
        assertEquals(customerEntity.getDateOfBirth(), customer.getDateOfBirth());
        assertEquals(customerEntity.getFirstName(), customer.getFirstName());
        assertEquals(customerEntity.getLastName(), customer.getLastName());
    }


}