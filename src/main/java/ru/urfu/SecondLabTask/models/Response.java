package ru.urfu.SecondLabTask.models;

import lombok.Data;
import lombok.Builder;
import ru.urfu.SecondLabTask.enums.Codes;
import ru.urfu.SecondLabTask.enums.ErrorCodes;
import ru.urfu.SecondLabTask.enums.ErrorMessages;

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
