package ru.yandex.practicum.sleeptracker.processingfunction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.entity.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.entity.SleepSession;
import ru.yandex.practicum.sleeptracker.implementation.MinSleepDuration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinSessionDurationMinTest {
    private static List<SleepSession> sleepSessions;
    private static SleepAnalysisResult sleepAnalysisResult;
    private final MinSleepDuration minSleepDuration = new MinSleepDuration();

    @BeforeEach
    void setUp() {
        SleepSession sl1 = new SleepSession("01.10.25 23:15;02.10.25 07:30;GOOD");
        SleepSession sl2 = new SleepSession("02.10.25 23:50;03.10.25 06:40;NORMAL");

        sleepSessions = new ArrayList<>(List.of(sl1, sl2));
    }

    @Test
    void successMaxSessionDurationTest() {
        Double correctMinSleepDuration = 410.0;

        sleepAnalysisResult = minSleepDuration.apply(sleepSessions);
        assertEquals(
                correctMinSleepDuration,
                sleepAnalysisResult.getAnalysisParam(),
                "Расчет средней длительности сна проводится некорректно после обновления\n" +
                        "ожидаемое значение: " + correctMinSleepDuration +
                        ", имеем: " + sleepAnalysisResult.getAnalysisParam()
        );
    }

    @Test
    void successAvgSessionDurationAfterUpdateTest() {
        SleepSession sl3 = new SleepSession("03.10.25 14:10;03.10.25 15:00;BAD");
        sleepSessions.add(sl3);
        Double newCorrectMinSleepDuration = 50.0;

        sleepAnalysisResult = minSleepDuration.apply(sleepSessions);
        assertEquals(
                newCorrectMinSleepDuration,
                sleepAnalysisResult.getAnalysisParam(),
                "Расчет средней длительности сна проводится некорректно после обновления\n" +
                        "ожидаемое значение: " + newCorrectMinSleepDuration +
                        ", имеем: " + sleepAnalysisResult.getAnalysisParam()
        );
    }
}
