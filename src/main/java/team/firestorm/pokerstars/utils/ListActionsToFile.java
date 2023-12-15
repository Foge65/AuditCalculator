package team.firestorm.pokerstars.utils;

import com.opencsv.CSVWriter;
import team.firestorm.pokerstars.model.CsvParser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListActionsToFile {
    private static final List<File> files = new ArrayList<>();

    public static void main(String[] args) {
        ListActionsToFile listActionsToFile = new ListActionsToFile();
        listActionsToFile.addFileToList();

        Map<String, Set<String>> actions = new HashMap<>();
        for (File file : files) {
            try {
                Set<String> action = new CsvParser(file).getStrings().stream().map(element -> element[1]).collect(Collectors.toSet());
                actions.put(file.toString(), action);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error parsing file: " + file.toString());
                throw e;
            }
        }
        listActionsToFile.writeToCsv(actions);
    }

    private void addFileToList() {
        try (Stream<Path> stream = Files.walk(Path.of("c:\\Users\\user\\Downloads\\audits\\csv"))) {
            stream.filter(Files::isRegularFile)
                    .filter(file -> file.toString().endsWith(".csv"))
                    .forEach(file -> files.add(file.toFile()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeToCsv(Map<String, Set<String>> actions) {
        try (CSVWriter writer = new CSVWriter(
                new OutputStreamWriter(
                        new FileOutputStream("actions.csv"), "Cp1251"))) {
            for (Map.Entry<String, Set<String>> entry : actions.entrySet()) {
                String[] data = new String[]{entry.getKey(), String.join(",", entry.getValue())};
                writer.writeNext(data);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
