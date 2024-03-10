package team.firestorm.pokerstars.view;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public class Alerts {
    public static void unknownLanguage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Unsupported language detected");
        alert.setHeaderText(null);
        alert.setContentText("Please, re-order the audit in English or Russian!");
        alert.showAndWait();
    }

    public static void invalidDate() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect date range");
        alert.setHeaderText(null);
        alert.setContentText("Please, select the correct date!");
        alert.showAndWait();
    }

    public static void unknownError() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Unsupported exception");
            alert.setHeaderText(null);
            alert.setContentText("Unknown error. Please, contact with developer");
            alert.showAndWait();
        });
    }
}
