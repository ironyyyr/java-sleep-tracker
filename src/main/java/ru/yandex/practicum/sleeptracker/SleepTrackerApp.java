package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.entity.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.entity.SleepSession;
import ru.yandex.practicum.sleeptracker.implementation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SleepTrackerApp {

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("Путь к логу для чтения не указан");
        }

        Path resFilePath = Path.of(args[0]);

        if (!Files.exists(resFilePath)) {
            throw new RuntimeException("Путь к файлу указан некорректно или файл не существует");
        }

        try (BufferedReader bufferedReader = Files.newBufferedReader(resFilePath)) {
            List<SleepSession> sleepSessions = new ArrayList<>();
            String fileLine = bufferedReader.readLine();

            while (fileLine != null) {
                if (!fileLine.isBlank()) {
                    sleepSessions.add(new SleepSession(fileLine));
                }

                fileLine = bufferedReader.readLine();
            }

            List<Function<List<SleepSession>, SleepAnalysisResult>> functionalList = setUpFunctions();

            functionalList.stream()
                    .map(func -> func.apply(sleepSessions))
                    .forEach(res -> System.out.println(res));


        } catch (IOException ioException) {
            throw new RuntimeException(ioException.getMessage());
        }
    }

    public static List<Function<List<SleepSession>, SleepAnalysisResult>> setUpFunctions() {
        List<Function<List<SleepSession>, SleepAnalysisResult>> functionalInterfaces = new ArrayList<>();

        SleepListSize sleepListSize = new SleepListSize();
        CountBadSleepQuality countBadSleepQuality = new CountBadSleepQuality();
        CountSleeplessNights countSleeplessNights = new CountSleeplessNights();
        AverageSleepDuration averageSleepDuration = new AverageSleepDuration();
        MinSleepDuration minSleepDuration = new MinSleepDuration();
        MaxSleepDuration maxSleepDuration = new MaxSleepDuration();
        GetUserSleepStatus getUserSleepStatus = new GetUserSleepStatus();


        functionalInterfaces.add(sleepListSize);
        functionalInterfaces.add(minSleepDuration);
        functionalInterfaces.add(maxSleepDuration);
        functionalInterfaces.add(averageSleepDuration);
        functionalInterfaces.add(countBadSleepQuality);
        functionalInterfaces.add(countSleeplessNights);
        functionalInterfaces.add(getUserSleepStatus);

        return functionalInterfaces;
    }
}