package com.customer.customer_service.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;



public class DateOfBirthValidator implements ConstraintValidator<ValidDateOfBirth, LocalDate> {

    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext context) {
        return dateOfBirth!=null && dateOfBirth.isBefore(LocalDate.now());
    }
}
