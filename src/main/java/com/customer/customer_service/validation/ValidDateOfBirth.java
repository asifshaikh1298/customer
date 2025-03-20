package com.customer.customer_service.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateOfBirthValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDateOfBirth {

    String message() default "Date of birth is invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
