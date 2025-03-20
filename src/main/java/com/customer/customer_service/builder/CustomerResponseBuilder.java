package com.customer.customer_service.builder;

import com.customer.customer_service.dto.CustomerResponse;
import org.springframework.stereotype.Component;


@Component
public class CustomerResponseBuilder {

    public CustomerResponse buildCustomerResponse(String customerId){
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setCustomerId(customerId);
        return customerResponse;
    }

    public CustomerResponse buildCustomerErrorResponse(String errorCode, String errorMessage){
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setErrorCode(errorCode);
        customerResponse.setErrorMessage(errorMessage);
        return customerResponse;
    }
}
