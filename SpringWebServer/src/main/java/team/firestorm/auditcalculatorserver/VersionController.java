package team.firestorm.auditcalculatorserver;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class VersionController {
    public VersionController() {
        createVersionFile();
    }

    private void createVersionFile() {
        File file = new File("version");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/version")
    public String currentVersion() {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get("", "version").toAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return String.join("", lines);
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> update() {
        Resource resource;
        try {
            resource = new UrlResource(Paths.get("/home/foge/java/auditcalculator/app.exe").toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().body(resource);
    }

    @PatchMapping("/version")
    public void setNewVersion(@RequestBody Map<String, String> body) {
        String newVersion = body.get("version");
        try {
            List<String> lines;
            Path version = Path.of("version");
            try (Stream<String> stream = Files.lines(version)) {
                lines = stream.collect(Collectors.toList());
            }
            lines.set(0, newVersion);
            Files.write(version, lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
