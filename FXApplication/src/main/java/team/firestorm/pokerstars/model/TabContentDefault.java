package team.firestorm.pokerstars.model;

import lombok.Getter;
import team.firestorm.pokerstars.controller.TabController;
import team.firestorm.pokerstars.view.ModelObserver;
import team.firestorm.pokerstars.view.PokerStarsColumn;
import team.firestorm.pokerstars.view.TextBuilder;

@Getter
public class TabContentDefault {
    private CsvParser csvParser;
    private ModelBuilderFromCsvFile modelBuilderFromCsvFile;
    private Model model;

    private ModelObserver modelObserver;
    private PokerStarsColumn columnBuilder;
    private TextBuilder textBuilder;
    private DateSet dateSetFrom;
    private DateSet dateSetTo;

    public void saveData(CsvParser csvParser, ModelBuilderFromCsvFile modelBuilderFromCsvFile, Model model, ModelObserver modelObserver) {
        this.csvParser = csvParser;
        this.modelBuilderFromCsvFile = modelBuilderFromCsvFile;
        this.model = model;
        this.modelObserver = modelObserver;
    }

    public void saveView(ModelObserver modelObserver, PokerStarsColumn columnBuilder, TextBuilder textBuilder, DateSet dateSetFrom, DateSet dateSetTo) {
        this.modelObserver = modelObserver;
        this.columnBuilder = columnBuilder;
        this.textBuilder = textBuilder;
        this.dateSetFrom = dateSetFrom;
        this.dateSetTo = dateSetTo;
    }

    public void updateData() {
        modelBuilderFromCsvFile = new ModelBuilderFromCsvFile(csvParser);
        model = new Model();
        model.setModel(modelBuilderFromCsvFile);
    }

    public void setTabContent(TabContent tabContent) {
        tabContent.setCsvParser(csvParser);
        tabContent.setModelBuilderFromCsvFile(modelBuilderFromCsvFile);
        tabContent.setModel(model);
        tabContent.setModelObserver(modelObserver);
        tabContent.setColumnBuilderFromCsv(columnBuilder);
        tabContent.setTextBuilder(textBuilder);
        tabContent.setDateSetFrom(dateSetFrom);
        tabContent.setDateSetTo(dateSetTo);
    }

    public void updateView(TabController tabController) {
        modelObserver = new ModelObserver();
        modelObserver.clear(tabController);

        columnBuilder = new PokerStarsColumn(tabController, model);
        columnBuilder.buildColumnSpin();
        columnBuilder.buildColumnMTT();
        columnBuilder.buildColumnCash();
        columnBuilder.addGameToObservableList(model.getGameSpin(), modelObserver.getObservableListSpin(), tabController.getTableViewSpin());
        columnBuilder.addGameToObservableList(model.getGameMTT(), modelObserver.getObservableListMTT(), tabController.getTableViewMTT());
        columnBuilder.addGameToObservableList(model.getGameCash(), modelObserver.getObservableListCash(), tabController.getTableViewCash());

        textBuilder = new TextBuilder(tabController, model);
        textBuilder.setText();

        dateSetFrom = new DateSet();
        dateSetFrom.setDate(tabController.getDatePickerFrom(), model.getDateFrom());
        dateSetTo.setDate(tabController.getDatePickerTo(), model.getDateTo());
    }
}
