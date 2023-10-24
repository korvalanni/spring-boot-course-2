package ru.urfu.SecondLabTask.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.urfu.SecondLabTask.constants.ErrorMessagesConstants;

public class UidValidator implements ConstraintValidator<IsValidUidAnnotation, String> {
    @Override
    public boolean isValid(String uid, ConstraintValidatorContext context) {
        if (uid.equals("123")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorMessagesConstants.UNSUPPORTED_ERROR)
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}