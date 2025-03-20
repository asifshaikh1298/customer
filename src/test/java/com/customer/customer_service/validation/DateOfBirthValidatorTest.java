package com.customer.customer_service.validation;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DateOfBirthValidatorTest {

    @InjectMocks
    private DateOfBirthValidator dateOfBirthValidator;

    @Mock
    private ConstraintValidatorContext context;


    @Test
    void testValidDateOfBirth() {
        LocalDate validDate = LocalDate.of(2000, 5, 10);
        assertTrue(dateOfBirthValidator.isValid(validDate, context));
    }

    @Test
    void testFutureDateOfBirth() {
        LocalDate futureDate = LocalDate.now().plusDays(1);
        assertFalse(dateOfBirthValidator.isValid(futureDate, context));
    }

    @Test
    void testNullDateOfBirth() {
        assertFalse(dateOfBirthValidator.isValid(null, context));
    }
}