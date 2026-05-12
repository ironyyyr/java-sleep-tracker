package ru.yandex.practicum.sleeptracker.entity;

import ru.yandex.practicum.sleeptracker.exception.BadSleepFileFormat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SleepSession {
    private final SleepQualities quality;
    private final LocalDateTime sleepStart;
    private final LocalDateTime sleepEnd;

    DateTimeFormatter dateTimePattern = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    public SleepSession(String sleepData) {

        if (!sleepData.contains(";")) {
            throw new BadSleepFileFormat("Проверьте форматирование сообщений в файле с треками сна");
        }

        String[] list = sleepData.split(";");
        this.sleepStart = LocalDateTime.parse(list[0], dateTimePattern);
        this.sleepEnd = LocalDateTime.parse(list[1], dateTimePattern);
        this.quality = SleepQualities.parseString(list[2]);
    }

    public SleepQualities getQuality() {
        return quality;
    }

    public Duration getSleepDuration() {
        return Duration.between(sleepStart, sleepEnd);
    }

    public Boolean isSleepEndsThatDay() {
        return sleepEnd.getDayOfYear() - sleepStart.getDayOfYear() == 0;
    }

    private int calcDiffBetweenDays() {
        return sleepEnd.getDayOfYear() - sleepStart.getDayOfYear();
    }

    public Boolean isSleepStartsLaterThanSix() {
        return sleepStart.toLocalTime().isAfter(LocalTime.of(6, 0));
    }

    public LocalTime getSleepStartTime() {
        return sleepStart.toLocalTime();
    }

    public LocalTime getSleepEndTime() {
        return sleepEnd.toLocalTime();
    }

    public Boolean isDaytimeSleep() {
        LocalTime daytimeSleepStartTime = LocalTime.of(12, 59);
        LocalTime daytimeSleepEndTime = LocalTime.of(15, 1);

        return getSleepStartTime().isAfter(daytimeSleepStartTime) &&
                getSleepEndTime().isBefore(daytimeSleepEndTime) &&
                isSleepEndsThatDay();
    }

    @Override
    public String toString() {
        return "Старт и конец сна в разные дни: " + calcDiffBetweenDays() + " " + isSleepEndsThatDay() +
                ". Сон начинается раньше 6 часов утра: " + isSleepStartsLaterThanSix();
    }
}
