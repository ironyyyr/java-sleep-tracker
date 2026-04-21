package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.entity.SleepSession;
import ru.yandex.practicum.sleeptracker.interfaceimplementation.*;
import ru.yandex.practicum.sleeptracker.processingfunctioninterface.*;

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

            List<Function<List<SleepSession>, Object>> functionalList = setUpFunctions();

            functionalList.stream()
                    .map(func -> func.apply(sleepSessions))
                    .forEach(System.out::println);


        } catch (IOException ioException) {
            throw new RuntimeException(ioException.getMessage());
        }
    }

    public static List<Function<List<SleepSession>, Object>> setUpFunctions() {
        List<Function<List<SleepSession>, Object>> functionalInterfaces = new ArrayList<>();

        Size size = SizeImpl.size();
        MinSessionDurationMin min = MinSessionDurationMinImpl.minSessionDurationMin();
        MaxSessionDurationMin max = MaxSessionDurationMinImpl.maxSessionDurationMin();
        AvgSessionDurationMin avg = AvgSessionDurationMinImpl.avgSessionDurationMin();
        CountBadSleepQuality countBad = CountBadSleepQualityImpl.countBadSleepQuality();
        CountSleeplessNight countSleepless = CountSleeplessNightImpl.countSleeplessNight();
        GetUserSleepStatus getUserSleepStatus = GetUserSleepStatusImpl.getUserSleepStatus();

        functionalInterfaces.add(size::size);
        functionalInterfaces.add(min::minSessionDurationMin);
        functionalInterfaces.add(max::maxSessionDurationMin);
        functionalInterfaces.add(avg::avgSessionDurationMin);
        functionalInterfaces.add(countBad::countBadSleepQuality);
        functionalInterfaces.add(countSleepless::countSleeplessNight);
        functionalInterfaces.add(getUserSleepStatus::getUserSleepStatus);

        return functionalInterfaces;
    }
}