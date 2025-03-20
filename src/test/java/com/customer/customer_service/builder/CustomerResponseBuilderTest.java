package com.customer.customer_service.builder;

import com.customer.customer_service.constant.ErrorMapping;
import com.customer.customer_service.dto.CustomerResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerResponseBuilderTest {

    @InjectMocks
    private CustomerResponseBuilder customerResponseBuilder;

    private String customerId;

    private String errorCode;

    private String errorMessage;

    @BeforeEach
    void setUp() {
        customerId = UUID.randomUUID().toString();
        errorCode = ErrorMapping.VALIDATION_ERROR.getErrorCode();
        errorMessage = ErrorMapping.VALIDATION_ERROR.getErrorMessage();
    }

    @AfterEach
    void tearDown() {
        customerId = null;
        errorCode = null;
        errorMessage = null;
    }

    @Test
    void testBuildCustomerResponse(){
        CustomerResponse customerResponse = customerResponseBuilder.buildCustomerResponse(customerId);
        assertEquals(customerResponse.getCustomerId(),customerId);
    }

    @Test
    void testBuildCustomerErrorResponse(){
        CustomerResponse customerResponse = customerResponseBuilder.buildCustomerErrorResponse(errorCode,errorMessage);
        assertEquals(customerResponse.getErrorCode(), errorCode);
        assertEquals(customerResponse.getErrorMessage(), errorMessage);

    }
}