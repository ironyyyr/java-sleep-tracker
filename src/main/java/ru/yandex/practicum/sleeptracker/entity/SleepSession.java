package ru.yandex.practicum.sleeptracker.entity;

import ru.yandex.practicum.sleeptracker.exception.BadSleepFileFormat;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SleepSession {
    private SleepQualities quality;
    private final LocalDateTime sleepStart;
    private final LocalDateTime sleepEnd;
    private final LocalTime daytimeSleepStartTime = LocalTime.of(12, 59);
    private final LocalTime daytimeSleepEndTime = LocalTime.of(15, 1);
    DateTimeFormatter dateTimePattern = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
    private Duration sleepDuration;

    public SleepSession(String sleepData) {

        if (!sleepData.contains(";")) {
            throw new BadSleepFileFormat("Проверьте форматирование сообщений в файле с треками сна");
        }

        String[] list = sleepData.split(";");
        this.sleepStart = LocalDateTime.parse(list[0], dateTimePattern);
        this.sleepEnd = LocalDateTime.parse(list[1], dateTimePattern);
        switch (list[2]) {
            case "BAD" -> quality = SleepQualities.BAD;
            case "NORMAL" -> quality = SleepQualities.NORMAL;
            case "GOOD" -> quality = SleepQualities.GOOD;
        }

        calculateSleepDuration();
    }

    private void calculateSleepDuration() {
        this.sleepDuration = Duration.between(sleepStart, sleepEnd);
    }

    public SleepQualities getQuality() {
        return quality;
    }

    public Duration getSleepDuration() {
        return sleepDuration;
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

    public LocalDateTime getSleepStartTime() {
        return sleepStart;
    }

    public LocalDate getSleepStartDate() {
        return sleepStart.toLocalDate();
    }

    public LocalDate getSleepEndDate() {
        return sleepEnd.toLocalDate();
    }

    public LocalDateTime getSleepEndTime() {
        return sleepEnd;
    }

    public Boolean isDaytimeSleep() {
        return getSleepStartTime().toLocalTime().isAfter(daytimeSleepStartTime) &&
                getSleepEndTime().toLocalTime().isBefore(daytimeSleepEndTime) &&
                isSleepEndsThatDay();
    }

    @Override
    public String toString() {
        return "Старт и конец сна в разные дни: " + calcDiffBetweenDays() + " " + isSleepEndsThatDay() +
                ". Сон начинается раньше 6 часов утра: " + isSleepStartsLaterThanSix() +
                ". Длительность сна: " + getSleepDuration();
    }
}
