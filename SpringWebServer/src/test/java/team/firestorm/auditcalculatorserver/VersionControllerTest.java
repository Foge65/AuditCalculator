package team.firestorm.auditcalculatorserver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class VersionControllerTest {
    private static VersionController controller;

    @BeforeEach
    public void setUp() {
        controller = new VersionController();
    }

    @Test
    void currentVersion_StartWithDigit() {
        String currentVersion = controller.currentVersion();
        Pattern pattern = Pattern.compile("^\\d*+");
        Matcher matcher = pattern.matcher(currentVersion);
        Assertions.assertTrue(matcher.find());
    }
}
