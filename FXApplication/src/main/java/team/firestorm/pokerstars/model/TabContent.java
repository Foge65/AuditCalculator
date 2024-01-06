package team.firestorm.pokerstars.model;

import lombok.Getter;
import lombok.Setter;
import team.firestorm.pokerstars.controller.TabController;
import team.firestorm.pokerstars.view.ColumnBuilder;
import team.firestorm.pokerstars.view.ColumnBuilderImpl;
import team.firestorm.pokerstars.view.ModelObserver;
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

    private ModelObserver modelObserver;
    private ColumnBuilderImpl columnBuilderFromCsv;
    private TextBuilder textBuilder;
    private DateSet dateSetFrom;
    private DateSet dateSetTo;

    private String tabNameString;

    private ModelBuilderFilter modelBuilderCsvFilter;
    private ColumnBuilderImpl columnBuilderFilterByDate;
    private TabContentDefault tabContentDefault;

    public void buildCsvData(File file) {
        csvParser = new CsvParser(file);
        modelBuilderFromCsvFile = new ModelBuilderFromCsvFile(csvParser);
        model = new Model();
        model.setModel(modelBuilderFromCsvFile);

        tabContentDefault = new TabContentDefault();
        tabContentDefault.saveData(csvParser, modelBuilderFromCsvFile, model, modelObserver);
    }

    public void buildCsvView(TabController tabController) {
        modelObserver = new ModelObserver();
        modelObserver.clear(tabController);

        columnBuilderFromCsv = new ColumnBuilderImpl(tabController, model);
        buildTable(columnBuilderFromCsv, tabController);

        textBuilder = new TextBuilder(tabController, model);
        textBuilder.setText();

        dateSetFrom = new DateSet();
        dateSetFrom.setDate(tabController.getDatePickerFrom(), model.getDateFrom());

        dateSetTo = new DateSet();
        dateSetTo.setDate(tabController.getDatePickerTo(), model.getDateTo());

        tabContentDefault.saveView(modelObserver, columnBuilderFromCsv, textBuilder, dateSetFrom, getDateSetTo());
    }

    public void setTabName() {
        TabName tabName = new TabName(csvParser);
        tabNameString = tabName.getTabName();
    }

    public void buildFilterDataByDate(DateTimeFormatter dateTimeFormatter, LocalDate dateSelectFrom, LocalDate dateSelectTo) {
        modelBuilderCsvFilter = new ModelBuilderFilter(modelBuilderFromCsvFile.getPokerStarsBase());
        modelBuilderCsvFilter.addToFilteredList(dateTimeFormatter, dateSelectFrom, dateSelectTo);
        modelBuilderCsvFilter.setModel(model);
    }

    public void buildFilterViewByDate(TabController tabController) {
        modelObserver = new ModelObserver();
        modelObserver.clear(tabController);

        columnBuilderFilterByDate = new ColumnBuilderImpl(tabController, model);
        buildTable(columnBuilderFilterByDate, tabController);

        textBuilder = new TextBuilder(tabController, model);
        textBuilder.setText();
    }

    public void buildTable(ColumnBuilder columnBuilder, TabController tabController) {
        columnBuilder.buildColumnSpin();
        columnBuilder.buildColumnMTT();
        columnBuilder.buildColumnCash();
        columnBuilder.addGameToObservableList(model.getGameSpin(), modelObserver.getObservableListSpin(), tabController.getTableViewSpin());
        columnBuilder.addGameToObservableList(model.getGameMTT(), modelObserver.getObservableListMTT(), tabController.getTableViewMTT());
        columnBuilder.addGameToObservableList(model.getGameCash(), modelObserver.getObservableListCash(), tabController.getTableViewCash());
    }
}
