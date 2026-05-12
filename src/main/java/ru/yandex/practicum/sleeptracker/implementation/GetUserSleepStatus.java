package ru.yandex.practicum.sleeptracker.implementation;

import ru.yandex.practicum.sleeptracker.entity.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.entity.SleepSession;
import ru.yandex.practicum.sleeptracker.entity.SleepStatuses;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GetUserSleepStatus implements Function<List<SleepSession>, SleepAnalysisResult> {
    private final String MESSAGE = "Ваш тип сна: ";

    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {
        Map<SleepStatuses, Long> pair = sleepSessions.stream()
                .filter(sleepSession -> !sleepSession.isDaytimeSleep())
                .map(sleepSession -> {
                    boolean isOwl = isOwl(sleepSession);

                    boolean isLark = isLark(sleepSession);

                    if (isOwl) {
                        return SleepStatuses.OWL;
                    } else if (isLark) {
                        return SleepStatuses.LARK;
                    } else {
                        return SleepStatuses.PIGEON;
                    }
                })
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        long owls = pair.getOrDefault(SleepStatuses.OWL, 0L);
        long larks = pair.getOrDefault(SleepStatuses.LARK, 0L);
        long pigeons = pair.getOrDefault(SleepStatuses.PIGEON, 0L);

        if (owls == larks) {
            return new SleepAnalysisResult(MESSAGE, SleepStatuses.PIGEON);
        }

        if (owls > larks && owls > pigeons) {
            return new SleepAnalysisResult(MESSAGE, SleepStatuses.OWL);
        }

        if (larks > owls && larks > pigeons) {
            return new SleepAnalysisResult(MESSAGE, SleepStatuses.LARK);
        }

        return new SleepAnalysisResult(MESSAGE, SleepStatuses.PIGEON);
    }

    private boolean isOwl(SleepSession sleepSession) {
        LocalTime owlSleepStart = LocalTime.of(23, 0);
        LocalTime owlSleepEnd = LocalTime.of(9, 0);


        return (
                sleepSession.getSleepStartTime().isAfter(owlSleepStart) ||
                        sleepSession.getSleepStartTime().isBefore(sleepSession.getSleepEndTime())
        ) & sleepSession.getSleepEndTime().isAfter(owlSleepEnd);
    }

    private boolean isLark(SleepSession sleepSession) {
        LocalTime larkSleepStart = LocalTime.of(22, 0);
        LocalTime larkSleepEnd = LocalTime.of(7, 0);

        return sleepSession.getSleepStartTime().isBefore(larkSleepStart) &&
                sleepSession.getSleepEndTime().isBefore(larkSleepEnd);
    }
}
