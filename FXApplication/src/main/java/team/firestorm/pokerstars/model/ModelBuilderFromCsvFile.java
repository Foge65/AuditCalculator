package team.firestorm.pokerstars.model;

import lombok.Getter;

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
    private LanguageEn languageEn;
    private LanguageRu languageRu;
    private PokerStarsBase pokerStarsBase;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private DateTimeFormatter dateTimeFormatter;

    private final int elements;
    private ElementIndex elementIndex;

    private int amountIndex;
    private int coinAmountIndex;
    private int tMoneyAmountIndex;

    private int balanceIndex;
    private int coinBalanceIndex;
    private int tMoneyBalanceIndex;

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

    private Map<String, Boolean> pool;

    private final Set<String> gameAnother;
    private final Map<String, Integer> countRegistrationAnother;
    private final Map<String, BigDecimal> sumRegistrationAnother;
    private final Map<String, Integer> countUnRegistrationAnother;
    private final Map<String, BigDecimal> sumUnRegistrationAnother;
    private final Map<String, BigDecimal> sumNetWonAnother;
    private final Map<String, Integer> countReEntryAnother;
    private final Map<String, BigDecimal> sumReEntryAnother;
    private final Map<String, BigDecimal> sumKnockoutAnother;

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
    private String cash;

    private String casino;
    private String omaha;
    private String other;

    public ModelBuilderFromCsvFile(CsvParser csvParser) {
        List<String[]> csvStrings = csvParser.getStrings();

        languageDetection = new Language(csvParser);
        language = languageDetection.detect();
        date = new Date(csvParser);
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

//        pool = pokerStarsBase.setPoolBoolean(gameSpin);

        gameAnother = pokerStarsBase.game(pokerStarsBase.getRegexGameAnother(), csvStrings);
        countRegistrationAnother = pokerStarsBase.countTourney(csvStrings, gameAnother, pokerStarsBase.getRegistrationString());
        sumRegistrationAnother = pokerStarsBase.sumColumn(csvStrings, gameAnother, pokerStarsBase.getRegistrationString(), amountIndex);
        countUnRegistrationAnother = pokerStarsBase.countTourney(csvStrings, gameAnother, pokerStarsBase.getUnRegistrationString());
        sumUnRegistrationAnother = pokerStarsBase.sumColumn(csvStrings, gameAnother, pokerStarsBase.getUnRegistrationString(), amountIndex);
        sumNetWonAnother = pokerStarsBase.sumColumn(csvStrings, gameAnother, pokerStarsBase.getNetWonString(), amountIndex);
        countReEntryAnother = pokerStarsBase.countReEntry(csvStrings, gameAnother);
        sumReEntryAnother = pokerStarsBase.sumReEntry(csvStrings, gameAnother, amountIndex);
        sumKnockoutAnother = pokerStarsBase.sumKnockout(csvStrings, gameAnother, amountIndex);

        startBalance = pokerStarsBase.startBalanceMoney(csvStrings, amountIndex, balanceIndex);
        finalBalance = pokerStarsBase.finalBalance(csvStrings, balanceIndex);

        startTMoney = pokerStarsBase.startBalanceTMoney(csvStrings, tMoneyAmountIndex, tMoneyBalanceIndex);
        finalTMoney = pokerStarsBase.finalBalance(csvStrings, tMoneyAmountIndex);

        startCoin = pokerStarsBase.startBalance(csvStrings, coinBalanceIndex);
        finalCoin = pokerStarsBase.finalBalance(csvStrings, coinBalanceIndex);

        withdrawal = pokerStarsBase.sumTransfer(csvStrings, amountIndex, pokerStarsBase.getWithdrawalString(), "    ");
        sent = pokerStarsBase.sumTransfer(csvStrings, amountIndex, pokerStarsBase.getMoneySentString(), "    ");
        received = pokerStarsBase.sumTransfer(csvStrings, amountIndex, pokerStarsBase.getMoneyReceivedStringVer1(), pokerStarsBase.getMoneyReceivedStringVer2());
        deposit = pokerStarsBase.sumTransfer(csvStrings, amountIndex, pokerStarsBase.getDepositString(), "    ");

        chestReward = pokerStarsBase.sumTransfer(csvStrings, amountIndex, pokerStarsBase.getChestString(), "    ");
        exchangeCoin = pokerStarsBase.sumTransfer(csvStrings, amountIndex, pokerStarsBase.getExchangeCoinString(), "    ");

        casino = pokerStarsBase.sumTransfer(csvStrings, amountIndex, pokerStarsBase.getCasinoString(), "    ");
    }
}
