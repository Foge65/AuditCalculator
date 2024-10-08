package team.firestorm.pokerstars.view;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import team.firestorm.pokerstars.model.Model;

import java.util.Map;
import java.util.Set;

public interface ColumnBuilder {
    void buildColumnSpin();

    void buildColumnMTT();

    void buildColumnCash();

    void setCellCheckBox(TableView<Model> table, TableColumn<Model, Boolean> pool);

    void setCellValueGame(TableView<Model> table, TableColumn<Model, Set<String>> game);

    <T> void setCellValueNumber(TableView<Model> table, Map<String, T> value, TableColumn<Model, T> column);

    void addGameToObservableList(Set<String> game, ObservableList<Model> observableList, TableView<Model> table);

    void setSizeColumn(TableView<Model> tableView);
}
