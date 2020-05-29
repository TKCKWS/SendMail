package com.example.demo.sendmail.domain.model.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.demo.sendmail.constants.RequestType;

public class RequestTypeValidator implements ConstraintValidator<SendMailType, String> {

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        try {
            RequestType.valueOf(value);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}
