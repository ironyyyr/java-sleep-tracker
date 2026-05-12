package ru.yandex.practicum.sleeptracker.entity;

public enum SleepQualities {
    BAD,
    NORMAL,
    GOOD;

    public static SleepQualities parseString(String quality) {
        SleepQualities sleepQualities = SleepQualities.BAD;

        switch (quality) {
            case "NORMAL" -> sleepQualities = SleepQualities.NORMAL;
            case "GOOD" -> sleepQualities = SleepQualities.GOOD;
        }

        return sleepQualities;
    }
}
