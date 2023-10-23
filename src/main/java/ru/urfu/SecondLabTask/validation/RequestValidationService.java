package ru.urfu.SecondLabTask.validation;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.urfu.SecondLabTask.exception.ErrorMessageConstants;
import ru.urfu.SecondLabTask.exception.UnsupportedCodeException;
import ru.urfu.SecondLabTask.exception.ValidationException;

@Service
public class RequestValidationService implements EventValidation {
    @Override
    public void isValid(BindingResult bindingResult) throws ValidationException {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            String uidErrorMessage = ErrorMessageConstants.UNSUPPORTED_ERROR.getMessage();
            if (uidErrorMessage.equals(errorMessage)) {
                throw new UnsupportedCodeException(uidErrorMessage);
            }
            throw new ValidationException(errorMessage);
        }
    }
}
