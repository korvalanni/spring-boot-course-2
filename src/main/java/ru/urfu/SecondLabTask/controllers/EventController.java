package ru.urfu.SecondLabTask.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.urfu.SecondLabTask.exception.*;
import ru.urfu.SecondLabTask.model.Request;
import ru.urfu.SecondLabTask.model.Response;
import ru.urfu.SecondLabTask.util.DateTimeUtil;
import ru.urfu.SecondLabTask.validation.RequestValidationService;


import java.util.Date;


@RestController
@AllArgsConstructor
public class EventController {
    private final RequestValidationService requestValidationService;

    private Response buildResponse(String uid, String operationUid, Codes code,
                                   ErrorCodes errorCode, ErrorMessages errorMessage) {
        return Response.builder()
                .uid(uid)
                .operationUid(operationUid)
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(code)
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {


        try {
            requestValidationService.isValid(bindingResult);
        } catch (UnsupportedCodeException e) {
            return new ResponseEntity<>(
                    buildResponse(request.getUid(), request.getOperationUid(),
                            Codes.FAILED, ErrorCodes.UNSUPPORTED, ErrorMessages.UNSUPPORTED_ERROR),
                    HttpStatus.BAD_REQUEST);
        } catch (ValidationException e) {
            return new ResponseEntity<>(
                    buildResponse(request.getUid(), request.getOperationUid(), Codes.FAILED,
                            ErrorCodes.VALIDATION_EXCEPTION, ErrorMessages.VALIDATION_ERROR),
                    HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    buildResponse(request.getUid(), request.getOperationUid(),
                            Codes.FAILED, ErrorCodes.UNKNOWN, ErrorMessages.UNKNOWN_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(
                buildResponse(request.getUid(), request.getOperationUid(),
                        Codes.SUCCESS, ErrorCodes.EMPTY, ErrorMessages.EMPTY_ERROR),
                HttpStatus.OK);
    }
}
