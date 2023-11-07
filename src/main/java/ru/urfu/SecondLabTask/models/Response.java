package ru.urfu.SecondLabTask.models;

import lombok.Data;
import lombok.Builder;
import ru.urfu.SecondLabTask.enums.Codes;
import ru.urfu.SecondLabTask.enums.ErrorCodes;
import ru.urfu.SecondLabTask.enums.ErrorMessages;

import java.util.Objects;

@Data
@Builder
public class Response {

    private String uid;


    private String operationUid;


    private String systemTime;


    private Codes code;


    private ErrorCodes errorCode;


    private ErrorMessages errorMessage;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Response response)) return false;
        return getUid().equals(response.getUid()) && getOperationUid().equals(response.getOperationUid()) && getSystemTime().equals(response.getSystemTime()) && getCode() == response.getCode() && getErrorCode() == response.getErrorCode() && getErrorMessage() == response.getErrorMessage();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUid(), getOperationUid(), getSystemTime(), getCode(), getErrorCode(), getErrorMessage());
    }
}
