package team.firestorm.pokerstars.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    public final String regexGameSpin = "NL\\sHold'em\\sSit&Go\\sBuy-In:\\s";
    public final String regexGameAnother = "^(?!.*NL Hold'em Sit&Go Buy-In).*";
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

                String buyInValue = stringArray[GAME];
                String buyInValueQuote = replaceQuote(buyInValue);

                String columnValue = stringArray[column];
                String columnValueQuote = replaceQuote(columnValue);
                String columnValueComma = replaceComma(columnValueQuote);

                if (buyInValueQuote.equals(buyIn) && actionValue.equals(action)) {
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

                String buyInValue = stringArray[GAME];
                String buyInValueQuote = replaceQuote(buyInValue);

                String amountValue = stringArray[amount];
                String amountValueQuote = replaceQuote(amountValue);
                String amountValueComma = replaceComma(amountValueQuote);

                String tMoneyValue = stringArray[tMoney];
                String tMoneyValueQuote = replaceQuote(tMoneyValue);
                String tMoneyValueComma = replaceComma(tMoneyValueQuote);

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
        for (String buyIn : game) {
            BigDecimal sumRegistrationForMoney = BigDecimal.ZERO;
            BigDecimal sumUnregistrationForMoney = BigDecimal.ZERO;
            BigDecimal sumNetWon = BigDecimal.ZERO;
            BigDecimal sumRegistrationForTMoney = BigDecimal.ZERO;
            for (String[] stringArray : strings) {
                String actionValue = stringArray[ACTION];

                String buyInValue = stringArray[GAME];
                String buyInValueQuote = replaceQuote(buyInValue);

                String amountValue = stringArray[amount];
                String amountValueQuote = replaceQuote(amountValue);
                String amountValueComma = replaceComma(amountValueQuote);
                BigDecimal amountBigDecimal = new BigDecimal(amountValueComma);

                String tMoneyValue = stringArray[tMoney];
                String tMoneyValueQuote = replaceQuote(tMoneyValue);
                String tMoneyValueComma = replaceComma(tMoneyValueQuote);
                BigDecimal tMoneyBigDecimal = new BigDecimal(tMoneyValueComma);

                if (buyInValueQuote.equals(buyIn)) {
                    if (actionValue.equals(getRegistrationString())) {
                        sumRegistrationForMoney = sumRegistrationForMoney.add(amountBigDecimal);
                        sumRegistrationForTMoney = sumRegistrationForTMoney.add(tMoneyBigDecimal);
                    }
                    if (actionValue.equals(getUnRegistrationString())) {
                        sumUnregistrationForMoney = sumUnregistrationForMoney.add(amountBigDecimal);
                    }
                    if (actionValue.equals(getNetWonString())) {
                        sumNetWon = sumNetWon.add(amountBigDecimal);
                    }
                }
            }
            profit.put(buyIn, sumRegistrationForMoney
                    .add(sumUnregistrationForMoney)
                    .add(sumNetWon)
                    .add(sumRegistrationForTMoney));
        }
        return profit;
    }

    @Override
    public Map<String, BigDecimal> sumBonus(List<String[]> strings, Set<String> game, int amount, int tMoney) {
        Map<String, BigDecimal> bonus = new HashMap<>();
        for (String buyIn : game) {
            BigDecimal sumRegistrationForTMoney = BigDecimal.ZERO;
            double buyInStakePlusRake = 0.00;
            int counterTicket = 0;
            for (String[] stringArray : strings) {
                String actionValue = stringArray[ACTION];

                String buyInValue = stringArray[GAME];
                String buyInValueQuote = replaceQuote(buyInValue);

                String amountValue = stringArray[amount];
                String amountValueQuote = replaceQuote(amountValue);
                String amountValueComma = replaceComma(amountValueQuote);
                BigDecimal amountBigDecimal = new BigDecimal(amountValueComma);

                String tMoneyValue = stringArray[tMoney];
                String tMoneyValueQuote = replaceQuote(tMoneyValue);
                String tMoneyValueComma = replaceComma(tMoneyValueQuote);
                BigDecimal tMoneyBigDecimal = new BigDecimal(tMoneyValueComma);

                if (buyInValueQuote.equals(buyIn)) {
                    if (actionValue.equals(getRegistrationString())) {
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
            bonus.put(buyIn, BigDecimal.valueOf(buyInStakePlusRake * counterTicket).subtract(sumRegistrationForTMoney));
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

                String buyInValue = stringArray[GAME];
                String buyInValueQuote = replaceQuote(buyInValue);

                if (buyIn.equals(buyInValueQuote) && action.equals(actionValue)) {
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

                String buyInValue = stringArray[GAME];
                String buyInValueQuote = replaceQuote(buyInValue);

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

                String buyInValue = stringArray[GAME];
                String buyInValueQuote = replaceQuote(buyInValue);

                String amountValue = stringArray[amount];
                String amountValueQuote = replaceQuote(amountValue);
                String amountValueComma = replaceComma(amountValueQuote);

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

            String buyInValue = stringArray[GAME];
            String buyInValueQuote = replaceQuote(buyInValue);

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

                String amountValue = stringArray[amount];
                String amountValueQuote = replaceQuote(amountValue);
                String amountValueComma = replaceComma(amountValueQuote);

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

        String amountValue = firstString[amount];
        String amountValueQuote = replaceQuote(amountValue);
        String amountValueComma = replaceComma(amountValueQuote);
        BigDecimal amountBigDecimal = new BigDecimal(amountValueComma);

        String balanceValue = replaceQuote(firstString[balance]);
        String balanceValueQuote = replaceQuote(balanceValue);
        String balanceValueComma = replaceComma(balanceValueQuote);
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

        String amountValue = firstString[amount];
        String amountValueQuote = replaceQuote(amountValue);
        String amountValueComma = replaceComma(amountValueQuote);
        BigDecimal amountBigDecimal = new BigDecimal(amountValueComma);

        String balanceValue = replaceQuote(firstString[balance]);
        String balanceValueQuote = replaceQuote(balanceValue);
        String balanceValueComma = replaceComma(balanceValueQuote);
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
        int counter = 1;
        counter += (int) IntStream.rangeClosed(0, strings.size() - 3).count();
        return counter;
    }

    @Override
    public String sumBalance(List<String[]> strings, int column) {
        BigDecimal sumBalance = BigDecimal.ZERO;
        for (String[] stringArray : strings) {
            String balanceValue = replaceQuote(stringArray[column]);
            String balanceValueQuote = replaceQuote(balanceValue);
            String balanceValueComma = replaceComma(balanceValueQuote);
            BigDecimal sumValue = new BigDecimal(balanceValueComma);
            sumBalance = sumBalance.add(sumValue);
        }
        return String.valueOf(sumBalance);
    }

    @Override
    public String sumTransfer(List<String[]> strings, int amount, String actionParam1, String actionParam2) {
        BigDecimal sumTransfer = BigDecimal.ZERO;
        for (String[] stringArray : strings) {
            String actionValue = replaceQuote(stringArray[ACTION]);

            String amountValue = stringArray[amount];
            String amountValueQuote = replaceQuote(amountValue);
            String amountValueComma = replaceComma(amountValueQuote);

            if (actionValue.equals(actionParam1) || actionValue.equals(actionParam2)) {
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
            BigDecimal clearProfit = BigDecimal.ZERO;
            for (String[] stringArray : strings) {
                String actionValue = stringArray[ACTION];

                String buyInValue = stringArray[GAME];
                String buyInValueQuote = replaceQuote(buyInValue);

                String amountValue = stringArray[amount];
                String amountValueQuote = replaceQuote(amountValue);
                String amountValueComma = replaceComma(amountValueQuote);
                BigDecimal amountBigDecimal = new BigDecimal(amountValueComma);

                String tMoneyValue = stringArray[tMoney];
                String tMoneyValueQuote = replaceQuote(tMoneyValue);
                String tMoneyValueComma = replaceComma(tMoneyValueQuote);
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
                    .add(BigDecimal.valueOf(buyInStakePlusRake * counterTicket))
                    .subtract(clearProfit));
            System.out.println(buyIn + " "
                    + sumRegistrationForMoney + " "
                    + sumUnregistrationForMoney + " "
                    + sumNetWon + " "
                    + sumRegistrationForTMoney + " "
                    + buyInStakePlusRake * counterTicket + " "
                    + clearProfit + " ");
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

                String buyInValue = stringArray[GAME];
                String buyInValueQuote = replaceQuote(buyInValue);

                String amountValue = stringArray[amount];
                String amountValueQuote = replaceQuote(amountValue);
                String amountValueComma = replaceComma(amountValueQuote);
                BigDecimal amountBigDecimal = new BigDecimal(amountValueComma);

                String tMoneyValue = stringArray[tMoney];
                String tMoneyValueQuote = replaceQuote(tMoneyValue);
                String tMoneyValueComma = replaceComma(tMoneyValueQuote);
                BigDecimal tMoneyBigDecimal = new BigDecimal(tMoneyValueComma);

                if (buyInValueQuote.equals(buyIn)) {
                    if (actionValue.equals(getRegistrationString())
                            && amountBigDecimal.compareTo(BigDecimal.ZERO) == 0
                            && tMoneyBigDecimal.compareTo(BigDecimal.ZERO) == 0) {
                        buyInStakePlusRake = parseBuyInFromString(buyIn);
                        counterTicket++;
                    }
                    if (actionValue.equals(getRegistrationString())
                            && tMoneyBigDecimal.compareTo(BigDecimal.ZERO) < 0) {
                        sumRegistrationForTMoney = sumRegistrationForTMoney.add(tMoneyBigDecimal);
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

    @Override
    public Map<String, Boolean> setPoolBoolean(Set<String> game) {
        Map<String, Boolean> pool = new HashMap<>();
        for (String buyIn : game) {
            Boolean setting = false;
            pool.put(buyIn, setting);
        }
        return pool;
    }
}
