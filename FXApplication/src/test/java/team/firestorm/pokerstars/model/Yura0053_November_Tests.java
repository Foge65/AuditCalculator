package team.firestorm.pokerstars.model;

import java.math.BigDecimal;

class Yura0053_November_Tests extends CsvFileBaseForTests {
    Yura0053_November_Tests() {
        super("src/test/resources/Yura0053_November.csv");
    }

    @Override
    Integer[] setCountRegistrationSpin() {
        return new Integer[]{9161, 654};
    }

    @Override
    BigDecimal[] setSumRegistrationSpin() {
        return new BigDecimal[]{new BigDecimal("-18172.00"), new BigDecimal("-3220.00")};
    }

    @Override
    Integer[] setCountUnRegistrationSpin() {
        return new Integer[]{13, 2};
    }

    @Override
    BigDecimal[] setSumUnRegistrationSpin() {
        return new BigDecimal[]{new BigDecimal("26.00"), new BigDecimal("10.00")};
    }

    @Override
    BigDecimal[] setSumNetWonSpin() {
        return new BigDecimal[]{new BigDecimal("17676.00"), new BigDecimal("3205.00")};
    }

    @Override
    BigDecimal[] setSumRegistrationForTMoney() {
        return new BigDecimal[]{new BigDecimal("-150.00"), new BigDecimal("-50.00")};
    }

    @Override
    Integer[] setCountRegistrationForTicketSpin() {
        return new Integer[]{0, 0};
    }

    @Override
    BigDecimal[] setSumProfitSpin() {
        return new BigDecimal[]{new BigDecimal("-620"), new BigDecimal("-55")};
    }

    @Override
    BigDecimal[] setSumBonusSpin() {
        return new BigDecimal[]{new BigDecimal("150"), new BigDecimal("50")};
    }

    @Override
    BigDecimal[] setSumProfitPoolSpin() {
        return new BigDecimal[]{new BigDecimal("-620"), new BigDecimal("-55")};
    }

    @Override
    BigDecimal[] setSumBonusPoolSpin() {
        return new BigDecimal[]{new BigDecimal("150"), new BigDecimal("50")};
    }

    @Override
    Integer setTotalCountRegistrationWithoutUnregistration() {
        return 9800;
    }

    @Override
    BigDecimal setTotalProfit() {
        return new BigDecimal("-675.00");
    }

    @Override
    BigDecimal setTotalBonus() {
        return new BigDecimal("200.00");
    }
}
