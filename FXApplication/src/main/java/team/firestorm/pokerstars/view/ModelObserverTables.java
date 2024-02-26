package team.firestorm.pokerstars.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import team.firestorm.pokerstars.model.Model;

import java.util.ArrayList;

@Getter
public class ModelObserverTables {
    private final ObservableList<Model> observableListSpin = FXCollections.observableArrayList(new ArrayList<>());
    private final ObservableList<Model> observableListMTT = FXCollections.observableArrayList(new ArrayList<>());
    private final ObservableList<Model> observableListCash = FXCollections.observableArrayList(new ArrayList<>());

    public void clear() {
        observableListSpin.clear();
        observableListMTT.clear();
        observableListCash.clear();
    }
}
