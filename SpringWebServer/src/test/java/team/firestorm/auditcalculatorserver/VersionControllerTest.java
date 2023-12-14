package team.firestorm.auditcalculatorserver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VersionControllerTest {
    @Test
    void getCurrentVersion() {
        VersionController versionController = new VersionController();
        String currentVersion = versionController.currentVersion();
        assertEquals("1.0", currentVersion);
    }
}