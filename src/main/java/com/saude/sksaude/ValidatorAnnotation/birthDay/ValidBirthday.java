package com.saude.sksaude.ValidatorAnnotation.birthDay;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BirthdayValidator.class)
public @interface ValidBirthday {
    String message() default "Data de nascimento deve ser posterior a 1910-01-01";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

