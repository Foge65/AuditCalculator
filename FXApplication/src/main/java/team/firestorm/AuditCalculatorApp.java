package team.firestorm;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import team.firestorm.updateapp.UpdateController;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AuditCalculatorApp extends Application {

    public static void main(String[] args) {
        errorsToFile();

        updateVersion();

        Application.launch();
    }

    private static void errorsToFile() {
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            try (PrintWriter printWriter = new PrintWriter(new FileWriter("errors.log", true))) {
                printWriter.println("=== Uncaught Exception ===");
                throwable.printStackTrace(printWriter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static void updateVersion() {
        Platform.runLater(() -> {
            UpdateController updateController = new UpdateController();
            String serverVersion = updateController.fetchVersionFromServer();
            String localVersion = updateController.currentVersionFromFile();
            if (!serverVersion.equals(localVersion)) {
                updateController.downloadUpdate();
                updateController.updateVersionInFile(serverVersion);
            }
        });
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
