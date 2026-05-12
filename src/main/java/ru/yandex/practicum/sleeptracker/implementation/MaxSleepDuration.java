package ru.yandex.practicum.sleeptracker.implementation;

import ru.yandex.practicum.sleeptracker.entity.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.entity.SleepSession;

import java.util.List;
import java.util.function.Function;

public class MaxSleepDuration implements Function<List<SleepSession>, SleepAnalysisResult> {
    private final String MESSAGE = "Максимальная длительность сна: ";

    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {
        double maxSleepDuration = sleepSessions.stream()
                .max((SleepSession sl1, SleepSession sl2) -> sl1.getSleepDuration().compareTo(sl2.getSleepDuration()))
                .get().getSleepDuration().toMinutes();

        return new SleepAnalysisResult(
                MESSAGE,
                maxSleepDuration
        );
    }
}