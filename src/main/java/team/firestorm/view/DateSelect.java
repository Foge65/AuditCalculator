package team.firestorm.view;

import lombok.AllArgsConstructor;
import team.firestorm.controller.Controller;

import java.time.LocalDate;

@AllArgsConstructor
public class DateSelect {
    private final Controller controller;

    public void setDateFrom(LocalDate date) {
        controller.getDateFrom().valueProperty().set(date);
    }

    public void setDateTo(LocalDate date) {
        controller.getDateTo().valueProperty().set(date);
    }

}
