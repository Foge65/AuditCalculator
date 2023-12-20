package team.firestorm.pokerstars.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private Text otherBonus;
    @FXML
    private Text casino;

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
    private Button btnCopyChestReward;
    @FXML
    private Button btnCopyExchangeCoin;
    @FXML
    private Button btnCopyOtherBonus;
    @FXML
    private Button btnCopyCasino;

    private TabContent tabContent;
    private LocalDate dateSelectFrom;
    private LocalDate dateSelectTo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        datePickerFrom.setOnAction(event -> filterByDate(tabContent.getModelBuilderFromCsvFile()));
        datePickerTo.setOnAction(event -> filterByDate(tabContent.getModelBuilderFromCsvFile()));

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

        onClickBtnCopy(btnCopyChestReward, chestReward);
        onClickBtnCopy(btnCopyExchangeCoin, exchangeCoin);
        onClickBtnCopy(btnCopyOtherBonus, otherBonus);
        onClickBtnCopy(btnCopyCasino, casino);
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

    private void filterByDate(ModelBuilderFromCsvFile modelBuilderFromCsvFile) {
        dateSelectFrom = datePickerFrom.getValue();
        dateSelectTo = datePickerTo.getValue();
        tabContent.getModel().setDateFrom(dateSelectFrom);
        tabContent.getModel().setDateTo(dateSelectTo);
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

    public void onClickPoolSetting(boolean oldValue, boolean newValue, String buyIn) {
        Map<String, BigDecimal> profit = tabContent.getModel().getSumProfitSpin();
        Map<String, BigDecimal> profitPool = tabContent.getModel().getSumProfitPoolSpin();
        Map<String, BigDecimal> profitDefault = new HashMap<>(tabContent.getModel().getSumProfitSpin());

        Map<String, BigDecimal> bonus = tabContent.getModel().getSumBonusSpin();
        Map<String, BigDecimal> bonusPool = tabContent.getModel().getSumBonusPoolSpin();
        Map<String, BigDecimal> bonusDefault = new HashMap<>(tabContent.getModel().getSumBonusSpin());

        if (newValue) {
            System.out.println("---------------------");
            profit.put(buyIn, profitPool.get(buyIn));
            System.out.println("newValue: " + newValue);
            System.out.println("new profit: " + profit.entrySet().stream().filter(stringBigDecimalEntry -> stringBigDecimalEntry.getKey().contains(buyIn)).toList());
            System.out.println("---------------------");
        } else if (oldValue) {
            System.out.println("---------------------");
            profit.put(buyIn, profitDefault.get(buyIn));
            System.out.println("oldValue: " + oldValue);
            System.out.println("old profit: " + profit.entrySet().stream().filter(stringBigDecimalEntry -> stringBigDecimalEntry.getKey().contains(buyIn)).toList());
            System.out.println("---------------------");
        }

        tableViewSpin.refresh();
    }

    private void updateValue(String selectedBuyIn) {
        for (Object item : getTableViewSpin().getItems()) {
            if (item instanceof Model && ((Model) item).getGameSpin().contains(selectedBuyIn)) {
                getTableViewSpin().refresh();
            }
        }
    }
}
