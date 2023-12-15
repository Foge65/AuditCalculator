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

public class Updater {
    private static final String SERVER_URL = "http://195.201.60.237:8080/";
    private final Path pathToVersionFile = Paths.get("Version").toAbsolutePath();

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
        List<String> lines = Files.readAllLines(pathToVersionFile);
        return String.join("", lines);
    }

    @SneakyThrows
    public void downloadUpdate() {
        System.out.println("Downloading Update!");
        @Cleanup InputStream inputStream = new URL(SERVER_URL + "download").openStream();
        Path sourcePath = Paths.get(".", "app.tmp");
        Files.copy(inputStream, sourcePath, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Update Downloaded!");
    }

    @SneakyThrows
    public void updateVersionInFile(String current) {
        Files.writeString(pathToVersionFile, current);
    }

    @SneakyThrows
    public void replaceExe() {
        String newFile = "app.tmp";
        String oldFile = "app.exe";
        Files.copy(Path.of(newFile), Path.of(oldFile), StandardCopyOption.REPLACE_EXISTING);
    }

    @SneakyThrows
    public void deleteTmpFile() {
        Files.deleteIfExists(Path.of("app.tmp"));
    }
}
