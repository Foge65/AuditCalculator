package team.firestorm.model.pokerstars;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PokerStarsBuilder {
    private final LanguageDetection languageDetection;
    @Getter
    private final StringProcessor stringProcessor;

    private PokerStarsBase pokerStarsBase;
    private PokerStarsEn pokerStarsEn;
    private PokerStarsRu pokerStarsRu;
    private PokerStars13 pokerStars13;
    private PokerStars14 pokerStars14;

    private DateProcessor dateProcessor;

    @Getter
    private Set<String> gameSpin = new HashSet<>();
    @Getter
    private Map<String, Integer> countTourneyRegistrationSpin = new HashMap<>();
    @Getter
    private Map<String, Integer> countTourneyUnregistrationSpin = new HashMap<>();
    @Getter
    private Map<String, BigDecimal> sumTourneyRegistrationSpin = new HashMap<>();
    @Getter
    private Map<String, BigDecimal> sumTourneyUnregistrationSpin = new HashMap<>();
    @Getter
    private Map<String, BigDecimal> sumTourneyWonSpin = new HashMap<>();
    @Getter
    private Map<String, BigDecimal> sumRegistrationForTMoney = new HashMap<>();
    @Getter
    private Map<String, Integer> countRegistrationByTicket = new HashMap<>();
    @Getter
    private Map<String, BigDecimal> sumProfit = new HashMap<>();
    @Getter
    private Map<String, BigDecimal> sumBonus = new HashMap<>();

    @Getter
    private Set<String> gameAnother = new HashSet<>();
    @Getter
    private Map<String, Integer> countTourneyRegistrationAnother = new HashMap<>();
    @Getter
    private Map<String, Integer> countTourneyUnregistrationAnother = new HashMap<>();
    @Getter
    private Map<String, BigDecimal> sumTourneyRegistrationAnother = new HashMap<>();
    @Getter
    private Map<String, BigDecimal> sumTourneyUnregistrationAnother = new HashMap<>();
    @Getter
    private Map<String, BigDecimal> sumTourneyWonAnother = new HashMap<>();
    @Getter
    private Map<String, Integer> countReEntry = new HashMap<>();
    @Getter
    private Map<String, BigDecimal> sumReEntry = new HashMap<>();
    @Getter
    private Map<String, Integer> countWonTicket = new HashMap<>();
    @Getter
    private Map<String, BigDecimal> sumKnockout = new HashMap<>();

    @Getter
    private String startBalance;
    @Getter
    private String finalBalance;
    @Getter
    private String startTMoney;
    @Getter
    private String finalTMoney;
    @Getter
    private String totalTMoney;
    @Getter
    private String startCoin;
    @Getter
    private String finalCoin;
    @Getter
    private String totalCoin;
    @Getter
    private String withdrawal;
    @Getter
    private String sent;
    @Getter
    private String received;
    @Getter
    private String deposit;
    @Getter
    private String bonus;
    @Getter
    private String exchange;
    @Getter
    private String cash;
    @Getter
    private String roulette;
    @Getter
    private String omaha;
    @Getter
    private String other;
    @Getter
    private LocalDate dateFrom;
    @Getter
    private LocalDate dateTo;

    public PokerStarsBuilder(LanguageDetection languageDetection, StringProcessor stringProcessor) {
        this.languageDetection = languageDetection;
        this.stringProcessor = stringProcessor;

        pokerStarsEn = new PokerStarsEn(stringProcessor);
        pokerStarsRu = new PokerStarsRu(stringProcessor);
        pokerStars13 = new PokerStars13();
        pokerStars14 = new PokerStars14();

        int amount13 = pokerStars13.getAmount();
        int starsCoin13 = pokerStars13.getStarsCoin();
        int tMoney13 = pokerStars13.getTMoney();
        int balance13 = pokerStars13.getBalance();

        int amount14 = pokerStars14.getAmount();
        int starsCoin14 = pokerStars14.getStarsCoin();
        int tMoney14 = pokerStars14.getTMoney();
        int balance14 = pokerStars14.getBalance();

        String language = languageDetection.detect();
        int elements = stringProcessor.countElements();
        if (language.equals("en")) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd h:mm a");
            dateProcessor = new DateProcessor(stringProcessor);
            dateFrom = dateProcessor.setDateFrom(dateFormatter);
            dateTo = dateProcessor.setDateTo(dateFormatter);
        }
        if (language.equals("ru")) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy h:mm a");
            dateProcessor = new DateProcessor(stringProcessor);
            dateFrom = dateProcessor.setDateFrom(dateFormatter);
            dateTo = dateProcessor.setDateTo(dateFormatter);
        }

        if (language.equals("en") && elements == 13) {
            pokerStarsBase = new PokerStarsEn(stringProcessor);

            gameSpin = pokerStarsBase.gameSpin();
            countTourneyRegistrationSpin = pokerStarsBase.countTourney(gameSpin, pokerStarsEn.getRegistration());
            countTourneyUnregistrationSpin = pokerStarsBase.countTourney(gameSpin, pokerStarsEn.getUnregistration());
            sumTourneyRegistrationSpin = pokerStarsBase.sumColumn(gameSpin, pokerStarsEn.getRegistration(), amount13);
            sumTourneyUnregistrationSpin = pokerStarsBase.sumColumn(gameSpin, pokerStarsEn.getUnregistration(), amount13);
            sumTourneyWonSpin = pokerStarsBase.sumColumn(gameSpin, pokerStarsEn.getWon(), amount13);
            sumRegistrationForTMoney = pokerStarsBase.sumColumn(gameSpin, pokerStarsEn.getRegistration(), tMoney13);
            countRegistrationByTicket = pokerStarsBase.countRegistrationByTicket(gameSpin, amount13, tMoney13);
            sumProfit = pokerStarsBase.sumProfit(gameSpin, amount13, tMoney13);
            sumBonus = pokerStarsBase.sumBonus(gameSpin, amount13, tMoney13);

            gameAnother = pokerStarsBase.gameAnother();
            countTourneyRegistrationAnother = pokerStarsBase.countTourney(gameAnother, pokerStarsEn.getRegistration());
            countTourneyUnregistrationAnother = pokerStarsBase.countTourney(gameAnother, pokerStarsEn.getUnregistration());
            sumTourneyRegistrationAnother = pokerStarsBase.sumColumn(gameAnother, pokerStarsEn.getRegistration(), amount13);
            sumTourneyUnregistrationAnother = pokerStarsBase.sumColumn(gameAnother, pokerStarsEn.getUnregistration(), amount13);
            sumTourneyWonAnother = pokerStarsBase.sumColumn(gameAnother, pokerStarsEn.getWon(), amount13);
            countReEntry = pokerStarsBase.countReEntry(gameAnother);
            sumReEntry = pokerStarsBase.sumReEntry(gameAnother, amount13);
            countWonTicket = pokerStarsBase.countWonTicket(gameAnother);
            sumKnockout = pokerStarsBase.sumKnockout(gameAnother, amount13);

            startBalance = pokerStarsBase.startBalance(balance13);
            finalBalance = pokerStarsBase.finalBalance(balance13);
            startTMoney = pokerStarsBase.startBalance(tMoney13);
            finalTMoney = pokerStarsBase.finalBalance(tMoney13);
            totalTMoney = pokerStarsBase.sumBalance(tMoney13);
            startCoin = pokerStarsBase.startBalance(starsCoin13);
            finalCoin = pokerStarsBase.finalBalance(starsCoin13);
            totalCoin = pokerStarsBase.sumBalance(starsCoin13);

            withdrawal = pokerStarsBase.sumTransfer(amount13, pokerStarsEn.getWithdrawal(), "");
            sent = pokerStarsBase.sumTransfer(amount13, pokerStarsEn.getMoneySent(), "");
            received = pokerStarsBase.sumTransfer(amount13, pokerStarsEn.getMoneyReceived1(), pokerStarsEn.getMoneyReceived2());
            deposit = pokerStarsBase.sumTransfer(amount13, pokerStarsEn.getDeposit(), "");
        }

        if (language.equals("en") && elements == 14) {
            pokerStarsBase = new PokerStarsEn(stringProcessor);

            gameSpin = pokerStarsBase.gameSpin();
            countTourneyRegistrationSpin = pokerStarsBase.countTourney(gameSpin, pokerStarsEn.getRegistration());
            countTourneyUnregistrationSpin = pokerStarsBase.countTourney(gameSpin, pokerStarsEn.getUnregistration());
            sumTourneyRegistrationSpin = pokerStarsBase.sumColumn(gameSpin, pokerStarsEn.getRegistration(), amount14);
            sumTourneyUnregistrationSpin = pokerStarsBase.sumColumn(gameSpin, pokerStarsEn.getUnregistration(), amount14);
            sumTourneyWonSpin = pokerStarsBase.sumColumn(gameSpin, pokerStarsEn.getWon(), amount14);
            sumRegistrationForTMoney = pokerStarsBase.sumColumn(gameSpin, pokerStarsEn.getRegistration(), tMoney14);
            countRegistrationByTicket = pokerStarsBase.countRegistrationByTicket(gameSpin, amount14, tMoney14);
            sumProfit = pokerStarsBase.sumProfit(gameSpin, amount14, tMoney14);
            sumBonus = pokerStarsBase.sumBonus(gameSpin, amount14, tMoney14);

            gameAnother = pokerStarsBase.gameAnother();
            countTourneyRegistrationAnother = pokerStarsBase.countTourney(gameAnother, pokerStarsEn.getRegistration());
            countTourneyUnregistrationAnother = pokerStarsBase.countTourney(gameAnother, pokerStarsEn.getUnregistration());
            sumTourneyRegistrationAnother = pokerStarsBase.sumColumn(gameAnother, pokerStarsEn.getRegistration(), amount14);
            sumTourneyUnregistrationAnother = pokerStarsBase.sumColumn(gameAnother, pokerStarsEn.getUnregistration(), amount14);
            sumTourneyWonAnother = pokerStarsBase.sumColumn(gameAnother, pokerStarsEn.getWon(), amount14);
            countReEntry = pokerStarsBase.countReEntry(gameAnother);
            sumReEntry = pokerStarsBase.sumReEntry(gameAnother, amount14);
            countWonTicket = pokerStarsBase.countWonTicket(gameAnother);
            sumKnockout = pokerStarsBase.sumKnockout(gameAnother, amount14);

            startBalance = pokerStarsBase.startBalance(balance14);
            finalBalance = pokerStarsBase.finalBalance(balance14);
            startTMoney = pokerStarsBase.startBalance(tMoney14);
            finalTMoney = pokerStarsBase.finalBalance(tMoney14);
            totalTMoney = pokerStarsBase.sumBalance(tMoney14);
            startCoin = pokerStarsBase.startBalance(starsCoin14);
            finalCoin = pokerStarsBase.finalBalance(starsCoin14);
            totalCoin = pokerStarsBase.sumBalance(starsCoin14);

            withdrawal = pokerStarsBase.sumTransfer(amount14, pokerStarsEn.getWithdrawal(), "");
            sent = pokerStarsBase.sumTransfer(amount14, pokerStarsEn.getMoneySent(), "");
            received = pokerStarsBase.sumTransfer(amount14, pokerStarsEn.getMoneyReceived1(), pokerStarsEn.getMoneyReceived2());
            deposit = pokerStarsBase.sumTransfer(amount14, pokerStarsEn.getDeposit(), "");
        }

        if (language.equals("ru") && elements == 13) {
            pokerStarsBase = new PokerStarsRu(stringProcessor);

            gameSpin = pokerStarsBase.gameSpin();
            countTourneyRegistrationSpin = pokerStarsBase.countTourney(gameSpin, pokerStarsRu.getRegistration());
            countTourneyUnregistrationSpin = pokerStarsBase.countTourney(gameSpin, pokerStarsRu.getUnregistration());
            sumTourneyRegistrationSpin = pokerStarsBase.sumColumn(gameSpin, pokerStarsRu.getRegistration(), amount13);
            sumTourneyUnregistrationSpin = pokerStarsBase.sumColumn(gameSpin, pokerStarsRu.getUnregistration(), amount13);
            sumTourneyWonSpin = pokerStarsBase.sumColumn(gameSpin, pokerStarsRu.getWon(), amount13);
            sumRegistrationForTMoney = pokerStarsBase.sumColumn(gameSpin, pokerStarsRu.getRegistration(), tMoney13);
            countRegistrationByTicket = pokerStarsBase.countRegistrationByTicket(gameSpin, amount13, tMoney13);
            sumProfit = pokerStarsBase.sumProfit(gameSpin, amount13, tMoney13);
            sumBonus = pokerStarsBase.sumBonus(gameSpin, amount13, tMoney13);

            gameAnother = pokerStarsBase.gameAnother();
            countTourneyRegistrationAnother = pokerStarsBase.countTourney(gameAnother, pokerStarsRu.getRegistration());
            countTourneyUnregistrationAnother = pokerStarsBase.countTourney(gameAnother, pokerStarsRu.getUnregistration());
            sumTourneyRegistrationAnother = pokerStarsBase.sumColumn(gameAnother, pokerStarsRu.getRegistration(), amount13);
            sumTourneyUnregistrationAnother = pokerStarsBase.sumColumn(gameAnother, pokerStarsRu.getUnregistration(), amount13);
            sumTourneyWonAnother = pokerStarsBase.sumColumn(gameAnother, pokerStarsRu.getWon(), amount13);
            countReEntry = pokerStarsBase.countReEntry(gameAnother);
            sumReEntry = pokerStarsBase.sumReEntry(gameAnother, amount13);
            countWonTicket = pokerStarsBase.countWonTicket(gameAnother);
            sumKnockout = pokerStarsBase.sumKnockout(gameAnother, amount13);

            startBalance = pokerStarsBase.startBalance(balance13);
            finalBalance = pokerStarsBase.finalBalance(balance13);
            startTMoney = pokerStarsBase.startBalance(tMoney13);
            finalTMoney = pokerStarsBase.finalBalance(tMoney13);
            totalTMoney = pokerStarsBase.sumBalance(tMoney13);
            startCoin = pokerStarsBase.startBalance(starsCoin13);
            finalCoin = pokerStarsBase.finalBalance(starsCoin13);
            totalCoin = pokerStarsBase.sumBalance(starsCoin13);

            withdrawal = pokerStarsBase.sumTransfer(amount13, pokerStarsRu.getWithdrawal(), "");
            sent = pokerStarsBase.sumTransfer(amount13, pokerStarsRu.getMoneySent(), "");
            received = pokerStarsBase.sumTransfer(amount13, pokerStarsRu.getMoneyReceived1(), pokerStarsEn.getMoneyReceived2());
            deposit = pokerStarsBase.sumTransfer(amount13, pokerStarsRu.getDeposit(), "");
        }

        if (language.equals("ru") && elements == 14) {
            pokerStarsBase = new PokerStarsRu(stringProcessor);

            gameSpin = pokerStarsBase.gameSpin();
            countTourneyRegistrationSpin = pokerStarsBase.countTourney(gameSpin, pokerStarsRu.getRegistration());
            countTourneyUnregistrationSpin = pokerStarsBase.countTourney(gameSpin, pokerStarsRu.getUnregistration());
            sumTourneyRegistrationSpin = pokerStarsBase.sumColumn(gameSpin, pokerStarsRu.getRegistration(), amount14);
            sumTourneyUnregistrationSpin = pokerStarsBase.sumColumn(gameSpin, pokerStarsRu.getUnregistration(), amount14);
            sumTourneyWonSpin = pokerStarsBase.sumColumn(gameSpin, pokerStarsRu.getWon(), amount14);
            sumRegistrationForTMoney = pokerStarsBase.sumColumn(gameSpin, pokerStarsRu.getRegistration(), tMoney14);
            countRegistrationByTicket = pokerStarsBase.countRegistrationByTicket(gameSpin, amount14, tMoney14);
            sumProfit = pokerStarsBase.sumProfit(gameSpin, amount14, tMoney14);
            sumBonus = pokerStarsBase.sumBonus(gameSpin, amount14, tMoney14);

            gameAnother = pokerStarsBase.gameAnother();
            countTourneyRegistrationAnother = pokerStarsBase.countTourney(gameAnother, pokerStarsRu.getRegistration());
            countTourneyUnregistrationAnother = pokerStarsBase.countTourney(gameAnother, pokerStarsRu.getUnregistration());
            sumTourneyRegistrationAnother = pokerStarsBase.sumColumn(gameAnother, pokerStarsRu.getRegistration(), amount14);
            sumTourneyUnregistrationAnother = pokerStarsBase.sumColumn(gameAnother, pokerStarsRu.getUnregistration(), amount14);
            sumTourneyWonAnother = pokerStarsBase.sumColumn(gameAnother, pokerStarsRu.getWon(), amount14);
            countReEntry = pokerStarsBase.countReEntry(gameAnother);
            sumReEntry = pokerStarsBase.sumReEntry(gameAnother, amount14);
            countWonTicket = pokerStarsBase.countWonTicket(gameAnother);
            sumKnockout = pokerStarsBase.sumKnockout(gameAnother, amount14);

            startBalance = pokerStarsBase.startBalance(balance14);
            finalBalance = pokerStarsBase.finalBalance(balance14);
            startTMoney = pokerStarsBase.startBalance(tMoney14);
            finalTMoney = pokerStarsBase.finalBalance(tMoney14);
            totalTMoney = pokerStarsBase.sumBalance(tMoney14);
            startCoin = pokerStarsBase.startBalance(starsCoin14);
            finalCoin = pokerStarsBase.finalBalance(starsCoin14);
            totalCoin = pokerStarsBase.sumBalance(starsCoin14);

            withdrawal = pokerStarsBase.sumTransfer(amount14, pokerStarsRu.getWithdrawal(), "");
            sent = pokerStarsBase.sumTransfer(amount14, pokerStarsRu.getMoneySent(), "");
            received = pokerStarsBase.sumTransfer(amount14, pokerStarsRu.getMoneyReceived1(), pokerStarsEn.getMoneyReceived2());
            deposit = pokerStarsBase.sumTransfer(amount14, pokerStarsRu.getDeposit(), "");
        }

    }

}
