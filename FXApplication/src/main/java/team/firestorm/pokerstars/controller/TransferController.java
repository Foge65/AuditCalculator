package team.firestorm.pokerstars.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Modality;
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

    public Stage init(Parent parent) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Transfer Window");
        stage.getIcons().add(new Image("/team/firestorm/FSAC.png"));
        stage.setResizable(false);
        stage.setScene(new Scene(parent));
        return stage;
    }
}
