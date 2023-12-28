package team.firestorm.pokerstars.model;

import java.math.BigDecimal;

public class Yura0053_December_01_16_Tests extends CsvFileBaseForTests {
    public Yura0053_December_01_16_Tests() {
        super("src/test/resources/Yura0053_December_01_16.csv");
    }

    @Override
    Integer[] setTicketArray() {
        return new Integer[]{0, 0};
    }

    @Override
    BigDecimal[] setProfitArray() {
        return new BigDecimal[]{new BigDecimal("-1430"), new BigDecimal("-725")};
    }

    @Override
    BigDecimal[] setBonusArray() {
        return new BigDecimal[]{new BigDecimal("150"), new BigDecimal("150")};
    }

    @Override
    BigDecimal[] setProfitPoolArray() {
        return new BigDecimal[]{new BigDecimal("-1440"), new BigDecimal("-725")};
    }

    @Override
    BigDecimal[] setBonusPoolArray() {
        return new BigDecimal[]{new BigDecimal("160"), new BigDecimal("150")};
    }

    @Override
    Integer[] setCountRegistrationSpin() {
        return new Integer[]{1620, 2236};
    }

    @Override
    Integer[] setCountUnRegistrationSpin() {
        return new Integer[]{1, 0};
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
