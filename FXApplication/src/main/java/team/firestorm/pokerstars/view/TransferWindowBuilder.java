package team.firestorm.pokerstars.view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import lombok.RequiredArgsConstructor;
import team.firestorm.pokerstars.controller.TransferController;
import team.firestorm.pokerstars.model.Model;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TransferWindowBuilder {
    private final TransferController transferController;
    private final ModelObserverTransferWindow observerTransferWindow;

    public void buildWindow(Map<String, String> value) {
        TableColumn<Model, Set<String>> dateColumn = new TableColumn<>("Date");
        setCellValueDate(dateColumn, value);

        TableColumn<Model, String> amountColumn = new TableColumn<>("Amount");
        setCellValue(amountColumn, value);
    }

    private void setCellValueDate(TableColumn<Model, Set<String>> column, Map<String, String> keyMap) {
        column.setCellValueFactory(param -> {
            Set<String> dates = param.getValue().getDates();
            Set<String> mapKeys = keyMap.keySet();

            Set<String> result = mapKeys.stream()
                    .filter(dates::contains)
                    .collect(Collectors.toSet());

            observerTransferWindow.getObservableListTransfer().removeIf(
                    filter -> filter.getDates().stream().noneMatch(mapKeys::contains));

            return new SimpleObjectProperty<>(result);
        });

        column.setCellFactory(tableColumn -> new TableCell<>() {
            @Override
            protected void updateItem(Set<String> item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(String.join("\n", item));
                }
            }
        });

        transferController.getTransferTable().getColumns().add(column);
    }

    private <T> void setCellValue(TableColumn<Model, T> column, Map<String, T> value) {
        column.setCellValueFactory(cellData -> {
            Model model = cellData.getValue();
            String date = model.getDates().iterator().next();
            T result = value.getOrDefault(date, null);
            return new SimpleObjectProperty<>(result);
        });

        column.setCellFactory(tableColumn -> new TableCell<>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.toString().replace("\"", ""));
                }
            }
        });

        transferController.getTransferTable().getColumns().add(column);
    }
}
