package ru.yandex.practicum.sleeptracker.processingfunctioninterface;

import ru.yandex.practicum.sleeptracker.entity.SleepSession;

import java.time.LocalTime;
import java.util.List;

public interface AvgSessionDurationMin {
    Integer avgSessionDurationMin(List<SleepSession> sleepSessions);
}
