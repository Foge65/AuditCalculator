package team.firestorm.pokerstars.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import team.firestorm.pokerstars.model.Model;

@Getter
public class TransferController {
    @FXML
    private TableView<Model> transferTable = new TableView<>();
    @Setter
    private Stage stage;
}
