package team.firestorm.pokerstars.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ControllerManager {
    public FXMLLoader getLoader(String path) {
        return new FXMLLoader(getClass().getResource(path));
    }

    public AnchorPane getAnchorPane(FXMLLoader fxmlLoader) {
        try {
            return fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T getTabController(FXMLLoader fxmlLoader) {
        return fxmlLoader.getController();
    }
}
