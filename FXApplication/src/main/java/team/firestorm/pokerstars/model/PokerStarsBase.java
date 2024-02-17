package team.firestorm.pokerstars.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@AllArgsConstructor
public abstract class PokerStarsBase implements PokerStars {
    private static final int ACTION = 1;
    private static final int ID = 2;
    private static final int GAME = 3;
    public final String regexGameSpin = "^\sNL\sHold'em\sSit&Go\sBuy-In:\s\\d+[.,]\\d+\\/\\d+[.,]\\d+$";
    public final String regexGameMTT = "^(?!\\d+[.,]\\d+\\/\\d+[.,]\\d+)(?!.*Sit&Go\sBuy-In).*$";
    public final String regexGameCash = "^\\d+[.,]\\d+\\/\\d+[.,]\\d+\\s\\w.+$";

    @Override
    public Set<String> game(String regex, List<String[]> strings) {
        return strings.stream()
                .map(element -> element[GAME])
                .map(this::replaceQuote)
                .filter(buyIn -> Pattern.compile(regex).matcher(buyIn).find())
                .filter(buyIn -> !buyIn.isEmpty()).collect(Collectors.toSet());
    }

    @Override
    public Map<String, Boolean> checkBoxState(Set<String> game) {
        Map<String, Boolean> checkBoxStateMap = new HashMap<>();
        for (String buyIn : game) {
            checkBoxStateMap.put(buyIn, false);
        }
        return checkBoxStateMap;
    }

    @Override
    public Map<String, Integer> countGame(List<String[]> strings, Set<String> game, String action) {
        Map<String, Integer> count = new HashMap<>();
        for (String buyIn : game) {
            int counter = 0;
            for (String[] stringArray : strings) {
                if (buyIn.equals(gameParser(stringArray)) && stringArray[ACTION].startsWith(action)) {
                    counter++;
                }
            }
            count.put(buyIn, counter);
        }
        return count;
    }

    @Override
    public Map<String, BigDecimal> sumForDifferentColumn(List<String[]> strings, Set<String> game, String action, int column) {
        Map<String, BigDecimal> sumRegistration = new HashMap<>();
        for (String buyIn : game) {
            BigDecimal sum = BigDecimal.ZERO;
            for (String[] stringArray : strings) {
                if (gameParser(stringArray).equals(buyIn) && stringArray[ACTION].startsWith(action)) {
                    sum = sum.add(new BigDecimal(amountParser(stringArray[column])));
                }
            }
            sumRegistration.put(buyIn, sum);
        }
        return sumRegistration;
    }

    @Override
    public Map<String, Integer> countRegistrationByTMoney(List<String[]> strings, Set<String> game, int tMoney) {
        Map<String, Integer> count = new HashMap<>();
        for (String buyIn : game) {
            int counter = 0;
            for (String[] stringArray : strings) {
                if (gameParser(stringArray).equals(buyIn) && stringArray[ACTION].startsWith(getRegistrationString())
                        && new BigDecimal(amountParser(stringArray[tMoney])).compareTo(BigDecimal.ZERO) < 0) {
                    counter++;
                }
            }
            count.put(buyIn, counter);
        }
        return count;
    }

    @Override
    public Map<String, Integer> countUnRegistrationByTMoney(List<String[]> strings, Set<String> game, int tMoney) {
        Map<String, Integer> count = new HashMap<>();
        for (String buyIn : game) {
            int counter = 0;
            for (String[] stringArray : strings) {
                BigDecimal tMoneyBigDecimal = new BigDecimal(amountParser(stringArray[tMoney]));
                if (gameParser(stringArray).equals(buyIn) && stringArray[ACTION].startsWith(getUnRegistrationString())
                        && tMoneyBigDecimal.compareTo(BigDecimal.ZERO) > 0) {
                    counter++;
                }
            }
            count.put(buyIn, counter);
        }
        return count;
    }

