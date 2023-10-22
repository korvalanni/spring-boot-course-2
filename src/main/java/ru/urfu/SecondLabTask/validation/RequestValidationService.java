package ru.urfu.SecondLabTask.validation;

import ru.urfu.SecondLabTask.exception.MyValidationException;

public class RequestValidationService implements EventValidation {

    @Override
    public void isValid(String message) throws MyValidationException {
        throw new MyValidationException(message);
    }
}
