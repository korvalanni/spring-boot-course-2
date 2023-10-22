package ru.urfu.SecondLabTask.validation;

import org.springframework.stereotype.Service;
import ru.urfu.SecondLabTask.exception.UnsupportedCodeException;

@Service
public class UnsupportedUidValidationService implements EventValidation{

    public void isValid(String uid) throws UnsupportedCodeException {
        if(uid.equals("123"))
            throw new UnsupportedCodeException("uid равен 123");
    }
}
