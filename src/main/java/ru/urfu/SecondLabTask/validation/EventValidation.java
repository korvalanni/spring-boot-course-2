package ru.urfu.SecondLabTask.validation;

import org.springframework.stereotype.Service;
import ru.urfu.SecondLabTask.exception.MyValidationException;

@Service
public interface EventValidation {
    void isValid(String message) throws MyValidationException;
}
