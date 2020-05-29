package com.example.demo.sendmail.domain.model.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = RequestTypeValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface SendMailType {
    String message() default "RequestType is invalid.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
