package com.customer.customer_service.exception;

import lombok.Data;

@Data
public class CustomerNotFoundException extends RuntimeException  {

    private String errorCode;
    private String errorMessage;

    public CustomerNotFoundException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
