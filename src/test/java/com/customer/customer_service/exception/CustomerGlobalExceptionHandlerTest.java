package com.customer.customer_service.exception;

import com.customer.customer_service.TestUtils;
import com.customer.customer_service.builder.CustomerResponseBuilder;
import com.customer.customer_service.constant.ErrorMapping;
import com.customer.customer_service.dto.CustomerResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerGlobalExceptionHandlerTest {

    @InjectMocks
    private CustomerGlobalExceptionHandler customerGlobalExceptionHandler;

    @Mock
    private CustomerResponseBuilder customerResponseBuilder;

    private CustomerResponse customerResponseValidationFailed;
    private CustomerResponse customerResponseCustomerNotFound;
    private CustomerResponse customerResponseOtherException;
    private BindException bindException;
    private ObjectError objectError;
    private String errorMessage = "firstName is Invalid";
    private CustomerNotFoundException customerNotFoundException;
    private Exception exception;

    @BeforeEach
    void setUp() {
        customerResponseValidationFailed = TestUtils.createErrorCustomerResponseForValidation();
        customerResponseCustomerNotFound = TestUtils.createErrorCustomerResponseCustomerNotFound();
        customerResponseOtherException = TestUtils.createErrorCustomerResponseForOtherException();
        bindException = mock(BindException.class);
        objectError = new ObjectError("customer", errorMessage);
        customerNotFoundException = new CustomerNotFoundException(ErrorMapping.CUSTOMER_NOT_FOUND.getErrorCode(), ErrorMapping.CUSTOMER_NOT_FOUND.getErrorMessage());
        exception = new Exception("Internal server error");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void handleValidationExceptions() {
        when(bindException.getBindingResult()).thenReturn(mock(org.springframework.validation.BindingResult.class));
        when(bindException.getBindingResult().getAllErrors()).thenReturn(Collections.singletonList(objectError));
        when(customerResponseBuilder.buildCustomerErrorResponse(ErrorMapping.VALIDATION_ERROR.getErrorCode(), errorMessage))
                .thenReturn(customerResponseValidationFailed);
        ResponseEntity<CustomerResponse> response = customerGlobalExceptionHandler.handleValidationExceptions(bindException);
        assertEquals(customerResponseValidationFailed.getErrorCode(), response.getBody().getErrorCode());
        assertEquals(customerResponseValidationFailed.getErrorMessage(), response.getBody().getErrorMessage());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void handleCustomerNotFoundException() {
        when(customerResponseBuilder.buildCustomerErrorResponse(customerNotFoundException.getErrorCode(), customerNotFoundException.getErrorMessage()))
                .thenReturn(customerResponseCustomerNotFound);
        ResponseEntity<CustomerResponse> response = customerGlobalExceptionHandler.handleCustomerNotFoundException(customerNotFoundException);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(customerResponseCustomerNotFound.getErrorMessage(), response.getBody().getErrorMessage());
        assertEquals(customerResponseCustomerNotFound.getErrorCode(), response.getBody().getErrorCode());
    }

    @Test
    void handleException() {
        when(customerResponseBuilder.buildCustomerErrorResponse(ErrorMapping.SERVER_ERROR.getErrorCode(),
                ErrorMapping.SERVER_ERROR.getErrorMessage())).thenReturn(customerResponseOtherException);
        ResponseEntity<CustomerResponse> response = customerGlobalExceptionHandler.handleException(exception);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(ErrorMapping.SERVER_ERROR.getErrorMessage(), response.getBody().getErrorMessage());
        assertEquals(ErrorMapping.SERVER_ERROR.getErrorCode(), response.getBody().getErrorCode());
    }
}