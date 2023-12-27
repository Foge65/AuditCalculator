package team.firestorm.pokerstars.model;

import lombok.Getter;
import team.firestorm.pokerstars.controller.TabController;
import team.firestorm.pokerstars.view.ColumnBuilderImpl;
import team.firestorm.pokerstars.view.ModelObserver;
import team.firestorm.pokerstars.view.TextBuilder;

@Getter
public class TabContentDefault {
    private CsvParser csvParser;
    private ModelBuilderFromCsvFile modelBuilderFromCsvFile;
    private Model model;

    private ModelObserver modelObserver;
    private ColumnBuilderImpl columnBuilder;
    private TextBuilder textBuilder;
    private DateSet dateSetFrom;
    private DateSet dateSetTo;

    public void saveData(CsvParser csvParser, ModelBuilderFromCsvFile modelBuilderFromCsvFile, Model model, ModelObserver modelObserver) {
        this.csvParser = csvParser;
        this.modelBuilderFromCsvFile = modelBuilderFromCsvFile;
        this.model = model;
        this.modelObserver = modelObserver;
    }

    public void saveView(ModelObserver modelObserver, ColumnBuilderImpl columnBuilder, TextBuilder textBuilder, DateSet dateSetFrom, DateSet dateSetTo) {
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

        columnBuilder = new ColumnBuilderImpl(tabController, model);
        columnBuilder.buildColumnSpin();
        columnBuilder.buildColumnAnother();
        columnBuilder.addGameToObservableList(model.getGameSpin(), modelObserver.getObservableListSpinGame(), tabController.getTableViewSpin());
        columnBuilder.addGameToObservableList(model.getGameAnother(), modelObserver.getObservableListAnotherGame(), tabController.getTableViewAnother());

        textBuilder = new TextBuilder(tabController, model);
        textBuilder.setText();

        dateSetFrom = new DateSet();
        dateSetFrom.setDate(tabController.getDatePickerFrom(), model.getDateFrom());
        dateSetTo.setDate(tabController.getDatePickerTo(), model.getDateTo());
    }
}
