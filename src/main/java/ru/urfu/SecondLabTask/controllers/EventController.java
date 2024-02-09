package ru.urfu.SecondLabTask.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.urfu.SecondLabTask.constants.ErrorMessagesConstants;
import ru.urfu.SecondLabTask.enums.Codes;
import ru.urfu.SecondLabTask.enums.ErrorCodes;
import ru.urfu.SecondLabTask.enums.ErrorMessages;
import ru.urfu.SecondLabTask.exceptions.UnknownException;
import ru.urfu.SecondLabTask.exceptions.UnsupportedCodeException;
import ru.urfu.SecondLabTask.exceptions.ValidationException;
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
        String uid = request.getUid();
        String operationUid = request.getOperationUid();

        try {
            requestValidationService.isValid(bindingResult, uid, operationUid);
        } catch (UnsupportedCodeException ex) {
            log.error("UnsupportedException caught: {}", ex.toString());
            throw ex;
        } catch (ValidationException ex) {
            log.error("ValidationException caught: {}", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Exception caught: {}", ex.toString());
            log.error("UnknownException created: {}", ex.toString());

            throw new UnknownException(ErrorMessagesConstants.UNKNOWN_ERROR, ErrorCodes.UNKNOWN, ErrorMessages.UNKNOWN_ERROR,
                    uid, operationUid);
        }

        log.info("Request {} is valid", request);

        Response response = this.responseService.buildResponse(request.getUid(), request.getOperationUid(),
                Codes.SUCCESS, ErrorCodes.EMPTY, ErrorMessages.EMPTY_ERROR);

        log.info("Response {} created", response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
