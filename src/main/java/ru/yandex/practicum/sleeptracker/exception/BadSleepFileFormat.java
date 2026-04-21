package ru.yandex.practicum.sleeptracker.exception;

public class BadSleepFileFormat extends RuntimeException {
    public BadSleepFileFormat(String message) {
        super(message);
    }
}
