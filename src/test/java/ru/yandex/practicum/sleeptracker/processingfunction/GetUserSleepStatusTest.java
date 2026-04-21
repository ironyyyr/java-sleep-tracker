package ru.yandex.practicum.sleeptracker.processingfunction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.entity.SleepSession;
import ru.yandex.practicum.sleeptracker.interfaceimplementation.GetUserSleepStatusImpl;
import ru.yandex.practicum.sleeptracker.processingfunctioninterface.GetUserSleepStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetUserSleepStatusTest {
    private static List<SleepSession> sleepSessions;
    private static GetUserSleepStatus userSleepStatus;

    @BeforeEach
    void setUp() {
        SleepSession sl1 = new SleepSession("01.10.25 23:15;02.10.25 07:30;GOOD");
        SleepSession sl2 = new SleepSession("02.10.25 23:50;03.10.25 06:40;NORMAL");
        sleepSessions = new ArrayList<>(List.of(sl1, sl2));

        userSleepStatus = GetUserSleepStatusImpl.getUserSleepStatus();
    }

    @Test
    void getOwlSleepStatus() {
        sleepSessions.add(new SleepSession("02.10.25 23:10;03.10.25 15:00;BAD"));
        sleepSessions.add(new SleepSession("02.10.25 23:10;03.10.25 15:00;BAD"));
        sleepSessions.add(new SleepSession("02.10.25 23:10;03.10.25 15:00;BAD"));
        String correctUserSleepStatus = "Сова";
        assertEquals(
                correctUserSleepStatus,
                userSleepStatus.getUserSleepStatus(sleepSessions),
                "Определение статуса пользователя прошло некорректно, должно быть " + correctUserSleepStatus +
                        " получили " + userSleepStatus.getUserSleepStatus(sleepSessions)
        );
    }

    @Test
    void getLarkSleepStatus() {
        sleepSessions.add(new SleepSession("02.10.25 21:10;03.10.25 06:00;BAD"));
        sleepSessions.add(new SleepSession("02.10.25 21:10;03.10.25 06:00;BAD"));
        sleepSessions.add(new SleepSession("02.10.25 21:10;03.10.25 06:00;BAD"));
        String correctUserSleepStatus = "Жаворонок";
        assertEquals(
                correctUserSleepStatus,
                userSleepStatus.getUserSleepStatus(sleepSessions),
                "Определение статуса пользователя прошло некорректно, должно быть " + correctUserSleepStatus +
                        " получили " + userSleepStatus.getUserSleepStatus(sleepSessions)
        );
    }

    @Test
    void getPigeonsSleepStatus() {
        sleepSessions.add(new SleepSession("02.10.25 21:10;03.10.25 08:00;BAD"));
        sleepSessions.add(new SleepSession("02.10.25 21:10;03.10.25 06:00;BAD"));
        sleepSessions.add(new SleepSession("02.10.25 21:10;03.10.25 06:00;BAD"));
        String correctUserSleepStatus = "Голубь";
        assertEquals(
                correctUserSleepStatus,
                userSleepStatus.getUserSleepStatus(sleepSessions),
                "Определение статуса пользователя прошло некорректно, должно быть " + correctUserSleepStatus +
                        " получили " + userSleepStatus.getUserSleepStatus(sleepSessions)
        );
    }
}
