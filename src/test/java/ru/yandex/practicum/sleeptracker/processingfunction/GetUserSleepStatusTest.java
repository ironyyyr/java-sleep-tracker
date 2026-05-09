package ru.yandex.practicum.sleeptracker.processingfunction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.entity.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.entity.SleepSession;
import ru.yandex.practicum.sleeptracker.entity.SleepStatuses;
import ru.yandex.practicum.sleeptracker.implementation.GetUserSleepStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetUserSleepStatusTest {
    private static List<SleepSession> sleepSessions;
    private static SleepAnalysisResult sleepAnalysisResult;
    private final GetUserSleepStatus getUserSleepStatus = new GetUserSleepStatus();

    @BeforeEach
    void setUp() {
        SleepSession sl1 = new SleepSession("30.09.25 23:15;01.10.25 07:30;GOOD");
        SleepSession sl2 = new SleepSession("30.09.25 23:50;01.10.25 06:40;NORMAL");

        sleepSessions = new ArrayList<>(List.of(sl1, sl2));
    }

    @Test
    void getOwlSleepStatus() {
        sleepSessions.add(new SleepSession("01.10.25 01:10;01.10.25 15:00;BAD"));
        sleepSessions.add(new SleepSession("03.10.25 01:10;03.10.25 15:00;BAD"));
        sleepSessions.add(new SleepSession("03.10.25 01:10;03.10.25 15:00;BAD"));
        SleepStatuses correctUserSleepStatus = SleepStatuses.OWL;

        sleepAnalysisResult = getUserSleepStatus.apply(sleepSessions);
        assertEquals(
                correctUserSleepStatus,
                sleepAnalysisResult.getAnalysisParam(),
                "Определение статуса пользователя прошло некорректно, должно быть " + correctUserSleepStatus +
                        " получили " + sleepAnalysisResult.getAnalysisParam()
        );
    }

    @Test
    void getLarkSleepStatus() {
        sleepSessions.add(new SleepSession("02.10.25 21:10;03.10.25 06:00;BAD"));
        sleepSessions.add(new SleepSession("02.10.25 21:10;03.10.25 06:00;BAD"));
        sleepSessions.add(new SleepSession("02.10.25 21:10;03.10.25 06:00;BAD"));
        SleepStatuses correctUserSleepStatus = SleepStatuses.LARK;

        sleepAnalysisResult = getUserSleepStatus.apply(sleepSessions);
        assertEquals(
                correctUserSleepStatus,
                sleepAnalysisResult.getAnalysisParam(),
                "Определение статуса пользователя прошло некорректно, должно быть " + correctUserSleepStatus +
                        " получили " + sleepAnalysisResult.getAnalysisParam()
        );
    }

    @Test
    void getPigeonsSleepStatus() {
        sleepSessions.add(new SleepSession("02.10.25 21:10;03.10.25 08:00;BAD"));
        sleepSessions.add(new SleepSession("02.10.25 21:10;03.10.25 06:00;BAD"));
        sleepSessions.add(new SleepSession("02.10.25 21:10;03.10.25 06:00;BAD"));
        SleepStatuses correctUserSleepStatus = SleepStatuses.PIGEON;

        sleepAnalysisResult = getUserSleepStatus.apply(sleepSessions);
        assertEquals(
                correctUserSleepStatus,
                sleepAnalysisResult.getAnalysisParam(),
                "Определение статуса пользователя прошло некорректно, должно быть " + correctUserSleepStatus +
                        " получили " + sleepAnalysisResult.getAnalysisParam()
        );
    }
}
