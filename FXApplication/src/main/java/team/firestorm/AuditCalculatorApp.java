package team.firestorm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class AuditCalculatorApp extends Application {

    public static void main(String[] args) {
        ErrorLog.errorsToFile();
        Application.launch();
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
