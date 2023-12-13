package starter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionManager {
    private String currentVersion = "1.0";

    @GetMapping("/version")
    public String getCurrentVersion() {
        return currentVersion;
    }
}
