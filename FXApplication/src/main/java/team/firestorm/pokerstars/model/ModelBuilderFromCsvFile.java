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
    private final int elements;

    private final Set<String> gameSpin;
    private final Map<String, Integer> countRegistrationSpin;
    private final Map<String, BigDecimal> sumRegistrationSpin;
    private final Map<String, Integer> countUnRegistrationSpin;
    private final Map<String, BigDecimal> sumUnRegistrationSpin;
    private final Map<String, BigDecimal> sumNetWonSpin;
    private final Map<String, BigDecimal> sumRegistrationForTMoneySpin;
    private final Map<String, Integer> countRegistrationByTicketSpin;
    private final Map<String, BigDecimal> sumProfitSpin;
    private final Map<String, BigDecimal> sumBonusSpin;
    private final Map<String, BigDecimal> sumProfitPoolSpin;
    private final Map<String, BigDecimal> sumBonusPoolSpin;

    private final Set<String> gameMTT;
    private final Map<String, Integer> countRegistrationMTT;
    private final Map<String, BigDecimal> sumRegistrationMTT;
    private final Map<String, Integer> countUnRegistrationMTT;
    private final Map<String, BigDecimal> sumUnRegistrationMTT;
    private final Map<String, BigDecimal> sumNetWonMTT;
    private final Map<String, Integer> countReEntryMTT;
    private final Map<String, BigDecimal> sumReEntryMTT;
    private final Map<String, BigDecimal> sumKnockoutMTT;

    private final Set<String> gameCash;
    private final Map<String, Integer> countRegistrationCash;
    private final Map<String, BigDecimal> sumRegistrationCash;
    private final Map<String, Integer> countUnRegistrationCash;
    private final Map<String, BigDecimal> sumUnRegistrationCash;
    private final Map<String, BigDecimal> sumNetWonCash;

    private final Integer countRegistrationSpinWithoutUnregistration;
    private final Map<String, BigDecimal> sumProfitMTT;
    private final Map<String, BigDecimal> sumProfitCash;

    private final String startBalance;
    private final String finalBalance;
    private final String startTMoney;
    private final String finalTMoney;
    private final String startCoin;
    private final String finalCoin;
    private final String withdrawal;
    private final String sent;
    private final String received;
    private final String deposit;
    private final String chestReward;
    private final String exchangeCoin;
    private final String otherBonus;
    private final String casino;
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
        List<String[]> csvStrings = csvParser.getStrings();

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
                Platform.runLater(() -> Alerts.unknownLanguage());
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
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

        gameSpin = pokerStarsBase.game(pokerStarsBase.getRegexGameSpin(), csvStrings);
        countRegistrationSpin = pokerStarsBase.countTourney(csvStrings, gameSpin, pokerStarsBase.getRegistrationString());
        sumRegistrationSpin = pokerStarsBase.sumColumn(csvStrings, gameSpin, pokerStarsBase.getRegistrationString(), amountIndex);
        countUnRegistrationSpin = pokerStarsBase.countTourney(csvStrings, gameSpin, pokerStarsBase.getUnRegistrationString());
        sumUnRegistrationSpin = pokerStarsBase.sumColumn(csvStrings, gameSpin, pokerStarsBase.getUnRegistrationString(), amountIndex);
        sumNetWonSpin = pokerStarsBase.sumColumn(csvStrings, gameSpin, pokerStarsBase.getNetWonString(), amountIndex);
        sumRegistrationForTMoneySpin = pokerStarsBase.sumColumn(csvStrings, gameSpin, pokerStarsBase.getRegistrationString(), tMoneyAmountIndex);
        countRegistrationByTicketSpin = pokerStarsBase.countRegistrationByTicket(csvStrings, gameSpin, amountIndex, tMoneyAmountIndex);
        sumProfitSpin = pokerStarsBase.sumProfit(csvStrings, gameSpin, amountIndex, tMoneyAmountIndex);
        sumBonusSpin = pokerStarsBase.sumBonus(csvStrings, gameSpin, amountIndex, tMoneyAmountIndex);
        sumProfitPoolSpin = pokerStarsBase.sumProfitPool(csvStrings, gameSpin, amountIndex, tMoneyAmountIndex);
        sumBonusPoolSpin = pokerStarsBase.sumBonusPool(csvStrings, gameSpin, amountIndex, tMoneyAmountIndex);

        gameMTT = pokerStarsBase.game(pokerStarsBase.getRegexGameMTT(), csvStrings);
        countRegistrationMTT = pokerStarsBase.countTourney(csvStrings, gameMTT, pokerStarsBase.getRegistrationString());
        sumRegistrationMTT = pokerStarsBase.sumColumn(csvStrings, gameMTT, pokerStarsBase.getRegistrationString(), amountIndex);
        countUnRegistrationMTT = pokerStarsBase.countTourney(csvStrings, gameMTT, pokerStarsBase.getUnRegistrationString());
        sumUnRegistrationMTT = pokerStarsBase.sumColumn(csvStrings, gameMTT, pokerStarsBase.getUnRegistrationString(), amountIndex);
        sumNetWonMTT = pokerStarsBase.sumColumn(csvStrings, gameMTT, pokerStarsBase.getNetWonString(), amountIndex);
        countReEntryMTT = pokerStarsBase.countReEntry(csvStrings, gameMTT);
        sumReEntryMTT = pokerStarsBase.sumReEntry(csvStrings, gameMTT, amountIndex);
        sumKnockoutMTT = pokerStarsBase.sumKnockout(csvStrings, gameMTT, amountIndex);

        gameCash = pokerStarsBase.game(pokerStarsBase.getRegexGameCash(), csvStrings);
        countRegistrationCash = pokerStarsBase.countTourney(csvStrings, gameCash, pokerStarsBase.getRegistrationString());
        sumRegistrationCash = pokerStarsBase.sumColumn(csvStrings, gameCash, pokerStarsBase.getRegistrationString(), amountIndex);
        countUnRegistrationCash = pokerStarsBase.countTourney(csvStrings, gameCash, pokerStarsBase.getUnRegistrationString());
        sumUnRegistrationCash = pokerStarsBase.sumColumn(csvStrings, gameCash, pokerStarsBase.getUnRegistrationString(), amountIndex);
        sumNetWonCash = pokerStarsBase.sumColumn(csvStrings, gameCash, pokerStarsBase.getNetWonString(), amountIndex);

        countRegistrationSpinWithoutUnregistration = pokerStarsBase.totalCountRegistrationSpinWithoutUnregistration(csvStrings, getPokerStarsBase().getRegistrationString(), pokerStarsBase.getUnRegistrationString());
        sumProfitMTT = pokerStarsBase.sumForEachGame(csvStrings, gameMTT, amountIndex);
        sumProfitCash = pokerStarsBase.sumForEachGame(csvStrings, gameCash, amountIndex);

        startBalance = pokerStarsBase.startBalanceMoney(csvStrings, amountIndex, balanceIndex);
        finalBalance = pokerStarsBase.finalBalance(csvStrings, balanceIndex);

        startTMoney = pokerStarsBase.startBalanceTMoney(csvStrings, tMoneyAmountIndex, tMoneyBalanceIndex);
        finalTMoney = pokerStarsBase.finalBalance(csvStrings, tMoneyBalanceIndex);

        startCoin = pokerStarsBase.startBalance(csvStrings, coinBalanceIndex);
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
