package team.firestorm.pokerstars.model;

import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class DateSet {
    public void setDate(DatePicker datePicker, LocalDate date) {
        datePicker.valueProperty().set(date);
    }
}
