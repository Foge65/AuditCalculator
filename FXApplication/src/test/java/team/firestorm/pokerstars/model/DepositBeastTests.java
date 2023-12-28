package team.firestorm.pokerstars.model;

import java.math.BigDecimal;

class DepositBeastTests extends CsvFileBaseForTests {
    DepositBeastTests() {
        super("src/test/resources/DepositBeast.csv");
    }

    @Override
    Integer[] setCountRegistrationSpin() {
        return new Integer[]{1, 1825};
    }

    @Override
    BigDecimal[] setSumRegistrationSpin() {
        return new BigDecimal[]{new BigDecimal("0.00"), new BigDecimal("-45625.00")};
    }

    @Override
    Integer[] setCountUnRegistrationSpin() {
        return new Integer[]{0, 6};
    }

    @Override
    BigDecimal[] setSumUnRegistrationSpin() {
        return new BigDecimal[]{new BigDecimal("0.00"), new BigDecimal("150.00")};
    }

    @Override
    BigDecimal[] setSumNetWonSpin() {
        return new BigDecimal[]{new BigDecimal("250.00"), new BigDecimal("43100.00")};
    }

    @Override
    BigDecimal[] setSumRegistrationForTMoney() {
        return new BigDecimal[]{new BigDecimal("0.00"), new BigDecimal("0.00")};
    }

    @Override
    Integer[] setCountRegistrationForTicketSpin() {
        return new Integer[]{1, 0};
    }

    @Override
    BigDecimal[] setSumProfitSpin() {
        return new BigDecimal[]{new BigDecimal("0"), new BigDecimal("-2375")};
    }

    @Override
    BigDecimal[] setSumBonusSpin() {
        return new BigDecimal[]{new BigDecimal("250"), new BigDecimal("0")};
    }

    @Override
    BigDecimal[] setSumProfitPoolSpin() {
        return new BigDecimal[]{new BigDecimal("200"), new BigDecimal("-2375")};
    }

    @Override
    BigDecimal[] setSumBonusPoolSpin() {
        return new BigDecimal[]{new BigDecimal("50"), new BigDecimal("0")};
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
