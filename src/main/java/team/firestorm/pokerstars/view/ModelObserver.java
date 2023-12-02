package team.firestorm.pokerstars.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import team.firestorm.pokerstars.controller.TabController;
import team.firestorm.pokerstars.model.Model;

import java.util.ArrayList;
import java.util.List;

public class ModelObserver {
    private final List<Model> listModelSpinGame = new ArrayList<>();
    private final List<Model> listModelAnotherGame = new ArrayList<>();
    @Getter
    private final ObservableList<Model> observableListSpinGame = FXCollections.observableArrayList(listModelSpinGame);
    @Getter
    private final ObservableList<Model> observableListAnotherGame = FXCollections.observableArrayList(listModelAnotherGame);

    public void clear(TabController tabController) {
        tabController.getTableViewSpin().getColumns().clear();
        tabController.getTableViewAnother().getColumns().clear();
        observableListSpinGame.clear();
        observableListAnotherGame.clear();
    }
}
