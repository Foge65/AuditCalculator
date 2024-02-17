package team.firestorm.pokerstars.model;

import javafx.application.Platform;
import lombok.Getter;
import team.firestorm.pokerstars.view.Alerts;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@Getter
public class ModelBuilderFromCsvFile {
    private final List<String[]> csvStrings;
    private String language;
    private Date date;
    private int elements;
    private LanguageEn languageEn;
    private LanguageRu languageRu;
    private PokerStarsBase pokerStarsBase;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private DateTimeFormatter dateTimeFormatter;
    private ElementIndex elementIndex;
    private int amountIndex;
    private int coinAmountIndex;
    private int tMoneyAmountIndex;
    private int balanceIndex;
    private int coinBalanceIndex;
    private int tMoneyBalanceIndex;

    public ModelBuilderFromCsvFile(CsvParser csvParser) {
        csvStrings = csvParser.getStrings();

        build(csvParser);
    }

    private void build(CsvParser csvParser) {
        language = new Language(csvParser).detect();
        date = new Date(csvParser);
        try {
            if (language.equals("en")) {
                languageEn = new LanguageEn(csvParser);
                pokerStarsBase = languageEn;
                dateFrom = date.setDateFrom(csvStrings, languageEn.getFormat());
                dateTo = date.setDateTo(csvStrings, languageEn.getFormat());
                dateTimeFormatter = languageEn.getFormat();
            } else if (language.equals("ru")) {
                languageRu = new LanguageRu(csvParser);
                pokerStarsBase = languageRu;
                dateFrom = date.setDateFrom(csvStrings, languageRu.getFormat());
                dateTo = date.setDateTo(csvStrings, languageRu.getFormat());
                dateTimeFormatter = languageRu.getFormat();
            } else {
                Platform.runLater(Alerts::unknownLanguage);
            }

            elements = csvParser.countElements();
            if (elements == 13) {
                elementIndex = new ElementIndex13();
                amountIndex = elementIndex.getAmountIndex();
                coinAmountIndex = elementIndex.getStarsCoinAmountIndex();
                tMoneyAmountIndex = elementIndex.getTMoneyAmountIndex();
                balanceIndex = elementIndex.getBalanceIndex();
                coinBalanceIndex = elementIndex.getCoinBalanceIndex();
                tMoneyBalanceIndex = elementIndex.getTMoneyBalanceIndex();
            } else if (elements == 14) {
                elementIndex = new ElementIndex14();
                amountIndex = elementIndex.getAmountIndex();
                coinAmountIndex = elementIndex.getStarsCoinAmountIndex();
                tMoneyAmountIndex = elementIndex.getTMoneyAmountIndex();
                balanceIndex = elementIndex.getBalanceIndex();
                coinBalanceIndex = elementIndex.getCoinBalanceIndex();
                tMoneyBalanceIndex = elementIndex.getTMoneyBalanceIndex();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setModel(Model model) {
        model.setDateFrom(getDateFrom());
        model.setDateTo(getDateTo());

        Set<String> gameSpin = pokerStarsBase.game(pokerStarsBase.getRegexGameSpin(), csvStrings);
        model.setGameSpin(pokerStarsBase.game(pokerStarsBase.getRegexGameSpin(), csvStrings));
        model.setCheckBoxState(pokerStarsBase.checkBoxState(gameSpin));
        model.setCountRegistrationSpin(pokerStarsBase.countGame(csvStrings, gameSpin, pokerStarsBase.getRegistrationString()));
        model.setSumRegistrationSpin(pokerStarsBase.sumForDifferentColumn(csvStrings, gameSpin, pokerStarsBase.getRegistrationString(), amountIndex));
        model.setCountUnRegistrationSpin(pokerStarsBase.countGame(csvStrings, gameSpin, pokerStarsBase.getUnRegistrationString()));
        model.setSumUnRegistrationSpin(pokerStarsBase.sumForDifferentColumn(csvStrings, gameSpin, pokerStarsBase.getUnRegistrationString(), amountIndex));
        model.setSumWonSpin(pokerStarsBase.sumForDifferentColumn(csvStrings, gameSpin, pokerStarsBase.getWonString(), amountIndex));
        model.setSumRegistrationForTMoneySpin(pokerStarsBase.sumForDifferentColumn(csvStrings, gameSpin, pokerStarsBase.getRegistrationString(), tMoneyAmountIndex));
        model.setCountRegistrationByTicketSpin(pokerStarsBase.countRegistrationByTicket(csvStrings, gameSpin, amountIndex, tMoneyAmountIndex));
        model.setSumProfitSpin(pokerStarsBase.sumProfitSpin(csvStrings, gameSpin, amountIndex, tMoneyAmountIndex));
        model.setSumBonusSpin(pokerStarsBase.sumBonusSpin(csvStrings, gameSpin, amountIndex, tMoneyAmountIndex));
        model.setSumProfitPoolSpin(pokerStarsBase.sumProfitPool(csvStrings, gameSpin, amountIndex, tMoneyAmountIndex));
        model.setSumBonusPoolSpin(pokerStarsBase.sumBonusPool(csvStrings, gameSpin, amountIndex, tMoneyAmountIndex));
        model.setCountRegistrationSpinWithoutUnregistration(pokerStarsBase.totalCountRegistrationSpinWithoutUnregistration(csvStrings, getPokerStarsBase().getRegistrationString(), pokerStarsBase.getUnRegistrationString()));

        Set<String> gameMTT = pokerStarsBase.game(pokerStarsBase.getRegexGameMTT(), csvStrings);
        model.setGameMTT(pokerStarsBase.game(pokerStarsBase.getRegexGameMTT(), csvStrings));
        model.setCountRegistrationMTT(pokerStarsBase.countGame(csvStrings, gameMTT, pokerStarsBase.getRegistrationString()));
        model.setSumRegistrationMTT(pokerStarsBase.sumForDifferentColumn(csvStrings, gameMTT, pokerStarsBase.getRegistrationString(), amountIndex));
        model.setCountRegistrationByTMoneyMTT(pokerStarsBase.countRegistrationByTMoney(csvStrings, gameMTT, tMoneyAmountIndex));
        model.setSumRegistrationByTMoneyMTT(pokerStarsBase.sumRegistrationByTMoney(csvStrings, gameMTT, tMoneyAmountIndex));
        model.setCountUnRegistrationMTT(pokerStarsBase.countGame(csvStrings, gameMTT, pokerStarsBase.getUnRegistrationString()));
        model.setSumUnRegistrationMTT(pokerStarsBase.sumForDifferentColumn(csvStrings, gameMTT, pokerStarsBase.getUnRegistrationString(), amountIndex));
        model.setCountUnRegistrationByTMoneyMTT(pokerStarsBase.countUnRegistrationByTMoney(csvStrings, gameMTT, tMoneyAmountIndex));
        model.setSumUnRegistrationByTMoneyMTT(pokerStarsBase.sumUnRegistrationByTMoney(csvStrings, gameMTT, tMoneyAmountIndex));
        model.setSumWonMTT(pokerStarsBase.sumForDifferentColumn(csvStrings, gameMTT, pokerStarsBase.getWonString(), amountIndex));
        model.setCountReEntryMTT(pokerStarsBase.countReEntry(csvStrings, gameMTT));
        model.setSumReEntryMTT(pokerStarsBase.sumReEntry(csvStrings, gameMTT, amountIndex));
        model.setSumKnockoutMTT(pokerStarsBase.sumKnockout(csvStrings, gameMTT, amountIndex));
        model.setSumInterimMTT(pokerStarsBase.sumInterim(csvStrings, gameMTT, amountIndex));
        model.setSumProfitMTT(pokerStarsBase.sumProfitMTT(csvStrings, gameMTT, amountIndex));

        Set<String> gameCash = pokerStarsBase.game(pokerStarsBase.getRegexGameCash(), csvStrings);
        model.setGameCash(pokerStarsBase.game(pokerStarsBase.getRegexGameCash(), csvStrings));
        model.setCountRegistrationCash(pokerStarsBase.countGame(csvStrings, gameCash, pokerStarsBase.getSeatInTable()));
        model.setSumRegistrationCash(pokerStarsBase.sumForDifferentColumn(csvStrings, gameCash, pokerStarsBase.getSeatInTable(), amountIndex));
        model.setCountUnRegistrationCash(pokerStarsBase.countGame(csvStrings, gameCash, pokerStarsBase.getSeatOutTable()));
        model.setSumUnRegistrationCash(pokerStarsBase.sumForDifferentColumn(csvStrings, gameCash, pokerStarsBase.getSeatOutTable(), amountIndex));
        model.setCountRebuyCash(pokerStarsBase.countGame(csvStrings, gameCash, pokerStarsBase.getAutoRebuyTable()));
        model.setSumRebuyCash(pokerStarsBase.sumForDifferentColumn(csvStrings, gameCash, pokerStarsBase.getAutoRebuyTable(), amountIndex));
        model.setSumWonCash(pokerStarsBase.sumProfitCashGame(csvStrings, gameCash, amountIndex));

        model.setStartBalance(pokerStarsBase.startBalanceMoney(csvStrings, amountIndex, balanceIndex));
        model.setFinalBalance(pokerStarsBase.finalBalance(csvStrings, balanceIndex));

        model.setStartTMoney(pokerStarsBase.startBalanceTMoney(csvStrings, tMoneyAmountIndex, tMoneyBalanceIndex));
        model.setFinalTMoney(pokerStarsBase.finalBalance(csvStrings, tMoneyBalanceIndex));

        model.setStartCoin(pokerStarsBase.startBalanceCoin(csvStrings, coinBalanceIndex));
        model.setFinalCoin(pokerStarsBase.finalBalance(csvStrings, coinBalanceIndex));

        model.setWithdrawal(pokerStarsBase.sumTransfer(csvStrings, amountIndex, pokerStarsBase.getWithdrawalString(), "    "));
        model.setSent(pokerStarsBase.sumTransfer(csvStrings, amountIndex, pokerStarsBase.getMoneySentString(), "    "));
        model.setReceived(pokerStarsBase.sumTransfer(csvStrings, amountIndex, pokerStarsBase.getMoneyReceivedStringVer1(), pokerStarsBase.getMoneyReceivedStringVer2()));
        model.setDeposit(pokerStarsBase.sumTransfer(csvStrings, amountIndex, pokerStarsBase.getDepositString(), "    "));

        model.setChestReward(pokerStarsBase.sumTransfer(csvStrings, amountIndex, pokerStarsBase.getChestString(), "    "));
        model.setExchangeCoin(pokerStarsBase.sumTransfer(csvStrings, amountIndex, pokerStarsBase.getExchangeCoinString(), "    "));
        model.setOtherBonus(pokerStarsBase.sumOtherBonus(csvStrings, pokerStarsBase.getBonuses(), amountIndex));
        model.setCasino(pokerStarsBase.sumTransfer(csvStrings, amountIndex, pokerStarsBase.getCasinoString(), "    "));
    }
}
