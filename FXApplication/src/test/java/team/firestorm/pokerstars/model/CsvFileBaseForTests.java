package team.firestorm.pokerstars.model;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

abstract class CsvFileBaseForTests {
    protected Model model;
    protected ModelBuilderFromCsvFile modelBuilderFromCsvFile;

    public CsvFileBaseForTests(String filePath) {
        CsvParser csvParser = new CsvParser(new File(filePath));
        model = new Model();
        modelBuilderFromCsvFile = new ModelBuilderFromCsvFile(csvParser);
        modelBuilderFromCsvFile.setModel(model);
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
        Integer[] actual = model.getCountRegistrationSpin().values().toArray(new Integer[0]);
        assertionIntegerArrays(expected, actual);
    }

    abstract Integer[] setCountRegistrationSpin();

    @Test
    void sumRegistrationSpin() {
        BigDecimal[] expected = setSumRegistrationSpin();
        BigDecimal[] actual = model.getSumRegistrationSpin().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumRegistrationSpin();

    @Test
    void countUnRegistrationSpin() {
        Integer[] expected = setCountUnRegistrationSpin();
        Integer[] actual = model.getCountUnRegistrationSpin().values().toArray(new Integer[0]);
        assertionIntegerArrays(expected, actual);
    }

    abstract Integer[] setCountUnRegistrationSpin();

    @Test
    void sumUnRegistrationSpin() {
        BigDecimal[] expected = setSumUnRegistrationSpin();
        BigDecimal[] actual = model.getSumUnRegistrationSpin().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumUnRegistrationSpin();

    @Test
    void sumRegistrationForTMoneySpin() {
        BigDecimal[] expected = setSumRegistrationForTMoney();
        BigDecimal[] actual = model.getSumRegistrationForTMoneySpin().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumRegistrationForTMoney();

    @Test
    void countRegistrationForTicketSpin() {
        Integer[] expected = setCountRegistrationForTicketSpin();
        Integer[] actual = model.getCountRegistrationByTicketSpin().values().toArray(new Integer[0]);
        assertionIntegerArrays(expected, actual);
    }

    abstract Integer[] setCountRegistrationForTicketSpin();

    @Test
    void sumNetWonSpin() {
        BigDecimal[] expected = setSumWonSpin();
        BigDecimal[] actual = model.getSumWonSpin().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumWonSpin();

    @Test
    void sumProfitSpin() {
        BigDecimal[] expected = setSumProfitSpin();
        BigDecimal[] actual = model.getSumProfitSpin().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumProfitSpin();

    @Test
    void sumBonusSpin() {
        BigDecimal[] expected = setSumBonusSpin();
        BigDecimal[] actual = model.getSumBonusSpin().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumBonusSpin();

    @Test
    void sumProfitPoolSpin() {
        BigDecimal[] expected = setSumProfitPoolSpin();
        BigDecimal[] actual = model.getSumProfitPoolSpin().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumProfitPoolSpin();

    @Test
    void sumBonusPoolSpin() {
        BigDecimal[] expected = setSumBonusPoolSpin();
        BigDecimal[] actual = model.getSumBonusPoolSpin().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumBonusPoolSpin();

    @Test
    void totalCountRegistrationWithoutUnregistration() {
        Integer expected = setTotalCountRegistrationWithoutUnregistration();
        Integer actual = model.getCountRegistrationSpinWithoutUnregistration();
        assertEquals(expected, actual);
    }

    abstract Integer setTotalCountRegistrationWithoutUnregistration();

    @Test
    void totalProfitSpin() {
        BigDecimal expected = setTotalProfitSpin();
        BigDecimal[] actual = {BigDecimal.ZERO};
        model.getSumProfitSpin().values().stream()
                .map(BigDecimal.ZERO::add)
                .forEach(element -> actual[0] = actual[0].add(element));
        assertEquals(expected, actual[0]);
    }

    abstract BigDecimal setTotalProfitSpin();

    @Test
    void totalBonusSpin() {
        BigDecimal expected = setTotalBonusSpin();
        BigDecimal[] actual = {BigDecimal.ZERO};
        model.getSumBonusSpin().values().stream()
                .map(BigDecimal.ZERO::add)
                .forEach(element -> actual[0] = actual[0].add(element));
        assertEquals(expected, actual[0]);
    }

    abstract BigDecimal setTotalBonusSpin();

    @Test
    void countRegistrationMTT() {
        Integer[] expected = setCountRegistrationMTT();
        Integer[] actual = model.getCountRegistrationMTT().values().toArray(new Integer[0]);
        assertionIntegerArrays(expected, actual);
    }

    abstract Integer[] setCountRegistrationMTT();

    @Test
    void sumRegistrationMTT() {
        BigDecimal[] expected = setSumRegistrationMTT();
        BigDecimal[] actual = model.getSumRegistrationMTT().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumRegistrationMTT();

    @Test
    void countUnRegistrationMTT() {
        Integer[] expected = setCountUnRegistrationMTT();
        Integer[] actual = model.getCountUnRegistrationMTT().values().toArray(new Integer[0]);
        assertionIntegerArrays(expected, actual);
    }

    abstract Integer[] setCountUnRegistrationMTT();

    @Test
    void sumUnRegistrationMTT() {
        BigDecimal[] expected = setSumUnRegistrationMTT();
        BigDecimal[] actual = model.getSumUnRegistrationMTT().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumUnRegistrationMTT();

    @Test
    void countRegistrationByTMoneyMTT() {
        Integer[] expected = setCountRegistrationByTMoneyMTT();
        Integer[] actual = model.getCountRegistrationByTMoneyMTT().values().toArray(new Integer[0]);
        assertionIntegerArrays(expected, actual);
    }

    abstract Integer[] setCountRegistrationByTMoneyMTT();

    @Test
    void sumRegistrationByTMoneyMTT() {
        BigDecimal[] expected = setSumRegistrationByTMoneyMTT();
        BigDecimal[] actual = model.getSumRegistrationByTMoneyMTT().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumRegistrationByTMoneyMTT();

    @Test
    void countUnRegistrationByTMoneyMTT() {
        Integer[] expected = setCountUnRegistrationByTMoneyMTT();
        Integer[] actual = model.getCountUnRegistrationByTMoneyMTT().values().toArray(new Integer[0]);
        assertionIntegerArrays(expected, actual);
    }

    abstract Integer[] setCountUnRegistrationByTMoneyMTT();

    @Test
    void sumUnRegistrationByTMoneyMTT() {
        BigDecimal[] expected = setSumUnRegistrationByTMoneyMTT();
        BigDecimal[] actual = model.getSumUnRegistrationByTMoneyMTT().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumUnRegistrationByTMoneyMTT();

    @Test
    void countReEntryMTT() {
        Integer[] expected = setCountReEntryMTT();
        Integer[] actual = model.getCountReEntryMTT().values().toArray(new Integer[0]);
        assertionIntegerArrays(expected, actual);
    }

    abstract Integer[] setCountReEntryMTT();

    @Test
    void sumReEntryMTT() {
        BigDecimal[] expected = setSumReEntryMTT();
        BigDecimal[] actual = model.getSumReEntryMTT().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumReEntryMTT();

    @Test
    void sumKnockoutMTT() {
        BigDecimal[] expected = setSumKnockoutMTT();
        BigDecimal[] actual = model.getSumKnockoutMTT().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumKnockoutMTT();

    @Test
    void sumInterimMTT() {
        BigDecimal[] expected = setSumInterimMTT();
        BigDecimal[] actual = model.getSumInterimMTT().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumInterimMTT();

    @Test
    void sumWonMTT() {
        BigDecimal[] expected = setSumWonMTT();
        BigDecimal[] actual = model.getSumWonMTT().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setSumWonMTT();

    @Test
    void sumProfitMTT() {
        BigDecimal[] expected = setProfitMTT();
        BigDecimal[] actual = model.getSumProfitMTT().values().toArray(new BigDecimal[0]);
        assertionBigDecimalArrays(expected, actual);
    }

    abstract BigDecimal[] setProfitMTT();

    @Test
    void totalProfitMTT() {
        BigDecimal expected = setTotalProfitMTT();
        BigDecimal[] actual = {BigDecimal.ZERO};
        model.getSumProfitMTT().values().stream()
                .map(BigDecimal.ZERO::add)
                .forEach(element -> actual[0] = actual[0].add(element));
        assertEquals(expected, actual[0]);
    }

    abstract BigDecimal setTotalProfitMTT();
}
