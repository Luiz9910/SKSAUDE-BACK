package com.saude.sksaude.ValidatorAnnotation.birthDay;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class BirthdayValidator implements ConstraintValidator<ValidBirthday, LocalDateTime> {

    @Override
    public void initialize(ValidBirthday constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        LocalDateTime minDate = LocalDateTime.of(1910, 1, 1, 0, 0);
        return !value.isBefore(minDate);
    }
}
