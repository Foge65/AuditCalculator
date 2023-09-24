package team.firestorm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import team.firestorm.model.pokerstars.LanguageDetection;

import java.io.IOException;

public class AuditCalculatorApp extends Application {

    public static void main(String[] args) {
        LanguageDetection.loadProfile();
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AuditCalculatorApp.class.getResource("FXMLAuditCalculator.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.getIcons().add(new Image("team/firestorm/FSAC.png"));
        stage.setTitle("FireStorm Team Audits Calculator");
        stage.setScene(scene);
        stage.show();
    }

}
