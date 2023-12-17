package team.firestorm.pokerstars.view;

import lombok.AllArgsConstructor;
import team.firestorm.pokerstars.controller.TabController;
import team.firestorm.pokerstars.model.Model;

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

        tabController.getCasino().setText(model.getCasino());
    }
}
