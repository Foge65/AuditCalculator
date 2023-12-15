package team.firestorm.auditcalculatorserver;

import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Paths;

@RestController
public class VersionController {
    private static final String CURRENT_VERSION = "1.0";

    @GetMapping("/version")
    public String currentVersion() {
        return CURRENT_VERSION;
    }

    @GetMapping("/download")
    @SneakyThrows
    public ResponseEntity<Resource> update() {
            Resource resource = new UrlResource(Paths.get("/home/foge/java/AuditCalculator/AuditCalculator.exe").toUri());
            return ResponseEntity.ok()
                    .body(resource);
    }
}
