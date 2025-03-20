package com.customer.customer_service.builder;

import com.customer.customer_service.TestUtils;
import com.customer.customer_service.dto.Customer;
import com.customer.customer_service.entity.CustomerEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerBuilderTest {

    @InjectMocks
    private CustomerBuilder customerBuilder;

    private static CustomerEntity customerEntity;

    @BeforeEach
    public void setUp(){
        customerEntity = TestUtils.createCustomerEntity();
    }

    @AfterEach
    public void tearDown(){
        customerEntity = null;
    }

    @Test
    public void testBuildCustomer(){
        Customer customer = customerBuilder.buildCustomer(customerEntity);
        assertEquals(customer.getFirstName(), customerEntity.getFirstName());
        assertEquals(customer.getLastName(), customerEntity.getLastName());
        assertEquals(customer.getDateOfBirth(), customerEntity.getDateOfBirth());
    }

}