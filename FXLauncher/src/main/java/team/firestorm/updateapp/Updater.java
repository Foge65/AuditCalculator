package team.firestorm.updateapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class Updater {
    private static final String SERVER_URL = "http://195.201.60.237:8080/";
    private final Path pathToVersionFile = Paths.get("Version").toAbsolutePath();

    public String fetchVersionFromServer() {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(SERVER_URL + "version").openConnection();
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
        } catch (MalformedURLException | ProtocolException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }

    public String currentVersionFromFile() {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(pathToVersionFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return String.join("", lines);
    }

    public void downloadUpdate() {
        System.out.println("Downloading Update!");
        try (InputStream inputStream = new URL(SERVER_URL + "download").openStream()){
            Path sourcePath = Paths.get(".", "app.tmp");
            Files.copy(inputStream, sourcePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Update Downloaded!");
    }

    public void updateVersionInFile(String current) {
        try {
            Files.writeString(pathToVersionFile, current);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void replaceExe() {
        String newFile = "app.tmp";
        String oldFile = "app.exe";
        try {
            Files.copy(Path.of(newFile), Path.of(oldFile), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTmpFile() {
        try {
            Files.deleteIfExists(Path.of("app.tmp"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
