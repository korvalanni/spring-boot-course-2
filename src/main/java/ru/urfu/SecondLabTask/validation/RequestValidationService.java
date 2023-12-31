package ru.urfu.SecondLabTask.validation;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.urfu.SecondLabTask.constants.ErrorMessagesConstants;
import ru.urfu.SecondLabTask.exception.UnsupportedCodeException;
import ru.urfu.SecondLabTask.exception.ValidationException;

@Service
public class RequestValidationService implements EventValidation {
    @Override
    public void isValid(BindingResult bindingResult) throws ValidationException {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            String uidErrorMessage = ErrorMessagesConstants.UNSUPPORTED_ERROR;
            if (uidErrorMessage.equals(errorMessage)) {
                throw new UnsupportedCodeException(uidErrorMessage);
            }
            throw new ValidationException(errorMessage);
        }
    }
}
