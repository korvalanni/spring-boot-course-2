package ru.urfu.SecondLabTask.exceptions;

import ru.urfu.SecondLabTask.enums.ErrorCodes;

public class UnsupportedCodeException extends RepresentableException{
    public UnsupportedCodeException(String message, ErrorCodes errorCode) {
        super(message, errorCode);
    }
}
