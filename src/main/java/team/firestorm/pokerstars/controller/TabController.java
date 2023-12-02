package team.firestorm.pokerstars.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.firestorm.pokerstars.model.Model;
import team.firestorm.pokerstars.model.ModelBuilderFromCsvFile;
import team.firestorm.pokerstars.model.TabContent;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

@Getter
@NoArgsConstructor
public class TabController implements Initializable {
    @FXML
    private Button btnResetFilter;
    @FXML
    private DatePicker datePickerFrom;
    @FXML
    private DatePicker datePickerTo;
    @FXML
    private TableView<Model> tableViewSpin;
    @FXML
    private TableView<Model> tableViewAnother;

    @FXML
    private Text startBalance;
    @FXML
    private Text finalBalance;

    @FXML
    private Text startTMoney;
    @FXML
    private Text finalTMoney;

    @FXML
    private Text startCoin;
    @FXML
    private Text finalCoin;

    @FXML
    private Text withdrawal;
    @FXML
    private Text transferSent;
    @FXML
    private Text transferReceived;
    @FXML
    private Text deposit;

    @FXML
    private Text chestReward;
    @FXML
    private Text exchangeCoin;
    @FXML
    private Text cashGame;

    @FXML
    private Text roulette;
    @FXML
    private Text omaha;
    @FXML
    private Text otherGame;

    @FXML
    private Button btnCopyStartBalance;
    @FXML
    private Button btnCopyFinalBalance;

    @FXML
    private Button btnCopyStartTMoney;
    @FXML
    private Button btnCopyFinalTMoney;

    @FXML
    private Button btnCopyStartCoin;
    @FXML
    private Button btnCopyFinalCoin;

    @FXML
    private Button btnCopyWithdrawal;
    @FXML
    private Button btnCopyTransferSent;
    @FXML
    private Button btnCopyTransferReceived;
    @FXML
    private Button btnCopyDeposit;

    @FXML
    private Button btnCopyBonus;
    @FXML
    private Button btnCopyExchangeCoin;
    @FXML
    private Button btnCopyCashGame;

    @FXML
    private Button btnCopyRoulette;
    @FXML
    private Button btnCopyOmaha;
    @FXML
    private Button btnCopyOtherGame;

    private TabContent tabContent;
    private LocalDate dateSelectFrom;
    private LocalDate dateSelectTo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        datePickerFrom.setOnAction(event -> onClickDatePickerFrom());
        datePickerTo.setOnAction(event -> onClickDatePickerTo());

        onClickBtnResetFilter();

        onClickBtnCopy(btnCopyStartBalance, startBalance);
        onClickBtnCopy(btnCopyFinalBalance, finalBalance);

        onClickBtnCopy(btnCopyStartTMoney, startTMoney);
        onClickBtnCopy(btnCopyFinalTMoney, finalTMoney);

        onClickBtnCopy(btnCopyStartCoin, startCoin);
        onClickBtnCopy(btnCopyFinalCoin, finalCoin);

        onClickBtnCopy(btnCopyWithdrawal, withdrawal);
        onClickBtnCopy(btnCopyTransferSent, transferSent);
        onClickBtnCopy(btnCopyTransferReceived, transferReceived);
        onClickBtnCopy(btnCopyDeposit, deposit);

        onClickBtnCopy(btnCopyBonus, chestReward);
        onClickBtnCopy(btnCopyExchangeCoin, exchangeCoin);
        onClickBtnCopy(btnCopyCashGame, cashGame);

        onClickBtnCopy(btnCopyRoulette, roulette);
        onClickBtnCopy(btnCopyOmaha, omaha);
        onClickBtnCopy(btnCopyOtherGame, otherGame);
    }

    public void buildTabData(File file) {
        tabContent = new TabContent();
        tabContent.buildCsvData(file);
    }

    public void buildTabView(TabController tabController) {
        tabContent.buildCsvView(tabController);
        tabContent.setTabName();
    }

    // TODO: Реализовать автоматическое переключение на последнюю добавленную вкладку.
    //  Но перед этим уточнить, нужно ли такое
    public void addTab(AnchorPane anchorPane, TabPane tabPane) {
        Tab tab = new Tab(tabContent.getTabNameString());
        tab.setContent(anchorPane);
        tabPane.getTabs().add(tab);
    }

    @FXML
    public void onClickDatePickerFrom() {
        dateSelectFrom = datePickerFrom.getValue();
        dateSelectTo = datePickerTo.getValue();
        LocalDate dateModelBuilder = tabContent.getModel().getDateFrom();
        if (!dateSelectFrom.isEqual(dateModelBuilder)) {
            filterByDate(tabContent.getModelBuilderFromCsvFile());
        }
    }

    @FXML
    private void onClickDatePickerTo() {
        dateSelectFrom = datePickerFrom.getValue();
        dateSelectTo = datePickerTo.getValue();
        LocalDate dateModelBuilder = tabContent.getModel().getDateTo();
        if (!dateSelectTo.isEqual(dateModelBuilder)) {
            filterByDate(tabContent.getModelBuilderFromCsvFile());
        }
    }

    private void filterByDate(ModelBuilderFromCsvFile modelBuilderFromCsvFile) {
        tabContent.buildFilterDataByDate(modelBuilderFromCsvFile.getDateTimeFormatter(), dateSelectFrom, dateSelectTo);
        tabContent.buildFilterViewByDate(this);
    }

    // TODO: Добавить сброс чек-боксов
    private void onClickBtnResetFilter() {
        btnResetFilter.setOnAction(event -> {
            tabContent.getTabContentDefault().updateData();
            tabContent.getTabContentDefault().setTabContent(tabContent);
            tabContent.getTabContentDefault().updateView(this);
        });
    }

    private void onClickBtnCopy(Button button, Text text) {
        button.setOnMouseClicked(event -> {
            String textToCopy = text.getText();
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            if (!textToCopy.isEmpty()) {
                content.putString(textToCopy);
                clipboard.setContent(content);
            }
        });
    }

    public void onClickPoolSetting() {
        ObservableList<Model> selectedItems = tableViewSpin.getSelectionModel().getSelectedItems();
        selectedItems.forEach(e -> System.out.println(e.getSumProfitSpin()));

        int indexColumnProfit = 8;
        Map<String, BigDecimal> sumProfitPoolSpin = tabContent.getModel().getSumProfitPoolSpin();
        Model row = tableViewSpin.getSelectionModel().getSelectedItem();
        BigDecimal cellProfitValue = (BigDecimal) tableViewSpin.getColumns().get(indexColumnProfit).getCellData(row);
        Set<String> gameSpin = row.getGameSpin();
        String buyIn = gameSpin.toString().replace("[", "").replace("]", "");
        if (sumProfitPoolSpin.containsKey(buyIn)) {
            Map<String, BigDecimal> filteredSumProfitPoolSpin = new HashMap<>();
            filteredSumProfitPoolSpin.put(buyIn, cellProfitValue);
        }
    }
}
