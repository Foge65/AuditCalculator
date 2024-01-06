package team.firestorm.pokerstars.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import team.firestorm.pokerstars.controller.TabController;
import team.firestorm.pokerstars.model.Model;

import java.util.ArrayList;
import java.util.List;

public class ModelObserver {
    private final List<Model> listModelSpin = new ArrayList<>();
    private final List<Model> listModelMTT = new ArrayList<>();
    private final List<Model> listModelCash = new ArrayList<>();
    @Getter
    private final ObservableList<Model> observableListSpin = FXCollections.observableArrayList(listModelSpin);
    @Getter
    private final ObservableList<Model> observableListMTT = FXCollections.observableArrayList(listModelMTT);
    @Getter
    private final ObservableList<Model> observableListCash = FXCollections.observableArrayList(listModelCash);

    public void clear(TabController tabController) {
        tabController.getTableViewSpin().getColumns().clear();
        tabController.getTableViewMTT().getColumns().clear();
        tabController.getTableViewCash().getColumns().clear();
        observableListSpin.clear();
        observableListMTT.clear();
        observableListCash.clear();
    }
}
