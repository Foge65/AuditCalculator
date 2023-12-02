package team.firestorm.pokerstars.model;

import lombok.Getter;
import lombok.Setter;
import team.firestorm.pokerstars.controller.TabController;
import team.firestorm.pokerstars.view.ColumnBuilderCsv;
import team.firestorm.pokerstars.view.ColumnBuilderFilterByDate;
import team.firestorm.pokerstars.view.ColumnBuilderFilterByPool;
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
    private ColumnBuilderCsv columnBuilderCsv;
    private TextBuilder textBuilder;
    private DateSet dateSetFrom;
    private DateSet dateSetTo;

    private String tabNameString;

    private ModelBuilderFilter modelBuilderCsvFilter;
    private ColumnBuilderFilterByDate columnBuilderFilterByDate;
    private ColumnBuilderFilterByPool columnBuilderFilterByPool;

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

        columnBuilderCsv = new ColumnBuilderCsv(tabController, model);
        columnBuilderCsv.buildColumnSpin();
        columnBuilderCsv.buildColumnAnother();
        columnBuilderCsv.addGameToObservableList(model.getGameSpin(), model.getCountRegistrationSpin(), modelObserver.getObservableListSpinGame(), tabController.getTableViewSpin());
        columnBuilderCsv.addGameToObservableList(model.getGameAnother(), model.getCountRegistrationAnother(), modelObserver.getObservableListAnotherGame(), tabController.getTableViewAnother());

        textBuilder = new TextBuilder(tabController, model);
        textBuilder.setText();

        dateSetFrom = new DateSet();
        dateSetFrom.setDate(tabController.getDatePickerFrom(), model.getDateFrom());

        dateSetTo = new DateSet();
        dateSetTo.setDate(tabController.getDatePickerTo(), model.getDateTo());

        tabContentDefault.saveView(modelObserver, columnBuilderCsv, textBuilder, dateSetFrom, getDateSetTo());
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

        columnBuilderFilterByDate = new ColumnBuilderFilterByDate(tabController, model);
        columnBuilderFilterByDate.buildColumnSpin();
        columnBuilderFilterByDate.buildColumnAnother();

        textBuilder = new TextBuilder(tabController, model);
        textBuilder.setText();
    }

    public void buildFilterDataByPool(DateTimeFormatter dateTimeFormatter, LocalDate dateSelectFrom, LocalDate dateSelectTo) {
        modelBuilderCsvFilter = new ModelBuilderFilter(modelBuilderFromCsvFile.getPokerStarsBase());
        modelBuilderCsvFilter.addToFilteredList(dateTimeFormatter, dateSelectFrom, dateSelectTo);
        modelBuilderCsvFilter.setModel(model);
    }

    public void buildFilterViewByPool(TabController tabController) {
        modelObserver = new ModelObserver();
        modelObserver.clear(tabController);

        columnBuilderFilterByPool = new ColumnBuilderFilterByPool(tabController, model);
        columnBuilderFilterByPool.buildColumnSpin();
        columnBuilderFilterByPool.buildColumnAnother();

        textBuilder = new TextBuilder(tabController, model);
        textBuilder.setText();
    }
}
