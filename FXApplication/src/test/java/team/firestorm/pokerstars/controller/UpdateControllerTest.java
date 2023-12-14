package team.firestorm.pokerstars.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateControllerTest {
    private UpdateController updateController = new UpdateController();

    @Test
    @SneakyThrows
    void fetchVersion() {
        String version = updateController.fetchVersion();
        assertEquals("1.0", version);
    }

    @Test
    void currentVersion() {
        String currentVersion = updateController.currentVersion();
        assertEquals("1.0", currentVersion);
    }
}
