package ru.urfu.SecondLabTask.exceptions;

import ru.urfu.SecondLabTask.enums.ErrorCodes;
import ru.urfu.SecondLabTask.enums.ErrorMessages;

public class UnsupportedCodeException extends RepresentableException{
    public UnsupportedCodeException(String message, ErrorCodes errorCode, ErrorMessages errorMessages,
                                    String uid, String operationUid) {
        super(message, errorCode, errorMessages, uid, operationUid);
    }
}
