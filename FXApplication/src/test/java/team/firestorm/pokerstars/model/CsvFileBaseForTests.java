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
    void sumNetWonSpin() {
        BigDecimal[] expected = setSumWonSpin();
        BigDecimal[] actual = modelBuilderFromCsvFile.getSumWonSpin().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumWonSpin();

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
    void totalProfitSpin() {
        BigDecimal expected = setTotalProfitSpin();
        BigDecimal[] actual = {BigDecimal.ZERO};
        modelBuilderFromCsvFile.getSumProfitSpin().values().stream()
                .map(BigDecimal.ZERO::add)
                .forEach(element -> actual[0] = actual[0].add(element));
        assertEquals(expected, actual[0]);
    }

    abstract BigDecimal setTotalProfitSpin();

    @Test
    void totalBonusSpin() {
        BigDecimal expected = setTotalBonusSpin();
        BigDecimal[] actual = {BigDecimal.ZERO};
        modelBuilderFromCsvFile.getSumBonusSpin().values().stream()
                .map(BigDecimal.ZERO::add)
                .forEach(element -> actual[0] = actual[0].add(element));
        assertEquals(expected, actual[0]);
    }

    abstract BigDecimal setTotalBonusSpin();

    @Test
    void countRegistrationMTT() {
        Integer[] expected = setCountRegistrationMTT();
        Integer[] actual = modelBuilderFromCsvFile.getCountRegistrationMTT().values().toArray(new Integer[0]);
        assertionIntegerArrays(expected, actual);
    }

    abstract Integer[] setCountRegistrationMTT();

    @Test
    void sumRegistrationMTT() {
        BigDecimal[] expected = setSumRegistrationMTT();
        BigDecimal[] actual = modelBuilderFromCsvFile.getSumRegistrationMTT().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumRegistrationMTT();

    @Test
    void countUnRegistrationMTT() {
        Integer[] expected = setCountUnRegistrationMTT();
        Integer[] actual = modelBuilderFromCsvFile.getCountUnRegistrationMTT().values().toArray(new Integer[0]);
        assertionIntegerArrays(expected, actual);
    }

    abstract Integer[] setCountUnRegistrationMTT();

    @Test
    void sumUnRegistrationMTT() {
        BigDecimal[] expected = setSumUnRegistrationMTT();
        BigDecimal[] actual = modelBuilderFromCsvFile.getSumUnRegistrationMTT().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumUnRegistrationMTT();

    @Test
    void countRegistrationByTMoneyMTT() {
        Integer[] expected = setCountRegistrationByTMoneyMTT();
        Integer[] actual = modelBuilderFromCsvFile.getCountRegistrationByTMoneyMTT().values().toArray(new Integer[0]);
        assertionIntegerArrays(expected, actual);
    }

    abstract Integer[] setCountRegistrationByTMoneyMTT();

    @Test
    void sumRegistrationByTMoneyMTT() {
        BigDecimal[] expected = setSumRegistrationByTMoneyMTT();
        BigDecimal[] actual = modelBuilderFromCsvFile.getSumRegistrationByTMoneyMTT().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumRegistrationByTMoneyMTT();

    @Test
    void countUnRegistrationByTMoneyMTT() {
        Integer[] expected = setCountUnRegistrationByTMoneyMTT();
        Integer[] actual = modelBuilderFromCsvFile.getCountUnRegistrationByTMoneyMTT().values().toArray(new Integer[0]);
        assertionIntegerArrays(expected, actual);
    }

    abstract Integer[] setCountUnRegistrationByTMoneyMTT();

    @Test
    void sumUnRegistrationByTMoneyMTT() {
        BigDecimal[] expected = setSumUnRegistrationByTMoneyMTT();
        BigDecimal[] actual = modelBuilderFromCsvFile.getSumUnRegistrationByTMoneyMTT().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumUnRegistrationByTMoneyMTT();

    @Test
    void countReEntryMTT() {
        Integer[] expected = setCountReEntryMTT();
        Integer[] actual = modelBuilderFromCsvFile.getCountReEntryMTT().values().toArray(new Integer[0]);
        assertionIntegerArrays(expected, actual);
    }

    abstract Integer[] setCountReEntryMTT();

    @Test
    void sumReEntryMTT() {
        BigDecimal[] expected = setSumReEntryMTT();
        BigDecimal[] actual = modelBuilderFromCsvFile.getSumReEntryMTT().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumReEntryMTT();

    @Test
    void sumKnockoutMTT() {
        BigDecimal[] expected = setSumKnockoutMTT();
        BigDecimal[] actual = modelBuilderFromCsvFile.getSumKnockoutMTT().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumKnockoutMTT();

    @Test
    void sumInterimMTT() {
        BigDecimal[] expected = setSumInterimMTT();
        BigDecimal[] actual = modelBuilderFromCsvFile.getSumInterimMTT().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumInterimMTT();

    @Test
    void sumWonMTT() {
        BigDecimal[] expected = setSumWonMTT();
        BigDecimal[] actual = modelBuilderFromCsvFile.getSumWonMTT().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumWonMTT();

    @Test
    void sumProfitMTT() {
        BigDecimal[] expected = setProfitMTT();
        BigDecimal[] actual = modelBuilderFromCsvFile.getSumProfitMTT().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setProfitMTT();

    @Test
    void totalProfitMTT() {
        BigDecimal expected = setTotalProfitMTT();
        BigDecimal[] actual = {BigDecimal.ZERO};
        modelBuilderFromCsvFile.getSumProfitMTT().values().stream()
                .map(BigDecimal.ZERO::add)
                .forEach(element -> actual[0] = actual[0].add(element));
        assertEquals(expected, actual[0]);
    }

    abstract BigDecimal setTotalProfitMTT();
}
