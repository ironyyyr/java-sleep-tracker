package ru.yandex.practicum.sleeptracker.implementation;

import ru.yandex.practicum.sleeptracker.entity.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.entity.SleepSession;

import java.util.List;
import java.util.function.Function;

public class AverageSleepDuration implements Function<List<SleepSession>, SleepAnalysisResult> {
    private final String MESSAGE = "Средняя длительность сна: ";

    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {
        double averageDuration =
                sleepSessions.stream()
                        .mapToInt(sl -> Math.toIntExact(sl.getSleepDuration().toMinutes()))
                        .average()
                        .orElse(0);

        return new SleepAnalysisResult(
                MESSAGE,
                Math.round(averageDuration * 100) / 100.0
        );
    }
}
