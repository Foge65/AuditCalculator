package team.firestorm.pokerstars.model;

import lombok.Getter;
import lombok.Setter;
import team.firestorm.pokerstars.controller.TabController;
import team.firestorm.pokerstars.view.ColumnBuilder;
import team.firestorm.pokerstars.view.ModelObserverTables;
import team.firestorm.pokerstars.view.PokerStarsColumnBuilder;
import team.firestorm.pokerstars.view.TextBuilder;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class TabContent {
    private CsvParser csvParser;
    private ModelBuilderFromCsvFile modelBuilderFromCsvFile;
    private Model model;

    private ModelObserverTables modelObserverTables;
    private PokerStarsColumnBuilder columnBuilderFromCsv;
    private TextBuilder textBuilder;
    private DateSet dateSetFrom;
    private DateSet dateSetTo;

    private ModelBuilderFilter modelBuilderCsvFilter;
    private PokerStarsColumnBuilder columnBuilderFilterByDate;
    private TabContentDefault tabContentDefault;

    public static void buildColumns(ColumnBuilder columnBuilder, TabController tabController, Model model, ModelObserverTables modelObserverTables) {
        columnBuilder.buildColumnSpin();
        columnBuilder.buildColumnMTT();
        columnBuilder.buildColumnCash();
        columnBuilder.addGameToObservableList(model.getGameSpin(), modelObserverTables.getObservableListSpin(), tabController.getTableViewSpin());
        columnBuilder.addGameToObservableList(model.getGameMTT(), modelObserverTables.getObservableListMTT(), tabController.getTableViewMTT());
        columnBuilder.addGameToObservableList(model.getGameCash(), modelObserverTables.getObservableListCash(), tabController.getTableViewCash());
    }

    public void buildCsvData(File file) {
        csvParser = new CsvParser(file);
        model = new Model();
        modelBuilderFromCsvFile = new ModelBuilderFromCsvFile(csvParser);
        modelBuilderFromCsvFile.setModel(model);

        tabContentDefault = new TabContentDefault();
        tabContentDefault.saveData(csvParser, modelBuilderFromCsvFile, model, modelObserverTables);
    }

    public void buildFilterDataByDate(DateTimeFormatter dateTimeFormatter, LocalDate dateSelectFrom, LocalDate dateSelectTo) {
        modelBuilderCsvFilter = new ModelBuilderFilter(csvParser);
        modelBuilderCsvFilter.addToFilteredList(dateTimeFormatter, dateSelectFrom, dateSelectTo);
        modelBuilderCsvFilter.setModel(model);
    }

    public void buildCsvView(TabController tabController) {
        modelObserverTables = new ModelObserverTables();
        tabController.clearTables();
        modelObserverTables.clear();

        columnBuilderFromCsv = new PokerStarsColumnBuilder(tabController, model);
        buildTable(columnBuilderFromCsv, tabController);

        textBuilder = new TextBuilder(tabController, model);
        textBuilder.setText();

        dateSetFrom = new DateSet();
        dateSetFrom.setDate(tabController.getDatePickerFrom(), model.getDateFrom());

        dateSetTo = new DateSet();
        dateSetTo.setDate(tabController.getDatePickerTo(), model.getDateTo());

        tabContentDefault.saveView(modelObserverTables, columnBuilderFromCsv, textBuilder, dateSetFrom, getDateSetTo());
    }

    public void buildFilterViewByDate(TabController tabController) {
        modelObserverTables = new ModelObserverTables();
        tabController.clearTables();
        modelObserverTables.clear();

        columnBuilderFilterByDate = new PokerStarsColumnBuilder(tabController, model);
        buildTable(columnBuilderFilterByDate, tabController);

        textBuilder = new TextBuilder(tabController, model);
        textBuilder.setText();
    }

    public void buildTable(ColumnBuilder columnBuilder, TabController tabController) {
        buildColumns(columnBuilder, tabController, model, modelObserverTables);
    }
}
