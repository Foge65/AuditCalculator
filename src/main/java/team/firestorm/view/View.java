package team.firestorm.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import team.firestorm.controller.Controller;
import team.firestorm.model.pokerstars.PokerStarsBuilder;
import team.firestorm.model.pokerstars.PokerStarsGameModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class View {
    private final Controller controller;
    private final PokerStarsBuilder pokerStarsBuilder;
    private final Column column;
    private final Text text;
    private final DateSelect dateFrom;
    private final DateSelect dateTo;

    private final List<PokerStarsGameModel> listModelSpinGame = new ArrayList<>();
    private final List<PokerStarsGameModel> listModelAnotherGame = new ArrayList<>();
    private final ObservableList<PokerStarsGameModel> observableListSpinGame = FXCollections.observableArrayList(listModelSpinGame);
    private final ObservableList<PokerStarsGameModel> observableListAnotherGame = FXCollections.observableArrayList(listModelAnotherGame);

    public View(Controller controller, PokerStarsBuilder pokerStarsBuilder) {
        this.controller = controller;
        this.pokerStarsBuilder = pokerStarsBuilder;

        controller.getTableViewSpin().getColumns().clear();
        controller.getTableViewAnother().getColumns().clear();
        observableListSpinGame.clear();
        observableListAnotherGame.clear();

        column = new Column(controller, pokerStarsBuilder);
        column.buildColumnSpin();
        column.buildColumnAnother();

        setValueTable(pokerStarsBuilder.getGameSpin(), observableListSpinGame, controller.getTableViewSpin());
        setValueTable(pokerStarsBuilder.getGameAnother(), observableListAnotherGame, controller.getTableViewAnother());

        text = new Text(controller, pokerStarsBuilder);
        text.setText();

        dateFrom = new DateSelect(controller);
        dateFrom.setDateFrom(pokerStarsBuilder.getDateFrom());

        dateTo = new DateSelect(controller);
        dateTo.setDateTo(pokerStarsBuilder.getDateTo());
    }

    private void setValueTable(Set<String> buyIns, ObservableList<PokerStarsGameModel> observableList, TableView<PokerStarsGameModel> table) {
        for (String buyIn : buyIns) {
            PokerStarsGameModel model = new PokerStarsGameModel(Collections.singleton(buyIn));
            observableList.add(model);
        }
        table.setItems(observableList);
    }

}
