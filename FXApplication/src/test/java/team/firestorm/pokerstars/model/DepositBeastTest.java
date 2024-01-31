package team.firestorm.pokerstars.model;

import java.math.BigDecimal;

class DepositBeastTest extends CsvFileBaseForTests {
    DepositBeastTest() {
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
    BigDecimal[] setSumRegistrationForTMoney() {
        return new BigDecimal[]{new BigDecimal("0.00"), new BigDecimal("0.00")};
    }

    @Override
    Integer[] setCountRegistrationForTicketSpin() {
        return new Integer[]{1, 0};
    }

    @Override
    BigDecimal[] setSumWonSpin() {
        return new BigDecimal[]{new BigDecimal("250.00"), new BigDecimal("43100.00")};
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
    BigDecimal setTotalProfitSpin() {
        return new BigDecimal("-2375.00");
    }

    @Override
    BigDecimal setTotalBonusSpin() {
        return new BigDecimal("250.00");
    }

    @Override
    Integer[] setCountRegistrationMTT() {
        return new Integer[]{1, 1, 1};
    }

    @Override
    BigDecimal[] setSumRegistrationMTT() {
        return new BigDecimal[]{
                new BigDecimal("-7.5"),
                new BigDecimal("-7.5"),
                new BigDecimal("-1.1")
        };
    }

    @Override
    Integer[] setCountUnRegistrationMTT() {
        return new Integer[]{1, 1, 1};
    }

    @Override
    BigDecimal[] setSumUnRegistrationMTT() {
        return new BigDecimal[]{
                new BigDecimal("7.5"),
                new BigDecimal("7.5"),
                new BigDecimal("1.1")
        };
    }

    @Override
    Integer[] setCountRegistrationByTMoneyMTT() {
        return new Integer[]{
                0,
                0,
                0
        };
    }

    @Override
    BigDecimal[] setSumRegistrationByTMoneyMTT() {
        return new BigDecimal[]{
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("0")
        };
    }

    @Override
    Integer[] setCountUnRegistrationByTMoneyMTT() {
        return new Integer[]{
                0,
                0,
                0
        };
    }

    @Override
    BigDecimal[] setSumUnRegistrationByTMoneyMTT() {
        return new BigDecimal[]{
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("0")
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
