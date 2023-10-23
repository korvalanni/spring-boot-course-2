package ru.urfu.SecondLabTask.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UidValidator implements ConstraintValidator<IsValidUidAnnotation, String> {
    @Override
    public boolean isValid(String uid, ConstraintValidatorContext context) {
        if (uid.equals("123")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Не поддерживаемая ошибка")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}