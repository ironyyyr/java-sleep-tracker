package ru.yandex.practicum.sleeptracker.processingfunction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.entity.SleepSession;
import ru.yandex.practicum.sleeptracker.interfaceimplementation.SizeImpl;
import ru.yandex.practicum.sleeptracker.processingfunctioninterface.Size;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SizeTest {
    private static List<SleepSession> sleepSessions;
    private static Size size;

    @BeforeEach
    void setUp() {
        SleepSession sl1 = new SleepSession("01.10.25 23:15;02.10.25 07:30;GOOD");
        SleepSession sl2 = new SleepSession("02.10.25 23:50;03.10.25 06:40;NORMAL");
        sleepSessions = new ArrayList<>(List.of(sl1, sl2));

        size = SizeImpl.size();
    }

    @Test
    void successMaxSessionDurationTest() {
        Integer correctSleepListSize = 2;
        assertEquals(
                correctSleepListSize,
                size.size(sleepSessions),
                "Расчет средней длительности сна проводится некорректно после обновления" +
                        "ожидаемое значение: " + correctSleepListSize +
                        "имеем: " + size.size(sleepSessions)
        );
    }

    @Test
    void successAvgSessionDurationAfterUpdateTest() {
        SleepSession sl3 = new SleepSession("03.10.25 14:10;03.10.25 15:00;BAD");
        sleepSessions.add(sl3);
        Integer newCorrectSleepListSize = 3;

        assertEquals(
                newCorrectSleepListSize,
                size.size(sleepSessions),
                "Расчет средней длительности сна проводится некорректно после обновления" +
                        "ожидаемое значение: " + newCorrectSleepListSize +
                        "имеем: " + size.size(sleepSessions)
        );
    }
}
