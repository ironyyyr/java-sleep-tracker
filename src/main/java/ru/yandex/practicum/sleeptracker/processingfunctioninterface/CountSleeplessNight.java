package ru.yandex.practicum.sleeptracker.processingfunctioninterface;

import ru.yandex.practicum.sleeptracker.entity.SleepSession;

import java.util.List;

@FunctionalInterface
public interface CountSleeplessNight {
    Integer countSleeplessNight(List<SleepSession> sleepSessions);
}
