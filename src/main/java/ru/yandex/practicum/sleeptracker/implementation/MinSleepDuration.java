package ru.yandex.practicum.sleeptracker.implementation;

import ru.yandex.practicum.sleeptracker.entity.AnalysisMessages;
import ru.yandex.practicum.sleeptracker.entity.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.entity.SleepSession;

import java.util.List;
import java.util.function.Function;

public class MinSleepDuration implements Function<List<SleepSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {
        double minSleepDuration = sleepSessions.stream()
                .min((SleepSession sl1, SleepSession sl2) -> sl1.getSleepDuration().compareTo(sl2.getSleepDuration()))
                .get().getSleepDuration().toMinutes();

        return new SleepAnalysisResult(
                AnalysisMessages.getMessage("minSleepDuration"),
                minSleepDuration
        );
    }
}
