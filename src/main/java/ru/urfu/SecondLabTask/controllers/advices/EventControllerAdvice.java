package ru.urfu.SecondLabTask.controllers.advices;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.urfu.SecondLabTask.enums.Codes;
import ru.urfu.SecondLabTask.enums.ErrorCodes;
import ru.urfu.SecondLabTask.enums.ErrorMessages;
import ru.urfu.SecondLabTask.exceptions.CustomWrappedException;
import ru.urfu.SecondLabTask.exceptions.UnsupportedCodeException;
import ru.urfu.SecondLabTask.exceptions.ValidationException;
import ru.urfu.SecondLabTask.models.Response;
import ru.urfu.SecondLabTask.service.modify.ModifySystemTimeResponseService;
import ru.urfu.SecondLabTask.service.modify.ModifyUuidResponseService;


@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class EventControllerAdvice {

    private final ModifySystemTimeResponseService modifySystemTimeResponseService;
    private final ModifyUuidResponseService modifyUuidResponseService;


    @ExceptionHandler(value = {CustomWrappedException.class})
    public ResponseEntity<Response> handleCustomWrappedException(CustomWrappedException ex) {
        RuntimeException originalException = ex.getOriginalException();
        Response response = ex.getResponse();

        Codes code = response.getCode();
        ErrorCodes errorCode = response.getErrorCode();
        ErrorMessages errorMessage = response.getErrorMessage();

        Codes updatedCode =Codes.FAILED;
        ErrorCodes updatedErrorCode;
        ErrorMessages updatedErrorMessage;
        HttpStatus status;

        if (originalException instanceof UnsupportedCodeException) {
            log.error("Caught UnsupportedCodeException = {}", originalException.getMessage(), originalException);
            updatedErrorCode = ErrorCodes.UNSUPPORTED;
            updatedErrorMessage = ErrorMessages.UNSUPPORTED_ERROR;
            status = HttpStatus.BAD_REQUEST;
        } else if (originalException instanceof ValidationException) {
            log.error("Caught ValidationException = {}", originalException.getMessage(), originalException);
            updatedErrorCode = ErrorCodes.VALIDATION_EXCEPTION;
            updatedErrorMessage = ErrorMessages.VALIDATION_ERROR;
            status = HttpStatus.BAD_REQUEST;
        } else {
            log.error("Caught UnknownException = {}", originalException.getMessage(), originalException);
            updatedErrorCode = ErrorCodes.UNKNOWN;
            updatedErrorMessage = ErrorMessages.UNKNOWN_ERROR;
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        log.info("Response errorCode {} changed to {}", errorCode, updatedErrorCode);
        response.setErrorCode(updatedErrorCode);

        log.info("Response code {} sent changed to {}", code, updatedCode);
        response.setCode(updatedCode);

        log.info("Response errorMessage {} changed to {}", errorMessage, updatedErrorMessage);
        response.setErrorMessage(updatedErrorMessage);

        response = modifySystemTimeResponseService.modify(response);
        response = modifyUuidResponseService.modify(response);

        log.error("Response {} sent due to {}", response, originalException.getClass().getSimpleName());
        return new ResponseEntity<>(response, status);
    }
}
