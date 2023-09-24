package team.firestorm.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.Getter;
import team.firestorm.model.pokerstars.PokerStarsCollector;
import team.firestorm.model.pokerstars.PokerStarsGameModel;
import team.firestorm.view.View;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

@Getter
public class Controller implements Initializable {
    @FXML
    private Button btnOpen;
    @FXML
    private DatePicker dateFrom;
    @FXML
    private DatePicker dateTo;
    @FXML
    private TableView<PokerStarsGameModel> tableViewSpin;
    @FXML
    private TableView<PokerStarsGameModel> tableViewAnother;
    @FXML
    private Text startBalance;
    @FXML
    private Text finalBalance;
    @FXML
    private Text startTMoney;
    @FXML
    private Text finalTMoney;
    @FXML
    private Text totalTMoney;
    @FXML
    private Text startCoin;
    @FXML
    private Text finalCoin;
    @FXML
    private Text totalCoin;
    @FXML
    private Text withdrawal;
    @FXML
    private Text transferSent;
    @FXML
    private Text transferReceived;
    @FXML
    private Text deposit;

    private static boolean fileChooserOpen = false;

    private static PokerStarsCollector pokerStarsCollector;
    private static View view;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnOpen.setOnAction(event -> {
            if (!fileChooserOpen) {
                fileChooserOpen = true;
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters()
                        .addAll(new FileChooser.ExtensionFilter("CSV", "*.csv", "*.xlsx"),
                                new FileChooser.ExtensionFilter("All Files", "*.*"));
                fileChooser.setTitle("Please, select a files");
                Preferences preferences = Preferences.userNodeForPackage(getClass());
                String lastDirectory = preferences.get("lastDirectory", System.getProperty("user.home"));
                fileChooser.setInitialDirectory(new File(lastDirectory));
                List<File> files = fileChooser.showOpenMultipleDialog(new Stage());
                if (files != null) {
                    preferences.put("lastDirectory", files.get(0).getParentFile().getAbsolutePath());
                    for (File file : files) {
                        pokerStarsCollector = new PokerStarsCollector(file);
                        view = new View(this, pokerStarsCollector.getPokerStarsBuilder());
                    }
                }
                fileChooserOpen = false;
            }
        });
    }

}
