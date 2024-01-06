package team.firestorm.pokerstars.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
@Getter
public abstract class PokerStarsBase implements PokerStars {
    private static final int ACTION = 1;
    private static final int ID = 2;
    private static final int GAME = 3;
    public final String regexGameSpin = "^\sNL\sHold'em\sSit&Go\sBuy-In:\s\\d+[.,]\\d+\\/\\d+[.,]\\d+$";
    public final String regexGameMTT = "^(?!\\d+[.,]\\d+\\/\\d+[.,]\\d+)(?!.*Sit&Go\sBuy-In).*$";
    public final String regexGameCash = "^\\d+[.,]\\d+\\/\\d+[.,]\\d+\\s\\w.+$";
    private CsvParser csvParser;

    @Override
    public String replaceQuote(String string) {
        return string.replace("\"", "");
    }

    @Override
    public Set<String> game(String regex, List<String[]> strings) {
        Pattern pattern = Pattern.compile(regex);
        return strings.stream()
                .map(element -> element[GAME])
                .map(this::replaceQuote)
                .filter(buyIn -> pattern.matcher(buyIn).find())
                .filter(buyIn -> !buyIn.isEmpty())
                .collect(Collectors.toSet());
    }

    @Override
    public Map<String, BigDecimal> sumColumn(List<String[]> strings, Set<String> game, String action, int column) {
        Map<String, BigDecimal> sumRegistration = new HashMap<>();
        for (String buyIn : game) {
            BigDecimal sum = BigDecimal.ZERO;
            for (String[] stringArray : strings) {
                String actionValue = stringArray[ACTION];
                String buyInValueQuote = gameParser(stringArray);
                String columnValueComma = numberColumnParser(stringArray[column]);
                if (buyInValueQuote.equals(buyIn) && actionValue.startsWith(action)) {
                    sum = sum.add(new BigDecimal(columnValueComma));
                }
            }
            sumRegistration.put(buyIn, sum);
        }
        return sumRegistration;
    }

    @Override
    public Map<String, Integer> countRegistrationByTicket(List<String[]> strings, Set<String> game, int amount, int tMoney) {
        Map<String, Integer> countRegistration = new HashMap<>();
        for (String buyIn : game) {
            int counter = 0;
            for (String[] stringArray : strings) {
                String actionValue = stringArray[ACTION];
                String buyInValueQuote = gameParser(stringArray);
                String amountValueComma = numberColumnParser(stringArray[amount]);
                String tMoneyValueComma = numberColumnParser(stringArray[tMoney]);
                if (buyInValueQuote.equals(buyIn)
                        && actionValue.equals(getRegistrationString())
                        && BigDecimal.ZERO.compareTo(new BigDecimal(amountValueComma)) == 0
                        && BigDecimal.ZERO.compareTo(new BigDecimal(tMoneyValueComma)) == 0) {
                    counter++;
                }
            }
            countRegistration.put(buyIn, counter);
        }
        return countRegistration;
    }

    @Override
    public Map<String, BigDecimal> sumProfit(List<String[]> strings, Set<String> game, int amount, int tMoney) {
        Map<String, BigDecimal> profit = new HashMap<>();
        Set<String> ids = new HashSet<>();
        for (String buyIn : game) {
            BigDecimal sumRegistrationForMoney = BigDecimal.ZERO;
            BigDecimal sumRegistrationForTMoney = BigDecimal.ZERO;
            BigDecimal sumUnRegistrationForMoney = BigDecimal.ZERO;
            BigDecimal sumUnRegistrationForTMoney = BigDecimal.ZERO;
            BigDecimal sumNetWon = BigDecimal.ZERO;
            BigDecimal sumNetWonForTicket = BigDecimal.ZERO;
            for (String[] stringArray : strings) {
                String actionValue = stringArray[ACTION];
                String idValue = stringArray[ID];
                String buyInValueQuote = gameParser(stringArray);

                String amountValueComma = numberColumnParser(stringArray[amount]);
                BigDecimal amountBigDecimal = new BigDecimal(amountValueComma);

                String tMoneyValueComma = numberColumnParser(stringArray[tMoney]);
                BigDecimal tMoneyBigDecimal = new BigDecimal(tMoneyValueComma);
                if (buyInValueQuote.equals(buyIn)) {
                    if (actionValue.equals(getRegistrationString())) {
                        sumRegistrationForMoney = sumRegistrationForMoney.add(amountBigDecimal);
                        sumRegistrationForTMoney = sumRegistrationForTMoney.add(tMoneyBigDecimal);
                    }
                    if (actionValue.equals(getRegistrationString())
                            && amountBigDecimal.compareTo(BigDecimal.ZERO) == 0
                            && tMoneyBigDecimal.compareTo(BigDecimal.ZERO) == 0) {
                        ids.add(idValue);
                    }
                    if (actionValue.equals(getUnRegistrationString())) {
                        sumUnRegistrationForMoney = sumUnRegistrationForMoney.add(amountBigDecimal);
                        sumUnRegistrationForTMoney = sumUnRegistrationForTMoney.add(tMoneyBigDecimal);
                    }
                    if (actionValue.equals(getNetWonString())) {
                        sumNetWon = sumNetWon.add(amountBigDecimal);
                    }
                    if (actionValue.equals(getNetWonString()) && ids.contains(idValue)) {
                        sumNetWonForTicket = sumNetWonForTicket.subtract(amountBigDecimal);
                    }
                }
            }
            profit.put(buyIn, sumRegistrationForMoney
                    .add(sumRegistrationForTMoney)
                    .add(sumUnRegistrationForMoney)
                    .add(sumUnRegistrationForTMoney)
                    .add(sumNetWon)
                    .add(sumNetWonForTicket));
        }
        return profit;
    }

