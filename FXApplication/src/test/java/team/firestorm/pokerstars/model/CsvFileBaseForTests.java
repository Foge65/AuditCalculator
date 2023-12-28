package team.firestorm.pokerstars.model;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void countRegistrationSpin() {
        Integer[] expected = setCountRegistrationSpin();
        Integer[] actual = modelBuilderFromCsvFile.getCountRegistrationSpin().values().toArray(new Integer[0]);
        assertionIntegerArrays(expected, actual);
    }

    abstract Integer[] setCountRegistrationSpin();

    @Test
    void sumRegistrationSpin() {
        BigDecimal[] expected = setSumRegistrationSpin();
        BigDecimal[] actual = modelBuilderFromCsvFile.getSumRegistrationSpin().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumRegistrationSpin();

    @Test
    void countUnRegistrationSpin() {
        Integer[] expected = setCountUnRegistrationSpin();
        Integer[] actual = modelBuilderFromCsvFile.getCountUnRegistrationSpin().values().toArray(new Integer[0]);
        assertionIntegerArrays(expected, actual);
    }

    abstract Integer[] setCountUnRegistrationSpin();

    @Test
    void sumUnRegistrationSpin() {
        BigDecimal[] expected = setSumUnRegistrationSpin();
        BigDecimal[] actual = modelBuilderFromCsvFile.getSumUnRegistrationSpin().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumUnRegistrationSpin();

    @Test
    void sumNetWonSpin() {
        BigDecimal[] expected = setSumNetWonSpin();
        BigDecimal[] actual = modelBuilderFromCsvFile.getSumNetWonSpin().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumNetWonSpin();

    @Test
    void sumRegistrationForTMoneySpin() {
        BigDecimal[] expected = setSumRegistrationForTMoney();
        BigDecimal[] actual = modelBuilderFromCsvFile.getSumRegistrationForTMoneySpin().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumRegistrationForTMoney();

    @Test
    void countRegistrationForTicketSpin() {
        Integer[] expected = setCountRegistrationForTicketSpin();
        Integer[] actual = modelBuilderFromCsvFile.getCountRegistrationByTicketSpin().values().toArray(new Integer[0]);
        assertionIntegerArrays(expected, actual);
    }

    abstract Integer[] setCountRegistrationForTicketSpin();

    @Test
    void sumProfitSpin() {
        BigDecimal[] expected = setSumProfitSpin();
        BigDecimal[] actual = modelBuilderFromCsvFile.getSumProfitSpin().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumProfitSpin();

    @Test
    void sumBonusSpin() {
        BigDecimal[] expected = setSumBonusSpin();
        BigDecimal[] actual = modelBuilderFromCsvFile.getSumBonusSpin().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumBonusSpin();

    @Test
    void sumProfitPoolSpin() {
        BigDecimal[] expected = setSumProfitPoolSpin();
        BigDecimal[] actual = modelBuilderFromCsvFile.getSumProfitPoolSpin().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumProfitPoolSpin();

    @Test
    void sumBonusPoolSpin() {
        BigDecimal[] expected = setSumBonusPoolSpin();
        BigDecimal[] actual = modelBuilderFromCsvFile.getSumBonusPoolSpin().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumBonusPoolSpin();


    @Test
    void totalCountRegistrationWithoutUnregistration() {
        Integer expected = setTotalCountRegistrationWithoutUnregistration();
        Integer actual = modelBuilderFromCsvFile.getCountRegistrationSpinWithoutUnregistration();
        assertEquals(expected, actual);
    }

    abstract Integer setTotalCountRegistrationWithoutUnregistration();

    @Test
    void totalProfit() {
        BigDecimal expected = setTotalProfit();
        BigDecimal[] actual = {BigDecimal.ZERO};
        modelBuilderFromCsvFile.getSumProfitSpin().values().stream()
                .map(BigDecimal.ZERO::add)
                .forEach(element -> actual[0] = actual[0].add(element));
        assertEquals(expected, actual[0]);
    }

    abstract BigDecimal setTotalProfit();

    @Test
    void totalBonus() {
        BigDecimal expected = setTotalBonus();
        BigDecimal[] actual = {BigDecimal.ZERO};
        modelBuilderFromCsvFile.getSumBonusSpin().values().stream()
                .map(BigDecimal.ZERO::add)
                .forEach(element -> actual[0] = actual[0].add(element));
        assertEquals(expected, actual[0]);
    }

    abstract BigDecimal setTotalBonus();
}
