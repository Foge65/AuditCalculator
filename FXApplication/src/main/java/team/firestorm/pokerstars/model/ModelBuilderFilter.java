package team.firestorm.pokerstars.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ModelBuilderFilter extends ModelBuilderFromCsvFile {
    private final PokerStarsBase pokerStarsBase;
    private final List<String[]> filteredStrings = new ArrayList<>();

    public ModelBuilderFilter(PokerStarsBase pokerStarsBase) {
        super(pokerStarsBase.getCsvParser());
        this.pokerStarsBase = pokerStarsBase;
    }

    public void addToFilteredList(DateTimeFormatter dateTimeFormatter, LocalDate dateSelectFrom, LocalDate dateSelectTo) {
        for (String[] string : pokerStarsBase.getCsvParser().getStrings()) {
            LocalDate date = LocalDate.parse(string[0], dateTimeFormatter);
            if ((date.isEqual(dateSelectFrom) || date.isAfter(dateSelectFrom))
                    && (date.isEqual(dateSelectTo) || date.isBefore(dateSelectTo))) {
                filteredStrings.add(string);
            }
        }
    }

    public void setModel(Model model) {
        model.setDateFrom(getDateFrom());
        model.setDateTo(getDateTo());

        Set<String> gameSpin = pokerStarsBase.game(pokerStarsBase.getRegexGameSpin(), filteredStrings);
        model.setGameSpin(gameSpin);
        model.setCountRegistrationSpin(pokerStarsBase.countTourney(filteredStrings, gameSpin, pokerStarsBase.getRegistrationString()));
        model.setSumRegistrationSpin(pokerStarsBase.sumColumn(filteredStrings, gameSpin, pokerStarsBase.getRegistrationString(), getAmountIndex()));
        model.setCountUnRegistrationSpin(pokerStarsBase.countTourney(filteredStrings, gameSpin, pokerStarsBase.getUnRegistrationString()));
        model.setSumUnRegistrationSpin(pokerStarsBase.sumColumn(filteredStrings, gameSpin, pokerStarsBase.getUnRegistrationString(), getAmountIndex()));
        model.setSumNetWonSpin(pokerStarsBase.sumColumn(filteredStrings, gameSpin, pokerStarsBase.getNetWonString(), getAmountIndex()));
        model.setSumRegistrationForTMoneySpin(pokerStarsBase.sumColumn(filteredStrings, gameSpin, pokerStarsBase.getRegistrationString(), getTMoneyAmountIndex()));
        model.setCountRegistrationByTicketSpin(pokerStarsBase.countRegistrationByTicket(filteredStrings, gameSpin, getAmountIndex(), getTMoneyAmountIndex()));
        model.setSumProfitSpin(pokerStarsBase.sumProfit(filteredStrings, gameSpin, getAmountIndex(), getTMoneyAmountIndex()));
        model.setSumBonusSpin(pokerStarsBase.sumBonus(filteredStrings, gameSpin, getAmountIndex(), getTMoneyAmountIndex()));
        model.setSumProfitPoolSpin(pokerStarsBase.sumProfitPool(filteredStrings, gameSpin, getAmountIndex(), getTMoneyAmountIndex()));
        model.setSumBonusPoolSpin(pokerStarsBase.sumBonusPool(filteredStrings, gameSpin, getAmountIndex(), getTMoneyAmountIndex()));

        Set<String> gameAnother = pokerStarsBase.game(pokerStarsBase.getRegexGameAnother(), filteredStrings);
        model.setGameAnother(gameAnother);
        model.setCountRegistrationAnother(pokerStarsBase.countTourney(filteredStrings, gameAnother, pokerStarsBase.getRegistrationString()));
        model.setSumRegistrationAnother(pokerStarsBase.sumColumn(filteredStrings, gameAnother, pokerStarsBase.getRegistrationString(), getAmountIndex()));
        model.setCountUnRegistrationAnother(pokerStarsBase.countTourney(filteredStrings, gameAnother, pokerStarsBase.getUnRegistrationString()));
        model.setSumUnRegistrationAnother(pokerStarsBase.sumColumn(filteredStrings, gameAnother, pokerStarsBase.getUnRegistrationString(), getAmountIndex()));
        model.setSumNetWonAnother(pokerStarsBase.sumColumn(filteredStrings, gameAnother, pokerStarsBase.getNetWonString(), getAmountIndex()));
        model.setCountReEntryAnother(pokerStarsBase.countReEntry(filteredStrings, gameAnother));
        model.setSumReEntryAnother(pokerStarsBase.sumReEntry(filteredStrings, gameAnother, getAmountIndex()));
        model.setSumKnockoutAnother(pokerStarsBase.sumKnockout(filteredStrings, gameAnother, getAmountIndex()));

        model.setCountRegistrationSpinWithoutUnregistration(pokerStarsBase.totalCountRegistrationSpinWithoutUnregistration(filteredStrings, pokerStarsBase.getRegistrationString(), pokerStarsBase.getUnRegistrationString()));
        model.setSumProfitMTT(pokerStarsBase.gameMTT(filteredStrings, gameAnother, getAmountIndex()));
        model.setSumProfitCash(pokerStarsBase.gameCash(filteredStrings, gameAnother, getAmountIndex()));

        model.setStartBalance(pokerStarsBase.startBalanceMoney(filteredStrings, getAmountIndex(), getBalanceIndex()));
        model.setFinalBalance(pokerStarsBase.finalBalance(filteredStrings, getBalanceIndex()));

        model.setStartTMoney(pokerStarsBase.startBalanceTMoney(filteredStrings, getTMoneyAmountIndex(), getTMoneyBalanceIndex()));
        model.setFinalTMoney(pokerStarsBase.finalBalance(filteredStrings, getTMoneyBalanceIndex()));

        model.setStartCoin(pokerStarsBase.startBalance(filteredStrings, getCoinBalanceIndex()));
        model.setFinalCoin(pokerStarsBase.finalBalance(filteredStrings, getCoinBalanceIndex()));

        model.setWithdrawal(pokerStarsBase.sumTransfer(filteredStrings, getAmountIndex(), pokerStarsBase.getWithdrawalString(), "    "));
        model.setSent(pokerStarsBase.sumTransfer(filteredStrings, getAmountIndex(), pokerStarsBase.getMoneySentString(), "    "));
        model.setReceived(pokerStarsBase.sumTransfer(filteredStrings, getAmountIndex(), pokerStarsBase.getMoneyReceivedStringVer1(), pokerStarsBase.getMoneyReceivedStringVer2()));
        model.setDeposit(pokerStarsBase.sumTransfer(filteredStrings, getAmountIndex(), pokerStarsBase.getDepositString(), "    "));

        model.setChestReward(pokerStarsBase.sumTransfer(filteredStrings, getAmountIndex(), pokerStarsBase.getChestString(), "    "));
        model.setExchangeCoin(pokerStarsBase.sumTransfer(filteredStrings, getAmountIndex(), pokerStarsBase.getExchangeCoinString(), "    "));
        model.setOtherBonus(pokerStarsBase.sumOtherBonus(filteredStrings, pokerStarsBase.getBonuses(), getAmountIndex()));
        model.setCasino(pokerStarsBase.sumTransfer(filteredStrings, getAmountIndex(), pokerStarsBase.getCasinoString(), "    "));
    }
}
