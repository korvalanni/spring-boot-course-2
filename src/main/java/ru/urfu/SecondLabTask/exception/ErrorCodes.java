package ru.urfu.SecondLabTask.exception;

import com.fasterxml.jackson.annotation.JsonValue;
import ru.urfu.SecondLabTask.constants.ErrorCodesConstants;

public enum ErrorCodes {
    EMPTY(ErrorCodesConstants.EMPTY),
    VALIDATION_EXCEPTION(ErrorCodesConstants.VALIDATION_EXCEPTION),
    UNKNOWN(ErrorCodesConstants.UNKNOWN),
    UNSUPPORTED(ErrorCodesConstants.UNSUPPORTED);
    private final String message;

    ErrorCodes(String name){
        this.message = name;
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
