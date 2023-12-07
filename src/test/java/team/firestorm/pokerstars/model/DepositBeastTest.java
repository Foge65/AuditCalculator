package team.firestorm.pokerstars.model;

import java.math.BigDecimal;

class DepositBeastTest extends PokerStarsBaseTest {
    DepositBeastTest() {
        super("c:\\Users\\user\\Downloads\\audits\\csv\\ccf732bef1c8ac13\\Pl111aying history audit.csv");
    }

    @Override
    Integer[] setTicketArray() {
        return new Integer[]{1, 0};
    }

    @Override
    BigDecimal[] setProfitArray() {
        return new BigDecimal[]{new BigDecimal("0"), new BigDecimal("-2375")};
    }

    @Override
    BigDecimal[] setBonusArray() {
        return new BigDecimal[]{new BigDecimal("250"), new BigDecimal("0")};
    }

    @Override
    BigDecimal[] setProfitPoolArray() {
        return new BigDecimal[]{new BigDecimal("200"), new BigDecimal("-2375")};
    }

    @Override
    BigDecimal[] setBonusPoolArray() {
        return new BigDecimal[]{new BigDecimal("50"), new BigDecimal("0")};
    }
}
