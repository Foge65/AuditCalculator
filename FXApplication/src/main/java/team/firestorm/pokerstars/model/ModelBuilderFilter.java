package team.firestorm.pokerstars.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ModelBuilderFilter extends ModelBuilderFromCsvFile {
    private final PokerStarsBase pokerStarsBase;
    private final List<String[]> filteredStrings;

    public ModelBuilderFilter(PokerStarsBase pokerStarsBase) {
        super(pokerStarsBase.getCsvParser());
        this.pokerStarsBase = pokerStarsBase;
        filteredStrings = new ArrayList<>();
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

    @Override
    public void setModel(Model model) {
        model.setDateFrom(getDateFrom());
        model.setDateTo(getDateTo());

        Set<String> gameSpin = pokerStarsBase.game(pokerStarsBase.getRegexGameSpin(), filteredStrings);
        model.setGameSpin(gameSpin);
        model.setCheckBoxState(pokerStarsBase.checkBoxState(gameSpin));
        model.setCountRegistrationSpin(pokerStarsBase.countGame(filteredStrings, gameSpin, pokerStarsBase.getRegistrationString()));
        model.setSumRegistrationSpin(pokerStarsBase.sumForDifferentColumn(filteredStrings, gameSpin, pokerStarsBase.getRegistrationString(), getAmountIndex()));
        model.setCountUnRegistrationSpin(pokerStarsBase.countGame(filteredStrings, gameSpin, pokerStarsBase.getUnRegistrationString()));
        model.setSumUnRegistrationSpin(pokerStarsBase.sumForDifferentColumn(filteredStrings, gameSpin, pokerStarsBase.getUnRegistrationString(), getAmountIndex()));
        model.setSumWonSpin(pokerStarsBase.sumForDifferentColumn(filteredStrings, gameSpin, pokerStarsBase.getWonString(), getAmountIndex()));
        model.setSumRegistrationForTMoneySpin(pokerStarsBase.sumForDifferentColumn(filteredStrings, gameSpin, pokerStarsBase.getRegistrationString(), getTMoneyAmountIndex()));
        model.setCountRegistrationByTicketSpin(pokerStarsBase.countRegistrationByTicket(filteredStrings, gameSpin, getAmountIndex(), getTMoneyAmountIndex()));
        model.setSumProfitSpin(pokerStarsBase.sumProfitSpin(filteredStrings, gameSpin, getAmountIndex(), getTMoneyAmountIndex()));
        model.setSumBonusSpin(pokerStarsBase.sumBonusSpin(filteredStrings, gameSpin, getAmountIndex(), getTMoneyAmountIndex()));
        model.setSumProfitPoolSpin(pokerStarsBase.sumProfitPool(filteredStrings, gameSpin, getAmountIndex(), getTMoneyAmountIndex()));
        model.setSumBonusPoolSpin(pokerStarsBase.sumBonusPool(filteredStrings, gameSpin, getAmountIndex(), getTMoneyAmountIndex()));
        model.setCountRegistrationSpinWithoutUnregistration(pokerStarsBase.totalCountRegistrationSpinWithoutUnregistration(filteredStrings, pokerStarsBase.getRegistrationString(), pokerStarsBase.getUnRegistrationString()));

        Set<String> gameMTT = pokerStarsBase.game(pokerStarsBase.getRegexGameMTT(), filteredStrings);
        model.setGameMTT(gameMTT);
        model.setCountRegistrationMTT(pokerStarsBase.countGame(filteredStrings, gameMTT, pokerStarsBase.getRegistrationString()));
        model.setSumRegistrationMTT(pokerStarsBase.sumForDifferentColumn(filteredStrings, gameMTT, pokerStarsBase.getRegistrationString(), getAmountIndex()));
        model.setCountRegistrationByTMoneyMTT(pokerStarsBase.countRegistrationByTMoney(filteredStrings, gameMTT, getTMoneyAmountIndex()));
        model.setSumRegistrationByTMoneyMTT(pokerStarsBase.sumRegistrationByTMoney(filteredStrings, gameMTT, getTMoneyAmountIndex()));
        model.setCountUnRegistrationMTT(pokerStarsBase.countGame(filteredStrings, gameMTT, pokerStarsBase.getUnRegistrationString()));
        model.setSumUnRegistrationMTT(pokerStarsBase.sumForDifferentColumn(filteredStrings, gameMTT, pokerStarsBase.getUnRegistrationString(), getAmountIndex()));
        model.setCountUnRegistrationByTMoneyMTT(pokerStarsBase.countUnRegistrationByTMoney(filteredStrings, gameMTT, getTMoneyAmountIndex()));
        model.setSumUnRegistrationByTMoneyMTT(pokerStarsBase.sumUnRegistrationByTMoney(filteredStrings, gameMTT, getTMoneyAmountIndex()));
        model.setSumWonMTT(pokerStarsBase.sumForDifferentColumn(filteredStrings, gameMTT, pokerStarsBase.getWonString(), getAmountIndex()));
        model.setCountReEntryMTT(pokerStarsBase.countReEntry(filteredStrings, gameMTT));
        model.setSumReEntryMTT(pokerStarsBase.sumReEntry(filteredStrings, gameMTT, getAmountIndex()));
        model.setSumKnockoutMTT(pokerStarsBase.sumKnockout(filteredStrings, gameMTT, getAmountIndex()));
        model.setSumInterimMTT(pokerStarsBase.sumInterim(filteredStrings, gameMTT, getAmountIndex()));
        model.setSumProfitMTT(pokerStarsBase.sumProfitMTT(filteredStrings, gameMTT, getAmountIndex()));

        Set<String> gameCash = pokerStarsBase.game(pokerStarsBase.getRegexGameCash(), filteredStrings);
        model.setGameCash(gameCash);
        model.setCountRegistrationCash(pokerStarsBase.countGame(filteredStrings, gameCash, pokerStarsBase.getSeatInTable()));
        model.setSumRegistrationCash(pokerStarsBase.sumForDifferentColumn(filteredStrings, gameCash, pokerStarsBase.getSeatInTable(), getAmountIndex()));
        model.setCountUnRegistrationCash(pokerStarsBase.countGame(filteredStrings, gameCash, pokerStarsBase.getSeatOutTable()));
        model.setSumUnRegistrationCash(pokerStarsBase.sumForDifferentColumn(filteredStrings, gameCash, pokerStarsBase.getSeatOutTable(), getAmountIndex()));
        model.setCountRebuyCash(pokerStarsBase.countGame(filteredStrings, gameCash, pokerStarsBase.getAutoRebuyTable()));
        model.setSumRebuyCash(pokerStarsBase.sumForDifferentColumn(filteredStrings, gameCash, pokerStarsBase.getAutoRebuyTable(), getAmountIndex()));
        model.setSumWonCash(pokerStarsBase.sumProfitCashGame(filteredStrings, gameCash, getAmountIndex()));

        model.setStartBalance(pokerStarsBase.startBalanceMoney(filteredStrings, getAmountIndex(), getBalanceIndex()));
        model.setFinalBalance(pokerStarsBase.finalBalance(filteredStrings, getBalanceIndex()));

        model.setStartTMoney(pokerStarsBase.startBalanceTMoney(filteredStrings, getTMoneyAmountIndex(), getTMoneyBalanceIndex()));
        model.setFinalTMoney(pokerStarsBase.finalBalance(filteredStrings, getTMoneyBalanceIndex()));

        model.setStartCoin(pokerStarsBase.startBalanceCoin(filteredStrings, getCoinBalanceIndex()));
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
