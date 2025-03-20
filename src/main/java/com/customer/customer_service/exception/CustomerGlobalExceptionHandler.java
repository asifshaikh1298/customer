package com.customer.customer_service.exception;

import com.customer.customer_service.builder.CustomerResponseBuilder;
import com.customer.customer_service.constant.ErrorMapping;
import com.customer.customer_service.controller.CustomerController;
import com.customer.customer_service.dto.CustomerResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice(assignableTypes = CustomerController.class)
@Slf4j
public class CustomerGlobalExceptionHandler {

    private final CustomerResponseBuilder customerResponseBuilder;

    @Autowired
    public CustomerGlobalExceptionHandler (CustomerResponseBuilder customerResponseBuilder){
        this.customerResponseBuilder = customerResponseBuilder;
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<CustomerResponse> handleValidationExceptions(BindException ex) {
        log.error("CustomerGlobalExceptionHandler handleValidationExceptions : {}", ex.getMessage());
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        List<String> errorMessages = new ArrayList<>();
        for (ObjectError error : allErrors) {
            if (StringUtils.isNotBlank(error.getDefaultMessage())) {
                errorMessages.add(error.getDefaultMessage());
            }
        }
        String errorMessage = StringUtils.join(errorMessages,',');
        CustomerResponse customerResponse = customerResponseBuilder.buildCustomerErrorResponse(ErrorMapping.VALIDATION_ERROR.getErrorCode(), errorMessage);
        return new ResponseEntity<>(customerResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<CustomerResponse> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        log.error("CustomerGlobalExceptionHandler handleCustomerNotFoundException : {}", ex.getMessage());
        CustomerResponse customerResponse = customerResponseBuilder.buildCustomerErrorResponse(ex.getErrorCode(), ex.getErrorMessage());
        return new ResponseEntity<>(customerResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomerResponse> handleException(Exception ex) {
        log.error("CustomerGlobalExceptionHandler handleException : {}", ex.getMessage());
        CustomerResponse customerResponse = customerResponseBuilder.buildCustomerErrorResponse(ErrorMapping.SERVER_ERROR.getErrorCode(),
                ErrorMapping.SERVER_ERROR.getErrorMessage());
        return new ResponseEntity<>(customerResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }





}
