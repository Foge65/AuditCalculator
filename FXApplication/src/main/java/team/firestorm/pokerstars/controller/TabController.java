package team.firestorm.pokerstars.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.firestorm.pokerstars.model.Model;
import team.firestorm.pokerstars.model.ModelBuilderFromCsvFile;
import team.firestorm.pokerstars.model.TabContent;
import team.firestorm.pokerstars.model.TabName;
import team.firestorm.pokerstars.view.Alerts;
import team.firestorm.pokerstars.view.ModelObserverTransferWindow;
import team.firestorm.pokerstars.view.TransferWindowBuilder;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
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
    private TableView<Model> tableViewMTT;
    @FXML
    private TableView<Model> tableViewCash;

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
    private Text countSpins;
    @FXML
    private Text totalProfitSpin;
    @FXML
    private Text totalBonusSpin;
    @FXML
    private Text totalAllBonuses;

    @FXML
    private Text totalProfitMTT;
    @FXML
    private Text totalProfitCash;

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
    private Button btnDetailsWithdrawal;
    @FXML
    private Button btnDetailsTransferSent;
    @FXML
    private Button btnDetailsTransferReceived;
    @FXML
    private Button btnDetailsDeposit;
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

    @FXML
    private Button btnCopyTotalSpins;
    @FXML
    private Button btnCopyTotalProfitSpin;
    @FXML
    private Button btnCopyTotalBonusSpin;
    @FXML
    private Button btnCopyAllBonuses;

    @FXML
    private Button btnCopyTotalProfitMTT;
    @FXML
    private Button btnCopyTotalProfitCash;

    private TabContent tabContent;
    private LocalDate dateSelectFrom;
    private LocalDate dateSelectTo;

    private String tabNameString;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        disableOutOfRangeDate();
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

        onClickBtnCopy(btnCopyTotalSpins, countSpins);
        onClickBtnCopy(btnCopyTotalProfitSpin, totalProfitSpin);
        onClickBtnCopy(btnCopyTotalBonusSpin, totalBonusSpin);
        onClickBtnCopy(btnCopyAllBonuses, totalAllBonuses);

        onClickBtnCopy(btnCopyTotalProfitMTT, totalProfitMTT);
        onClickBtnCopy(btnCopyTotalProfitCash, totalProfitCash);

        btnDetailsWithdrawal.setOnAction(event -> onClickBtnDetail(tabContent.getModel().getWithdrawalDetails()));
        btnDetailsTransferSent.setOnAction(event -> onClickBtnDetail(tabContent.getModel().getTransferSentDetails()));
        btnDetailsTransferReceived.setOnAction(event -> onClickBtnDetail(tabContent.getModel().getTransferReceivedDetails()));
        btnDetailsDeposit.setOnAction(event -> onClickBtnDetail(tabContent.getModel().getDepositDetails()));
    }

    public void buildTabData(File file) {
        tabContent = new TabContent();
        tabContent.buildCsvData(file);
    }

    public void buildTabView(TabController tabController) {
        tabContent.buildCsvView(tabController);
        setTabName();
    }

    public void addTab(AnchorPane anchorPane, TabPane tabPane) {
        Tab tab = new Tab(tabNameString);
        tab.setContent(anchorPane);
        tabPane.getTabs().add(tab);
    }

    public void clearTables() {
        tableViewSpin.getColumns().clear();
        tableViewMTT.getColumns().clear();
        tableViewCash.getColumns().clear();
    }

    private void setTabName() {
        tabNameString = new TabName(tabContent.getCsvParser()).getTabName();
    }

    private void filterByDate(ModelBuilderFromCsvFile modelBuilderFromCsvFile) {
        dateSelectFrom = datePickerFrom.getValue();
        dateSelectTo = datePickerTo.getValue();
        try {
            tabContent.getModel().setDateFrom(dateSelectFrom);
            tabContent.getModel().setDateTo(dateSelectTo);
            tabContent.buildFilterDataByDate(modelBuilderFromCsvFile.getDateTimeFormatter(), dateSelectFrom, dateSelectTo);
            tabContent.buildFilterViewByDate(this);
        } catch (IndexOutOfBoundsException e) {
            Alerts.invalidDate();
        }
    }

    private void disableOutOfRangeDate() {
        Callback<DatePicker, DateCell> dayCellFactory = getDayCellFactory();
        datePickerFrom.setDayCellFactory(dayCellFactory);
        datePickerTo.setDayCellFactory(dayCellFactory);
    }

    private Callback<DatePicker, DateCell> getDayCellFactory() {
        return param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate dateFrom = tabContent.getModel().getDateFrom();
                LocalDate dateTo = tabContent.getModel().getDateTo();
                setDisable(date.isBefore(dateFrom) || date.isAfter(dateTo));
            }
        };
    }

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
            ClipboardContent content = new ClipboardContent();
            if (!textToCopy.isEmpty()) {
                content.putString(textToCopy);
                Clipboard.getSystemClipboard().setContent(content);
            }
        });
    }

    public void addListenerToCheckBox(String buyIn, CheckBox checkBox, Map<String, Boolean> checkBoxStateDefault, Map<String, BigDecimal> profitDefault, Map<String, BigDecimal> bonusDefault) {
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            putNewValuesToModel(buyIn, newValue, checkBoxStateDefault, profitDefault, bonusDefault);
            tableViewSpin.refresh();
        });
    }

    private void putNewValuesToModel(String buyIn, Boolean newValue, Map<String, Boolean> checkBoxStateDefault, Map<String, BigDecimal> profitDefault, Map<String, BigDecimal> bonusDefault) {
        Map<String, Boolean> checkBoxState = tabContent.getModel().getCheckBoxState();
        Map<String, BigDecimal> profit = tabContent.getModel().getSumProfitSpin();
        Map<String, BigDecimal> bonus = tabContent.getModel().getSumBonusSpin();
        Map<String, BigDecimal> profitPool = tabContent.getModel().getSumProfitPoolSpin();
        Map<String, BigDecimal> bonusPool = tabContent.getModel().getSumBonusPoolSpin();
        if (newValue) {
            checkBoxState.put(buyIn, true);
            profit.put(buyIn, profitPool.get(buyIn));
            bonus.put(buyIn, bonusPool.get(buyIn));
            setNewTextValue(profit, totalProfitSpin);
            setNewTextValue(bonus, totalBonusSpin);
        } else {
            checkBoxState.put(buyIn, checkBoxStateDefault.get(buyIn));
            profit.put(buyIn, profitDefault.get(buyIn));
            bonus.put(buyIn, bonusDefault.get(buyIn));
            setNewTextValue(profitDefault, totalProfitSpin);
            setNewTextValue(bonusDefault, totalBonusSpin);
        }
        totalAllBonuses.setText(tabContent.getTextBuilder().sumTotalBonuses());
    }

    private void setNewTextValue(Map<String, BigDecimal> valueMap, Text field) {
        BigDecimal[] result = {BigDecimal.ZERO};
        valueMap.values().stream().map(BigDecimal.ZERO::add).forEach(sum -> result[0] = result[0].add(sum));
        field.setText(result[0].toString());
    }

    private void onClickBtnDetail(Map<String, String> value) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/team/firestorm/Transfer.fxml"));
        AnchorPane anchorPane;
        try {
            anchorPane = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TransferController transferController = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Transfer Window");
        stage.getIcons().add(new Image("/team/firestorm/FSAC.png"));
        stage.setResizable(false);
        stage.setScene(new Scene(anchorPane));
        transferController.setStage(stage);

        ModelObserverTransferWindow observerTransfer = new ModelObserverTransferWindow();

        transferController.getTransferTable().getColumns().clear();
        observerTransfer.clear();

        observerTransfer.addGameToObservableList(tabContent.getModel().getDates(), transferController.getTransferTable());

        TransferWindowBuilder transferWindowBuilder = new TransferWindowBuilder(transferController, observerTransfer);
        transferWindowBuilder.buildWindow(value);

        stage.showAndWait();
    }
}
