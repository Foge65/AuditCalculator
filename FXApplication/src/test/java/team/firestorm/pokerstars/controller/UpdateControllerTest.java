package team.firestorm.pokerstars.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateControllerTest {
    @Test
    @SneakyThrows
    void fetchVersion() {
        UpdateController updateController = new UpdateController();
        String version = updateController.fetchVersion();
        assertEquals("1.0", version);
    }
}
