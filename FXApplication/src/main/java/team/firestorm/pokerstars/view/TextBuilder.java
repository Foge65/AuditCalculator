package team.firestorm.pokerstars.view;

import lombok.AllArgsConstructor;
import team.firestorm.pokerstars.controller.TabController;
import team.firestorm.pokerstars.model.Model;

import java.math.BigDecimal;
import java.util.Map;

@AllArgsConstructor
public class TextBuilder {
    private TabController tabController;
    private Model model;

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

        tabController.getCountSpins().setText(model.getCountRegistrationSpinWithoutUnregistration().toString());
        tabController.getTotalProfitSpin().setText(getTextFromBigDecimal(model.getSumProfitSpin()));
        tabController.getTotalBonusSpin().setText(getTextFromBigDecimal(model.getSumBonusSpin()));
        tabController.getTotalAllBonus().setText(getTextFromTotalBonus());

        tabController.getTotalProfitMTT().setText(getTextFromProfitMTT());
        tabController.getTotalProfitCash().setText(getTextFromProfitCash());
    }

    private String getTextFromBigDecimal(Map<String, BigDecimal> valueMap) {
        BigDecimal[] result = {BigDecimal.ZERO};
        valueMap.values().stream()
                .map(BigDecimal.ZERO::add)
                .forEach(sum -> result[0] = result[0].add(sum));
        return result[0].toString();
    }

    private String getTextFromTotalBonus() {
        double chest = Double.parseDouble(model.getChestReward());
        double exchangeCoin = Double.parseDouble(model.getExchangeCoin());
        double otherBonus = Double.parseDouble(model.getOtherBonus());
        return String.valueOf(chest + exchangeCoin + otherBonus);
    }

    private String getTextFromProfitMTT() {
        return model.getSumProfitMTT().values().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add).toString();
    }

    private String getTextFromProfitCash() {
        return model.getSumProfitCash().values().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add).toString();
    }
}
