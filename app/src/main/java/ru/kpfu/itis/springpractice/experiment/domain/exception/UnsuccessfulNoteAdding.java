package ru.kpfu.itis.springpractice.experiment.domain.exception;

public class UnsuccessfulNoteAdding extends RuntimeException {
    public UnsuccessfulNoteAdding(String message) {
        super(message);
    }
}
