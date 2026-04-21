package ru.yandex.practicum.sleeptracker.interfaceimplementation;

import ru.yandex.practicum.sleeptracker.entity.SleepSession;
import ru.yandex.practicum.sleeptracker.processingfunctioninterface.GetUserSleepStatus;

import java.time.LocalTime;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GetUserSleepStatusImpl {
    public static GetUserSleepStatus getUserSleepStatus() {
        LocalTime owlSleepStartTime = LocalTime.of(23, 0);
        LocalTime owlSleepEndTime = LocalTime.of(9, 0);

        LocalTime larkSleepStartTime = LocalTime.of(22, 0);
        LocalTime larkSleepEndTime = LocalTime.of(7, 0);

        return sleepSessions -> {
            Map<String, Long> pair = sleepSessions.stream()
                    .filter(SleepSession::isSleepStartsLaterThanSix)
                    .filter(sleepSession -> !sleepSession.isDaytimeSleep())
                    .map(sleepSession -> {
                        if (
                                sleepSession.getSleepStartTime().isAfter(owlSleepStartTime) &&
                                        sleepSession.getSleepEndTime().isAfter(owlSleepEndTime)
                        ) {
                            return "Сова";
                        } else if (
                                sleepSession.getSleepStartTime().isBefore(larkSleepStartTime) &&
                                        sleepSession.getSleepEndTime().isBefore(larkSleepEndTime)
                        ) {
                            return "Жаворонок";
                        } else {
                            return "Голубь";
                        }
                    })
                    .collect(Collectors.groupingBy(
                            Function.identity(),
                            Collectors.counting()
                    ));

            long owls = pair.getOrDefault("Сова", 0L);
            long larks = pair.getOrDefault("Жаворонок", 0L);
            long pigeons = pair.getOrDefault("Голубь", 0L);

            if (owls == larks) {
                return "Голубь";
            }

            if (owls > larks && owls > pigeons) {
                return "Сова";
            }

            if (larks > owls && larks > pigeons) {
                return "Жаворонок";
            }

            return "Голубь";
        };
    }
}
