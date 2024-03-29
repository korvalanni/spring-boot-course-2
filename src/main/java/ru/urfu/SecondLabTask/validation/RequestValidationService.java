package ru.urfu.SecondLabTask.validation;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.urfu.SecondLabTask.constants.ErrorMessagesConstants;
import ru.urfu.SecondLabTask.enums.ErrorCodes;
import ru.urfu.SecondLabTask.enums.ErrorMessages;
import ru.urfu.SecondLabTask.exceptions.UnsupportedCodeException;
import ru.urfu.SecondLabTask.exceptions.ValidationException;

@Service
public class RequestValidationService implements EventValidation {
    @Override
    public void isValid(BindingResult bindingResult, String uid, String operationUid) throws ValidationException {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            String uidErrorMessage = ErrorMessagesConstants.UNSUPPORTED_ERROR;
            if (uidErrorMessage.equals(errorMessage)) {
                throw new UnsupportedCodeException(uidErrorMessage, ErrorCodes.UNSUPPORTED,
                        ErrorMessages.UNSUPPORTED_ERROR, uid, operationUid);
            }
            throw new ValidationException(errorMessage, ErrorCodes.VALIDATION_EXCEPTION,
                    ErrorMessages.VALIDATION_ERROR, uid, operationUid);
        }
    }
}
