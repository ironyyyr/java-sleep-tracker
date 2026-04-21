package ru.yandex.practicum.sleeptracker.interfaceimplementation;

import ru.yandex.practicum.sleeptracker.processingfunctioninterface.Size;

public class SizeImpl {
    public static Size size() {
        return sleepSessions -> sleepSessions.size();
    }
}
