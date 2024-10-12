package com.saude.sksaude.ValidatorAnnotation.DateAfter;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class DateAfterValidation implements ConstraintValidator<ValidDateAfter, LocalDateTime> {

    @Override
    public void initialize(ValidDateAfter constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
        return value == null || value.toLocalDate().isEqual(LocalDateTime.now().plusDays(1).toLocalDate());
    }
}
