package team.firestorm.pokerstars.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import lombok.Getter;
import team.firestorm.pokerstars.model.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

@Getter
public class ModelObserverTransferWindow {
    private final ObservableList<Model> observableListTransfer = FXCollections.observableArrayList(new ArrayList<>());

    public void addGameToObservableList(Set<String> dates, TableView<Model> table) {
        for (String date : dates) {
            Model model = new Model(Collections.singleton(date));
            observableListTransfer.add(model);
        }
        table.setItems(observableListTransfer);
    }

    public void clear() {
        observableListTransfer.clear();
    }
}
