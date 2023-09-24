package team.firestorm.view;

import team.firestorm.controller.Controller;
import team.firestorm.model.pokerstars.PokerStarsBuilder;

public class Text {
    private Controller controller;
    private PokerStarsBuilder pokerStarsBuilder;

    public Text(Controller controller, PokerStarsBuilder pokerStarsBuilder) {
        this.controller = controller;
        this.pokerStarsBuilder = pokerStarsBuilder;
    }

    public void setText() {
        controller.getStartBalance().setText(pokerStarsBuilder.getStartBalance());
        controller.getFinalBalance().setText(pokerStarsBuilder.getFinalBalance());
        controller.getStartTMoney().setText(pokerStarsBuilder.getStartTMoney());
        controller.getFinalTMoney().setText(pokerStarsBuilder.getFinalTMoney());
        controller.getTotalTMoney().setText(pokerStarsBuilder.getTotalTMoney());
        controller.getStartCoin().setText(pokerStarsBuilder.getStartCoin());
        controller.getFinalCoin().setText(pokerStarsBuilder.getFinalCoin());
        controller.getTotalCoin().setText(pokerStarsBuilder.getTotalCoin());
        controller.getWithdrawal().setText(pokerStarsBuilder.getWithdrawal());
        controller.getTransferSent().setText(pokerStarsBuilder.getSent());
        controller.getTransferReceived().setText(pokerStarsBuilder.getReceived());
        controller.getDeposit().setText(pokerStarsBuilder.getDeposit());
    }

}
