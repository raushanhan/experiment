package ru.kpfu.itis.springpractice.experiment.domain.exception;

public class AuthorizationException extends RuntimeException {
    public AuthorizationException(String message) {
        super(message);
    }
}
