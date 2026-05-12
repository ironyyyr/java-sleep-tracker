package ru.yandex.practicum.sleeptracker.processingfunction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.entity.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.entity.SleepSession;
import ru.yandex.practicum.sleeptracker.implementation.AverageSleepDuration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AvgSessionDurationMinTest {

    private static List<SleepSession> sleepSessions;
    private static SleepAnalysisResult sleepAnalysisResult;
    private final AverageSleepDuration averageSleepDuration = new AverageSleepDuration();

    @BeforeEach
    void setUp() {
        SleepSession sl1 = new SleepSession("01.10.25 23:15;02.10.25 07:30;GOOD");
        SleepSession sl2 = new SleepSession("02.10.25 23:50;03.10.25 06:40;NORMAL");

        sleepSessions = new ArrayList<>(List.of(sl1, sl2));
    }

    @Test
    void successAverageSleepDurationTest() {
        Double correctSleepDuration = 452.5;

        sleepAnalysisResult = averageSleepDuration.apply(sleepSessions);
        assertEquals(
                correctSleepDuration,
                sleepAnalysisResult.getAnalysisParam(),
                "Расчет средней длительности сна проводится некорректно после обновления\n" +
                        "ожидаемое значение: " + correctSleepDuration +
                        ", имеем: " + sleepAnalysisResult.getAnalysisParam()
        );
    }

    @Test
    void successAverageSleepDurationAfterUpdateTest() {
        SleepSession sl3 = new SleepSession("03.10.25 14:10;03.10.25 15:00;NORMAL");
        sleepSessions.add(sl3);
        Double newCorrectSleepDuration = 318.33;

        sleepAnalysisResult = averageSleepDuration.apply(sleepSessions);
        assertEquals(
                newCorrectSleepDuration,
                sleepAnalysisResult.getAnalysisParam(),
                "Расчет средней длительности сна проводится некорректно после обновления\n" +
                        "ожидаемое значение: " + newCorrectSleepDuration +
                        ", имеем: " + sleepAnalysisResult.getAnalysisParam()
        );
    }
}
