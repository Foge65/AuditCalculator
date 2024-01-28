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
    private Integer countRegistrationSpinWithoutUnregistration;

    private Set<String> gameMTT;
    private Map<String, Integer> countRegistrationMTT;
    private Map<String, BigDecimal> sumRegistrationMTT;
    private Map<String, Integer> countUnRegistrationMTT;
    private Map<String, BigDecimal> sumUnRegistrationMTT;
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
    private String otherBonus;
    private String casino;

    public Model(Set<String> game) {
        gameSpin = game;
        gameMTT = game;
        gameCash = game;
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
        setCountRegistrationSpinWithoutUnregistration(modelBuilderFromCsvFile.getCountRegistrationSpinWithoutUnregistration());

        setGameMTT(modelBuilderFromCsvFile.getGameMTT());
        setCountRegistrationMTT(modelBuilderFromCsvFile.getCountRegistrationMTT());
        setSumRegistrationMTT(modelBuilderFromCsvFile.getSumRegistrationMTT());
        setCountUnRegistrationMTT(modelBuilderFromCsvFile.getCountUnRegistrationMTT());
        setSumUnRegistrationMTT(modelBuilderFromCsvFile.getSumUnRegistrationMTT());
        setSumWonMTT(modelBuilderFromCsvFile.getSumWonMTT());
        setCountReEntryMTT(modelBuilderFromCsvFile.getCountReEntryMTT());
        setSumReEntryMTT(modelBuilderFromCsvFile.getSumReEntryMTT());
        setSumKnockoutMTT(modelBuilderFromCsvFile.getSumKnockoutMTT());
        setSumInterimMTT(modelBuilderFromCsvFile.getSumInterimMTT());
        setSumProfitMTT(modelBuilderFromCsvFile.getSumProfitMTT());

        setGameCash(modelBuilderFromCsvFile.getGameCash());
        setCountRegistrationCash(modelBuilderFromCsvFile.getCountRegistrationCash());
        setSumRegistrationCash(modelBuilderFromCsvFile.getSumRegistrationCash());
        setCountUnRegistrationCash(modelBuilderFromCsvFile.getCountUnRegistrationCash());
        setSumUnRegistrationCash(modelBuilderFromCsvFile.getSumUnRegistrationCash());
        setCountRebuyCash(modelBuilderFromCsvFile.getCountRebuyCash());
        setSumRebuyCash(modelBuilderFromCsvFile.getSumRebuyCash());
        setSumWonCash(modelBuilderFromCsvFile.getSumWonCash());

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
        setOtherBonus(modelBuilderFromCsvFile.getOtherBonus());
        setCasino(modelBuilderFromCsvFile.getCasino());
    }
}
