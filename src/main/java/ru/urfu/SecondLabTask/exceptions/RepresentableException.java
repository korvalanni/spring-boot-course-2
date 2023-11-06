package ru.urfu.SecondLabTask.exceptions;

import lombok.Getter;
import ru.urfu.SecondLabTask.enums.ErrorCodes;
import ru.urfu.SecondLabTask.enums.ErrorMessages;


@Getter
public abstract class RepresentableException extends RuntimeException {
    private final ErrorCodes errorCode;
    private final ErrorMessages errorMessages;
    private final String uid;
    private final String operationUid;


    public RepresentableException(String message, ErrorCodes errorCode, ErrorMessages errorMessages,
                                  String uid, String operationUid) {
        super(message);
        this.errorCode = errorCode;
        this.errorMessages = errorMessages;
        this.uid = uid;
        this.operationUid = operationUid;
    }

    @Override
    public String toString() {
        return "Exception{" +
                "errorCode=" + errorCode +
                ", errorMessages=" + errorMessages +
                ", uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                '}';
    }
}
