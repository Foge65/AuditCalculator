package team.firestorm.pokerstars.model;

import javafx.application.Platform;
import lombok.Getter;
import team.firestorm.pokerstars.view.Alerts;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
public class ModelBuilderFromCsvFile {
    private final Language languageDetection;
    private final String language;
    private final Date date;
    private final List<String[]> csvStrings;
    private int elements;

    private Set<String> gameSpin;
    private Map<String, Boolean> checkBoxState;
    private Map<String, Integer> countRegistrationSpin;
    private Map<String, BigDecimal> sumRegistrationSpin;
    private Map<String, Integer> countUnRegistrationSpin;
    private Map<String, BigDecimal> sumUnRegistrationSpin;
    private Map<String, BigDecimal> sumWonSpin;
    private Map<String, BigDecimal> sumRegistrationForTMoneySpin;
    private Map<String, Integer> countRegistrationByTicketSpin;
    private Map<String, BigDecimal> sumProfitSpin;
    private Map<String, BigDecimal> sumBonusSpin;
    private Map<String, BigDecimal> sumProfitPoolSpin;
    private Map<String, BigDecimal> sumBonusPoolSpin;
    private Integer countRegistrationSpinWithoutUnregistration;

    private Set<String> gameMTT;
    private Map<String, Integer> countRegistrationMTT;
    private Map<String, BigDecimal> sumRegistrationMTT;
    private Map<String, Integer> countRegistrationByTMoneyMTT;
    private Map<String, BigDecimal> sumRegistrationByTMoneyMTT;
    private Map<String, Integer> countUnRegistrationMTT;
    private Map<String, BigDecimal> sumUnRegistrationMTT;
    private Map<String, Integer> countUnRegistrationByTMoneyMTT;
    private Map<String, BigDecimal> sumUnRegistrationByTMoneyMTT;
    private Map<String, BigDecimal> sumWonMTT;
    private Map<String, Integer> countReEntryMTT;
    private Map<String, BigDecimal> sumReEntryMTT;
    private Map<String, BigDecimal> sumKnockoutMTT;
    private Map<String, BigDecimal> sumInterimMTT;
    private Map<String, BigDecimal> sumProfitMTT;

    private Set<String> gameCash;
    private Map<String, Integer> countRegistrationCash;
    private Map<String, BigDecimal> sumRegistrationCash;
    private Map<String, Integer> countUnRegistrationCash;
    private Map<String, BigDecimal> sumUnRegistrationCash;
    private Map<String, Integer> countRebuyCash;
    private Map<String, BigDecimal> sumRebuyCash;
    private Map<String, BigDecimal> sumWonCash;

    private String startBalance;
    private String finalBalance;
    private String startTMoney;
    private String finalTMoney;
    private String startCoin;
    private String finalCoin;
    private String withdrawal;
    private String sent;
    private String received;
    private String deposit;
    private String chestReward;
    private String exchangeCoin;
    private String otherBonus;
    private String casino;
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

        languageDetection = new Language(csvParser);
        language = languageDetection.detect();
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

