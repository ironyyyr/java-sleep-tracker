package ru.yandex.practicum.sleeptracker.processingfunction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.entity.SleepSession;
import ru.yandex.practicum.sleeptracker.interfaceimplementation.MaxSessionDurationMinImpl;
import ru.yandex.practicum.sleeptracker.processingfunctioninterface.MaxSessionDurationMin;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxSessionDurationMinTest {
    private static List<SleepSession> sleepSessions;
    private static MaxSessionDurationMin max;

    @BeforeEach
    void setUp() {
        SleepSession sl1 = new SleepSession("01.10.25 23:15;02.10.25 07:30;GOOD");
        SleepSession sl2 = new SleepSession("02.10.25 23:50;03.10.25 06:40;NORMAL");
        sleepSessions = new ArrayList<>(List.of(sl1, sl2));

        max = MaxSessionDurationMinImpl.maxSessionDurationMin();
    }

    @Test
    void successMaxSessionDurationTest() {
        Integer correctMaxSleepDurationSession = 495;
        assertEquals(
                correctMaxSleepDurationSession,
                max.maxSessionDurationMin(sleepSessions),
                "Расчет средней длительности сна проводится некорректно после обновления" +
                        "ожидаемое значение: " + correctMaxSleepDurationSession +
                        "имеем: " + max.maxSessionDurationMin(sleepSessions)
        );
    }

    @Test
    void successAvgSessionDurationAfterUpdateTest() {
        SleepSession sl3 = new SleepSession("02.10.25 14:10;03.10.25 15:00;BAD");
        sleepSessions.add(sl3);
        Integer newCorrectMaxSleepDurationSession = 1490;

        assertEquals(
                newCorrectMaxSleepDurationSession,
                max.maxSessionDurationMin(sleepSessions),
                "Расчет средней длительности сна проводится некорректно после обновления" +
                        "ожидаемое значение: " + newCorrectMaxSleepDurationSession +
                        "имеем: " + max.maxSessionDurationMin(sleepSessions)
        );
    }
}
