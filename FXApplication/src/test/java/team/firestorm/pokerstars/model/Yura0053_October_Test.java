package team.firestorm.pokerstars.model;

import java.math.BigDecimal;

class Yura0053_October_Test extends BaseForTests {
    Yura0053_October_Test() {
        super("src/test/resources/Yura0053_October.csv");
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
    BigDecimal[] setSumRegistrationForTMoney() {
        return new BigDecimal[]{new BigDecimal("-150.00"), new BigDecimal("-50.00")};
    }

    @Override
    Integer[] setCountRegistrationForTicketSpin() {
        return new Integer[]{0, 0};
    }

    @Override
    BigDecimal[] setSumWonSpin() {
        return new BigDecimal[]{new BigDecimal("17676.00"), new BigDecimal("3205.00")};
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
    BigDecimal setTotalProfitSpin() {
        return new BigDecimal("-675.00");
    }

    @Override
    BigDecimal setTotalBonusSpin() {
        return new BigDecimal("200.00");
    }

    @Override
    Integer[] setCountRegistrationMTT() {
        return new Integer[]{1, 1, 1, 1};
    }

    @Override
    BigDecimal[] setSumRegistrationMTT() {
        return new BigDecimal[]{
                new BigDecimal("-22"),
                new BigDecimal("0"),
                new BigDecimal("-16.5"),
                new BigDecimal("-3.3")
        };
    }

    @Override
    Integer[] setCountUnRegistrationMTT() {
        return new Integer[]{1, 1, 1, 1};
    }

    @Override
    BigDecimal[] setSumUnRegistrationMTT() {
        return new BigDecimal[]{
                new BigDecimal("22"),
                new BigDecimal("0"),
                new BigDecimal("16.5"),
                new BigDecimal("3.3")
        };
    }

    @Override
    Integer[] setCountRegistrationByTMoneyMTT() {
        return new Integer[]{
                0,
                1,
                0,
                0
        };
    }

    @Override
    BigDecimal[] setSumRegistrationByTMoneyMTT() {
        return new BigDecimal[]{
                new BigDecimal("0"),
                new BigDecimal("-1.1"),
                new BigDecimal("0"),
                new BigDecimal("0")
        };
    }

    @Override
    Integer[] setCountUnRegistrationByTMoneyMTT() {
        return new Integer[]{
                0,
                1,
                0,
                0
        };
    }

    @Override
    BigDecimal[] setSumUnRegistrationByTMoneyMTT() {
        return new BigDecimal[]{
                new BigDecimal("0"),
                new BigDecimal("1.1"),
                new BigDecimal("0"),
                new BigDecimal("0")
        };
    }

    @Override
    Integer[] setCountReEntryMTT() {
        return new Integer[]{0, 0, 0, 0};
    }

    @Override
    BigDecimal[] setSumReEntryMTT() {
        return new BigDecimal[]{
                new BigDecimal("0"),
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
                new BigDecimal("0"),
                new BigDecimal("0")
        };
    }

    @Override
    BigDecimal[] setSumInterimMTT() {
        return new BigDecimal[]{
                new BigDecimal("0"),
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
                new BigDecimal("0"),
                new BigDecimal("0")
        };
    }

    @Override
    BigDecimal[] setProfitMTT() {
        return new BigDecimal[]{
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("0")
        };
    }

    @Override
    BigDecimal setTotalProfitMTT() {
        return new BigDecimal("0.00");
    }

    @Override
    String setStartBalance() {
        return "1015.84";
    }

    @Override
    String setFinalBalance() {
        return "326.39";
    }

    @Override
    String setStartTMoneyBalance() {
        return "75.00";
    }

    @Override
    String setFinalTMoneyBalance() {
        return "0.00";
    }

    @Override
    String setStartCoinBalance() {
        return "198.56";
    }

    @Override
    String setFinalCoinBalance() {
        return "192.56";
    }
}
