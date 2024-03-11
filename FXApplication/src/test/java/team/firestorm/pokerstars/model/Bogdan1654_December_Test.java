package team.firestorm.pokerstars.model;

import java.math.BigDecimal;

public class Bogdan1654_December_Test extends BaseForTests {
    public Bogdan1654_December_Test() {
        super("src/test/resources/Bogdan1654_December.csv");
    }

    @Override
    Integer[] setCountRegistrationSpin() {
        return new Integer[]{};
    }

    @Override
    BigDecimal[] setSumRegistrationSpin() {
        return new BigDecimal[]{};
    }

    @Override
    Integer[] setCountUnRegistrationSpin() {
        return new Integer[]{};
    }

    @Override
    BigDecimal[] setSumUnRegistrationSpin() {
        return new BigDecimal[]{};
    }

    @Override
    BigDecimal[] setSumRegistrationForTMoney() {
        return new BigDecimal[]{};
    }

    @Override
    Integer[] setCountRegistrationForTicketSpin() {
        return new Integer[]{};
    }

    @Override
    BigDecimal[] setSumWonSpin() {
        return new BigDecimal[]{};
    }

    @Override
    BigDecimal[] setSumProfitSpin() {
        return new BigDecimal[]{};
    }

    @Override
    BigDecimal[] setSumBonusSpin() {
        return new BigDecimal[]{};
    }

    @Override
    BigDecimal[] setSumProfitPoolSpin() {
        return new BigDecimal[]{};
    }

    @Override
    BigDecimal[] setSumBonusPoolSpin() {
        return new BigDecimal[]{};
    }

    @Override
    Integer setTotalCountRegistrationWithoutUnregistration() {
        return 0;
    }

    @Override
    BigDecimal setTotalProfitSpin() {
        return new BigDecimal("0");
    }

    @Override
    BigDecimal setTotalBonusSpin() {
        return new BigDecimal("0");
    }

    @Override
    Integer[] setCountRegistrationMTT() {
        return new Integer[]{
                5,
                32,
                3,
                2,
                14,
                27,
                24,
                1,
                4,
                1
        };
    }

    @Override
    BigDecimal[] setSumRegistrationMTT() {
        return new BigDecimal[]{
                new BigDecimal("-135"),
                new BigDecimal("-1710.0"),
                new BigDecimal("-327.0"),
                new BigDecimal("-430.0"),
                new BigDecimal("-308.0"),
                new BigDecimal("-1188.0"),
                new BigDecimal("-792.0"),
                new BigDecimal("-11.0"),
                new BigDecimal("-66.0"),
                new BigDecimal("-33.0")
        };
    }

    @Override
    Integer[] setCountUnRegistrationMTT() {
        return new Integer[]{
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                0,
                0,
                0
        };
    }

    @Override
    BigDecimal[] setSumUnRegistrationMTT() {
        return new BigDecimal[]{
                new BigDecimal("0"),
                new BigDecimal("55"),
                new BigDecimal("0"),
                new BigDecimal("215"),
                new BigDecimal("0"),
                new BigDecimal("44"),
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("0")
        };
    }

    @Override
    Integer[] setCountRegistrationByTMoneyMTT() {
        return new Integer[]{
                0,
                1,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0
        };
    }

    @Override
    BigDecimal[] setSumRegistrationByTMoneyMTT() {
        return new BigDecimal[]{
                new BigDecimal("0"),
                new BigDecimal("-50.0"),
                new BigDecimal("0"),
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
    Integer[] setCountUnRegistrationByTMoneyMTT() {
        return new Integer[]{
                0,
                0,
                0,
                0,
                0,
                0,
                0,
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
                new BigDecimal("0"),
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
        return new Integer[]{
                0,
                10,
                1,
                1,
                4,
                11,
                4,
                0,
                1,
                0
        };
    }

    @Override
    BigDecimal[] setSumReEntryMTT() {
        return new BigDecimal[]{
                new BigDecimal("0"),
                new BigDecimal("-550"),
                new BigDecimal("-109"),
                new BigDecimal("-215"),
                new BigDecimal("-88"),
                new BigDecimal("-484"),
                new BigDecimal("-132"),
                new BigDecimal("0"),
                new BigDecimal("-16.5"),
                new BigDecimal("0")
        };
    }

    @Override
    BigDecimal[] setSumKnockoutMTT() {
        return new BigDecimal[]{
                new BigDecimal("42.89"),
                new BigDecimal("912.51"),
                new BigDecimal("165.63"),
                new BigDecimal("0"),
                new BigDecimal("146.89"),
                new BigDecimal("2858.19"),
                new BigDecimal("204.84"),
                new BigDecimal("0"),
                new BigDecimal("8.44"),
                new BigDecimal("0")
        };
    }

    @Override
    BigDecimal[] setSumInterimMTT() {
        return new BigDecimal[]{
                new BigDecimal("22.88"),
                new BigDecimal("560.89"),
                new BigDecimal("90.46"),
                new BigDecimal("0"),
                new BigDecimal("71.15"),
                new BigDecimal("186.48"),
                new BigDecimal("137.2"),
                new BigDecimal("0"),
                new BigDecimal("13.08"),
                new BigDecimal("0")
        };
    }

    @Override
    BigDecimal[] setSumWonMTT() {
        return new BigDecimal[]{
                new BigDecimal("19.14"),
                new BigDecimal("355.2"),
                new BigDecimal("39.74"),
                new BigDecimal("0"),
                new BigDecimal("56.29"),
                new BigDecimal("2932.16"),
                new BigDecimal("389.44"),
                new BigDecimal("0"),
                new BigDecimal("13.91"),
                new BigDecimal("0")
        };
    }

    @Override
    BigDecimal[] setProfitMTT() {
        return new BigDecimal[]{
                new BigDecimal("-50.09"),
                new BigDecimal("-376.4"),
                new BigDecimal("-140.17"),
                new BigDecimal("-430"),
                new BigDecimal("-121.67"),
                new BigDecimal("4348.83"),
                new BigDecimal("-192.52"),
                new BigDecimal("-11"),
                new BigDecimal("-47.07"),
                new BigDecimal("-33"),
        };
    }

    @Override
    BigDecimal setTotalProfitMTT() {
        return new BigDecimal("2946.91");
    }

    @Override
    String setStartBalance() {
        return "2282.93";
    }

    @Override
    String setFinalBalance() {
        return "3004.84";
    }

    @Override
    String setStartTMoneyBalance() {
        return "0.00";
    }

    @Override
    String setFinalTMoneyBalance() {
        return "0.00";
    }

    @Override
    String setStartCoinBalance() {
        return "9178.00";
    }

    @Override
    String setFinalCoinBalance() {
        return "11678.00";
    }
}
