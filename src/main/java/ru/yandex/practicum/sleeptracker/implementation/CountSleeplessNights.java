package ru.yandex.practicum.sleeptracker.implementation;

import ru.yandex.practicum.sleeptracker.entity.AnalysisMessages;
import ru.yandex.practicum.sleeptracker.entity.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.entity.SleepSession;

import java.util.List;
import java.util.function.Function;

public class CountSleeplessNights implements Function<List<SleepSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {
        double sleeplessNightsCount =
                sleepSessions.stream()
                        .filter(SleepSession::isSleepEndsThatDay)
                        .filter(SleepSession::isSleepStartsLaterThanSix)
                        .count();

        return new SleepAnalysisResult(
                AnalysisMessages.getMessage("countSleeplessNights"),
                sleeplessNightsCount
        );
    }
}
