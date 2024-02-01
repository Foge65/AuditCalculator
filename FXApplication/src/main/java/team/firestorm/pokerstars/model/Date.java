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
        return LocalDate.from(LocalDateTime.parse(strings.get(0)[0], dateTimeFormatter));
    }

    public LocalDate setDateTo(List<String[]> strings, DateTimeFormatter dateTimeFormatter) {
        return LocalDate.from(LocalDateTime.parse(csvParser.getStrings().get(strings.size() - 1)[0], dateTimeFormatter));
    }
}
