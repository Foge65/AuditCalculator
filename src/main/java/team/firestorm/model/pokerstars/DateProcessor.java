package team.firestorm.model.pokerstars;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateProcessor {
    private StringProcessor stringProcessor;

    public DateProcessor(StringProcessor stringProcessor) {
        this.stringProcessor = stringProcessor;
    }

    public LocalDate setDateFrom(DateTimeFormatter dateTimeFormatter) {
        String[] firstStringArray = stringProcessor.getStrings().get(0);
        String dateStart = firstStringArray[0];
        return LocalDate.from(LocalDateTime.parse(dateStart, dateTimeFormatter));
    }

    public LocalDate setDateTo(DateTimeFormatter dateTimeFormatter) {
        int sizeList = stringProcessor.getStrings().size();
        String[] lastStringArray = stringProcessor.getStrings().get(sizeList - 1);
        String dateEnd = lastStringArray[0];
        return LocalDate.from(LocalDateTime.parse(dateEnd, dateTimeFormatter));
    }

}
