package ru.urfu.SecondLabTask.exception;


public abstract class RepresentableException extends RuntimeException {
    public RepresentableException(String message) {
        super(message);
    }
}
