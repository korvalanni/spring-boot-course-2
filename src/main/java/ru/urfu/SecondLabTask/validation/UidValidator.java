package ru.urfu.SecondLabTask.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import ru.urfu.SecondLabTask.constants.ErrorMessagesConstants;

@Slf4j
public class UidValidator implements ConstraintValidator<IsValidUidAnnotation, String> {
    @Override
    public boolean isValid(String uid, ConstraintValidatorContext context) {
        if (uid.equals("123")) {

            log.error("Unsupported uid: {}", uid);

            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorMessagesConstants.UNSUPPORTED_ERROR)
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}