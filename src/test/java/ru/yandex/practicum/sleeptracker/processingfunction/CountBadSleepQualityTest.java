package ru.yandex.practicum.sleeptracker.processingfunction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.entity.SleepSession;
import ru.yandex.practicum.sleeptracker.interfaceimplementation.CountBadSleepQualityImpl;
import ru.yandex.practicum.sleeptracker.processingfunctioninterface.CountBadSleepQuality;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountBadSleepQualityTest {
    private static List<SleepSession> sleepSessions;
    private static CountBadSleepQuality count;

    @BeforeEach
    void setUp() {
        SleepSession sl1 = new SleepSession("01.10.25 23:15;02.10.25 07:30;GOOD");
        SleepSession sl2 = new SleepSession("02.10.25 23:50;03.10.25 06:40;NORMAL");
        sleepSessions = new ArrayList<>(List.of(sl1, sl2));

        count = CountBadSleepQualityImpl.countBadSleepQuality();
    }

    @Test
    void successAvgSessionDurationTest() {
        Integer correctBadCountSleepSessions = 0;
        assertEquals(
                correctBadCountSleepSessions,
                count.countBadSleepQuality(sleepSessions),
                "Расчет средней длительности сна проводится некорректно после обновления" +
                        "ожидаемое значение: " + correctBadCountSleepSessions +
                        "имеем: " + count.countBadSleepQuality(sleepSessions)
        );
    }

    @Test
    void successAvgSessionDurationAfterUpdateTest() {
        SleepSession sl3 = new SleepSession("03.10.25 14:10;03.10.25 15:00;BAD");
        sleepSessions.add(sl3);
        Integer newCorrectBadCountSleepSessions = 1;

        assertEquals(
                newCorrectBadCountSleepSessions,
                count.countBadSleepQuality(sleepSessions),
                "Расчет средней длительности сна проводится некорректно после обновления" +
                        "ожидаемое значение: " + newCorrectBadCountSleepSessions +
                        "имеем: " + count.countBadSleepQuality(sleepSessions)
        );
    }
}
