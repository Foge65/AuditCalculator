package team.firestorm.auditcalculatorserver;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class VersionController {

    @GetMapping("/version")
    public String currentVersion() {
        Path filePath = Paths.get("", "version").toAbsolutePath();
        List<String> lines = null;
        try {
            lines = Files.readAllLines(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return String.join("", lines);
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> update() {
        Resource resource = null;
        try {
            resource = new UrlResource(Paths.get("/home/foge/java/AuditCalculator/app.exe").toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok()
                .body(resource);
    }
}
