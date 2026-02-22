package com.rick1135.eventostech.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EventValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface EventValidation {
    String message() default "localização ou URL inválidos para o tipo de evento";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
