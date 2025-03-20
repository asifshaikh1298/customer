package com.customer.customer_service.dto;


import com.customer.customer_service.validation.ValidDateOfBirth;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Customer {

    @NotEmpty(message = "firstName should not be blank or empty")
    @Size(min = 3,max = 50, message = "firstName should be of minimum 3 characters and maximum of 50 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "firstName should contain only alphabets")
    @JsonProperty(value = "firstName")
    private String firstName;

    @NotEmpty(message = "lastName should not be blank or empty")
    @Size(min = 3,max = 50, message = "lastName should be of minimum 3 characters and maximum of 50 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "lastName should contain only alphabets")
    @JsonProperty(value = "lastName")
    private String lastName;

    @NotNull(message = "dateOfBirth should not null")
    @ValidDateOfBirth
    @JsonProperty(value = "dateOfBirth")
    private LocalDate dateOfBirth;

}
