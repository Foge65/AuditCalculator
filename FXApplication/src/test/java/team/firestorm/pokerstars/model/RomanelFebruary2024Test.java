package team.firestorm.pokerstars.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;

public class RomanelFebruary2024Test {
    private final Model model;

    public RomanelFebruary2024Test() {
        File file = new File("src/test/resources/Romanel_February_2024.csv");
        ModelBuilderFromCsvFile modelBuilderFromCsvFile = new ModelBuilderFromCsvFile(new CsvParser(file));
        model = new Model();
        modelBuilderFromCsvFile.setModel(model);
    }

    @Test
    void startBalance() {
        Assertions.assertEquals("1935.46", model.getStartBalance());
    }

    @Test
    void finalBalance() {
        Assertions.assertEquals("6403.03", model.getFinalBalance());
    }

    @Test
    void profitMTT() {
        String actual = model.getSumProfitMTT().values().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add).toString();

        Assertions.assertEquals("2573.45", actual);
    }
}
