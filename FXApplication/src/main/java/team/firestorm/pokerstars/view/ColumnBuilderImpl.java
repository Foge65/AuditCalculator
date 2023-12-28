package team.firestorm.pokerstars.view;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.util.Callback;
import team.firestorm.pokerstars.controller.TabController;
import team.firestorm.pokerstars.model.Model;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ColumnBuilderImpl implements ColumnBuilder {
    private final TabController tabController;
    private final Model model;
    private final Map<String, CheckBox> checkBoxMap = new HashMap<>();

    public ColumnBuilderImpl(TabController tabController, Model model) {
        this.tabController = tabController;
        this.model = model;
    }

    @Override
    public void buildColumnSpin() {
        TableView<Model> table = tabController.getTableViewSpin();

        TableColumn<Model, Boolean> pool = new TableColumn<>("Enable\nPool");
        setCellCheckBox(table, pool);

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
    }

    @Override
    public void buildColumnAnother() {
        TableView<Model> table = tabController.getTableViewAnother();

        TableColumn<Model, Set<String>> game = new TableColumn<>("Another\nGame");
        setCellValueGame(table, game);

        TableColumn<Model, Integer> countRegistration = new TableColumn<>("Count\nRegistration");
        setCellValueNumber(table, model.getCountRegistrationAnother(), countRegistration);

        TableColumn<Model, BigDecimal> sumRegistration = new TableColumn<>("Sum\nRegistration");
        setCellValueNumber(table, model.getSumRegistrationAnother(), sumRegistration);

        TableColumn<Model, Integer> countUnRegistration = new TableColumn<>("Count\nUnRegistration");
        setCellValueNumber(table, model.getCountUnRegistrationAnother(), countUnRegistration);

        TableColumn<Model, BigDecimal> sumUnRegistration = new TableColumn<>("Sum\nUnRegistration");
        setCellValueNumber(table, model.getSumUnRegistrationAnother(), sumUnRegistration);

        TableColumn<Model, BigDecimal> sumNetWon = new TableColumn<>("Sum\nNetWon");
        setCellValueNumber(table, model.getSumNetWonAnother(), sumNetWon);

        TableColumn<Model, Integer> countReEntry = new TableColumn<>("Count\nRe-Entry");
        setCellValueNumber(table, model.getCountReEntryAnother(), countReEntry);

        TableColumn<Model, BigDecimal> sumReEntry = new TableColumn<>("Sum\nRe-Entry");
        setCellValueNumber(table, model.getSumReEntryAnother(), sumReEntry);

        TableColumn<Model, BigDecimal> sumKnockout = new TableColumn<>("Sum\nKnockout");
        setCellValueNumber(table, model.getSumKnockoutAnother(), sumKnockout);
    }

    @Override
    public void setCellCheckBox(TableView<Model> table, TableColumn<Model, Boolean> pool) {
        Map<String, BigDecimal> profitDefault = new HashMap<>(model.getSumProfitSpin());
        Map<String, BigDecimal> bonusDefault = new HashMap<>(model.getSumBonusSpin());
        pool.setCellValueFactory(cell -> new SimpleBooleanProperty());
        pool.setCellFactory(new Callback<>() {
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
                                checkBox.setSelected(getCheckBoxState().getOrDefault(getTableRow().getItem().getGameSpin().stream().findFirst().orElse(null), false));
                                addListenerToCheckBox();
                                checkBoxMap.put(getTableRow().getItem().getGameSpin().stream().findFirst().orElse(null), checkBox);
                            }
                        }
                    }

                    private void addListenerToCheckBox() {
                        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                            String buyIn = getTableRow().getItem().getGameSpin().stream().findFirst().orElse(null);
                            Map<String, Boolean> checkBoxStateMap = getCheckBoxState();
                            checkBoxStateMap.put(buyIn, newValue);
                            Map<String, BigDecimal> profit = model.getSumProfitSpin();
                            Map<String, BigDecimal> bonus = model.getSumBonusSpin();
                            Map<String, BigDecimal> profitPool = model.getSumProfitPoolSpin();
                            Map<String, BigDecimal> bonusPool = model.getSumBonusPoolSpin();
                            if (newValue) {
                                profit.put(buyIn, profitPool.get(buyIn));
                                bonus.put(buyIn, bonusPool.get(buyIn));
                                setNewTextValue(profit, tabController.getTotalProfitSpin());
                                setNewTextValue(bonus, tabController.getTotalBonusSpin());
                            } else {
                                profit.put(buyIn, profitDefault.get(buyIn));
                                bonus.put(buyIn, bonusDefault.get(buyIn));
                                setNewTextValue(profitDefault, tabController.getTotalProfitSpin());
                                setNewTextValue(bonusDefault, tabController.getTotalBonusSpin());
                            }
                            table.refresh();
                        });
                    }

                    private Map<String, Boolean> getCheckBoxState() {
                        Map<String, Boolean> poolMap = new HashMap<>();
                        for (Map.Entry<String, CheckBox> entry : checkBoxMap.entrySet()) {
                            poolMap.put(entry.getKey(), entry.getValue().isSelected());
                        }
                        return poolMap;
                    }

                    private void setNewTextValue(Map<String, BigDecimal> valueMap, Text field) {
                        BigDecimal[] result = {BigDecimal.ZERO};
                        valueMap.values().stream().map(BigDecimal.ZERO::add).forEach(sum -> result[0] = result[0].add(sum));
                        field.setText(result[0].toString());
                    }
                };
            }
        });
        table.getColumns().add(pool);
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
}