    @Override
    public Map<String, BigDecimal> sumBonus(List<String[]> strings, Set<String> game, int amount, int tMoney) {
        Map<String, BigDecimal> bonus = new HashMap<>();
        Set<String> ids = new HashSet<>();
        for (String buyIn : game) {
            BigDecimal sumRegistrationForTMoney = BigDecimal.ZERO;
            BigDecimal sumUnRegistrationForTMoney = BigDecimal.ZERO;
            BigDecimal sumNetWonForTicket = BigDecimal.ZERO;
            for (String[] stringArray : strings) {
                String actionValue = stringArray[ACTION];
                String idValue = stringArray[ID];
                String buyInValueQuote = gameParser(stringArray);

                String amountValueComma = numberColumnParser(stringArray[amount]);
                BigDecimal amountBigDecimal = new BigDecimal(amountValueComma);

                String tMoneyValueComma = numberColumnParser(stringArray[tMoney]);
                BigDecimal tMoneyBigDecimal = new BigDecimal(tMoneyValueComma);
                if (buyInValueQuote.equals(buyIn)) {
                    if (actionValue.equals(getRegistrationString())) {
                        sumRegistrationForTMoney = sumRegistrationForTMoney.subtract(tMoneyBigDecimal);
                    }
                    if (actionValue.equals(getRegistrationString())
                            && amountBigDecimal.compareTo(BigDecimal.ZERO) == 0
                            && tMoneyBigDecimal.compareTo(BigDecimal.ZERO) == 0) {
                        ids.add(idValue);
                    }
                    if (actionValue.equals(getUnRegistrationString())) {
                        sumUnRegistrationForTMoney = sumUnRegistrationForTMoney.subtract(tMoneyBigDecimal);
                    }
                    if (actionValue.equals(getNetWonString()) && ids.contains(idValue)) {
                        sumNetWonForTicket = sumNetWonForTicket.add(amountBigDecimal);
                    }
                }
            }
            bonus.put(buyIn, sumRegistrationForTMoney
                    .add(sumUnRegistrationForTMoney)
                    .add(sumNetWonForTicket));
        }
        return bonus;
    }

    @Override
    public Map<String, Integer> countTourney(List<String[]> strings, Set<String> game, String action) {
        Map<String, Integer> count = new HashMap<>();
        for (String buyIn : game) {
            int counter = 0;
            for (String[] stringArray : strings) {
                String actionValue = stringArray[ACTION];
                String buyInValueQuote = gameParser(stringArray);
                if (buyIn.equals(buyInValueQuote) && actionValue.startsWith(action)) {
                    counter++;
                }
            }
            count.put(buyIn, counter);
        }
        return count;
    }

    @Override
    public Map<String, Integer> countReEntry(List<String[]> strings, Set<String> game) {
        Map<String, Integer> countReEntry = new HashMap<>();
        List<String> ids = new ArrayList<>();
        for (String[] stringArray : strings) {
            String actionValue = stringArray[ACTION];
            String idValue = stringArray[ID];
            if (actionValue.equals(getReEntryString())) {
                ids.add(idValue);
            }
        }
        for (String buyIn : game) {
            int counter = 0;
            for (String[] stringArray : strings) {
                String actionValue = stringArray[ACTION];
                String idValue = stringArray[ID];
                String buyInValueQuote = gameParser(stringArray);
                if (buyInValueQuote.equals(buyIn) && actionValue.equals(getRegistrationString())) {
                    counter += (int) ids.stream().filter(idValue::startsWith).count();
                }
            }
            countReEntry.put(buyIn, counter);
        }
        return countReEntry;
    }

