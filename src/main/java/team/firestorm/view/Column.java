package team.firestorm.view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.AllArgsConstructor;
import team.firestorm.controller.Controller;
import team.firestorm.model.pokerstars.PokerStarsBuilder;
import team.firestorm.model.pokerstars.PokerStarsGameModel;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
public class Column {
    private final Controller controller;

    private final PokerStarsBuilder pokerStarsBuilder;

    public void buildColumnSpin() {
        TableColumn<PokerStarsGameModel, Set<String>> game = new TableColumn<>("Spin Game Limit");
        setCellValueGame(controller.getTableViewSpin(), game);

        TableColumn<PokerStarsGameModel, Integer> countRegistration = new TableColumn<>("Count\nRegistration");
        setCellValueInteger(controller.getTableViewSpin(), pokerStarsBuilder.getCountTourneyRegistrationSpin(), countRegistration);

        TableColumn<PokerStarsGameModel, BigDecimal> sumRegistration = new TableColumn<>("Sum\nRegistration");
        setCellValueBigDecimal(controller.getTableViewSpin(), pokerStarsBuilder.getSumTourneyRegistrationSpin(), sumRegistration);

        TableColumn<PokerStarsGameModel, Integer> countUnregistration = new TableColumn<>("Count\nUnregistration");
        setCellValueInteger(controller.getTableViewSpin(), pokerStarsBuilder.getCountTourneyUnregistrationSpin(), countUnregistration);

        TableColumn<PokerStarsGameModel, BigDecimal> sumUnregistration = new TableColumn<>("Sum\nUnregistration");
        setCellValueBigDecimal(controller.getTableViewSpin(), pokerStarsBuilder.getSumTourneyUnregistrationSpin(), sumUnregistration);

        TableColumn<PokerStarsGameModel, BigDecimal> sumWon = new TableColumn<>("Sum\nNetWon");
        setCellValueBigDecimal(controller.getTableViewSpin(), pokerStarsBuilder.getSumTourneyWonSpin(), sumWon);

        TableColumn<PokerStarsGameModel, BigDecimal> sumRegistrationForTMoney = new TableColumn<>("Sum Registration\nFor TMoney");
        setCellValueBigDecimal(controller.getTableViewSpin(), pokerStarsBuilder.getSumRegistrationForTMoney(), sumRegistrationForTMoney);

        TableColumn<PokerStarsGameModel, Integer> countRegistrationByTicket = new TableColumn<>("Count Registration\nBy Ticket");
        setCellValueInteger(controller.getTableViewSpin(), pokerStarsBuilder.getCountRegistrationByTicket(), countRegistrationByTicket);

        TableColumn<PokerStarsGameModel, BigDecimal> sumProfit = new TableColumn<>("Sum\nProfit");
        setCellValueBigDecimal(controller.getTableViewSpin(), pokerStarsBuilder.getSumProfit(), sumProfit);

        TableColumn<PokerStarsGameModel, BigDecimal> sumBonus = new TableColumn<>("Sum\nBonus");
        setCellValueBigDecimal(controller.getTableViewSpin(), pokerStarsBuilder.getSumBonus(), sumBonus);
    }

    public void buildColumnAnother() {
        TableColumn<PokerStarsGameModel, Set<String>> game = new TableColumn<>("Another Game Limit");
        setCellValueGame(controller.getTableViewAnother(), game);

        TableColumn<PokerStarsGameModel, Integer> countRegistration = new TableColumn<>("Count\nRegistration");
        setCellValueInteger(controller.getTableViewAnother(), pokerStarsBuilder.getCountTourneyRegistrationAnother(), countRegistration);

        TableColumn<PokerStarsGameModel, BigDecimal> sumRegistration = new TableColumn<>("Sum\nRegistration");
        setCellValueBigDecimal(controller.getTableViewAnother(), pokerStarsBuilder.getSumTourneyRegistrationAnother(), sumRegistration);

        TableColumn<PokerStarsGameModel, Integer> countUnregistration = new TableColumn<>("Count\nUnregistration");
        setCellValueInteger(controller.getTableViewAnother(), pokerStarsBuilder.getCountTourneyUnregistrationAnother(), countUnregistration);

        TableColumn<PokerStarsGameModel, BigDecimal> sumUnregistration = new TableColumn<>("Sum\nUnregistration");
        setCellValueBigDecimal(controller.getTableViewAnother(), pokerStarsBuilder.getSumTourneyUnregistrationAnother(), sumUnregistration);

        TableColumn<PokerStarsGameModel, BigDecimal> sumWon = new TableColumn<>("Sum\nNetWon");
        setCellValueBigDecimal(controller.getTableViewAnother(), pokerStarsBuilder.getSumTourneyWonAnother(), sumWon);

        TableColumn<PokerStarsGameModel, Integer> countReEntry = new TableColumn<>("Count\nRe-Entry");
        setCellValueInteger(controller.getTableViewAnother(), pokerStarsBuilder.getCountReEntry(), countReEntry);

        TableColumn<PokerStarsGameModel, BigDecimal> sumReEntry = new TableColumn<>("Sum\nRe-Entry");
        setCellValueBigDecimal(controller.getTableViewAnother(), pokerStarsBuilder.getSumReEntry(), sumReEntry);

        TableColumn<PokerStarsGameModel, Integer> countWonTicket = new TableColumn<>("Count\nWon Tickets");
        setCellValueInteger(controller.getTableViewAnother(), pokerStarsBuilder.getCountWonTicket(), countWonTicket);

        TableColumn<PokerStarsGameModel, BigDecimal> sumKnockout = new TableColumn<>("Sum\nKnockout");
        setCellValueBigDecimal(controller.getTableViewAnother(), pokerStarsBuilder.getSumKnockout(), sumKnockout);
    }

    private void setCellValueGame(TableView<PokerStarsGameModel> table, TableColumn<PokerStarsGameModel, Set<String>> game) {
        game.setCellValueFactory(cellData -> {
            PokerStarsGameModel model = cellData.getValue();
            return new SimpleObjectProperty<>(model.getGame());
        });
        game.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Set<String> item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (String game : item) {
                        sb.append(game).append("\n");
                    }
                    setText(sb.toString());
                }
            }
        });
        table.getColumns().add(game);
    }

    private void setCellValueInteger(TableView<PokerStarsGameModel> table, Map<String, Integer> buyIn, TableColumn<PokerStarsGameModel, Integer> column) {
        column.setCellValueFactory(cellData -> {
            PokerStarsGameModel model = cellData.getValue();
            String stringReg = model.getGame().iterator().next();
            int count = buyIn.getOrDefault(stringReg, 0);
            return new SimpleObjectProperty<>(count);
        });
        table.getColumns().add(column);
    }

    private void setCellValueBigDecimal(TableView<PokerStarsGameModel> tableView, Map<String, BigDecimal> map, TableColumn<PokerStarsGameModel, BigDecimal> tableColumn) {
        tableColumn.setCellValueFactory(cellData -> {
            PokerStarsGameModel model = cellData.getValue();
            String sumRegistration = model.getGame().iterator().next();
            BigDecimal sum = map.getOrDefault(sumRegistration, BigDecimal.ZERO);
            return new SimpleObjectProperty<>(sum);
        });
        tableView.getColumns().add(tableColumn);
    }

}
