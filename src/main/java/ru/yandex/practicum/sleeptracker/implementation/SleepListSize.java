package ru.yandex.practicum.sleeptracker.implementation;

import ru.yandex.practicum.sleeptracker.entity.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.entity.SleepSession;

import java.util.List;
import java.util.function.Function;

public class SleepListSize implements Function<List<SleepSession>, SleepAnalysisResult> {
    private static final String MESSAGE = "Количество сессий сна в логе: ";

    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {
        return new SleepAnalysisResult(
                MESSAGE,
                (double) sleepSessions.size()
        );
    }
}
