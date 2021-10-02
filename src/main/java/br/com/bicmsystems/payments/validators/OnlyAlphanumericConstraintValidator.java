package br.com.bicmsystems.payments.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OnlyAlphanumericConstraintValidator implements
        ConstraintValidator<OnlyAlphanumericConstraint, String> {

    @Override
    public void initialize(OnlyAlphanumericConstraint value) {
    }

    @Override
    public boolean isValid(String field, ConstraintValidatorContext cxt) {
        return field != null && field.matches("^[a-zA-Z0-9]+$");
    }

}