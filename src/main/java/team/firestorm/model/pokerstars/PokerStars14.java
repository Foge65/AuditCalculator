package team.firestorm.model.pokerstars;

public class PokerStars14 implements PokerStarsIndex {
    private final int amount = 6;
    private final int starsCoin = 7;
    private final int tMoney = 8;
    private final int balance = 10;

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public int getStarsCoin() {
        return starsCoin;
    }

    @Override
    public int getTMoney() {
        return tMoney;
    }

    @Override
    public int getBalance() {
        return balance;
    }

}
