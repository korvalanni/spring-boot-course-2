package ru.urfu.SecondLabTask.exceptions;

import ru.urfu.SecondLabTask.models.Response;

public class CustomWrappedException extends RuntimeException {
    private final RuntimeException originalException;
    private final Response response;

    public CustomWrappedException(RuntimeException originalException, Response response) {
        this.originalException = originalException;
        this.response = response;
    }

    public RuntimeException getOriginalException() {
        return originalException;
    }

    public Response getResponse() {
        return response;
    }
}
