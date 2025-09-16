package ru.kpfu.itis.springpractice.experiment.data.exception;

public class UnsuccessfulNoteAdding extends RuntimeException {
    public UnsuccessfulNoteAdding(String message) {
        super(message);
    }
}
