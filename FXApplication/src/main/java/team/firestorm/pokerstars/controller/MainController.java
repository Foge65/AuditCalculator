package team.firestorm.pokerstars.controller;

import javafx.collections.ListChangeListener;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

@Getter
public class MainController implements Initializable {
    @FXML
    public AnchorPane anchorTabContent;
    @FXML
    public Button btnCloseAllTab;
    @FXML
    private TabPane tabPane;
    @FXML
    private Button btnOpen;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabPane.getTabs().addListener((ListChangeListener<? super Tab>) change -> {
            while (change.next()) {
                updateAnchorVisibility();
            }
        });
        btnCloseAllTab.setOnAction(event -> {
            tabPane.getTabs().removeAll(tabPane.getTabs());
        });
    }

    private void updateAnchorVisibility() {
        anchorTabContent.setVisible(tabPane.getTabs().isEmpty());
    }

    @FXML
    private void onClickBtnOpen() {
        btnOpen.setOnMouseClicked(event -> {
                    btnOpen.setDisable(true);
                    FileChooser fileChooser = new FileChooser();
                    setFileChooserProperty(fileChooser);
                    Preferences preferences = Preferences.userNodeForPackage(getClass());
                    String lastDirectory = getLastDirectory(preferences);
                    fileChooser.setInitialDirectory(new File(lastDirectory));
                    List<File> files = getFileList(fileChooser, (Stage) tabPane.getScene().getWindow());
                    if (files != null) {
                        String selectedDirectory = files.get(0).getParent();
                        preferences.put("lastDirectory", selectedDirectory);
                        addTask(files);
                    }
                    btnOpen.setDisable(false);
                }
        );
    }

    private void setFileChooserProperty(FileChooser fileChooser) {
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV", "*.csv", "*.xlsx"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        fileChooser.setTitle("Please, select files");
    }

    private String getLastDirectory(Preferences preferences) {
        try {
            String lastDirectory = preferences.get("lastDirectory", System.getProperty("user.home"));
            File lastDirectoryFile = new File(lastDirectory);
            if (lastDirectoryFile.exists() && lastDirectoryFile.isDirectory()) {
                return lastDirectory;
            } else {
                return System.getProperty("user.home");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return System.getProperty("user.home");
        }
    }

    private List<File> getFileList(FileChooser fileChooser, Stage stage) {
        return fileChooser.showOpenMultipleDialog(stage);
    }

    private void addTask(List<File> files) {
        for (File file : files) {
            FXMLLoader fxmlLoader = getLoader("/team/firestorm/TabContent.fxml");
            AnchorPane anchorPane = getAnchorPane(fxmlLoader);
            TabController tabController = getTabController(fxmlLoader);
            Task<Void> task = new Task<>() {
                @Override
                protected Void call() {
                    tabController.buildTabData(file);
                    tabController.buildTabView(tabController);
                    return null;
                }

                @Override
                protected void succeeded() {
                    tabController.addTab(anchorPane, tabPane);
                    btnOpen.setDisable(false);
                }
            };
            new Thread(task).start();
        }
    }

    private FXMLLoader getLoader(String path) {
        return new FXMLLoader(getClass().getResource(path));
    }

    private AnchorPane getAnchorPane(FXMLLoader fxmlLoader) {
        try {
            return fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T getTabController(FXMLLoader fxmlLoader) {
        return fxmlLoader.getController();
    }
}
