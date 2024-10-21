package ru.arkhipov.MyThirdTestAppSpringBoot.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorMessages {

    EMPTY( ""),
    VALIDATION( "Oшибка валидации"),
    UNSUPPORTED( "Произошла непредвиденная ошибка"),
    UNKNOWN("He поддерживаемая ошибка");

    private final String description;

    ErrorMessages(String description) {
        this.description = description;
    }

    @JsonValue
    public String getName() {
        return description;
    }
}