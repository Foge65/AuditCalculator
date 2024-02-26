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

    private Set<String> dates;
    private Map<String, String> withdrawalDetails;
    private Map<String, String> transferSentDetails;
    private Map<String, String> transferReceivedDetails;
    private Map<String, String> depositDetails;

    public Model(Set<String> set) {
        gameSpin = set;
        gameMTT = set;
        gameCash = set;
        dates = set;
    }
}
