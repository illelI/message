package com.ilelli.message;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MessageValidator implements ConstraintValidator<CorrectMessage, MessageContent> {
    @Override
    public void initialize(CorrectMessage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MessageContent messageContent, ConstraintValidatorContext constraintValidatorContext) {
        return !(messageContent.getImage() == null && messageContent.getText().isEmpty());
    }
}
