package ru.urfu.SecondLabTask.model;

import lombok.Data;
import lombok.Builder;
import ru.urfu.SecondLabTask.exception.Codes;
import ru.urfu.SecondLabTask.exception.ErrorCodes;
import ru.urfu.SecondLabTask.exception.ErrorMessages;

@Data
@Builder
public class Response {

    private String uid;


    private String operationUid;


    private String systemTime;


    private Codes code;


    private ErrorCodes errorCode;


    private ErrorMessages errorMessage;
}
