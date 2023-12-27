package team.firestorm.pokerstars.model;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.fail;

abstract class CsvFileBaseForTests {
    protected ModelBuilderFromCsvFile modelBuilderFromCsvFile;

    public CsvFileBaseForTests(String filePath) {
        CsvParser csvParser = new CsvParser(new File(filePath));
        modelBuilderFromCsvFile = new ModelBuilderFromCsvFile(csvParser);
    }

    private static void assertionIntegerArrays(Integer[] expectedArray, Integer[] actualArray) {
        for (int i = 0; i < expectedArray.length; i++) {
            if (expectedArray[i].compareTo(actualArray[i]) != 0) {
                fail("Values at index " + i + " differ. Expected: " + expectedArray[i] + ", Actual: " + actualArray[i]);
            }
        }
    }

    private static void assertionBigDecimalArrays(BigDecimal[] expectedArray, BigDecimal[] actualArray) {
        for (int i = 0; i < expectedArray.length; i++) {
            if (expectedArray[i].compareTo(actualArray[i]) != 0) {
                fail("Values at index " + i + " differ. Expected: " + expectedArray[i] + ", Actual: " + actualArray[i]);
            }
        }
    }

    @Test
    void ticket() {
        Collection<Integer> values = modelBuilderFromCsvFile.getCountRegistrationByTicketSpin().values();
        Integer[] expectedArray = setTicketArray();
        Integer[] actualArray = values.toArray(new Integer[0]);
        assertionIntegerArrays(expectedArray, actualArray);
    }

    abstract Integer[] setTicketArray();

    @Test
    void profit() {
        Collection<BigDecimal> values = modelBuilderFromCsvFile.getSumProfitSpin().values();
        BigDecimal[] expectedArray = setProfitArray();
        BigDecimal[] actualArray = values.toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expectedArray, actualArray);
    }

    abstract BigDecimal[] setProfitArray();

    @Test
    void bonus() {
        Collection<BigDecimal> values = modelBuilderFromCsvFile.getSumBonusSpin().values();
        BigDecimal[] expectedArray = setBonusArray();
        BigDecimal[] actualArray = values.toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expectedArray, actualArray);
    }

    abstract BigDecimal[] setBonusArray();

    @Test
    void profitPool() {
        Collection<BigDecimal> values = modelBuilderFromCsvFile.getSumProfitPoolSpin().values();
        BigDecimal[] expectedArray = setProfitPoolArray();
        BigDecimal[] actualArray = values.toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expectedArray, actualArray);
    }

    abstract BigDecimal[] setProfitPoolArray();

    @Test
    void bonusPool() {
        Collection<BigDecimal> values = modelBuilderFromCsvFile.getSumBonusPoolSpin().values();
        BigDecimal[] expectedArray = setBonusPoolArray();
        BigDecimal[] actualArray = values.toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expectedArray, actualArray);
    }

    abstract BigDecimal[] setBonusPoolArray();

    @Test
    void countRegistration() {
        Collection<Integer> values = modelBuilderFromCsvFile.getCountRegistrationSpin().values();
        Integer[] expectedArray = setCountRegistrationSpin();
        Integer[] actualArray = values.toArray(new Integer[0]);
        assertionIntegerArrays(expectedArray, actualArray);
    }

    abstract Integer[] setCountRegistrationSpin();
}