    @Override
    public Map<String, BigDecimal> sumReEntry(List<String[]> strings, Set<String> game, int amount) {
        Map<String, BigDecimal> sumReEntry = new HashMap<>();
        ArrayList<String> ids = new ArrayList<>();
        for (String[] stringArray : strings) {
            String actionValue = stringArray[ACTION];
            String idValue = stringArray[ID];
            if (actionValue.equals(getReEntryString())) {
                ids.add(idValue);
            }
        }
        for (String buyIn : game) {
            BigDecimal sum = BigDecimal.ZERO;
            for (String[] stringArray : strings) {
                String actionValue = stringArray[ACTION];
                String idValue = stringArray[ID];
                String buyInValueQuote = gameParser(stringArray);
                String amountValueComma = numberColumnParser(stringArray[amount]);
                if (buyInValueQuote.equals(buyIn) && actionValue.equals(getRegistrationString())) {
                    for (String id : ids) {
                        if (id.startsWith(idValue)) {
                            sum = sum.add(new BigDecimal(amountValueComma));
                        }
                    }
                }
            }
            sumReEntry.put(buyIn, sum);
        }
        return sumReEntry;
    }

    @Override
    public Map<String, BigDecimal> sumKnockout(List<String[]> strings, Set<String> game, int amount) {
        Map<String, BigDecimal> knockoutMap = new HashMap<>();
        Map<String, List<String>> buyInIdMap = new HashMap<>();
        for (String[] stringArray : strings) {
            String actionValue = stringArray[ACTION];
            String idValue = stringArray[ID];
            String buyInValueQuote = gameParser(stringArray);
            if (actionValue.equals(getRegistrationString())) {
                if (buyInIdMap.containsKey(buyInValueQuote)) {
                    buyInIdMap.get(buyInValueQuote).add(idValue);
                } else {
                    List<String> ids = new ArrayList<>();
                    ids.add(idValue);
                    buyInIdMap.put(buyInValueQuote, ids);
                }
            }
        }
        for (String buyIn : game) {
            BigDecimal sum = BigDecimal.ZERO;
            for (String[] stringArray : strings) {
                String actionValue = stringArray[ACTION];
                String idValue = stringArray[ID];
                String amountValueComma = numberColumnParser(stringArray[amount]);
                if (actionValue.equals(getKnockOutString())) {
                    for (Map.Entry<String, List<String>> entry : buyInIdMap.entrySet()) {
                        String bi = entry.getKey();
                        List<String> ids = entry.getValue();
                        if (bi.equals(buyIn)) {
                            for (String id : ids) {
                                if (id.startsWith(idValue)) {
                                    sum = sum.add(new BigDecimal(amountValueComma));
                                }
                            }
                        }
                    }
                }
            }
            knockoutMap.put(buyIn, sum);
        }
        return knockoutMap;
    }

    @Override
    public String startBalance(List<String[]> strings, int column) {
        String[] string = strings.get(0);
        String startBalanceValue = replaceQuote(string[column]);
        return replaceComma(startBalanceValue);
    }

    @Override
    public String startBalanceMoney(List<String[]> strings, int amount, int balance) {
        String[] firstString = strings.get(0);
        String actionValue = replaceQuote(firstString[ACTION]);

        String amountValueComma = numberColumnParser(firstString[amount]);
        BigDecimal amountBigDecimal = new BigDecimal(amountValueComma);

        String balanceValueComma = numberColumnParser(firstString[balance]);
        BigDecimal balanceBigDecimal = new BigDecimal(balanceValueComma);

        BigDecimal startBalance = BigDecimal.ZERO;
        if (actionValue.equals(getRegistrationString())
                && amountBigDecimal.compareTo(BigDecimal.ZERO) < 0) {
            BigDecimal amountBD = new BigDecimal(amountValueComma).negate();
            startBalance = startBalance.add(balanceBigDecimal).add(amountBD);
        } else if (actionValue.equals(getMoneyReceivedStringVer1()) || actionValue.equals(getMoneyReceivedStringVer2())) {
            startBalance = startBalance.add(balanceBigDecimal).subtract(amountBigDecimal);
        } else {
            startBalance = startBalance.add(balanceBigDecimal);
        }
        return startBalance.toString();
    }

