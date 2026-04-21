package ru.yandex.practicum.sleeptracker.interfaceimplementation;

import ru.yandex.practicum.sleeptracker.processingfunctioninterface.CountBadSleepQuality;

public class CountBadSleepQualityImpl {
    public static CountBadSleepQuality countBadSleepQuality() {
        return sleepSessions -> Math.toIntExact(sleepSessions.stream()
                .filter(sleepSession -> sleepSession.getQuality().equals("BAD"))
                .count());
    }
}
