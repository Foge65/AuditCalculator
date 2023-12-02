package team.firestorm.pokerstars.view;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import team.firestorm.pokerstars.model.Model;

import java.util.Map;
import java.util.Set;

//TODO: Установить минимальный размер для всех столбцов
public interface ColumnBuilder {
    void buildColumnSpin();
    void buildColumnAnother();
    void setCellCheckBox(TableView<Model> table, TableColumn<Model, Boolean> pool);
    void setCellValueGame(TableView<Model> table, TableColumn<Model, Set<String>> game);
    <T> void setCellValueNumber(TableView<Model> table, Map<String, T> value, TableColumn<Model, T> column);
    void addGameToObservableList(Set<String> game, Map<String, ?> value, ObservableList<Model> observableList, TableView<Model> table);
}
