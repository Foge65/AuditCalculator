package team.firestorm.pokerstars.model;

import java.math.BigDecimal;

public class Artemio09_2023_Test extends CsvFileBaseForTests {
    public Artemio09_2023_Test() {
        super("src/test/resources/Artemio09_2023.csv");
    }

    @Override
    Integer[] setCountRegistrationSpin() {
        return new Integer[]{33, 4};
    }

    @Override
    BigDecimal[] setSumRegistrationSpin() {
        return new BigDecimal[]{new BigDecimal("0"),
                new BigDecimal("0")};
    }

    @Override
    Integer[] setCountUnRegistrationSpin() {
        return new Integer[]{0, 0};
    }

    @Override
    BigDecimal[] setSumUnRegistrationSpin() {
        return new BigDecimal[]{new BigDecimal("0"),
                new BigDecimal("0")};
    }

    @Override
    Integer[] setCountRegistrationForTicketSpin() {
        return new Integer[]{32, 4};
    }

    @Override
    BigDecimal[] setSumRegistrationForTMoney() {
        return new BigDecimal[]{new BigDecimal("-0.5"),
                new BigDecimal("0")};
    }

    @Override
    BigDecimal[] setSumWonSpin() {
        return new BigDecimal[]{new BigDecimal("0"),
                new BigDecimal("0")};
    }

    @Override
    BigDecimal[] setSumProfitSpin() {
        return new BigDecimal[]{new BigDecimal("-0.5"),
                new BigDecimal("0")};
    }

    @Override
    BigDecimal[] setSumBonusSpin() {
        return new BigDecimal[]{new BigDecimal("0.5"),
                new BigDecimal("0")};
    }

    @Override
    BigDecimal[] setSumProfitPoolSpin() {
        return new BigDecimal[]{new BigDecimal("-16.5"),
                new BigDecimal("-4.0")};
    }

    @Override
    BigDecimal[] setSumBonusPoolSpin() {
        return new BigDecimal[]{new BigDecimal("16.5"),
                new BigDecimal("4.0")};
    }

    @Override
    Integer setTotalCountRegistrationWithoutUnregistration() {
        return 37;
    }

    @Override
    BigDecimal setTotalProfitSpin() {
        return new BigDecimal("-0.50");
    }

    @Override
    BigDecimal setTotalBonusSpin() {
        return new BigDecimal("0.50");
    }

    @Override
    Integer[] setCountRegistrationMTT() {
        return new Integer[]{40, 24, 1, 1, 47, 25, 8, 43, 4, 7, 32, 6};
    }

    @Override
    BigDecimal[] setSumRegistrationMTT() {
        return new BigDecimal[]{
                new BigDecimal("-1080.0"),
                new BigDecimal("-1320.0"),
                new BigDecimal("-94.5"),
                new BigDecimal("-20.24"),
                new BigDecimal("-1009.0"),
                new BigDecimal("-1100.0"),
                new BigDecimal("0"),
                new BigDecimal("-468"),
                new BigDecimal("-200"),
                new BigDecimal("-231"),
                new BigDecimal("-523"),
                new BigDecimal("0")
        };
    }

    @Override
    Integer[] setCountUnRegistrationMTT() {
        return new Integer[]{0, 1, 0, 0, 0, 0, 3, 1, 0, 0, 0, 0,};
    }

    @Override
    BigDecimal[] setSumUnRegistrationMTT() {
        return new BigDecimal[]{
                new BigDecimal("0"),
                new BigDecimal("55"),
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("11"),
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("0")
        };
    }

    @Override
    Integer[] setCountReEntryMTT() {
        return new Integer[]{14, 3, 1, 0, 8, 3, 0, 6, 1, 3, 4, 0};
    }

    @Override
    BigDecimal[] setSumReEntryMTT() {
        return new BigDecimal[]{
                new BigDecimal("-378"),
                new BigDecimal("-165"),
                new BigDecimal("-109"),
                new BigDecimal("0"),
                new BigDecimal("-176"),
                new BigDecimal("-132"),
                new BigDecimal("0"),
                new BigDecimal("-66"),
                new BigDecimal("-50"),
                new BigDecimal("-99"),
                new BigDecimal("-66"),
                new BigDecimal("0")
        };
    }

    @Override
    BigDecimal[] setSumKnockoutMTT() {
        return new BigDecimal[]{
                new BigDecimal("334.34"),
                new BigDecimal("337.51"),
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("250.63"),
                new BigDecimal("257.5"),
                new BigDecimal("0"),
                new BigDecimal("196.72"),
                new BigDecimal("232.82"),
                new BigDecimal("226.88"),
                new BigDecimal("302.05"),
                new BigDecimal("0")
        };
    }

    @Override
    BigDecimal[] setSumInterimMTT() {
        return new BigDecimal[]{
                new BigDecimal("133.21"),
                new BigDecimal("113.5"),
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("127.49"),
                new BigDecimal("73.98"),
                new BigDecimal("0"),
                new BigDecimal("73.91"),
                new BigDecimal("35.98"),
                new BigDecimal("28.18"),
                new BigDecimal("93.13"),
                new BigDecimal("0")
        };
    }

    @Override
    BigDecimal[] setSumWonMTT() {
        return new BigDecimal[]{
                new BigDecimal("149.9"),
                new BigDecimal("17.65"),
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("107.45"),
                new BigDecimal("7.5"),
                new BigDecimal("0"),
                new BigDecimal("88.73"),
                new BigDecimal("87.17"),
                new BigDecimal("47.03"),
                new BigDecimal("44.73"),
                new BigDecimal("0")
        };
    }

    @Override
    BigDecimal[] setProfitMTT() {
        return new BigDecimal[]{
                new BigDecimal("-840.55"),
                new BigDecimal("-961.34"),
                new BigDecimal("-203.5"),
                new BigDecimal("-20.24"),
                new BigDecimal("-699.43"),
                new BigDecimal("-893.02"),
                new BigDecimal("0"),
                new BigDecimal("-163.64"),
                new BigDecimal("105.97"),
                new BigDecimal("-27.91"),
                new BigDecimal("-149.09"),
                new BigDecimal("0")
        };
    }

    @Override
    BigDecimal setTotalProfitMTT() {
        return new BigDecimal("-3852.75");
    }
}
