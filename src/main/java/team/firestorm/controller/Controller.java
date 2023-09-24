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

public class Controller implements Initializable {
    @FXML
    private Button btnOpen;
    @FXML
    @Getter
    private DatePicker dateFrom;
    @FXML
    @Getter
    private DatePicker dateTo;
    @FXML
    @Getter
    private TableView<PokerStarsGameModel> tableViewSpin;
    @FXML
    @Getter
    private TableView<PokerStarsGameModel> tableViewAnother;
    @FXML
    @Getter
    private Text startBalance;
    @FXML
    @Getter
    private Text finalBalance;
    @FXML
    @Getter
    private Text startTMoney;
    @FXML
    @Getter
    private Text finalTMoney;
    @FXML
    @Getter
    private Text totalTMoney;
    @FXML
    @Getter
    private Text startCoin;
    @FXML
    @Getter
    private Text finalCoin;
    @FXML
    @Getter
    private Text totalCoin;
    @FXML
    @Getter
    private Text withdrawal;
    @FXML
    @Getter
    private Text transferSent;
    @FXML
    @Getter
    private Text transferReceived;
    @FXML
    @Getter
    private Text deposit;

    private boolean fileChooserOpen = false;

    private PokerStarsCollector pokerStarsCollector;
    private View view;

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
