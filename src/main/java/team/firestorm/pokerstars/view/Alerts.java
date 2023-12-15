package team.firestorm.pokerstars.view;

import javafx.scene.control.Alert;

public class Alerts {
    public static void unknown() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please, contact with developer");
        alert.showAndWait();
    }
}
