package ru.urfu.SecondLabTask.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.urfu.SecondLabTask.enums.Codes;
import ru.urfu.SecondLabTask.enums.ErrorCodes;
import ru.urfu.SecondLabTask.enums.ErrorMessages;
import ru.urfu.SecondLabTask.exceptions.CustomWrappedException;
import ru.urfu.SecondLabTask.models.Request;
import ru.urfu.SecondLabTask.models.Response;
import ru.urfu.SecondLabTask.service.ResponseService;
import ru.urfu.SecondLabTask.validation.RequestValidationService;


@Slf4j
@RestController
@AllArgsConstructor
public class EventController {
    private final RequestValidationService requestValidationService;
    private final ResponseService responseService;


    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {
        Response response = this.responseService.buildResponse(request.getUid(), request.getOperationUid(),
                Codes.SUCCESS, ErrorCodes.EMPTY, ErrorMessages.EMPTY_ERROR);
        try {
            requestValidationService.isValid(bindingResult);
            log.info("Request {} is valid", request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new CustomWrappedException(e, response);
        }
    }

}
