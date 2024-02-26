package team.firestorm.pokerstars.model;

import lombok.Getter;
import team.firestorm.pokerstars.controller.TabController;
import team.firestorm.pokerstars.view.ModelObserverTables;
import team.firestorm.pokerstars.view.PokerStarsColumnBuilder;
import team.firestorm.pokerstars.view.TextBuilder;

@Getter
public class TabContentDefault {
    private CsvParser csvParser;
    private ModelBuilderFromCsvFile modelBuilderFromCsvFile;
    private Model model;

    private ModelObserverTables modelObserverTables;
    private PokerStarsColumnBuilder columnBuilder;
    private TextBuilder textBuilder;
    private DateSet dateSetFrom;
    private DateSet dateSetTo;

    public void saveData(CsvParser csvParser, ModelBuilderFromCsvFile modelBuilderFromCsvFile, Model model, ModelObserverTables modelObserverTables) {
        this.csvParser = csvParser;
        this.modelBuilderFromCsvFile = modelBuilderFromCsvFile;
        this.model = model;
        this.modelObserverTables = modelObserverTables;
    }

    public void saveView(ModelObserverTables modelObserverTables, PokerStarsColumnBuilder columnBuilder, TextBuilder textBuilder, DateSet dateSetFrom, DateSet dateSetTo) {
        this.modelObserverTables = modelObserverTables;
        this.columnBuilder = columnBuilder;
        this.textBuilder = textBuilder;
        this.dateSetFrom = dateSetFrom;
        this.dateSetTo = dateSetTo;
    }

    public void updateData() {
        modelBuilderFromCsvFile = new ModelBuilderFromCsvFile(csvParser);
        model = new Model();
        modelBuilderFromCsvFile.setModel(model);
    }

    public void setTabContent(TabContent tabContent) {
        tabContent.setCsvParser(csvParser);
        tabContent.setModelBuilderFromCsvFile(modelBuilderFromCsvFile);
        tabContent.setModel(model);
        tabContent.setModelObserverTables(modelObserverTables);
        tabContent.setColumnBuilderFromCsv(columnBuilder);
        tabContent.setTextBuilder(textBuilder);
        tabContent.setDateSetFrom(dateSetFrom);
        tabContent.setDateSetTo(dateSetTo);
    }

    public void updateView(TabController tabController) {
        modelObserverTables = new ModelObserverTables();
        tabController.clearTables();
        modelObserverTables.clear();

        columnBuilder = new PokerStarsColumnBuilder(tabController, model);
        TabContent.buildColumns(columnBuilder, tabController, model, modelObserverTables);

        textBuilder = new TextBuilder(tabController, model);
        textBuilder.setText();

        dateSetFrom = new DateSet();
        dateSetFrom.setDate(tabController.getDatePickerFrom(), model.getDateFrom());
        dateSetTo.setDate(tabController.getDatePickerTo(), model.getDateTo());
    }
}
