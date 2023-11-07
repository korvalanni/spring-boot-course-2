package ru.urfu.SecondLabTask.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.urfu.SecondLabTask.enums.Codes;
import ru.urfu.SecondLabTask.enums.ErrorCodes;
import ru.urfu.SecondLabTask.enums.ErrorMessages;
import ru.urfu.SecondLabTask.models.Response;
import ru.urfu.SecondLabTask.utils.DateTimeUtil;

import java.util.Date;

@Slf4j
@Component
public class ResponseService {

      public Response buildResponse(String uid, String operationUid, Codes code,
                                  ErrorCodes errorCode, ErrorMessages errorMessage) {
        Response response = Response.builder()
                .uid(uid)
                .operationUid(operationUid)
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(code)
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();

        log.info("Response = {} built", response);

        return response;
    }
}
