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
    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {
        LocalTime owlSleepStartTime = LocalTime.of(23, 0);
        LocalTime owlSleepEndTime = LocalTime.of(9, 0);

        LocalTime larkSleepStartTime = LocalTime.of(22, 0);
        LocalTime larkSleepEndTime = LocalTime.of(7, 0);

        Map<SleepStatuses, Long> pair = sleepSessions.stream()
                .filter(SleepSession::isSleepStartsLaterThanSix)
                .filter(sleepSession -> !sleepSession.isDaytimeSleep())
                .map(sleepSession -> {
                    if (
                            sleepSession.getSleepStartTime().isAfter(owlSleepStartTime) &&
                                    sleepSession.getSleepEndTime().isAfter(owlSleepEndTime)
                    ) {
                        return SleepStatuses.OWL;
                    } else if (
                            sleepSession.getSleepStartTime().isBefore(larkSleepStartTime) &&
                                    sleepSession.getSleepEndTime().isBefore(larkSleepEndTime)
                    ) {
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
            return new SleepAnalysisResult("Ваш тип сна: ", SleepStatuses.PIGEON);
        }

        if (owls > larks && owls > pigeons) {
            return new SleepAnalysisResult("Ваш тип сна: ", SleepStatuses.OWL);
        }

        if (larks > owls && larks > pigeons) {
            return new SleepAnalysisResult("Ваш тип сна: ", SleepStatuses.LARK);
        }

        return new SleepAnalysisResult("Ваш тип сна: ", SleepStatuses.PIGEON);
    }

    ;
}
