package ru.yandex.practicum.sleeptracker.entity;

public class SleepAnalysisResult {
    private final String message;
    private final Object analysisParam;

    public SleepAnalysisResult(String message, Object analysisParam) {
        this.message = message;
        this.analysisParam = analysisParam;
    }

    public Object getAnalysisParam() {
        return analysisParam;
    }

    @Override
    public String toString() {
        return message + analysisParam;
    }
}
