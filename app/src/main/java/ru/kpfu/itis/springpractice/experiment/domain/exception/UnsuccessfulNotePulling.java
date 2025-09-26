package ru.kpfu.itis.springpractice.experiment.domain.exception;

public class UnsuccessfulNotePulling extends RuntimeException {
    public UnsuccessfulNotePulling(String message) {
        super(message);
    }
}
