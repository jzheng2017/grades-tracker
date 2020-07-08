package com.grades.tracker.api.exceptions;

public class BadParameterException extends RuntimeException {
    public BadParameterException(String message) {
        super(message);
    }
}
