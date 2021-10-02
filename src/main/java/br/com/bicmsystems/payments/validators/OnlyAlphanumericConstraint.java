package br.com.bicmsystems.payments.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OnlyAlphanumericConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface OnlyAlphanumericConstraint {
    String message() default "Invalid content";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

