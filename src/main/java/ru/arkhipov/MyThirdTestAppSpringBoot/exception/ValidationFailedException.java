package ru.arkhipov.MyThirdTestAppSpringBoot.exception;

public class ValidationFailedException extends RuntimeException {
    public ValidationFailedException(String message) {
        super(message);
    }
}
