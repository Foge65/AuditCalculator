package team.firestorm.pokerstars.view;

import lombok.RequiredArgsConstructor;
import team.firestorm.pokerstars.controller.TabController;
import team.firestorm.pokerstars.model.Model;

import java.math.BigDecimal;
import java.util.Map;

@RequiredArgsConstructor
public class TextBuilder {
    private final TabController tabController;
    private final Model model;

    public void setText() {
        tabController.getStartBalance().setText(model.getStartBalance());
        tabController.getFinalBalance().setText(model.getFinalBalance());

        tabController.getStartTMoney().setText(model.getStartTMoney());
        tabController.getFinalTMoney().setText(model.getFinalTMoney());

        tabController.getStartCoin().setText(model.getStartCoin());
        tabController.getFinalCoin().setText(model.getFinalCoin());

        tabController.getWithdrawal().setText(model.getWithdrawal());
        tabController.getTransferSent().setText(model.getSent());
        tabController.getTransferReceived().setText(model.getReceived());
        tabController.getDeposit().setText(model.getDeposit());

        tabController.getChestReward().setText(model.getChestReward());
        tabController.getExchangeCoin().setText(model.getExchangeCoin());
        tabController.getOtherBonus().setText(model.getOtherBonus());
        tabController.getCasino().setText(model.getCasino());

        tabController.getCountSpins().setText(String.valueOf(model.getCountRegistrationSpinWithoutUnRegistration()));
        tabController.getTotalProfitSpin().setText(getSumValuesFromMap(model.getSumProfitSpin()));
        tabController.getTotalBonusSpin().setText(getSumValuesFromMap(model.getSumBonusSpin()));

        tabController.getTotalProfitMTT().setText(getSumValuesFromMap(model.getSumProfitMTT()));
        tabController.getTotalProfitCash().setText(getSumValuesFromMap(model.getSumWonCash()));

        tabController.getTotalAllBonuses().setText(sumTotalBonuses());
    }

    private String getSumValuesFromMap(Map<String, BigDecimal> value) {
        return value.values().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add).toString();
    }

    public String sumTotalBonuses() {
        double chest = Double.parseDouble(model.getChestReward());
        double exchangeCoin = Double.parseDouble(model.getExchangeCoin());
        double otherBonus = Double.parseDouble(model.getOtherBonus());
        double bonus = Double.parseDouble(getSumValuesFromMap(model.getSumBonusSpin()));
        double bonusPool = Double.parseDouble(getSumValuesFromMap(model.getSumBonusPoolSpin()));
        Map<String, Boolean> checkBoxStateMap = model.getCheckBoxState();
        if (checkBoxStateMap.containsValue(false)) {
            return String.valueOf(chest + exchangeCoin + otherBonus + bonus);
        } else {
            return String.valueOf(chest + exchangeCoin + otherBonus + bonusPool);
        }
    }
}
