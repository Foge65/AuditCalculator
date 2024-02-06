package team.firestorm.pokerstars.view;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import lombok.RequiredArgsConstructor;
import team.firestorm.pokerstars.controller.TabController;
import team.firestorm.pokerstars.model.DefaultBonus;
import team.firestorm.pokerstars.model.DefaultProfit;
import team.firestorm.pokerstars.model.Model;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
public class PokerStarsColumnBuilder implements ColumnBuilder {
    private final TabController tabController;
    private final Model model;

    @Override
    public void buildColumnSpin() {
        TableView<Model> table = tabController.getTableViewSpin();

        TableColumn<Model, Boolean> pool = new TableColumn<>("Enable\nPool");
        setCellCheckBox(table, pool);

        TableColumn<Model, Set<String>> game = new TableColumn<>("Spin\nBuyIn");
        setCellValueGame(table, game);

        TableColumn<Model, Integer> countRegistration = new TableColumn<>("Count\nRegistration");
        setCellValueNumber(table, model.getCountRegistrationSpin(), countRegistration);

        TableColumn<Model, BigDecimal> sumRegistration = new TableColumn<>("Sum\nRegistration");
        setCellValueNumber(table, model.getSumRegistrationSpin(), sumRegistration);

        TableColumn<Model, Integer> countUnRegistration = new TableColumn<>("Count\nUnRegistration");
        setCellValueNumber(table, model.getCountUnRegistrationSpin(), countUnRegistration);

        TableColumn<Model, BigDecimal> sumUnRegistration = new TableColumn<>("Sum\nUnRegistration");
        setCellValueNumber(table, model.getSumUnRegistrationSpin(), sumUnRegistration);

        TableColumn<Model, BigDecimal> sumRegistrationPerTMoney = new TableColumn<>("Sum Registration\nPer TMoney");
        setCellValueNumber(table, model.getSumRegistrationForTMoneySpin(), sumRegistrationPerTMoney);

        TableColumn<Model, Integer> countRegistrationPerTicket = new TableColumn<>("Count Registration\nPer Ticket");
        setCellValueNumber(table, model.getCountRegistrationByTicketSpin(), countRegistrationPerTicket);

        TableColumn<Model, BigDecimal> sumWon = new TableColumn<>("Sum\nWon");
        setCellValueNumber(table, model.getSumWonSpin(), sumWon);

        TableColumn<Model, BigDecimal> sumProfit = new TableColumn<>("Sum\nProfit");
        setCellValueNumber(table, model.getSumProfitSpin(), sumProfit);

        TableColumn<Model, BigDecimal> sumBonus = new TableColumn<>("Sum\nBonus");
        setCellValueNumber(table, model.getSumBonusSpin(), sumBonus);

        setSizeColumn(table);
    }

    @Override
    public void buildColumnMTT() {
        TableView<Model> table = tabController.getTableViewMTT();

        TableColumn<Model, Set<String>> game = new TableColumn<>("MTT\nBuyIn");
        setCellValueGame(table, game);

        TableColumn<Model, Integer> countRegistration = new TableColumn<>("Count\nRegistration");
        setCellValueNumber(table, model.getCountRegistrationMTT(), countRegistration);

        TableColumn<Model, BigDecimal> sumRegistration = new TableColumn<>("Sum\nRegistration");
        setCellValueNumber(table, model.getSumRegistrationMTT(), sumRegistration);

        TableColumn<Model, Integer> countUnRegistration = new TableColumn<>("Count\nUnRegistration");
        setCellValueNumber(table, model.getCountUnRegistrationMTT(), countUnRegistration);

        TableColumn<Model, BigDecimal> sumUnRegistration = new TableColumn<>("Sum\nUnRegistration");
        setCellValueNumber(table, model.getSumUnRegistrationMTT(), sumUnRegistration);

        TableColumn<Model, Integer> countRegistrationByTMoney = new TableColumn<>("Count Registration\nPer TMoney");
        setCellValueNumber(table, model.getCountRegistrationByTMoneyMTT(), countRegistrationByTMoney);

        TableColumn<Model, BigDecimal> sumRegistrationByTMoney = new TableColumn<>("Sum Registration\nPer TMoney");
        setCellValueNumber(table, model.getSumRegistrationByTMoneyMTT(), sumRegistrationByTMoney);

        TableColumn<Model, Integer> countUnRegistrationByTMoney = new TableColumn<>("Count UnRegistration\nPer TMoney");
        setCellValueNumber(table, model.getCountUnRegistrationByTMoneyMTT(), countUnRegistrationByTMoney);

        TableColumn<Model, BigDecimal> sumUnRegistrationByTMoney = new TableColumn<>("Sum UnRegistration\nPer TMoney");
        setCellValueNumber(table, model.getSumUnRegistrationByTMoneyMTT(), sumUnRegistrationByTMoney);

        TableColumn<Model, Integer> countReEntry = new TableColumn<>("Count\nRe-Entry");
        setCellValueNumber(table, model.getCountReEntryMTT(), countReEntry);

        TableColumn<Model, BigDecimal> sumReEntry = new TableColumn<>("Sum\nRe-Entry");
        setCellValueNumber(table, model.getSumReEntryMTT(), sumReEntry);

        TableColumn<Model, BigDecimal> sumKnockout = new TableColumn<>("Sum\nKnockout");
        setCellValueNumber(table, model.getSumKnockoutMTT(), sumKnockout);

        TableColumn<Model, BigDecimal> sumInterim = new TableColumn<>("Sum\nInterim");
        setCellValueNumber(table, model.getSumInterimMTT(), sumInterim);

        TableColumn<Model, BigDecimal> sumWon = new TableColumn<>("Sum\nWon");
        setCellValueNumber(table, model.getSumWonMTT(), sumWon);

        TableColumn<Model, BigDecimal> sumProfit = new TableColumn<>("Sum\nProfit");
        setCellValueNumber(table, model.getSumProfitMTT(), sumProfit);

        setSizeColumn(table);
    }

