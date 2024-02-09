package ru.urfu.SecondLabTask.exceptions;

import ru.urfu.SecondLabTask.enums.ErrorCodes;
import ru.urfu.SecondLabTask.enums.ErrorMessages;

public class ValidationException extends RepresentableException {

    public ValidationException(String message, ErrorCodes errorCode, ErrorMessages errorMessages,
                               String uid, String operationUid) {
        super(message, errorCode, errorMessages, uid, operationUid);
    }

}
