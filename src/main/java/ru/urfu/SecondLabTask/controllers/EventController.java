package ru.urfu.SecondLabTask.controllers;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.urfu.SecondLabTask.exception.ErrorResponse;
import ru.urfu.SecondLabTask.exception.UnsupportedCodeException;
import ru.urfu.SecondLabTask.exception.MyValidationException;
import ru.urfu.SecondLabTask.model.Request;
import ru.urfu.SecondLabTask.model.Response;
import ru.urfu.SecondLabTask.validation.EventValidation;
import ru.urfu.SecondLabTask.validation.UnsupportedUidValidationService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@AllArgsConstructor
public class EventController {
    private final EventValidation eventValidation;
    private final UnsupportedUidValidationService unsupportedUidValidationService;

    private static final Map<Class<? extends Exception>, ErrorResponse> exceptionMapping = new HashMap<>();

    static {
        exceptionMapping.put(UnsupportedCodeException.class, new ErrorResponse("UnsupportedCodeException", "Не поддерживаемая ошибка"));
        exceptionMapping.put(MyValidationException.class, new ErrorResponse("ValidationException", "Ошибка валидации"));
        exceptionMapping.put(ValidationException.class, new ErrorResponse("ValidationException", "Ошибка валидации"));
    }

    private ResponseEntity<Response> handleExceptions(Response response, Exception e, HttpStatus status) {
        response.setCode("failed");
        ErrorResponse error = exceptionMapping.getOrDefault(e.getClass(), new ErrorResponse("UnknownException", "Произошла непредвиденная ошибка"));
        response.setErrorCode(error.errorCode());
        response.setErrorMessage(error.errorMessage());
        return new ResponseEntity<>(response, status);
    }


    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(simpleDateFormat.format(new Date()))
                .code("success")
                .errorCode("")
                .errorMessage("")
                .build();

        try {
            unsupportedUidValidationService.isValid(request.getUid());
            eventValidation.isValid(bindingResult.getFieldError().toString());
        } catch (Exception e) {
            return handleExceptions(response, e, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}