    @Override
    public Map<String, BigDecimal> sumRegistrationByTMoney(List<String[]> strings, Set<String> game, int tMoney) {
        Map<String, BigDecimal> result = new HashMap<>();
        for (String buyIn : game) {
            BigDecimal sumRegistration = BigDecimal.ZERO;
            for (String[] stringArray : strings) {
                BigDecimal tMoneyBigDecimal = new BigDecimal(amountParser(stringArray[tMoney]));
                if (gameParser(stringArray).equals(buyIn) && stringArray[ACTION].startsWith(getRegistrationString())
                        && tMoneyBigDecimal.compareTo(BigDecimal.ZERO) < 0) {
                    sumRegistration = sumRegistration.add(tMoneyBigDecimal);
                }
            }
            result.put(buyIn, sumRegistration);
        }
        return result;
    }

    @Override
    public Map<String, BigDecimal> sumUnRegistrationByTMoney(List<String[]> strings, Set<String> game, int tMoney) {
        Map<String, BigDecimal> result = new HashMap<>();
        for (String buyIn : game) {
            BigDecimal sumRegistration = BigDecimal.ZERO;
            for (String[] stringArray : strings) {
                BigDecimal tMoneyBigDecimal = new BigDecimal(amountParser(stringArray[tMoney]));
                if (gameParser(stringArray).equals(buyIn) && stringArray[ACTION].startsWith(getUnRegistrationString())
                        && tMoneyBigDecimal.compareTo(BigDecimal.ZERO) > 0) {
                    sumRegistration = sumRegistration.add(tMoneyBigDecimal);
                }
            }
            result.put(buyIn, sumRegistration);
        }
        return result;
    }

    @Override
    public Map<String, Integer> countRegistrationByTicket(List<String[]> strings, Set<String> game, int amount, int tMoney) {
        Map<String, Integer> countRegistration = new HashMap<>();
        for (String buyIn : game) {
            int counter = 0;
            for (String[] stringArray : strings) {
                if (gameParser(stringArray).equals(buyIn) && stringArray[ACTION].equals(getRegistrationString())
                        && BigDecimal.ZERO.compareTo(new BigDecimal(amountParser(stringArray[amount]))) == 0
                        && BigDecimal.ZERO.compareTo(new BigDecimal(amountParser(stringArray[tMoney]))) == 0) {
                    counter++;
                }
            }
            countRegistration.put(buyIn, counter);
        }
        return countRegistration;
    }

