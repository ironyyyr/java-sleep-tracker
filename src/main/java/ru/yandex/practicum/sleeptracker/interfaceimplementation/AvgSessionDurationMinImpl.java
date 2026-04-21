package ru.yandex.practicum.sleeptracker.interfaceimplementation;

import ru.yandex.practicum.sleeptracker.processingfunctioninterface.AvgSessionDurationMin;

public class AvgSessionDurationMinImpl {
    public static AvgSessionDurationMin avgSessionDurationMin() {
        return sleepSessions -> Math.toIntExact(
                sleepSessions.stream()
                        .mapToInt(sl ->
                                Math.toIntExact(sl.getSleepDuration().toMinutes())).sum()
        ) / sleepSessions.size();
    }
}
