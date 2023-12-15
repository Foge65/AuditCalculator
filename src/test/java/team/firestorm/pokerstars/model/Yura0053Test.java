package team.firestorm.pokerstars.model;

import java.math.BigDecimal;

class Yura0053Test extends PokerStarsBaseTest {
    Yura0053Test() {
        super("src/test/resources/Yura0053.csv");
    }

    @Override
    Integer[] setTicketArray() {
        return new Integer[]{0, 0};
    }

    @Override
    BigDecimal[] setProfitArray() {
        return new BigDecimal[]{new BigDecimal("-620"), new BigDecimal("-55")};
    }

    @Override
    BigDecimal[] setBonusArray() {
        return new BigDecimal[]{new BigDecimal("150"), new BigDecimal("50")};
    }

    @Override
    BigDecimal[] setProfitPoolArray() {
        return new BigDecimal[]{new BigDecimal("-620"), new BigDecimal("-55")};
    }

    @Override
    BigDecimal[] setBonusPoolArray() {
        return new BigDecimal[]{new BigDecimal("150"), new BigDecimal("50")};
    }
}
