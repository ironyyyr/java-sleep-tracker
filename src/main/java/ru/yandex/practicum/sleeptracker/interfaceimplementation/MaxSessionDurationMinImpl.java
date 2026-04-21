package ru.yandex.practicum.sleeptracker.interfaceimplementation;

import ru.yandex.practicum.sleeptracker.processingfunctioninterface.MaxSessionDurationMin;

public class MaxSessionDurationMinImpl {
    public static MaxSessionDurationMin maxSessionDurationMin() {
        return sleepSessions -> Math.toIntExact(sleepSessions.stream()
                .max((sl1, sl2) ->
                        sl1.getSleepDuration().compareTo(sl2.getSleepDuration())
                ).get().getSleepDuration().toMinutes());
    }
}
