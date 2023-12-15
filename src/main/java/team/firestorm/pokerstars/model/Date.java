package team.firestorm.pokerstars.model;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor
public class Date {
    private CsvParser csvParser;

    public LocalDate setDateFrom(List<String[]> strings, DateTimeFormatter dateTimeFormatter) {
        String[] firstStringArray = strings.get(0);
        String date = firstStringArray[0];
        return LocalDate.from(LocalDateTime.parse(date, dateTimeFormatter));
    }

    public LocalDate setDateTo(List<String[]> strings, DateTimeFormatter dateTimeFormatter) {
        int sizeList = strings.size();
        String[] lastStringArray = csvParser.getStrings().get(sizeList - 1);
        String date = lastStringArray[0];
        return LocalDate.from(LocalDateTime.parse(date, dateTimeFormatter));
    }
}
