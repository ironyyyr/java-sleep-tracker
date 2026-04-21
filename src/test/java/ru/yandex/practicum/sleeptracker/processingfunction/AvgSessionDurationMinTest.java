package ru.yandex.practicum.sleeptracker.processingfunction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.entity.SleepSession;
import ru.yandex.practicum.sleeptracker.interfaceimplementation.AvgSessionDurationMinImpl;
import ru.yandex.practicum.sleeptracker.processingfunctioninterface.AvgSessionDurationMin;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AvgSessionDurationMinTest {

    private static List<SleepSession> sleepSessions;
    private static AvgSessionDurationMin avg;

    @BeforeEach
    void setUp() {
        SleepSession sl1 = new SleepSession("01.10.25 23:15;02.10.25 07:30;GOOD");
        SleepSession sl2 = new SleepSession("02.10.25 23:50;03.10.25 06:40;NORMAL");
        sleepSessions = new ArrayList<>(List.of(sl1, sl2));

        avg = AvgSessionDurationMinImpl.avgSessionDurationMin();
    }

    @Test
    void successAvgSessionDurationTest() {
        Integer correctSleepDuration = 452;
        assertEquals(
                correctSleepDuration,
                avg.avgSessionDurationMin(sleepSessions),
                "Расчет средней длительности сна проводится некорректно после обновления" +
                        "ожидаемое значение: " + correctSleepDuration +
                        "имеем: " + avg.avgSessionDurationMin(sleepSessions)
        );
    }

    @Test
    void successAvgSessionDurationAfterUpdateTest() {
        SleepSession sl3 = new SleepSession("03.10.25 14:10;03.10.25 15:00;NORMAL");
        sleepSessions.add(sl3);
        Integer newCorrectSleepDuration = 318;

        assertEquals(
                newCorrectSleepDuration,
                avg.avgSessionDurationMin(sleepSessions),
                "Расчет средней длительности сна проводится некорректно после обновления" +
                        "ожидаемое значение: " + newCorrectSleepDuration +
                        "имеем: " + avg.avgSessionDurationMin(sleepSessions)
        );
    }
}
