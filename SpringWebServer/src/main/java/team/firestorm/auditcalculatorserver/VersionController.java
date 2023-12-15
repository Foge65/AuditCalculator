package team.firestorm.auditcalculatorserver;

import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class VersionController {

    @GetMapping("/version")
    @SneakyThrows
    public String currentVersion() {
        Path filePath = Paths.get("", "version").toAbsolutePath();
        List<String> lines = Files.readAllLines(filePath);
        return String.join("", lines);
    }

    @GetMapping("/download")
    @SneakyThrows
    public ResponseEntity<Resource> update() {
        Resource resource = new UrlResource(Paths.get("/home/foge/java/AuditCalculator/app.exe").toUri());
        return ResponseEntity.ok()
                .body(resource);
    }
}
