package team.firestorm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import team.firestorm.pokerstars.controller.UpdateController;

import java.io.IOException;

public class AuditCalculatorApp extends Application {

    public static void main(String[] args) {
        updateVersion();

        Application.launch();
    }

    private static void updateVersion() {
        UpdateController updateController = new UpdateController();
        String fetchVersion = updateController.fetchVersion();
        String currentVersion = updateController.currentVersion();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/team/firestorm/Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("FireStorm Team Audit Calculator");
        stage.getIcons().add(new Image("/team/firestorm/FSAC.png"));
        stage.setMinHeight(600.0);
        stage.setMinWidth(950.0);
        stage.setScene(scene);
        stage.show();
    }
}
