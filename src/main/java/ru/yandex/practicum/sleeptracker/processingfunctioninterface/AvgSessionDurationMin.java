package ru.yandex.practicum.sleeptracker.processingfunctioninterface;

import ru.yandex.practicum.sleeptracker.entity.SleepSession;

import java.util.List;

public interface AvgSessionDurationMin {
    Integer avgSessionDurationMin(List<SleepSession> sleepSessions);
}
