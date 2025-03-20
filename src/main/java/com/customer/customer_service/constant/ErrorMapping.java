package com.customer.customer_service.constant;

public enum ErrorMapping {

    CUSTOMER_NOT_FOUND("customer_not_found", "The customer you are looking does not exists"),
    VALIDATION_ERROR("validation_error", "Validation error occured"),
    SERVER_ERROR("server_error", "internal server error occured");

    private String errorCode;
    private String errorMessage;

    ErrorMapping(String errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode(){
        return errorCode;
    }

    public String getErrorMessage(){
        return errorMessage;
    }
}
