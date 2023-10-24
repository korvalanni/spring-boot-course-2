package ru.urfu.SecondLabTask.exception;


import com.fasterxml.jackson.annotation.JsonValue;
import ru.urfu.SecondLabTask.constants.ErrorMessagesConstants;


public enum ErrorMessages {

    VALIDATION_ERROR(ErrorMessagesConstants.VALIDATION_ERROR),
    UNSUPPORTED_ERROR(ErrorMessagesConstants.UNSUPPORTED_ERROR),
    UNKNOWN_ERROR(ErrorMessagesConstants.UNKNOWN_ERROR),
    EMPTY_ERROR(ErrorMessagesConstants.EMPTY_ERROR);

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    @JsonValue
    public String getMessage() {
        return message;
    }


    @Override
    public String toString() {
        return message;
    }
}

