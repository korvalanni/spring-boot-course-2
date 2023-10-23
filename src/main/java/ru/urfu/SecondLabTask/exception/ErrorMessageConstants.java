package ru.urfu.SecondLabTask.exception;

import lombok.Getter;

@Getter
public enum ErrorMessageConstants {

    VALIDATION_ERROR("Ошибка валидации"),
    UNSUPPORTED_ERROR("Не поддерживаемая ошибка"),
    UNKNOWN_ERROR("Произошла непредвиденная ошибка");

    private final String message;

    ErrorMessageConstants(String message) {
        this.message = message;
    }
}

