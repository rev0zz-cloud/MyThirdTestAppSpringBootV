package ru.arkhipov.MyThirdTestAppSpringBoot.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCodes {

    EMPTY(""),
    UNKNOWN_EXCEPTION("UnknownException"),
    UNSUPPORTED_EXCEPTION("UnsupportedException"),
    VALIDATION_EXCEPTION("ValidationException"),    ;

    private final String name;

    ErrorCodes(String name) {
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