package team.firestorm.pokerstars.model;

import lombok.Getter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Getter
public class CsvParser {
    private final List<String[]> strings;
    private final String firstString;

    public CsvParser(File file) {
        strings = new ArrayList<>();
        List<String> stringList = read(file);
        this.firstString = firstString(stringList);
        combine(stringList);
    }

    private List<String> read(File file) {
        List<String> strings = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                strings.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        removeStringWhichStartWithHTML(strings);
        return strings;
    }

    private void removeStringWhichStartWithHTML(List<String> strings) {
        strings.removeIf(string -> string.contains("<HTML><HEAD><title>Playing History Audit"));
    }

    private void combine(List<String> stringList) {
        for (int i = 3; i < stringList.size(); i++) {
            String[] parseLine = parse(stringList.get(i));
            strings.add(parseLine);
        }
    }

    private String[] parse(String line) {
        String doubleQuotesPattern = "\"\"";
        String onesQuotesPattern = "\"";
        String replaceDoubleQuotesPattern = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
        String commasPattern = ",";
        String blankPattern = "";

        String replaceDoubleQuotesToOneQuotes = line.replace(doubleQuotesPattern, onesQuotesPattern);
        String replaceDoubleQuotesToBlank = replaceDoubleQuotesToOneQuotes.replace(doubleQuotesPattern, onesQuotesPattern);
        String[] extractElements = replaceDoubleQuotesToBlank.split(replaceDoubleQuotesPattern);

        String[] separateFirstElement = extractElements[0].split(commasPattern);
        String[] replaceOneQuotesToBlank = Arrays.stream(separateFirstElement)
                .map(element -> element.replace(onesQuotesPattern, blankPattern).trim())
                .toArray(String[]::new);

        String spacePattern = "^\\s*(.*)$";
        Pattern pattern = Pattern.compile(spacePattern);
        String[] cleanedRemainingElements = Arrays.stream(extractElements)
                .skip(1)
                .map(input -> input != null ? input.replaceAll(pattern.pattern(), "$1") : input)
                .toArray(String[]::new);

        String[] stringArray = Arrays.copyOf(replaceOneQuotesToBlank,
                replaceOneQuotesToBlank.length + cleanedRemainingElements.length);
        System.arraycopy(cleanedRemainingElements, 0,
                stringArray, replaceOneQuotesToBlank.length, cleanedRemainingElements.length);

        stringArray[stringArray.length - 1] = stringArray[stringArray.length - 1].replaceAll("\"$", blankPattern);

        return stringArray;
    }

    private String firstString(List<String> stringList) {
        return stringList.get(0);
    }

    public int countElements() {
        return (int) Arrays.stream(strings.get(2)).count();
    }
}
