package team.firestorm.auditcalculatorserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
    private static final String CURRENT_VERSION = "1.0";

    @GetMapping("/version")
    public String currentVersion() {
        return CURRENT_VERSION;
    }
}
