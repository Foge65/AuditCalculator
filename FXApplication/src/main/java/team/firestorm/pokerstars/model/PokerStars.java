package team.firestorm.pokerstars.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PokerStars {
    Set<String> game(String regex, List<String[]> strings);
    String replaceQuote(String string);
    String replaceComma(String string);
    Map<String, Integer> countTourney(List<String[]> strings, Set<String> game, String action);
    Map<String, BigDecimal> sumColumn(List<String[]> strings, Set<String> game, String action, int column);
    Map<String, Integer> countRegistrationByTicket(List<String[]> strings, Set<String> game, int amount, int tMoney);
    Map<String, BigDecimal> sumProfit(List<String[]> strings, Set<String> game, int amount, int tMoney);
    Map<String, BigDecimal> sumBonus(List<String[]> strings, Set<String> game, int amount, int tMoney);
    Map<String, Integer> countReEntry(List<String[]> strings, Set<String> game);
    Map<String, BigDecimal> sumReEntry(List<String[]> strings, Set<String> game, int amount);
    Map<String, BigDecimal> sumKnockout(List<String[]> strings, Set<String> game, int amount);
    String startBalance(List<String[]> strings, int column);
    String startBalanceMoney(List<String[]> strings, int amount, int balance);
    String startBalanceTMoney(List<String[]> strings, int amount, int balance);
    String finalBalance(List<String[]> strings, int column);
    int totalCountRow(List<String[]> strings);
    String sumTransfer(List<String[]> strings, int amount, String actionParam1, String actionParam2);
    Map<String, BigDecimal> sumProfitPool(List<String[]> strings, Set<String> game, int amount, int tMoney);
    Map<String, BigDecimal> sumBonusPool(List<String[]> strings, Set<String> game, int amount, int tMoney);
    Double parseBuyInFromString(String buyIn);
    String getRegistrationString();
    String getUnRegistrationString();
    String getNetWonString();
    String getReEntryString();
    String getKnockOutString();
    String getWithdrawalString();
    String getMoneySentString();
    String getMoneyReceivedStringVer1();
    String getMoneyReceivedStringVer2();
    String getDepositString();
    String getChestString();
    String getExchangeCoinString();
    String getCasinoString();
    String[] getBonuses();
    String sumOtherBonus(List<String[]> strings, String[] bonuses, int amount);
    Integer totalCountRegistrationSpinWithoutUnregistration(List<String[]> strings, String registerAction, String unRegisterAction);
}
