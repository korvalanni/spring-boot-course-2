package ru.urfu.SecondLabTask.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import ru.urfu.SecondLabTask.constants.SystemsConstants;

public enum Systems {
    ENTERPRISE(SystemsConstants.ENTERPRISE_RESOURCE_PLANNING),
    CUSTOMER(SystemsConstants.CUSTOMER_RELATIONSHIP_MANAGEMENT),
    WAREHOUSE(SystemsConstants.WAREHOUSE_MANAGEMENT_SYSTEM);

    private final String message;

    Systems(String message) {
        this.message = message;
    }

    @JsonValue
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }

}
