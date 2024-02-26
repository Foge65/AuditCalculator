package team.firestorm.pokerstars.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PokerStars {
    String getRegistrationString();

    String getUnRegistrationString();

    String getReEntryString();

    String getKnockOutString();

    String getInterimString();

    String getWonString();

    String getWithdrawalString();

    String getMoneySentString();

    String[] getMoneyReceivedString();

    String getDepositString();

    String getChestString();

    String getExchangeCoinString();

    String getCasinoString();

    String[] getBonuses();

    String getSeatInTable();

    String getSeatOutTable();

    String getAutoRebuyTable();

    Set<String> game(String regex, List<String[]> strings);

    Map<String, Boolean> checkBoxState(Set<String> game);

    String replaceQuote(String string);

    String replaceComma(String string);

    Map<String, Integer> countGame(List<String[]> strings, Set<String> game, String action);

    Map<String, BigDecimal> sumForDifferentColumn(List<String[]> strings, Set<String> game, String action, int element);

    Map<String, Integer> countRegistrationByTMoney(List<String[]> strings, Set<String> game, int tMoney);

    Map<String, Integer> countUnRegistrationByTMoney(List<String[]> strings, Set<String> game, int tMoney);

    Map<String, BigDecimal> sumRegistrationByTMoney(List<String[]> strings, Set<String> game, int tMoney);

    Map<String, BigDecimal> sumUnRegistrationByTMoney(List<String[]> strings, Set<String> game, int tMoney);

    Map<String, Integer> countRegistrationByTicket(List<String[]> strings, Set<String> game, int amount, int tMoney);

    Map<String, BigDecimal> sumProfitSpin(List<String[]> strings, Set<String> game, int amount, int tMoney);

    Map<String, BigDecimal> sumBonusSpin(List<String[]> strings, Set<String> game, int amount, int tMoney);

    Map<String, BigDecimal> sumProfitPool(List<String[]> strings, Set<String> game, int amount, int tMoney);

    Map<String, BigDecimal> sumBonusPool(List<String[]> strings, Set<String> game, int amount, int tMoney);

    Integer totalCountRegistrationSpinWithoutUnregistration(List<String[]> strings, String registerAction, String unRegisterAction);

    Map<String, Integer> countReEntry(List<String[]> strings, Set<String> game);

    Map<String, BigDecimal> sumReEntry(List<String[]> strings, Set<String> game, int amount);

    Map<String, BigDecimal> sumKnockout(List<String[]> strings, Set<String> game, int amount);

    Map<String, BigDecimal> sumInterim(List<String[]> strings, Set<String> game, int amount);

    Map<String, BigDecimal> sumProfitMTT(List<String[]> strings, Set<String> game, int amount);

    Map<String, BigDecimal> sumProfitCashGame(List<String[]> strings, Set<String> game, int amount);

    String startBalanceMoney(List<String[]> strings, int amount, int balance, String[] receivedActions);

    String startBalanceTMoney(List<String[]> strings, int amount, int balance);

    String startBalanceCoin(List<String[]> strings, int element);

    String finalBalance(List<String[]> strings, int element);

    int totalCountRow(List<String[]> strings);

    String sumTransfer(List<String[]> strings, int amount, String action);

    String sumTransfer(List<String[]> strings, int amount, String[] actions);

    String sumOtherBonus(List<String[]> strings, String[] bonuses, int amount);

    Double parseBuyInFromString(String buyIn);

    Set<String> dates(List<String[]> strings, String[] receivedAction);

    Map<String, String> transferDetail(List<String[]> strings, Set<String> dates, String action, int amount);

    Map<String, String> transferDetail(List<String[]> strings, Set<String> dates, String[] receivedAction, int amount);
}
