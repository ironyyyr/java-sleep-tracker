package ru.yandex.practicum.sleeptracker.entity;

public class AnalysisMessages {
    private static String message;

    public static String getMessage(String useCase) {
        String averageSleepDurationMessage = "Средняя длительность сна: ";
        String countBadSleepQualityNightsMessage = "Количество ночей с плохим качеством сна: ";
        String countSleeplessNightsMessage = "Количество бессонных ночей: ";
        String getUserSleeplessStatusMessage = "Ваш тип сна: ";
        String maxSleepDurationMessage = "Максимальная длительность сна: ";
        String minSleepDurationMessage = "Минимальная длительность сна: ";
        String sleepListSizeMessage = "Количество сессий сна в логе: ";


        switch (useCase) {
            case "averageSleepDuration" -> message = averageSleepDurationMessage;
            case "countBadSleepQuality" -> message = countBadSleepQualityNightsMessage;
            case "countSleeplessNights" -> message = countSleeplessNightsMessage;
            case "getUserSleeplessStatus" -> message = getUserSleeplessStatusMessage;
            case "maxSleepDuration" -> message = maxSleepDurationMessage;
            case "minSleepDuration" -> message = minSleepDurationMessage;
            case "sleepListSize" -> message = sleepListSizeMessage;
        }

        return message;
    }
}
