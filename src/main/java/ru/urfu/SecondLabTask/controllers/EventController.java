package ru.urfu.SecondLabTask.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.urfu.SecondLabTask.exception.ErrorMessageConstants;
import ru.urfu.SecondLabTask.exception.UnsupportedCodeException;
import ru.urfu.SecondLabTask.exception.ValidationException;
import ru.urfu.SecondLabTask.model.Request;
import ru.urfu.SecondLabTask.model.Response;
import ru.urfu.SecondLabTask.validation.RequestValidationService;


import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@AllArgsConstructor
public class EventController {
    private final RequestValidationService requestValidationService;

    private Response buildResponse(String uid, String operationUid, String date, String code,
                                   String errorCode, String errorMessage) {
        return Response.builder()
                .uid(uid)
                .operationUid(operationUid)
                .systemTime(date)
                .code(code)
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        try {
            requestValidationService.isValid(bindingResult);
        } catch (UnsupportedCodeException e) {
            return new ResponseEntity<>(
                    buildResponse(request.getUid(), request.getOperationUid(), simpleDateFormat.format(new Date()),
                            "failed", "401", ErrorMessageConstants.UNSUPPORTED_ERROR.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
        catch (ValidationException e) {
            return new ResponseEntity<>(
                    buildResponse(request.getUid(), request.getOperationUid(), simpleDateFormat.format(new Date()),
                            "failed", "402", ErrorMessageConstants.VALIDATION_ERROR.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(
                    buildResponse(request.getUid(), request.getOperationUid(), simpleDateFormat.format(new Date()),
                            "failed", "500", ErrorMessageConstants.UNKNOWN_ERROR.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(buildResponse(request.getUid(), request.getOperationUid(),
                simpleDateFormat.format(new Date()), "success","",""), HttpStatus.OK);
    }
}
