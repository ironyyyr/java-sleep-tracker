package ru.yandex.practicum.sleeptracker.implementation;

import ru.yandex.practicum.sleeptracker.entity.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.entity.SleepQualities;
import ru.yandex.practicum.sleeptracker.entity.SleepSession;

import java.util.List;
import java.util.function.Function;

public class CountBadSleepQuality implements Function<List<SleepSession>, SleepAnalysisResult> {
    private final String MESSAGE = "Количество ночей с плохим качеством сна: ";

    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {
        double averageDuration =
                sleepSessions.stream()
                        .filter(sleepSession -> sleepSession.getQuality() == SleepQualities.BAD)
                        .count();

        return new SleepAnalysisResult(
                MESSAGE,
                averageDuration
        );
    }
}
