package team.firestorm.pokerstars.view;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import lombok.Getter;
import team.firestorm.pokerstars.controller.TabController;
import team.firestorm.pokerstars.model.Model;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public abstract class ColumnBuilderBase implements ColumnBuilder {
    private final TabController tabController;
    private final Model model;
    @Getter
    private CheckBox checkBox;

    public ColumnBuilderBase(TabController tabController, Model model) {
        this.tabController = tabController;
        this.model = model;
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
        pool.setCellValueFactory(cellData -> new SimpleBooleanProperty(false));
        pool.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Model, Boolean> call(TableColumn<Model, Boolean> column) {
                return new TableCell<>() {
                    {
                        checkBox = new CheckBox();
                        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                                tabController.onClickPoolSetting();
                        });
                    }

                    @Override
                    protected void updateItem(Boolean item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setGraphic(null);
                        } else {
                            checkBox.setSelected(item);
                            setGraphic(checkBox);
                        }
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

    public void addListenerToTable(TableView<Model> table) {
        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        });
    }

    public <T> void setCellValueNumberCustom(TableView<Model> table, Map<String, T> value, TableColumn<Model, T> column) {
        table.getColumns().add(column);
    }
}
