package ru.yandex.practicum.sleeptracker.interfaceimplementation;

import ru.yandex.practicum.sleeptracker.entity.SleepSession;
import ru.yandex.practicum.sleeptracker.processingfunctioninterface.CountSleeplessNight;

public class CountSleeplessNightImpl {
    public static CountSleeplessNight countSleeplessNight() {
        return sleepSessions -> Math.toIntExact(sleepSessions.stream()
                .filter(SleepSession::isSleepEndsThatDay)
                .filter(SleepSession::isSleepStartsLaterThanSix)
                .count());
    }
}
