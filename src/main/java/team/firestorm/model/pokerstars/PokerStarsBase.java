package team.firestorm.model.pokerstars;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class PokerStarsBase implements PokerStars {
    private static final int ACTION = 1;
    private static final int ID = 2;
    private static final int GAME = 3;

    private final StringProcessor stringProcessor;
    private PokerStarsIndex pokerStarsIndex;

    public PokerStarsBase(StringProcessor stringProcessor) {
        this.stringProcessor = stringProcessor;
    }

    @Override
    public String replaceQuote(String string) {
        return string.replace("\"", "");
    }

    private String[] processStringArray(String[] stringArray, int... column) {
        String actionValue0 = stringArray[ACTION];

        String idValue1 = stringArray[ID];

        String buyInValue = stringArray[GAME];
        String buyInValueQuote2 = replaceQuote(buyInValue);

        String amountValue = stringArray[pokerStarsIndex.getAmount()];
        String amountValueQuote = replaceQuote(amountValue);
        String amountValueComma3 = replaceComma(amountValueQuote);

        String starsCoinValue = stringArray[pokerStarsIndex.getStarsCoin()];
        String starsCoinValueQuote = replaceQuote(starsCoinValue);
        String starsCoinValueComma4 = replaceComma(starsCoinValueQuote);

        String tMoneyValue = stringArray[pokerStarsIndex.getTMoney()];
        String tMoneyValueQuote = replaceQuote(tMoneyValue);
        String tMoneyValueComma5 = replaceComma(tMoneyValueQuote);

        String balanceValue = stringArray[pokerStarsIndex.getBalance()];
        String balanceValueQuote = replaceQuote(balanceValue);
        String balanceValueComma6 = replaceComma(balanceValueQuote);

        return new String[]{actionValue0, idValue1, buyInValueQuote2, amountValueComma3,
                starsCoinValueComma4, tMoneyValueComma5, balanceValueComma6};
    }

    private BigDecimal calculateSum(String amount, String tMoney, BigDecimal currentSum, boolean addToSum) {
        BigDecimal amountBigDecimal = new BigDecimal(amount);
        BigDecimal tMoneyBigDecimal = new BigDecimal(tMoney);
        BigDecimal sum = currentSum;

        if (addToSum) {
            sum = sum.add(amountBigDecimal).add(tMoneyBigDecimal);
        } else {
            sum = sum.subtract(amountBigDecimal).subtract(tMoneyBigDecimal);
        }

        return sum;
    }

    @Override
    public Set<String> gameSpin() {
        Pattern pattern = Pattern.compile("NL\\sHold\\'em\\sSit\\&Go\\sBuy\\-In\\:\\s");
        return stringProcessor.getStrings().stream()
                .map(element -> element[GAME])
                .map(element -> replaceQuote(element))
                .filter(buyIn -> pattern.matcher(buyIn).find())
                .filter(buyIn -> !buyIn.isEmpty())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> gameAnother() {
        Pattern pattern = Pattern.compile("^(?!.*NL Hold'em Sit&Go Buy-In).*");
        return stringProcessor.getStrings().stream()
                .map(element -> element[GAME])
                .map(element -> replaceQuote(element))
                .filter(buyIn -> pattern.matcher(buyIn).find())
                .filter(buyIn -> !buyIn.isEmpty())
                .collect(Collectors.toSet());
    }

    @Override
    public Map<String, BigDecimal> sumColumn(Set<String> buyIns, String action, int column) {
        Map<String, BigDecimal> sumRegistration = new HashMap<>();
        for (String buyIn : buyIns) {
            BigDecimal sum = BigDecimal.ZERO;
            for (String[] stringArray : stringProcessor.getStrings()) {
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
    public Map<String, Integer> countRegistrationByTicket(Set<String> buyIns, int amount, int tMoney) {
        Map<String, Integer> countRegistration = new HashMap<>();

        for (String buyIn : buyIns) {
            int counter = 0;
            for (String[] stringArray : stringProcessor.getStrings()) {
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
                        && actionValue.equals(getRegistration())
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
    public Map<String, BigDecimal> sumProfit(Set<String> buyIns, int amount, int tMoney) {
        Map<String, BigDecimal> profit = new HashMap<>();
        List<String> ids = new ArrayList<>();
        for (String buyIn : buyIns) {
            BigDecimal sumRegistration = BigDecimal.ZERO;
            BigDecimal sumUnregistration = BigDecimal.ZERO;
            BigDecimal sumWon = BigDecimal.ZERO;
            for (String[] stringArray : stringProcessor.getStrings()) {
                String actionValue = stringArray[ACTION];
                String idValue = stringArray[ID];

                String buyInValue = stringArray[GAME];
                String buyInValueQuote = replaceQuote(buyInValue);

                String amountValue = stringArray[amount];
                String amountValueQuote = replaceQuote(amountValue);
                String amountValueComma = replaceComma(amountValueQuote);

                String tMoneyValue = stringArray[tMoney];
                String tMoneyValueQuote = replaceQuote(tMoneyValue);
                String tMoneyValueComma = replaceComma(tMoneyValueQuote);

                if (buyInValueQuote.equals(buyIn)) {
                    if (actionValue.equals(getRegistration())) {
                        BigDecimal amountBigDecimal = new BigDecimal(amountValueComma);
                        BigDecimal tMoneyBigDecimal = new BigDecimal(tMoneyValueComma);
                        sumRegistration = sumRegistration.add(amountBigDecimal).add(tMoneyBigDecimal);
                        if (amountBigDecimal.compareTo(BigDecimal.ZERO) < 0) {
                            ids.add(idValue);
                        }
                    }
                    if (actionValue.equals(getUnregistration())) {
                        BigDecimal amountBigDecimal = new BigDecimal(amountValueComma);
                        BigDecimal tMoneyBigDecimal = new BigDecimal(tMoneyValueComma);
                        sumUnregistration = sumUnregistration.add(amountBigDecimal).add(tMoneyBigDecimal);
                    }
                    if (actionValue.equals(getWon())) {
                        for (String idString : ids) {
                            if (idValue.equals(idString)) {
                                sumWon = sumWon.add(new BigDecimal(amountValueComma));
                            }
                        }
                    }
                }
            }
            BigDecimal sum = sumRegistration.add(sumUnregistration).add(sumWon);
            profit.put(buyIn, sum);
        }
        return profit;
    }

    @Override
    public Map<String, BigDecimal> sumBonus(Set<String> buyIns, int amount, int tMoney) {
        Map<String, BigDecimal> bonus = new HashMap<>();
        List<String> ids = new ArrayList<>();
        for (String buyIn : buyIns) {
            BigDecimal registerForTMoneySum = BigDecimal.ZERO;
            BigDecimal registrationSum = BigDecimal.ZERO;
            BigDecimal wonSum = BigDecimal.ZERO;
            for (String[] stringArray : stringProcessor.getStrings()) {
                String actionValue = stringArray[ACTION];
                String idValue = stringArray[ID];

                String buyInValue = stringArray[GAME];
                String buyInValueQuote = replaceQuote(buyInValue);

                String amountValue = stringArray[amount];
                String amountValueQuote = replaceQuote(amountValue);
                String amountValueComma = replaceComma(amountValueQuote);

                String tMoneyValue = stringArray[tMoney];
                String tMoneyValueQuote = replaceQuote(tMoneyValue);
                String tMoneyValueComma = replaceComma(tMoneyValueQuote);

                if (buyInValueQuote.equals(buyIn)) {
                    if (actionValue.equals(getRegistration())) {
                        BigDecimal tMoneyBigDecimal = new BigDecimal(tMoneyValueComma);
                        tMoneyBigDecimal = tMoneyBigDecimal.negate();

                        if (tMoneyBigDecimal.compareTo(BigDecimal.ZERO) > 0) {
                            registerForTMoneySum = registerForTMoneySum.add(tMoneyBigDecimal);
                        }

                        registrationSum = registrationSum.add(new BigDecimal(tMoneyValueComma));
                        registrationSum = registrationSum.negate();

                        BigDecimal amountBigDecimal = new BigDecimal(amountValueComma);
                        int compareAmountToZero = amountBigDecimal.compareTo(BigDecimal.ZERO);
                        if (compareAmountToZero == 0) {
                            ids.add(idValue);
                        }

                    }
                    if (actionValue.equals(getWon())) {
                        wonSum = ids.stream()
                                .filter(id -> id.equals(idValue))
                                .map(amountVal -> new BigDecimal(amountValueComma))
                                .reduce(wonSum, BigDecimal::add);
                    }
                }
            }
            BigDecimal sum = registerForTMoneySum.add(registrationSum).add(wonSum);
            bonus.put(buyIn, sum);
        }
        return bonus;
    }

    @Override
    public Map<String, Integer> countTourney(Set<String> buyIns, String action) {
        Map<String, Integer> count = new HashMap<>();
        for (String buyIn : buyIns) {
            int counter = 0;
            for (String[] stringArray : stringProcessor.getStrings()) {
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
    public Map<String, Integer> countReEntry(Set<String> buyIns) {
        Map<String, Integer> countReEntry = new HashMap<>();
        List<String> ids = new ArrayList<>();
        for (String[] stringArray : stringProcessor.getStrings()) {
            String actionValue = stringArray[ACTION];
            String idValue = stringArray[ID];
            if (actionValue.equals(getReEntry())) {
                ids.add(idValue);
            }
        }
        for (String buyIn : buyIns) {
            int counter = 0;
            for (String[] stringArray : stringProcessor.getStrings()) {
                String actionValue = stringArray[ACTION];
                String idValue = stringArray[ID];

                String buyInValue = stringArray[GAME];
                String buyInValueQuote = replaceQuote(buyInValue);

                if (buyInValueQuote.equals(buyIn) && actionValue.equals(getRegistration())) {
                    counter += ids.stream().filter(idValue::startsWith).count();
                }
            }
            countReEntry.put(buyIn, counter);
        }
        return countReEntry;
    }

    @Override
    public Map<String, BigDecimal> sumReEntry(Set<String> buyIns, int amount) {
        Map<String, BigDecimal> sumReEntry = new HashMap<>();
        ArrayList<String> ids = new ArrayList<>();
        for (String[] stringArray : stringProcessor.getStrings()) {
            String actionValue = stringArray[ACTION];
            String idValue = stringArray[ID];
            if (actionValue.equals(getReEntry())) {
                ids.add(idValue);
            }
        }
        for (String buyIn : buyIns) {
            BigDecimal sum = BigDecimal.ZERO;
            for (String[] stringArray : stringProcessor.getStrings()) {
                String actionValue = stringArray[ACTION];
                String idValue = stringArray[ID];

                String buyInValue = stringArray[GAME];
                String buyInValueQuote = replaceQuote(buyInValue);

                String amountValue = stringArray[amount];
                String amountValueQuote = replaceQuote(amountValue);
                String amountValueComma = replaceComma(amountValueQuote);

                if (buyInValueQuote.equals(buyIn) && actionValue.equals(getRegistration())) {
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

    // TODO : Реализовать
    @Override
    public Map<String, Integer> countWonTicket(Set<String> buyIns) {
        Map<String, Integer> countTicket = new HashMap<>();
        return countTicket;
    }

    @Override
    public Map<String, BigDecimal> sumKnockout(Set<String> buyIns, int amount) {
        Map<String, BigDecimal> knockoutMap = new HashMap<>();
        Map<String, List<String>> buyInIdMap = new HashMap<>();
        for (String[] stringArray : stringProcessor.getStrings()) {
            String actionValue = stringArray[ACTION];
            String idValue = stringArray[ID];

            String buyInValue = stringArray[GAME];
            String buyInValueQuote = replaceQuote(buyInValue);

            if (actionValue.equals(getRegistration())) {
                if (buyInIdMap.containsKey(buyInValueQuote)) {
                    buyInIdMap.get(buyInValueQuote).add(idValue);
                } else {
                    List<String> ids = new ArrayList<>();
                    ids.add(idValue);
                    buyInIdMap.put(buyInValueQuote, ids);
                }
            }
        }

        for (String buyIn : buyIns) {
            BigDecimal sum = BigDecimal.ZERO;
            for (String[] stringArray : stringProcessor.getStrings()) {
                String actionValue = stringArray[ACTION];
                String idValue = stringArray[ID];

                String amountValue = stringArray[amount];
                String amountValueQuote = replaceQuote(amountValue);
                String amountValueComma = replaceComma(amountValueQuote);

                if (actionValue.equals(getKnockOut())) {
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
    public String startBalance(int column) {
        String[] strings = stringProcessor.getStrings().get(0);
        String startBalanceValue = replaceQuote(strings[column]);
        String startBalanceComma = replaceComma(startBalanceValue);
        return startBalanceComma;
    }

    @Override
    public int totalCountRow() {
        int counter = 1;
        counter += IntStream.rangeClosed(0, stringProcessor.getStrings().size() - 3).count();
        return counter;
    }

    @Override
    public String finalBalance(int column) {
        String[] strings = stringProcessor.getStrings().get(totalCountRow());
        String finalBalanceValue = replaceQuote(strings[column]);
        String finalBalanceValueComma = replaceComma(finalBalanceValue);
        return finalBalanceValueComma;
    }

    @Override
    public String sumBalance(int column) {
        double sumBalance = 0.00;
        for (String[] stringArray : stringProcessor.getStrings()) {
            String balanceValue = replaceQuote(stringArray[column]);
            String balanceValueQuote = replaceQuote(balanceValue);
            String balanceValueComma = replaceComma(balanceValueQuote);
            sumBalance += Double.parseDouble(balanceValueComma);
        }
        return String.valueOf(sumBalance);
    }

    @Override
    public String sumTransfer(int amount, String actionParam1, String actionParam2) {
        BigDecimal sumTransfer = BigDecimal.ZERO;
        for (String[] stringArray : stringProcessor.getStrings()) {
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

}