    @Override
    public void buildColumnCash() {
        TableView<Model> table = tabController.getTableViewCash();

        TableColumn<Model, Set<String>> game = new TableColumn<>("Cash\nGame");
        setCellValueGame(table, game);

        TableColumn<Model, Integer> countRegistration = new TableColumn<>("Count\nRegistration");
        setCellValueNumber(table, model.getCountRegistrationCash(), countRegistration);

        TableColumn<Model, BigDecimal> sumRegistration = new TableColumn<>("Sum\nRegistration");
        setCellValueNumber(table, model.getSumRegistrationCash(), sumRegistration);

        TableColumn<Model, Integer> countUnRegistration = new TableColumn<>("Count\nUnRegistration");
        setCellValueNumber(table, model.getCountUnRegistrationCash(), countUnRegistration);

        TableColumn<Model, BigDecimal> sumUnRegistration = new TableColumn<>("Sum\nUnRegistration");
        setCellValueNumber(table, model.getSumUnRegistrationCash(), sumUnRegistration);

        TableColumn<Model, Integer> countRebuy = new TableColumn<>("Count\nAutoRebuy");
        setCellValueNumber(table, model.getCountRebuyCash(), countRebuy);

        TableColumn<Model, BigDecimal> sumRebuy = new TableColumn<>("Sum\nAutoRebuy");
        setCellValueNumber(table, model.getSumRebuyCash(), sumRebuy);

        TableColumn<Model, BigDecimal> sumWon = new TableColumn<>("Sum\nWon");
        setCellValueNumber(table, model.getSumWonCash(), sumWon);

        setSizeColumn(table);
    }

    @Override
    public void setCellCheckBox(TableView<Model> table, TableColumn<Model, Boolean> poolColumn) {
        DefaultProfit defaultProfit = new DefaultProfit(model);
        DefaultBonus defaultBonus = new DefaultBonus(model);
        poolColumn.setCellValueFactory(cell -> new SimpleBooleanProperty());
        poolColumn.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Model, Boolean> call(TableColumn<Model, Boolean> poolColumn) {
                return new TableCell<>() {
                    private final CheckBox checkBox = new CheckBox();

                    @Override
                    protected void updateItem(Boolean item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(checkBox);
                            if (getTableRow() != null && getTableRow().getItem() != null) {
                                checkBox.setSelected(model.getCheckBoxState().getOrDefault(getKey(), null));
                                tabController.addListenerToCheckBox(getKey(), checkBox, defaultProfit.getProfit(), defaultBonus.getBonus());
                            }
                        }
                    }

                    private String getKey() {
                        return getTableRow().getItem().getGameSpin().stream().findFirst().orElse(null);
                    }
                };
            }
        });
        table.getColumns().add(poolColumn);
    }

    @Override
    public void setCellValueGame(TableView<Model> table, TableColumn<Model, Set<String>> game) {
        game.setCellValueFactory(cellData -> {
            Model model = cellData.getValue();
            return new SimpleObjectProperty<>(model.getGameSpin());
        });
        game.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Model, Set<String>> call(TableColumn<Model, Set<String>> column) {
                return new TableCell<>() {
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
                };
            }
        });
        table.getColumns().add(game);
    }

    @Override
    public <T> void setCellValueNumber(TableView<Model> table, Map<String, T> value, TableColumn<Model, T> column) {
        column.setCellValueFactory(cellData -> {
            Model model = cellData.getValue();
            String game = model.getGameSpin().iterator().next();
            T result = value.getOrDefault(game, null);
            return new SimpleObjectProperty<>(result);
        });
        table.getColumns().add(column);
    }

    @Override
    public void addGameToObservableList(Set<String> game, ObservableList<Model> observableList, TableView<Model> table) {
        for (String buyIn : game) {
            Model model = new Model(Collections.singleton(buyIn));
            observableList.add(model);
        }
        table.setItems(observableList);
    }

    @Override
    public void setSizeColumn(TableView<Model> tableView) {
        for (TableColumn<Model, ?> tableColumn : tableView.getColumns()) {
            if (tableColumn.getText().contains("Enable\nPool")) {
                tableColumn.setMinWidth(40.0);
                tableColumn.setPrefWidth(40.0);
            } else if (tableColumn.getText().contains("BuyIn") || tableColumn.getText().contains("Cash\nGame")) {
                tableColumn.setMinWidth(150.0);
                tableColumn.setPrefWidth(200.0);
            } else {
                tableColumn.setMinWidth(50.0);
                tableColumn.setPrefWidth(75.0);
            }
        }
    }
}
