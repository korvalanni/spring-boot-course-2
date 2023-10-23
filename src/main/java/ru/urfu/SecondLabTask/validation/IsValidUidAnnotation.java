package ru.urfu.SecondLabTask.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UidValidator.class)
@Documented
public @interface IsValidUidAnnotation {
    String message() default "Не поддерживаемая ошибка";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
