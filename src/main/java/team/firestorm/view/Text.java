package team.firestorm.view;

import lombok.AllArgsConstructor;
import team.firestorm.controller.Controller;
import team.firestorm.model.pokerstars.PokerStarsBuilder;

@AllArgsConstructor
public class Text {
    private final Controller controller;
    private final PokerStarsBuilder pokerStarsBuilder;

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