    @Override
    public Map<String, BigDecimal> sumProfitSpin(List<String[]> strings, Set<String> game, int amount, int tMoney) {
        Map<String, BigDecimal> profit = new HashMap<>();
        List<String> ids = new ArrayList<>();
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
                BigDecimal amountBigDecimal = new BigDecimal(amountParser(stringArray[amount]));
                BigDecimal tMoneyBigDecimal = new BigDecimal(amountParser(stringArray[tMoney]));
                if (gameParser(stringArray).equals(buyIn)) {
                    if (actionValue.equals(getRegistrationString())) {
                        sumRegistrationForMoney = sumRegistrationForMoney.add(amountBigDecimal);
                        sumRegistrationForTMoney = sumRegistrationForTMoney.add(tMoneyBigDecimal);
                    }
                    if (actionValue.equals(getRegistrationString()) && amountBigDecimal.compareTo(BigDecimal.ZERO) == 0
                            && tMoneyBigDecimal.compareTo(BigDecimal.ZERO) == 0) {
                        ids.add(idValue);
                    }
                    if (actionValue.equals(getUnRegistrationString())) {
                        sumUnRegistrationForMoney = sumUnRegistrationForMoney.add(amountBigDecimal);
                        sumUnRegistrationForTMoney = sumUnRegistrationForTMoney.add(tMoneyBigDecimal);
                    }
                    if (actionValue.equals(getWonString())) {
                        sumNetWon = sumNetWon.add(amountBigDecimal);
                    }
                    if (actionValue.equals(getWonString()) && ids.contains(idValue)) {
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
    public Map<String, BigDecimal> sumBonusSpin(List<String[]> strings, Set<String> game, int amount, int tMoney) {
        Map<String, BigDecimal> bonus = new HashMap<>();
        List<String> ids = new ArrayList<>();
        for (String buyIn : game) {
            BigDecimal sumRegistrationForTMoney = BigDecimal.ZERO;
            BigDecimal sumUnRegistrationForTMoney = BigDecimal.ZERO;
            BigDecimal sumNetWonForTicket = BigDecimal.ZERO;
            for (String[] stringArray : strings) {
                String actionValue = stringArray[ACTION];
                String idValue = stringArray[ID];
                BigDecimal amountBigDecimal = new BigDecimal(amountParser(stringArray[amount]));
                BigDecimal tMoneyBigDecimal = new BigDecimal(amountParser(stringArray[tMoney]));
                if (gameParser(stringArray).equals(buyIn)) {
                    if (actionValue.equals(getRegistrationString())) {
                        sumRegistrationForTMoney = sumRegistrationForTMoney.subtract(tMoneyBigDecimal);
                    }
                    if (actionValue.equals(getRegistrationString()) && amountBigDecimal.compareTo(BigDecimal.ZERO) == 0
                            && tMoneyBigDecimal.compareTo(BigDecimal.ZERO) == 0) {
                        ids.add(idValue);
                    }
                    if (actionValue.equals(getUnRegistrationString())) {
                        sumUnRegistrationForTMoney = sumUnRegistrationForTMoney.subtract(tMoneyBigDecimal);
                    }
                    if (actionValue.equals(getWonString()) && ids.contains(idValue)) {
                        sumNetWonForTicket = sumNetWonForTicket.add(amountBigDecimal);
                    }
                }
            }
            bonus.put(buyIn, sumRegistrationForTMoney.add(sumUnRegistrationForTMoney).add(sumNetWonForTicket));
        }
        return bonus;
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
                BigDecimal amountBigDecimal = new BigDecimal(amountParser(stringArray[amount]));
                BigDecimal tMoneyBigDecimal = new BigDecimal(amountParser(stringArray[tMoney]));
                if (gameParser(stringArray).equals(buyIn)) {
                    if (actionValue.equals(getRegistrationString()) && amountBigDecimal.compareTo(BigDecimal.ZERO) < 0) {
                        sumRegistrationForMoney = sumRegistrationForMoney.add(amountBigDecimal);
                    }
                    if (actionValue.equals(getUnRegistrationString())) {
                        sumUnregistrationForMoney = sumUnregistrationForMoney.add(amountBigDecimal);
                    }
                    if (actionValue.equals(getWonString())) {
                        sumNetWon = sumNetWon.add(amountBigDecimal);
                    }
                    if (actionValue.equals(getRegistrationString()) && tMoneyBigDecimal.compareTo(BigDecimal.ZERO) < 0) {
                        sumRegistrationForTMoney = sumRegistrationForTMoney.add(tMoneyBigDecimal);
                    }
                    if (actionValue.equals(getRegistrationString()) && amountBigDecimal.compareTo(BigDecimal.ZERO) == 0
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
                BigDecimal tMoneyBigDecimal = new BigDecimal(amountParser(stringArray[tMoney]));
                if (gameParser(stringArray).equals(buyIn)) {
                    if (actionValue.equals(getRegistrationString()) && tMoneyBigDecimal.compareTo(BigDecimal.ZERO) < 0) {
                        sumRegistrationForTMoney = sumRegistrationForTMoney.add(tMoneyBigDecimal);
                    }
                    if (actionValue.equals(getRegistrationString())
                            && new BigDecimal(amountParser(stringArray[amount])).compareTo(BigDecimal.ZERO) == 0
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
    public Integer totalCountRegistrationSpinWithoutUnregistration(List<String[]> strings,
                                                                   String registerAction, String unRegisterAction) {
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
    public Map<String, Integer> countReEntry(List<String[]> strings, Set<String> game) {
        Map<String, Integer> countReEntry = new HashMap<>();
        Map<String, Set<String>> idMap = getId(strings);
        for (String buyIn : game) {
            int counter = 0;
            for (String[] stringArray : strings) {
                if (stringArray[ACTION].equals(getReEntryString())) {
                    for (Map.Entry<String, Set<String>> entry : idMap.entrySet()) {
                        if (entry.getKey().equals(buyIn)) {
                            for (String id : entry.getValue()) {
                                if (id.startsWith(stringArray[ID])) {
                                    counter++;
                                }
                            }
                        }
                    }
                }
            }
            countReEntry.put(buyIn, counter);
        }
        return countReEntry;
    }

    @Override
    public Map<String, BigDecimal> sumReEntry(List<String[]> strings, Set<String> game, int amount) {
        Map<String, BigDecimal> reentry = new HashMap<>();
        Map<String, Set<String>> idMap = getId(strings);
        for (String buyIn : game) {
            BigDecimal sumReEntry = BigDecimal.ZERO;
            for (String[] stringArray : strings) {
                if (stringArray[ACTION].equals(getReEntryString())) {
                    sumReEntry = getSumProfitFromMap(idMap, buyIn, sumReEntry, stringArray[ID],
                            new BigDecimal(amountParser(stringArray[amount])));
                }
            }
            reentry.put(buyIn, sumReEntry);
        }
        return reentry;
    }

    @Override
    public Map<String, BigDecimal> sumKnockout(List<String[]> strings, Set<String> game, int amount) {
        Map<String, BigDecimal> knockoutMap = new HashMap<>();
        Map<String, Set<String>> idMap = getId(strings);
        for (String buyIn : game) {
            BigDecimal sumKnockout = BigDecimal.ZERO;
            for (String[] stringArray : strings) {
                if (stringArray[ACTION].equals(getKnockOutString())) {
                    sumKnockout = getSumProfitFromMap(idMap, buyIn, sumKnockout, stringArray[ID],
                            new BigDecimal(amountParser(stringArray[amount])));
                }
            }
            knockoutMap.put(buyIn, sumKnockout);
        }
        return knockoutMap;
    }

    @Override
    public Map<String, BigDecimal> sumInterim(List<String[]> strings, Set<String> game, int amount) {
        Map<String, BigDecimal> interimMap = new HashMap<>();
        Map<String, Set<String>> idMap = getId(strings);
        for (String buyIn : game) {
            BigDecimal sumInterim = BigDecimal.ZERO;
            for (String[] stringArray : strings) {
                if (stringArray[ACTION].equals(getInterimString())) {
                    sumInterim = getSumProfitFromMap(idMap, buyIn, sumInterim, stringArray[ID],
                            new BigDecimal(amountParser(stringArray[amount])));
                }
            }
            interimMap.put(buyIn, sumInterim);
        }
        return interimMap;
    }

    @Override
    public Map<String, BigDecimal> sumProfitMTT(List<String[]> strings, Set<String> game, int amount) {
        Map<String, BigDecimal> profitMap = new HashMap<>();
        Map<String, Set<String>> idMap = getId(strings);
        for (String buyIn : game) {
            BigDecimal sumProfit = BigDecimal.ZERO;
            for (String[] stringArray : strings) {
                sumProfit = getSumProfitFromMap(idMap, buyIn, sumProfit, stringArray[ID],
                        new BigDecimal(amountParser(stringArray[amount])));
            }
            profitMap.put(buyIn, sumProfit);
        }
        return profitMap;
    }

    @Override
    public Map<String, BigDecimal> sumProfitCashGame(List<String[]> strings, Set<String> games, int amount) {
        Map<String, BigDecimal> result = new HashMap<>();
        for (String game : games) {
            BigDecimal sum = BigDecimal.ZERO;
            for (String[] stringArray : strings) {
                if (gameParser(stringArray).equals(game)) {
                    sum = sum.add(new BigDecimal(amountParser(stringArray[amount])));
                }
            }
            result.put(game, sum);
        }
        return result;
    }

    @Override
    public String startBalanceMoney(List<String[]> strings, int amount, int balance) {
        String[] firstString = strings.get(0);
        String actionValue = replaceQuote(firstString[ACTION]);
        BigDecimal amountBigDecimal = new BigDecimal(amountParser(firstString[amount]));
        BigDecimal balanceBigDecimal = new BigDecimal(amountParser(firstString[balance]));
        BigDecimal startBalance = BigDecimal.ZERO;
        if (actionValue.equals(getRegistrationString()) && amountBigDecimal.compareTo(BigDecimal.ZERO) < 0) {
            startBalance = startBalance.add(balanceBigDecimal).add(amountBigDecimal.negate());
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
        BigDecimal amountBigDecimal = new BigDecimal(amountParser(firstString[amount]));
        BigDecimal balanceBigDecimal = new BigDecimal(amountParser(firstString[balance]));
        BigDecimal startBalance = BigDecimal.ZERO;
        if (replaceQuote(firstString[ACTION]).equals(getRegistrationString())
                && amountBigDecimal.compareTo(BigDecimal.ZERO) < 0) {
            startBalance = startBalance.add(balanceBigDecimal).add(amountBigDecimal.negate());
        } else {
            startBalance = startBalance.add(balanceBigDecimal);
        }
        return startBalance.toString();
    }

    @Override
    public String startBalanceCoin(List<String[]> strings, int column) {
        return replaceComma(replaceQuote(strings.get(0)[column]));
    }

    @Override
    public String finalBalance(List<String[]> strings, int column) {
        return replaceComma(replaceQuote(strings.get(totalCountRow(strings))[column]));
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
            if (actionValue.startsWith(actionParam1) || actionValue.startsWith(actionParam2)) {
                sumTransfer = sumTransfer.add(new BigDecimal(amountParser(stringArray[amount])));
            }
        }
        return String.valueOf(sumTransfer);
    }

    @Override
    public String sumOtherBonus(List<String[]> strings, String[] bonuses, int amount) {
        BigDecimal result = BigDecimal.ZERO;
        for (String bonus : bonuses) {
            for (String[] stringArray : strings) {
                if (stringArray[ACTION].startsWith(bonus)) {
                    result = result.add(new BigDecimal(amountParser(stringArray[amount])));
                }
            }
        }
        return String.valueOf(result);
    }

    @Override
    public String replaceQuote(String string) {
        return string.replace("\"", "");
    }

    private String gameParser(String[] stringArray) {
        return replaceQuote(stringArray[GAME]);
    }

    private String amountParser(String stringArray) {
        return replaceComma(replaceQuote(stringArray));
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

    private Map<String, Set<String>> getId(List<String[]> strings) {
        Map<String, Set<String>> idMap = new HashMap<>();
        for (String[] stringArray : strings) {
            String idValue = stringArray[ID];
            String buyInValueQuote = gameParser(stringArray);
            if (stringArray[ACTION].equals(getRegistrationString())) {
                if (idMap.containsKey(buyInValueQuote)) {
                    idMap.get(buyInValueQuote).add(idValue);
                } else {
                    Set<String> idList = new HashSet<>();
                    idList.add(idValue);
                    idMap.put(buyInValueQuote, idList);
                }
            }
        }
        return idMap;
    }

    private BigDecimal getSumProfitFromMap(Map<String, Set<String>> idMap, String buyIn,
                                           BigDecimal sumProfit, String idValue, BigDecimal amountBigDecimal) {
        for (Map.Entry<String, Set<String>> entry : idMap.entrySet()) {
            if (entry.getKey().equals(buyIn)) {
                for (String id : entry.getValue()) {
                    if (id.startsWith(idValue)) {
                        sumProfit = sumProfit.add(amountBigDecimal);
                    }
                }
            }
        }
        return sumProfit;
    }
}
