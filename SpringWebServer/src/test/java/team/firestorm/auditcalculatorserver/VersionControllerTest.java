package team.firestorm.auditcalculatorserver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VersionControllerTest {
    private VersionController versionController = new VersionController();

    @Test
    void getCurrentVersion() {
        String currentVersion = versionController.currentVersion();
        assertEquals("1.0", currentVersion);
    }
}