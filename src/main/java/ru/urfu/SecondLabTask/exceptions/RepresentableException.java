package ru.urfu.SecondLabTask.exceptions;

import ru.urfu.SecondLabTask.enums.ErrorCodes;

public abstract class RepresentableException extends RuntimeException{
    private final ErrorCodes errorCode;

    public RepresentableException(String message, ErrorCodes errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
