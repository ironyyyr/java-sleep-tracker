package ru.yandex.practicum.sleeptracker.processingfunction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.entity.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.entity.SleepSession;
import ru.yandex.practicum.sleeptracker.implementation.CountBadSleepQuality;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountBadSleepQualityTest {
    private static List<SleepSession> sleepSessions;
    private static SleepAnalysisResult sleepAnalysisResult;
    private final CountBadSleepQuality countBadSleepQuality = new CountBadSleepQuality();

    @BeforeEach
    void setUp() {
        SleepSession sl1 = new SleepSession("01.10.25 23:15;02.10.25 07:30;GOOD");
        SleepSession sl2 = new SleepSession("02.10.25 23:50;03.10.25 06:40;NORMAL");

        sleepSessions = new ArrayList<>(List.of(sl1, sl2));
    }

    @Test
    void successAvgSessionDurationTest() {
        Double correctBadCountSleepSessions = 0.0;

        sleepAnalysisResult = countBadSleepQuality.apply(sleepSessions);
        assertEquals(
                correctBadCountSleepSessions,
                sleepAnalysisResult.getAnalysisParam(),
                "Расчет средней длительности сна проводится некорректно после обновления" +
                        "ожидаемое значение: " + correctBadCountSleepSessions +
                        "имеем: " + sleepAnalysisResult.getAnalysisParam()
        );
    }

    @Test
    void successAvgSessionDurationAfterUpdateTest() {
        SleepSession sl3 = new SleepSession("03.10.25 14:10;03.10.25 15:00;BAD");
        sleepSessions.add(sl3);
        Double newCorrectBadCountSleepSessions = 1.0;

        sleepAnalysisResult = countBadSleepQuality.apply(sleepSessions);
        assertEquals(
                newCorrectBadCountSleepSessions,
                sleepAnalysisResult.getAnalysisParam(),
                "Расчет средней длительности сна проводится некорректно после обновления" +
                        "ожидаемое значение: " + newCorrectBadCountSleepSessions +
                        "имеем: " + sleepAnalysisResult.getAnalysisParam()
        );
    }
}
