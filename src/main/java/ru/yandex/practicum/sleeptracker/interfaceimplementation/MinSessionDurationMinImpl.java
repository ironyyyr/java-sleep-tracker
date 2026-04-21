package ru.yandex.practicum.sleeptracker.interfaceimplementation;

import ru.yandex.practicum.sleeptracker.processingfunctioninterface.MinSessionDurationMin;

public class MinSessionDurationMinImpl {
    public static MinSessionDurationMin minSessionDurationMin() {
        return sleepSessions -> Math.toIntExact(sleepSessions.stream()
                .min((sl1, sl2) ->
                        sl1.getSleepDuration().compareTo(sl2.getSleepDuration())
                ).get().getSleepDuration().toMinutes());
    }
}
