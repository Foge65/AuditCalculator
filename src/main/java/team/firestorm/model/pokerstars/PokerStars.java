package team.firestorm.model.pokerstars;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public interface PokerStars {
    String getRegistration();

    String getUnregistration();

    String getWon();

    String getReEntry();

    String getKnockOut();

    String getWithdrawal();

    String getMoneySent();

    String getMoneyReceived1();

    String getMoneyReceived2();

    String getDeposit();

    String replaceQuote(String string);

    String replaceComma(String string);

    Set<String> gameSpin();

    Set<String> gameAnother();

    Map<String, Integer> countTourney(Set<String> buyIns, String action);

    Map<String, BigDecimal> sumColumn(Set<String> buyIns, String action, int column);

    Map<String, Integer> countRegistrationByTicket(Set<String> buyIns, int amount, int tMoney);

    Map<String, BigDecimal> sumProfit(Set<String> buyIns, int amount, int tMoney);

    Map<String, BigDecimal> sumBonus(Set<String> buyIns, int amount, int tMoney);

    Map<String, Integer> countReEntry(Set<String> buyIns);

    Map<String, BigDecimal> sumReEntry(Set<String> buyIns, int amount);

    Map<String, Integer> countWonTicket(Set<String> buyIns);

    Map<String, BigDecimal> sumKnockout(Set<String> buyIns, int amount);

    String startBalance(int column);

    int totalCountRow();

    String finalBalance(int column);

    String sumBalance(int column);

    String sumTransfer(int amount, String actionParam1, String actionParam2);

}
