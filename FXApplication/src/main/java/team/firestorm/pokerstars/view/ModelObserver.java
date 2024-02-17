package team.firestorm.pokerstars.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import team.firestorm.pokerstars.controller.TabController;
import team.firestorm.pokerstars.model.Model;

import java.util.ArrayList;

public class ModelObserver {
    @Getter
    private final ObservableList<Model> observableListSpin = FXCollections.observableArrayList(new ArrayList<>());
    @Getter
    private final ObservableList<Model> observableListMTT = FXCollections.observableArrayList(new ArrayList<>());
    @Getter
    private final ObservableList<Model> observableListCash = FXCollections.observableArrayList(new ArrayList<>());

    public void clear(TabController tabController) {
        tabController.getTableViewSpin().getColumns().clear();
        tabController.getTableViewMTT().getColumns().clear();
        tabController.getTableViewCash().getColumns().clear();
        observableListSpin.clear();
        observableListMTT.clear();
        observableListCash.clear();
    }
}
