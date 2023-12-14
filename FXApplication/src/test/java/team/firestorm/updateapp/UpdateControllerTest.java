package team.firestorm.updateapp;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateControllerTest {
    private UpdateController updateController = new UpdateController();

    @Test
    @SneakyThrows
    void fetchVersion() {
        String version = updateController.fetchVersionFromServer();
        assertEquals("1.0", version);
    }

    @Test
    void currentVersion() {
        String currentVersion = updateController.currentVersionFromFile();
        assertEquals("1.0", currentVersion);
    }

    @Test
    void downloadUpdate() {
        updateController.downloadUpdate();
    }
}
