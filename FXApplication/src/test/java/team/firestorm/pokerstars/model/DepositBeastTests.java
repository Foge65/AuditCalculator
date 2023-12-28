package team.firestorm.pokerstars.model;

import java.math.BigDecimal;

class DepositBeastTests extends CsvFileBaseForTests {
    DepositBeastTests() {
        super("src/test/resources/DepositBeast.csv");
    }

    @Override
    Integer[] setTicketArray() {
        return new Integer[]{1, 0};
    }

    @Override
    BigDecimal[] setProfitArray() {
        return new BigDecimal[]{new BigDecimal("0"), new BigDecimal("-2375")};
    }

    @Override
    BigDecimal[] setBonusArray() {
        return new BigDecimal[]{new BigDecimal("250"), new BigDecimal("0")};
    }

    @Override
    BigDecimal[] setProfitPoolArray() {
        return new BigDecimal[]{new BigDecimal("200"), new BigDecimal("-2375")};
    }

    @Override
    BigDecimal[] setBonusPoolArray() {
        return new BigDecimal[]{new BigDecimal("50"), new BigDecimal("0")};
    }

    @Override
    Integer[] setCountRegistrationSpin() {
        return new Integer[]{1, 1825};
    }

    @Override
    Integer[] setCountUnRegistrationSpin() {
        return new Integer[]{0, 6};
    }

    @Override
    Integer setTotalCountRegistrationWithoutUnregistration() {
        return 1820;
    }

    @Override
    BigDecimal setTotalProfit() {
        return new BigDecimal("-2375.00");
    }

    @Override
    BigDecimal setTotalBonus() {
        return new BigDecimal("250.00");
    }
}
