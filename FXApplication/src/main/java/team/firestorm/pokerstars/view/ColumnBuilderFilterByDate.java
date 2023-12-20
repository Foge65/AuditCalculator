package team.firestorm.pokerstars.view;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import team.firestorm.pokerstars.controller.TabController;
import team.firestorm.pokerstars.model.Model;

import java.math.BigDecimal;
import java.util.Set;

public class ColumnBuilderFilterByDate extends ColumnBuilderBase {
    private final TabController tabController;
    private final Model model;

    public ColumnBuilderFilterByDate(TabController tabController, Model model) {
        super(tabController, model);
        this.tabController = tabController;
        this.model = model;
    }

    @Override
    public void buildColumnSpin() {
        TableView<Model> table = tabController.getTableViewSpin();

        TableColumn<Model, Set<String>> game = new TableColumn<>("Spin\nGame");
        setCellValueGame(table, game);

        TableColumn<Model, Integer> countRegistration = new TableColumn<>("Count\nRegistration");
        setCellValueNumber(table, model.getCountRegistrationSpin(), countRegistration);

        TableColumn<Model, BigDecimal> sumRegistration = new TableColumn<>("Sum\nRegistration");
        setCellValueNumber(table, model.getSumRegistrationSpin(), sumRegistration);

        TableColumn<Model, Integer> countUnRegistration = new TableColumn<>("Count\nUnRegistration");
        setCellValueNumber(table, model.getCountUnRegistrationSpin(), countUnRegistration);

        TableColumn<Model, BigDecimal> sumUnRegistration = new TableColumn<>("Sum\nUnRegistration");
        setCellValueNumber(table, model.getSumUnRegistrationSpin(), sumUnRegistration);

        TableColumn<Model, BigDecimal> sumNetWon = new TableColumn<>("Sum\nNetWon");
        setCellValueNumber(table, model.getSumNetWonSpin(), sumNetWon);

        TableColumn<Model, BigDecimal> sumRegistrationForTMoney = new TableColumn<>("Sum Registration\nFor TMoney");
        setCellValueNumber(table, model.getSumRegistrationForTMoneySpin(), sumRegistrationForTMoney);

        TableColumn<Model, Integer> countRegistrationByTicket = new TableColumn<>("Count Registration\nBy Ticket");
        setCellValueNumber(table, model.getCountRegistrationByTicketSpin(), countRegistrationByTicket);

        TableColumn<Model, BigDecimal> sumProfit = new TableColumn<>("Sum\nProfit");
        setCellValueNumber(table, model.getSumProfitSpin(), sumProfit);

        TableColumn<Model, BigDecimal> sumBonus = new TableColumn<>("Sum\nBonus");
        setCellValueNumber(table, model.getSumBonusSpin(), sumBonus);

        TableColumn<Model, Boolean> pool = new TableColumn<>("Enable\nPool");
        setCellCheckBox(table, pool);
    }
}
