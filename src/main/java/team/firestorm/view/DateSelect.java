package team.firestorm.view;

import team.firestorm.controller.Controller;

import java.time.LocalDate;

public class DateSelect {
    private Controller controller;

    public DateSelect(Controller controller) {
        this.controller = controller;
    }

    public void setDateFrom(LocalDate date) {
        controller.getDateFrom().valueProperty().set(date);
    }

    public void setDateTo(LocalDate date) {
        controller.getDateTo().valueProperty().set(date);
    }

}
