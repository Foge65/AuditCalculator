package team.firestorm.pokerstars.model;

import java.math.BigDecimal;

class Yura0053_November_Tests extends CsvFileBaseForTests {
    Yura0053_November_Tests() {
        super("src/test/resources/Yura0053_November.csv");
    }

    @Override
    Integer[] setTicketArray() {
        return new Integer[]{0, 0};
    }

    @Override
    BigDecimal[] setProfitArray() {
        return new BigDecimal[]{new BigDecimal("-620"), new BigDecimal("-55")};
    }

    @Override
    BigDecimal[] setBonusArray() {
        return new BigDecimal[]{new BigDecimal("150"), new BigDecimal("50")};
    }

    @Override
    BigDecimal[] setProfitPoolArray() {
        return new BigDecimal[]{new BigDecimal("-620"), new BigDecimal("-55")};
    }

    @Override
    BigDecimal[] setBonusPoolArray() {
        return new BigDecimal[]{new BigDecimal("150"), new BigDecimal("50")};
    }

    @Override
    Integer[] setCountRegistrationSpin() {
        return new Integer[]{9161, 654};
    }

    @Override
    Integer[] setCountUnRegistrationSpin() {
        return new Integer[]{13, 2};
    }

    @Override
    Integer setTotalCountRegistrationWithoutUnregistration() {
        return 9800;
    }
}
