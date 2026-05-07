package ru.yandex.practicum.sleeptracker.processingfunction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.entity.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.entity.SleepSession;
import ru.yandex.practicum.sleeptracker.implementation.MaxSleepDuration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxSessionDurationMinTest {
    private static List<SleepSession> sleepSessions;
    private static SleepAnalysisResult sleepAnalysisResult;
    private final MaxSleepDuration maxSleepDuration = new MaxSleepDuration();

    @BeforeEach
    void setUp() {
        SleepSession sl1 = new SleepSession("01.10.25 23:15;02.10.25 07:30;GOOD");
        SleepSession sl2 = new SleepSession("02.10.25 23:50;03.10.25 06:40;NORMAL");

        sleepSessions = new ArrayList<>(List.of(sl1, sl2));
    }

    @Test
    void successMaxSessionDurationTest() {
        Double correctMaxSleepDurationSession = 495.0;

        sleepAnalysisResult = maxSleepDuration.apply(sleepSessions);
        assertEquals(
                correctMaxSleepDurationSession,
                sleepAnalysisResult.getAnalysisParam(),
                "Расчет средней длительности сна проводится некорректно после обновления" +
                        "ожидаемое значение: " + correctMaxSleepDurationSession +
                        "имеем: " + sleepAnalysisResult.getAnalysisParam()
        );
    }

    @Test
    void successAvgSessionDurationAfterUpdateTest() {
        SleepSession sl3 = new SleepSession("02.10.25 14:10;03.10.25 15:00;BAD");
        sleepSessions.add(sl3);
        Double newCorrectMaxSleepDurationSession = 1490.0;

        sleepAnalysisResult = maxSleepDuration.apply(sleepSessions);
        assertEquals(
                newCorrectMaxSleepDurationSession,
                sleepAnalysisResult.getAnalysisParam(),
                "Расчет средней длительности сна проводится некорректно после обновления" +
                        "ожидаемое значение: " + newCorrectMaxSleepDurationSession +
                        "имеем: " + sleepAnalysisResult.getAnalysisParam()
        );
    }
}
