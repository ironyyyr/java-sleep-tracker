package ru.yandex.practicum.sleeptracker.implementation;

import ru.yandex.practicum.sleeptracker.entity.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.entity.SleepSession;

import java.util.List;
import java.util.function.Function;

public class CountBadSleepQuality implements Function<List<SleepSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {
        double averageDuration =
                sleepSessions.stream()
                        .filter(sleepSession -> sleepSession.getQuality().equals("BAD"))
                        .count();

        return new SleepAnalysisResult("Количество ночей с плохим качеством сна: ", averageDuration);
    }
}
