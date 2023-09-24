package team.firestorm.model.pokerstars;

public class PokerStars13 implements PokerStarsIndex {
    private final int amount = 5;
    private final int starsCoin = 6;
    private final int tMoney = 7;
    private final int balance = 9;

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
