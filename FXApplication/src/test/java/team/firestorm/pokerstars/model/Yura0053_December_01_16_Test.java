package team.firestorm.pokerstars.model;

import java.math.BigDecimal;

public class Yura0053_December_01_16_Test extends CsvFileBaseForTests {
    public Yura0053_December_01_16_Test() {
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
    BigDecimal[] setSumWonSpin() {
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
    BigDecimal setTotalProfitSpin() {
        return new BigDecimal("-2155.00");
    }

    @Override
    BigDecimal setTotalBonusSpin() {
        return new BigDecimal("300.00");
    }

    @Override
    Integer[] setCountRegistrationMTT() {
        return new Integer[]{1, 2, 1};
    }

    @Override
    BigDecimal[] setSumRegistrationMTT() {
        return new BigDecimal[]{
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("-3.3")
        };
    }

    @Override
    Integer[] setCountUnRegistrationMTT() {
        return new Integer[]{1, 0, 1};
    }

    @Override
    BigDecimal[] setSumUnRegistrationMTT() {
        return new BigDecimal[]{
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("3.3")
        };
    }

    @Override
    Integer[] setCountReEntryMTT() {
        return new Integer[]{0, 0, 0};
    }

    @Override
    BigDecimal[] setSumReEntryMTT() {
        return new BigDecimal[]{
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("0")
        };
    }

    @Override
    BigDecimal[] setSumKnockoutMTT() {
        return new BigDecimal[]{
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("0")
        };
    }

    @Override
    BigDecimal[] setSumInterimMTT() {
        return new BigDecimal[]{
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("0")
        };
    }

    @Override
    BigDecimal[] setSumWonMTT() {
        return new BigDecimal[]{
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("0")
        };
    }

    @Override
    BigDecimal[] setProfitMTT() {
        return new BigDecimal[]{
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("0")
        };
    }

    @Override
    BigDecimal setTotalProfitMTT() {
        return new BigDecimal("0.00");
    }
}
