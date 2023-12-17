package team.firestorm.pokerstars.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class Model {
    private Set<String> gameSpin;
    private Map<String, Integer> countRegistrationSpin;
    private Map<String, BigDecimal> sumRegistrationSpin;
    private Map<String, Integer> countUnRegistrationSpin;
    private Map<String, BigDecimal> sumUnRegistrationSpin;
    private Map<String, BigDecimal> sumNetWonSpin;
    private Map<String, BigDecimal> sumRegistrationForTMoneySpin;
    private Map<String, Integer> countRegistrationByTicketSpin;
    private Map<String, BigDecimal> sumProfitSpin;
    private Map<String, BigDecimal> sumBonusSpin;

    private Map<String, BigDecimal> sumProfitPoolSpin;
    private Map<String, BigDecimal> sumBonusPoolSpin;

    private Map<String, Boolean> pool;

    private Set<String> gameAnother;
    private Map<String, Integer> countRegistrationAnother;
    private Map<String, BigDecimal> sumRegistrationAnother;
    private Map<String, Integer> countUnRegistrationAnother;
    private Map<String, BigDecimal> sumUnRegistrationAnother;
    private Map<String, BigDecimal> sumNetWonAnother;
    private Map<String, Integer> countReEntryAnother;
    private Map<String, BigDecimal> sumReEntryAnother;
    private Map<String, BigDecimal> sumKnockoutAnother;

    private LocalDate dateFrom;
    private LocalDate dateTo;

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
    private String cash;

    private String casino;
    private String omaha;
    private String other;

    public Model(Set<String> game) {
        gameSpin = game;
        gameAnother = game;
    }

    public void setModel(ModelBuilderFromCsvFile modelBuilderFromCsvFile) {
        setGameSpin(modelBuilderFromCsvFile.getGameSpin());
        setCountRegistrationSpin(modelBuilderFromCsvFile.getCountRegistrationSpin());
        setSumRegistrationSpin(modelBuilderFromCsvFile.getSumRegistrationSpin());
        setCountUnRegistrationSpin(modelBuilderFromCsvFile.getCountUnRegistrationSpin());
        setSumUnRegistrationSpin(modelBuilderFromCsvFile.getSumUnRegistrationSpin());
        setSumNetWonSpin(modelBuilderFromCsvFile.getSumNetWonSpin());
        setSumRegistrationForTMoneySpin(modelBuilderFromCsvFile.getSumRegistrationForTMoneySpin());
        setCountRegistrationByTicketSpin(modelBuilderFromCsvFile.getCountRegistrationByTicketSpin());
        setSumProfitSpin(modelBuilderFromCsvFile.getSumProfitSpin());
        setSumBonusSpin(modelBuilderFromCsvFile.getSumBonusSpin());

        setSumProfitPoolSpin(modelBuilderFromCsvFile.getSumProfitPoolSpin());
        setSumBonusPoolSpin(modelBuilderFromCsvFile.getSumBonusPoolSpin());

        setPool(modelBuilderFromCsvFile.getPool());

        setGameAnother(modelBuilderFromCsvFile.getGameAnother());
        setCountRegistrationAnother(modelBuilderFromCsvFile.getCountRegistrationAnother());
        setSumRegistrationAnother(modelBuilderFromCsvFile.getSumRegistrationAnother());
        setCountUnRegistrationAnother(modelBuilderFromCsvFile.getCountUnRegistrationAnother());
        setSumUnRegistrationAnother(modelBuilderFromCsvFile.getSumUnRegistrationAnother());
        setSumNetWonAnother(modelBuilderFromCsvFile.getSumNetWonAnother());
        setCountReEntryAnother(modelBuilderFromCsvFile.getCountReEntryAnother());
        setSumReEntryAnother(modelBuilderFromCsvFile.getSumReEntryAnother());
        setSumKnockoutAnother(modelBuilderFromCsvFile.getSumKnockoutAnother());

        setDateFrom(modelBuilderFromCsvFile.getDateFrom());
        setDateTo(modelBuilderFromCsvFile.getDateTo());

        setStartBalance(modelBuilderFromCsvFile.getStartBalance());
        setFinalBalance(modelBuilderFromCsvFile.getFinalBalance());

        setStartTMoney(modelBuilderFromCsvFile.getStartTMoney());
        setFinalTMoney(modelBuilderFromCsvFile.getFinalTMoney());

        setStartCoin(modelBuilderFromCsvFile.getStartCoin());
        setFinalCoin(modelBuilderFromCsvFile.getFinalCoin());

        setWithdrawal(modelBuilderFromCsvFile.getWithdrawal());
        setSent(modelBuilderFromCsvFile.getSent());
        setReceived(modelBuilderFromCsvFile.getReceived());
        setDeposit(modelBuilderFromCsvFile.getDeposit());

        setChestReward(modelBuilderFromCsvFile.getChestReward());
        setExchangeCoin(modelBuilderFromCsvFile.getExchangeCoin());
        setCash(modelBuilderFromCsvFile.getCash());

        setCasino(modelBuilderFromCsvFile.getCasino());
        setOmaha(modelBuilderFromCsvFile.getOmaha());
        setOther(modelBuilderFromCsvFile.getOther());
    }
}
