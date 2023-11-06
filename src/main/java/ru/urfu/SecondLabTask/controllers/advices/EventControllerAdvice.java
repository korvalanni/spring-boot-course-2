package ru.urfu.SecondLabTask.controllers.advices;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.urfu.SecondLabTask.enums.Codes;
import ru.urfu.SecondLabTask.exceptions.*;
import ru.urfu.SecondLabTask.models.Response;
import ru.urfu.SecondLabTask.service.ResponseService;
import ru.urfu.SecondLabTask.service.modify.ModifySystemTimeResponseService;
import ru.urfu.SecondLabTask.service.modify.ModifyUuidResponseService;

@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class EventControllerAdvice {

    private final ModifySystemTimeResponseService modifySystemTimeResponseService;
    private final ModifyUuidResponseService modifyUuidResponseService;
    private final ResponseService responseService;


    private ResponseEntity<Response> buildExceptionResponseEntity(RepresentableException ex, String uuid, String operationUid) {
        Response response = this.responseService.buildResponse(uuid, operationUid,
                Codes.FAILED, ex.getErrorCode(), ex.getErrorMessages());

        log.error("Response {} created", response);

        response = modifySystemTimeResponseService.modify(response);
        response = modifyUuidResponseService.modify(response);

        log.error("Response {} with Exception info sent", response);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UnsupportedCodeException.class})
    public ResponseEntity<Response> handleUnsupportedCodeException(UnsupportedCodeException ex) {
        return buildExceptionResponseEntity(ex, ex.getUid(), ex.getOperationUid());
    }


    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<Response> handleValidationExceptionException(UnsupportedCodeException ex) {
        return buildExceptionResponseEntity(ex, ex.getUid(), ex.getOperationUid());
    }

    @ExceptionHandler(value = {UnknownException.class})
    public ResponseEntity<Response> handleUnknownException(UnknownException ex) {
        return buildExceptionResponseEntity(ex, ex.getUid(), ex.getOperationUid());
    }
}
