package ru.yandex.practicum.sleeptracker.interfaceimplementation;

import ru.yandex.practicum.sleeptracker.entity.SleepSession;
import ru.yandex.practicum.sleeptracker.processingfunctioninterface.Size;

import java.util.List;

public class SizeImpl {
    public static Size size() {
        return sleepSessions -> sleepSessions.size();
    }
}
