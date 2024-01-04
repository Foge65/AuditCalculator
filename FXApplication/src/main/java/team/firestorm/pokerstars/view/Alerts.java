package team.firestorm.pokerstars.view;

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
}
