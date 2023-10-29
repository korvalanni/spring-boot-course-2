package ru.urfu.SecondLabTask.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import ru.urfu.SecondLabTask.constants.CodesConstants;

public enum Codes {
    SUCCESS(CodesConstants.SUCCESS),
    FAILED(CodesConstants.FAILED);

    private final String name;

    Codes(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