    @Override
    public String startBalanceTMoney(List<String[]> strings, int amount, int balance) {
        String[] firstString = strings.get(0);
        String actionValue = replaceQuote(firstString[ACTION]);

        String amountValueComma = numberColumnParser(firstString[amount]);
        BigDecimal amountBigDecimal = new BigDecimal(amountValueComma);

        String balanceValueComma = numberColumnParser(firstString[balance]);
        BigDecimal balanceBigDecimal = new BigDecimal(balanceValueComma);

        BigDecimal startBalance = BigDecimal.ZERO;
        if (actionValue.equals(getRegistrationString())
                && amountBigDecimal.compareTo(BigDecimal.ZERO) < 0) {
            BigDecimal amountBD = new BigDecimal(amountValueComma).negate();
            startBalance = startBalance.add(balanceBigDecimal).add(amountBD);
        } else {
            startBalance = startBalance.add(balanceBigDecimal);
        }
        return startBalance.toString();
    }

    @Override
    public String finalBalance(List<String[]> strings, int column) {
        String[] string = strings.get(totalCountRow(strings));
        String finalBalanceValue = replaceQuote(string[column]);
        return replaceComma(finalBalanceValue);
    }

    @Override
    public int totalCountRow(List<String[]> strings) {
        int counter = 0;
        counter += (int) IntStream.rangeClosed(0, strings.size() - 2).count();
        return counter;
    }

    @Override
    public String sumTransfer(List<String[]> strings, int amount, String actionParam1, String actionParam2) {
        BigDecimal sumTransfer = BigDecimal.ZERO;
        for (String[] stringArray : strings) {
            String actionValue = replaceQuote(stringArray[ACTION]);
            String amountValueComma = numberColumnParser(stringArray[amount]);
            if (actionValue.startsWith(actionParam1) || actionValue.startsWith(actionParam2)) {
                sumTransfer = sumTransfer.add(new BigDecimal(amountValueComma));
            }
        }
        return String.valueOf(sumTransfer);
    }

    @Override
    public Map<String, BigDecimal> sumProfitPool(List<String[]> strings, Set<String> game, int amount, int tMoney) {
        Map<String, BigDecimal> profit = new HashMap<>();
        for (String buyIn : game) {
            Double buyInStakePlusRake = 0.00;
            int counterTicket = 0;
            BigDecimal sumRegistrationForMoney = BigDecimal.ZERO;
            BigDecimal sumUnregistrationForMoney = BigDecimal.ZERO;
            BigDecimal sumNetWon = BigDecimal.ZERO;
            BigDecimal sumRegistrationForTMoney = BigDecimal.ZERO;
            for (String[] stringArray : strings) {
                String actionValue = stringArray[ACTION];
                String buyInValueQuote = gameParser(stringArray);

                String amountValueComma = numberColumnParser(stringArray[amount]);
                BigDecimal amountBigDecimal = new BigDecimal(amountValueComma);

                String tMoneyValueComma = numberColumnParser(stringArray[tMoney]);
                BigDecimal tMoneyBigDecimal = new BigDecimal(tMoneyValueComma);
                if (buyInValueQuote.equals(buyIn)) {
                    if (actionValue.equals(getRegistrationString())
                            && amountBigDecimal.compareTo(BigDecimal.ZERO) < 0) {
                        sumRegistrationForMoney = sumRegistrationForMoney.add(amountBigDecimal);
                    }
                    if (actionValue.equals(getUnRegistrationString())) {
                        sumUnregistrationForMoney = sumUnregistrationForMoney.add(amountBigDecimal);
                    }
                    if (actionValue.equals(getNetWonString())) {
                        sumNetWon = sumNetWon.add(amountBigDecimal);
                    }
                    if (actionValue.equals(getRegistrationString())
                            && tMoneyBigDecimal.compareTo(BigDecimal.ZERO) < 0) {
                        sumRegistrationForTMoney = sumRegistrationForTMoney.add(tMoneyBigDecimal);
                    }
                    if (actionValue.equals(getRegistrationString())
                            && amountBigDecimal.compareTo(BigDecimal.ZERO) == 0
                            && tMoneyBigDecimal.compareTo(BigDecimal.ZERO) == 0) {
                        buyInStakePlusRake = parseBuyInFromString(buyIn);
                        counterTicket++;
                    }
                }
            }
            profit.put(buyIn, sumRegistrationForMoney
                    .add(sumUnregistrationForMoney)
                    .add(sumNetWon)
                    .add(sumRegistrationForTMoney)
                    .subtract(BigDecimal.valueOf(buyInStakePlusRake * counterTicket)));
        }
        return profit;
    }

