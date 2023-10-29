package ru.urfu.SecondLabTask.exceptions;

import ru.urfu.SecondLabTask.enums.ErrorCodes;

public class ValidationException extends RepresentableException {

    public ValidationException(String message, ErrorCodes errorCode) {
        super(message, errorCode);
    }
}
