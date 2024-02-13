package team.firestorm.auditcalculatorserver;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class VersionController {
    static {
        createVersionFile();
    }

    private static void createVersionFile() {
        final File file = new File("version");
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
        final List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get("", "version").toAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return String.join("", lines);
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> update() {
        final Resource resource;
        try {
            resource = new UrlResource(Paths.get("/home/foge/java/auditcalculator/app.exe").toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().body(resource);
    }
}