    @Override
    public Map<String, BigDecimal> sumBonusPool(List<String[]> strings, Set<String> game, int amount, int tMoney) {
        Map<String, BigDecimal> bonus = new HashMap<>();
        for (String buyIn : game) {
            Double buyInStakePlusRake = 0.00;
            int counterTicket = 0;
            BigDecimal sumRegistrationForTMoney = BigDecimal.ZERO;
            for (String[] stringArray : strings) {
                String actionValue = stringArray[ACTION];
                String buyInValueQuote = gameParser(stringArray);

                String amountValueComma = numberColumnParser(stringArray[amount]);
                BigDecimal amountBigDecimal = new BigDecimal(amountValueComma);

                String tMoneyValueComma = numberColumnParser(stringArray[tMoney]);
                BigDecimal tMoneyBigDecimal = new BigDecimal(tMoneyValueComma);
                if (buyInValueQuote.equals(buyIn)) {
                    if (actionValue.equals(getRegistrationString())
                            && tMoneyBigDecimal.compareTo(BigDecimal.ZERO) < 0) {
                        sumRegistrationForTMoney = sumRegistrationForTMoney.add(tMoneyBigDecimal);
                    }
                    if (actionValue.equals(getRegistrationString())
                            && amountBigDecimal.compareTo(BigDecimal.ZERO) == 0
                            && tMoneyBigDecimal.compareTo(BigDecimal.ZERO) == 0) {
                        buyInStakePlusRake = parseBuyInFromString(buyIn);
                        counterTicket++;
                    }
                }
                bonus.put(buyIn, BigDecimal.valueOf(buyInStakePlusRake * counterTicket).subtract(sumRegistrationForTMoney));
            }
        }
        return bonus;
    }

    @Override
    public Double parseBuyInFromString(String buyIn) {
        Pattern compile = Pattern.compile("(\\d*[.,]\\d*)/(\\d*[.,]\\d*)");
        Matcher matcher = compile.matcher(buyIn);
        double stake = 0.00;
        double rake = 0.00;
        if (matcher.find()) {
            String buyInLeft = matcher.group(1);
            String buyInRight = matcher.group(2);
            if (buyInLeft.contains(",") && buyInRight.contains(",")) {
                buyInLeft = buyInLeft.replace(",", ".");
                buyInRight = buyInRight.replace(",", ".");
            }
            stake = Double.parseDouble(buyInLeft);
            rake = Double.parseDouble(buyInRight);
        }
        return stake + rake;
    }

    private String gameParser(String[] stringArray) {
        String buyInValue = stringArray[GAME];
        String buyInValueQuote = replaceQuote(buyInValue);
        return buyInValueQuote;
    }

    private String numberColumnParser(String stringArray) {
        String columnValue = stringArray;
        String columnValueQuote = replaceQuote(columnValue);
        String columnValueComma = replaceComma(columnValueQuote);
        return columnValueComma;
    }

    @Override
    public String sumOtherBonus(List<String[]> strings, String[] bonuses, int amount) {
        BigDecimal result = BigDecimal.ZERO;
        for (String bonus : bonuses) {
            for (String[] stringArray : strings) {
                String action = stringArray[ACTION];
                if (action.startsWith(bonus)) {
                    result = result.add(new BigDecimal(numberColumnParser(stringArray[amount])));
                }
            }
        }
        return String.valueOf(result);
    }

    @Override
    public Integer totalCountRegistrationSpinWithoutUnregistration(List<String[]> strings, String registerAction, String unRegisterAction) {
        int registerCount = 0;
        int unRegisterCount = 0;
        for (String[] stringArray : strings) {
            String actionValue = stringArray[ACTION];
            String gameValue = gameParser(stringArray);
            String spin = " NL Hold'em Sit&Go Buy-In:";
            if (actionValue.equals(registerAction) && gameValue.startsWith(spin)) {
                registerCount++;
            }
            if (actionValue.equals(unRegisterAction) && gameValue.startsWith(spin)) {
                unRegisterCount++;
            }
        }
        return registerCount - unRegisterCount;
    }

    @Override
    public Map<String, BigDecimal> sumForEachGame(List<String[]> strings, Set<String> games, int amount) {
        Map<String, BigDecimal> result = new HashMap<>();
        for (String game : games) {
            BigDecimal sum = BigDecimal.ZERO;
            for (String[] stringArray : strings) {
                String buyInValue = gameParser(stringArray);
                String amountValue = numberColumnParser(stringArray[amount]);
                if (buyInValue.equals(game)) {
                    sum = sum.add(new BigDecimal(amountValue));
                }
            }
            result.put(game, sum);
        }
        return result;
    }

    public abstract String getSeatInTable();
    public abstract String getSeatOutTable();
    public abstract String getAutoRebuyTable();
}
