package ru.yandex.practicum.sleeptracker.processingfunction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.entity.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.entity.SleepSession;
import ru.yandex.practicum.sleeptracker.implementation.CountSleeplessNights;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountSleeplessNightTest {
    private static List<SleepSession> sleepSessions;
    private static SleepAnalysisResult sleepAnalysisResult;
    private final CountSleeplessNights countSleeplessNights = new CountSleeplessNights();

    @BeforeEach
    void setUp() {
        SleepSession sl1 = new SleepSession("01.10.25 23:15;02.10.25 07:30;GOOD");
        SleepSession sl2 = new SleepSession("02.10.25 23:50;03.10.25 06:40;NORMAL");

        sleepSessions = new ArrayList<>(List.of(sl1, sl2));
    }

    @Test
    void getZeroSleeplessNight() {
        Double correctQuantityOfSleeplessNight = 0.0;

        sleepAnalysisResult = countSleeplessNights.apply(sleepSessions);
        assertEquals(
                correctQuantityOfSleeplessNight,
                sleepAnalysisResult.getAnalysisParam(),
                "Определение количества бессонных ночей работает некорректно " +
                        "\nожидаемое значение: " + correctQuantityOfSleeplessNight +
                        "\nимеем: " + sleepAnalysisResult.getAnalysisParam()
        );
    }

    @Test
    void getOneSleeplessNight() {
        sleepSessions.add(new SleepSession("03.10.25 17:50;03.10.25 23:40;NORMAL"));
        Double correctQuantityOfSleeplessNight = 1.0;

        sleepAnalysisResult = countSleeplessNights.apply(sleepSessions);
        assertEquals(
                correctQuantityOfSleeplessNight,
                sleepAnalysisResult.getAnalysisParam(),
                "Определение количества бессонных ночей работает некорректно " +
                        "\nожидаемое значение: " + correctQuantityOfSleeplessNight +
                        "\nимеем: " + sleepAnalysisResult.getAnalysisParam()
        );
    }
}
