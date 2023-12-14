package team.firestorm.updateapp;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class UpdateController {
//    private static final String SERVER_URL = "http://195.201.60.237:8080/";
    private static final String SERVER_URL = "http://localhost:8080/";
    private final URL resource = UpdateController.class.getResource("/team/firestorm/Version");

    @SneakyThrows
    public String fetchVersionFromServer() {
        @Cleanup("disconnect") HttpURLConnection connection = (HttpURLConnection) new URL(SERVER_URL + "version").openConnection();
        connection.setRequestMethod("GET");
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder version = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                version.append(line);
            }
            return version.toString();
        } else {
            throw new RuntimeException("Failed to get version");
        }
    }

    @SneakyThrows
    public String currentVersionFromFile() {
        Path pathToFile = Path.of(resource.toURI());
        List<String> lines = Files.readAllLines(pathToFile);
        return String.join("", lines);
    }

    @SneakyThrows
    public void downloadUpdate() {
        @Cleanup InputStream inputStream = new URL(SERVER_URL + "download").openStream();
            Path sourcePath = Paths.get(".", "AuditCalculator.exe");
            Files.copy(inputStream, sourcePath, StandardCopyOption.REPLACE_EXISTING);
    }

    @SneakyThrows
    public void updateVersionInFile(String current) {
        Path pathToFile = Path.of(resource.toURI());
        Files.writeString(pathToFile, current);
    }
}
