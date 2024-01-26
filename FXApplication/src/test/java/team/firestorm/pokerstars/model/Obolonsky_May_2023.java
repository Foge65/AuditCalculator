package team.firestorm.pokerstars.model;

import java.math.BigDecimal;

public class Obolonsky_May_2023 extends CsvFileBaseForTests {
    public Obolonsky_May_2023() {
        super("src/test/resources/Obolonsky_May_2023.csv");
    }

    @Override
    Integer[] setCountRegistrationSpin() {
        return new Integer[]{1788};
    }

    @Override
    BigDecimal[] setSumRegistrationSpin() {
        return new BigDecimal[]{new BigDecimal("-8840")};
    }

    @Override
    Integer[] setCountUnRegistrationSpin() {
        return new Integer[]{0};
    }

    @Override
    BigDecimal[] setSumUnRegistrationSpin() {
        return new BigDecimal[]{new BigDecimal("0")};
    }

    @Override
    BigDecimal[] setSumNetWonSpin() {
        return new BigDecimal[]{new BigDecimal("8610")};
    }

    @Override
    BigDecimal[] setSumRegistrationForTMoney() {
        return new BigDecimal[]{new BigDecimal("-100")};
    }

    @Override
    Integer[] setCountRegistrationForTicketSpin() {
        return new Integer[]{0};
    }

    @Override
    BigDecimal[] setSumProfitSpin() {
        return new BigDecimal[]{new BigDecimal("-330")};
    }

    @Override
    BigDecimal[] setSumBonusSpin() {
        return new BigDecimal[]{new BigDecimal("100")};
    }

    @Override
    BigDecimal[] setSumProfitPoolSpin() {
        return new BigDecimal[]{new BigDecimal("-330")};
    }

    @Override
    BigDecimal[] setSumBonusPoolSpin() {
        return new BigDecimal[]{new BigDecimal("100")};
    }

    @Override
    Integer setTotalCountRegistrationWithoutUnregistration() {
        return 1788;
    }

    @Override
    BigDecimal setTotalProfitSpin() {
        return new BigDecimal("-330.00");
    }

    @Override
    BigDecimal setTotalBonusSpin() {
        return new BigDecimal("100.00");
    }

    @Override
    Integer[] setCountRegistrationMTT() {
        return new Integer[]{9, 9, 5, 7, 2, 5, 8};
    }

    @Override
    BigDecimal[] setSumRegistrationMTT() {
        return new BigDecimal[]{
                new BigDecimal("-49.5"),
                new BigDecimal("-67.5"),
                new BigDecimal("-37.5"),
                new BigDecimal("-77"),
                new BigDecimal("-33"),
                new BigDecimal("-16.5"),
                new BigDecimal("-26.4")
        };
    }

    @Override
    Integer[] setCountUnRegistrationMTT() {
        return new Integer[]{0, 2, 0, 2, 0, 0, 0};
    }

    @Override
    BigDecimal[] setSumUnRegistrationMTT() {
        return new BigDecimal[]{
                new BigDecimal("0"),
                new BigDecimal("15"),
                new BigDecimal("0"),
                new BigDecimal("22"),
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("0")
        };
    }

    @Override
    BigDecimal[] setSumNetWonMTT() {
        return new BigDecimal[]{
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("0")
        };
    }

    @Override
    Integer[] setCountReEntryMTT() {
        return new Integer[]{11, 8, 5, 4, 1, 10, 5};
    }

    @Override
    BigDecimal[] setSumReEntryMTT() {
        return new BigDecimal[]{
                new BigDecimal("-60.5"),
                new BigDecimal("-60"),
                new BigDecimal("-37.5"),
                new BigDecimal("-44"),
                new BigDecimal("-16.5"),
                new BigDecimal("-33"),
                new BigDecimal("-16.5")
        };
    }

    @Override
    BigDecimal[] setSumKnockoutMTT() {
        return new BigDecimal[]{
                new BigDecimal("385.17"),
                new BigDecimal("119.69"),
                new BigDecimal("123.37"),
                new BigDecimal("83.3"),
                new BigDecimal("619.73"),
                new BigDecimal("16.17"),
                new BigDecimal("42.67")
        };
    }

    @Override
    BigDecimal[] setProfitMTT() {
        return new BigDecimal[]{
                new BigDecimal("275.17"),
                new BigDecimal("7.19"),
                new BigDecimal("48.37"),
                new BigDecimal("-15.7"),
                new BigDecimal("570.23"),
                new BigDecimal("-33.33"),
                new BigDecimal("-0.23")
        };
    }

    @Override
    BigDecimal setTotalProfitMTT() {
        return new BigDecimal("851.70");
    }
}
