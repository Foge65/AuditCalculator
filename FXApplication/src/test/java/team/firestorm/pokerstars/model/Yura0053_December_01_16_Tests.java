package team.firestorm.pokerstars.model;

import java.math.BigDecimal;

public class Yura0053_December_01_16_Tests extends CsvFileBaseForTests {
    public Yura0053_December_01_16_Tests() {
        super("src/test/resources/Yura0053_December_01_16.csv");
    }

    @Override
    Integer[] setCountRegistrationSpin() {
        return new Integer[]{1620, 2236};
    }

    @Override
    BigDecimal[] setSumRegistrationSpin() {
        return new BigDecimal[]{new BigDecimal("-16040.00"), new BigDecimal("-11030.00")};
    }

    @Override
    Integer[] setCountUnRegistrationSpin() {
        return new Integer[]{1, 0};
    }

    @Override
    BigDecimal[] setSumUnRegistrationSpin() {
        return new BigDecimal[]{new BigDecimal("0.00"), new BigDecimal("0.00")};
    }

    @Override
    BigDecimal[] setSumNetWonSpin() {
        return new BigDecimal[]{new BigDecimal("14760.00"), new BigDecimal("10455.00")};
    }

    @Override
    BigDecimal[] setSumRegistrationForTMoney() {
        return new BigDecimal[]{new BigDecimal("-160.00"), new BigDecimal("-150.00")};
    }

    @Override
    Integer[] setCountRegistrationForTicketSpin() {
        return new Integer[]{0, 0};
    }

    @Override
    BigDecimal[] setSumProfitSpin() {
        return new BigDecimal[]{new BigDecimal("-1430"), new BigDecimal("-725")};
    }

    @Override
    BigDecimal[] setSumBonusSpin() {
        return new BigDecimal[]{new BigDecimal("150"), new BigDecimal("150")};
    }

    @Override
    BigDecimal[] setSumProfitPoolSpin() {
        return new BigDecimal[]{new BigDecimal("-1440"), new BigDecimal("-725")};
    }

    @Override
    BigDecimal[] setSumBonusPoolSpin() {
        return new BigDecimal[]{new BigDecimal("160"), new BigDecimal("150")};
    }

    @Override
    Integer setTotalCountRegistrationWithoutUnregistration() {
        return 3855;
    }

    @Override
    BigDecimal setTotalProfit() {
        return new BigDecimal("-2155.00");
    }

    @Override
    BigDecimal setTotalBonus() {
        return new BigDecimal("300.00");
    }
}