        setModel();
    }

    private void setModel() {
        gameSpin = pokerStarsBase.game(pokerStarsBase.getRegexGameSpin(), csvStrings);
        checkBoxState = pokerStarsBase.checkBoxState(gameSpin);
        countRegistrationSpin = pokerStarsBase.countGame(csvStrings, gameSpin, pokerStarsBase.getRegistrationString());
        sumRegistrationSpin = pokerStarsBase.sumForDifferentColumn(csvStrings, gameSpin, pokerStarsBase.getRegistrationString(), amountIndex);
        countUnRegistrationSpin = pokerStarsBase.countGame(csvStrings, gameSpin, pokerStarsBase.getUnRegistrationString());
        sumUnRegistrationSpin = pokerStarsBase.sumForDifferentColumn(csvStrings, gameSpin, pokerStarsBase.getUnRegistrationString(), amountIndex);
        sumWonSpin = pokerStarsBase.sumForDifferentColumn(csvStrings, gameSpin, pokerStarsBase.getWonString(), amountIndex);
        sumRegistrationForTMoneySpin = pokerStarsBase.sumForDifferentColumn(csvStrings, gameSpin, pokerStarsBase.getRegistrationString(), tMoneyAmountIndex);
        countRegistrationByTicketSpin = pokerStarsBase.countRegistrationByTicket(csvStrings, gameSpin, amountIndex, tMoneyAmountIndex);
        sumProfitSpin = pokerStarsBase.sumProfitSpin(csvStrings, gameSpin, amountIndex, tMoneyAmountIndex);
        sumBonusSpin = pokerStarsBase.sumBonusSpin(csvStrings, gameSpin, amountIndex, tMoneyAmountIndex);
        sumProfitPoolSpin = pokerStarsBase.sumProfitPool(csvStrings, gameSpin, amountIndex, tMoneyAmountIndex);
        sumBonusPoolSpin = pokerStarsBase.sumBonusPool(csvStrings, gameSpin, amountIndex, tMoneyAmountIndex);
        countRegistrationSpinWithoutUnregistration = pokerStarsBase.totalCountRegistrationSpinWithoutUnregistration(csvStrings, getPokerStarsBase().getRegistrationString(), pokerStarsBase.getUnRegistrationString());

        gameMTT = pokerStarsBase.game(pokerStarsBase.getRegexGameMTT(), csvStrings);
        countRegistrationMTT = pokerStarsBase.countGame(csvStrings, gameMTT, pokerStarsBase.getRegistrationString());
        sumRegistrationMTT = pokerStarsBase.sumForDifferentColumn(csvStrings, gameMTT, pokerStarsBase.getRegistrationString(), amountIndex);
        countRegistrationByTMoneyMTT = pokerStarsBase.countRegistrationByTMoney(csvStrings, gameMTT, tMoneyAmountIndex);
        sumRegistrationByTMoneyMTT = pokerStarsBase.sumRegistrationByTMoney(csvStrings, gameMTT, tMoneyAmountIndex);
        countUnRegistrationMTT = pokerStarsBase.countGame(csvStrings, gameMTT, pokerStarsBase.getUnRegistrationString());
        sumUnRegistrationMTT = pokerStarsBase.sumForDifferentColumn(csvStrings, gameMTT, pokerStarsBase.getUnRegistrationString(), amountIndex);
        countUnRegistrationByTMoneyMTT = pokerStarsBase.countUnRegistrationByTMoney(csvStrings, gameMTT, tMoneyAmountIndex);
        sumUnRegistrationByTMoneyMTT = pokerStarsBase.sumUnRegistrationByTMoney(csvStrings, gameMTT, tMoneyAmountIndex);
        sumWonMTT = pokerStarsBase.sumForDifferentColumn(csvStrings, gameMTT, pokerStarsBase.getWonString(), amountIndex);
        countReEntryMTT = pokerStarsBase.countReEntry(csvStrings, gameMTT);
        sumReEntryMTT = pokerStarsBase.sumReEntry(csvStrings, gameMTT, amountIndex);
        sumKnockoutMTT = pokerStarsBase.sumKnockout(csvStrings, gameMTT, amountIndex);
        sumInterimMTT = pokerStarsBase.sumInterim(csvStrings, gameMTT, amountIndex);
        sumProfitMTT = pokerStarsBase.sumProfitMTT(csvStrings, gameMTT, amountIndex);

        gameCash = pokerStarsBase.game(pokerStarsBase.getRegexGameCash(), csvStrings);
        countRegistrationCash = pokerStarsBase.countGame(csvStrings, gameCash, pokerStarsBase.getSeatInTable());
        sumRegistrationCash = pokerStarsBase.sumForDifferentColumn(csvStrings, gameCash, pokerStarsBase.getSeatInTable(), amountIndex);
        countUnRegistrationCash = pokerStarsBase.countGame(csvStrings, gameCash, pokerStarsBase.getSeatOutTable());
        sumUnRegistrationCash = pokerStarsBase.sumForDifferentColumn(csvStrings, gameCash, pokerStarsBase.getSeatOutTable(), amountIndex);
        countRebuyCash = pokerStarsBase.countGame(csvStrings, gameCash, pokerStarsBase.getAutoRebuyTable());
        sumRebuyCash = pokerStarsBase.sumForDifferentColumn(csvStrings, gameCash, pokerStarsBase.getAutoRebuyTable(), amountIndex);
        sumWonCash = pokerStarsBase.sumProfitCashGame(csvStrings, gameCash, amountIndex);

        startBalance = pokerStarsBase.startBalanceMoney(csvStrings, amountIndex, balanceIndex);
        finalBalance = pokerStarsBase.finalBalance(csvStrings, balanceIndex);

        startTMoney = pokerStarsBase.startBalanceTMoney(csvStrings, tMoneyAmountIndex, tMoneyBalanceIndex);
        finalTMoney = pokerStarsBase.finalBalance(csvStrings, tMoneyBalanceIndex);

        startCoin = pokerStarsBase.startBalanceCoin(csvStrings, coinBalanceIndex);
        finalCoin = pokerStarsBase.finalBalance(csvStrings, coinBalanceIndex);

        withdrawal = pokerStarsBase.sumTransfer(csvStrings, amountIndex, pokerStarsBase.getWithdrawalString(), "    ");
        sent = pokerStarsBase.sumTransfer(csvStrings, amountIndex, pokerStarsBase.getMoneySentString(), "    ");
        received = pokerStarsBase.sumTransfer(csvStrings, amountIndex, pokerStarsBase.getMoneyReceivedStringVer1(), pokerStarsBase.getMoneyReceivedStringVer2());
        deposit = pokerStarsBase.sumTransfer(csvStrings, amountIndex, pokerStarsBase.getDepositString(), "    ");

        chestReward = pokerStarsBase.sumTransfer(csvStrings, amountIndex, pokerStarsBase.getChestString(), "    ");
        exchangeCoin = pokerStarsBase.sumTransfer(csvStrings, amountIndex, pokerStarsBase.getExchangeCoinString(), "    ");
        otherBonus = pokerStarsBase.sumOtherBonus(csvStrings, pokerStarsBase.getBonuses(), amountIndex);
        casino = pokerStarsBase.sumTransfer(csvStrings, amountIndex, pokerStarsBase.getCasinoString(), "    ");
    }
}
