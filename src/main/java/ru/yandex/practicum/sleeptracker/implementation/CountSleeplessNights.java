package ru.yandex.practicum.sleeptracker.implementation;

import ru.yandex.practicum.sleeptracker.entity.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.entity.SleepSession;

import java.util.List;
import java.util.function.Function;

public class CountSleeplessNights implements Function<List<SleepSession>, SleepAnalysisResult> {
    private final String MESSAGE = "Количество бессонных ночей: ";

    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {
        double sleeplessNightsCount =
                sleepSessions.stream()
                        .filter(SleepSession::isSleepEndsThatDay)
                        .filter(SleepSession::isSleepStartsLaterThanSix)
                        .count();

        return new SleepAnalysisResult(
                MESSAGE,
                sleeplessNightsCount
        );
    }
}
