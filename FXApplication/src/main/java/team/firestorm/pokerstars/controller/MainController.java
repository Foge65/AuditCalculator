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
import lombok.SneakyThrows;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

@Getter
public class MainController implements Initializable {
    @FXML
    private TabPane tabPane;
    @FXML
    public AnchorPane anchorTabContent;
    @FXML
    private Button btnOpen;
    @FXML
    public Button btnCloseAllTab;

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
                    String lastDirectory = preferences.get("lastDirectory", System.getProperty("user.home"));
                    fileChooser.setInitialDirectory(new File(lastDirectory));
                    List<File> files = getFileList(fileChooser);
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

    private List<File> getFileList(FileChooser fileChooser) {
        return fileChooser.showOpenMultipleDialog(new Stage());
    }

    private void addTask(List<File> files) {
        for (File file : files) {
            if (!files.isEmpty()) {
                FXMLLoader fxmlLoader = getLoader("/team/firestorm/TabContent.fxml");
                AnchorPane anchorPane = getAnchorPane(fxmlLoader);
                TabController tabController = getTabController(fxmlLoader);
                Task<Void> task = new Task<>() {
                    @Override
                    protected Void call() {
                        tabController.buildTabData(file);
                        return null;
                    }

                    @Override
                    protected void succeeded() {
                        tabController.buildTabView(tabController);
                        tabController.addTab(anchorPane, tabPane);
                        btnOpen.setDisable(false);
                    }
                };
                new Thread(task).start();
            }
        }
    }

    private FXMLLoader getLoader(String path) {
        return new FXMLLoader(getClass().getResource(path));
    }

    @SneakyThrows
    private AnchorPane getAnchorPane(FXMLLoader fxmlLoader) {
        return fxmlLoader.load();
    }

    private <T> T getTabController(FXMLLoader fxmlLoader) {
        return fxmlLoader.getController();
    }
}
