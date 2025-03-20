package com.customer.customer_service.controller;

import com.customer.customer_service.dto.Customer;
import com.customer.customer_service.dto.CustomerResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.customer.customer_service.service.CustomerService;

import java.util.UUID;

@RestController
@RequestMapping("api/v1")
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping(value = "/customer" ,consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody @Valid Customer customer){
        log.info("CustomerController : Inside createCustomer");
        CustomerResponse customerResponse = customerService.createCustomer(customer);
        log.info("CustomerController : createCustomer ended ");
        return new ResponseEntity<>(customerResponse,HttpStatus.OK);
    }

    @GetMapping(value = "/customer/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomer(@PathVariable String customerId){
        log.info("CustomerController : Inside getCustomer with customerId {}",customerId);
        Customer customer = customerService.getCustomer(customerId);
        log.info("CustomerController : getCustomer ended");
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }





}
