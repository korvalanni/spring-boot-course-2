package ru.urfu.SecondLabTask.validation;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.urfu.SecondLabTask.exception.UnsupportedCodeException;

@Service
public interface EventValidation {
    void isValid(BindingResult bindingResult) throws UnsupportedCodeException;
}
