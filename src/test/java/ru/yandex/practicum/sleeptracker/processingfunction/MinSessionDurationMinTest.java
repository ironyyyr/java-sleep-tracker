package ru.yandex.practicum.sleeptracker.processingfunction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.entity.SleepSession;
import ru.yandex.practicum.sleeptracker.interfaceimplementation.MinSessionDurationMinImpl;
import ru.yandex.practicum.sleeptracker.processingfunctioninterface.MinSessionDurationMin;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinSessionDurationMinTest {
    private static List<SleepSession> sleepSessions;
    private static MinSessionDurationMin min;

    @BeforeEach
    void setUp() {
        SleepSession sl1 = new SleepSession("01.10.25 23:15;02.10.25 07:30;GOOD");
        SleepSession sl2 = new SleepSession("02.10.25 23:50;03.10.25 06:40;NORMAL");
        sleepSessions = new ArrayList<>(List.of(sl1, sl2));

        min = MinSessionDurationMinImpl.minSessionDurationMin();
    }

    @Test
    void successMaxSessionDurationTest() {
        Integer correctMinSleepDuration = 410;
        assertEquals(
                correctMinSleepDuration,
                min.minSessionDurationMin(sleepSessions),
                "Расчет средней длительности сна проводится некорректно после обновления" +
                        "ожидаемое значение: " + correctMinSleepDuration +
                        "имеем: " + min.minSessionDurationMin(sleepSessions)
        );
    }

    @Test
    void successAvgSessionDurationAfterUpdateTest() {
        SleepSession sl3 = new SleepSession("03.10.25 14:10;03.10.25 15:00;BAD");
        sleepSessions.add(sl3);
        Integer newCorrectMinSleepDuration = 50;

        assertEquals(
                newCorrectMinSleepDuration,
                min.minSessionDurationMin(sleepSessions),
                "Расчет средней длительности сна проводится некорректно после обновления" +
                        "ожидаемое значение: " + newCorrectMinSleepDuration +
                        "имеем: " + min.minSessionDurationMin(sleepSessions)
        );
    }
}
