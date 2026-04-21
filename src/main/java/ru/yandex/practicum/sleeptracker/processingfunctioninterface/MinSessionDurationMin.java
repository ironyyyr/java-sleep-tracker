package ru.yandex.practicum.sleeptracker.processingfunctioninterface;

import ru.yandex.practicum.sleeptracker.entity.SleepSession;

import java.time.Duration;
import java.util.List;

public interface MinSessionDurationMin {
    Integer minSessionDurationMin(List<SleepSession> sleepSessions);
}
