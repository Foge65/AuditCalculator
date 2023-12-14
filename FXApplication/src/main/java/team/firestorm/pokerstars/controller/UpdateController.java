package team.firestorm.pokerstars.controller;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateController {
    private static final String SERVER_URL = "http://localhost:8080/";

    @SneakyThrows
    public String fetchVersion() {
        String url = SERVER_URL + "version";
        @Cleanup("disconnect") HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        } else {
            throw new RuntimeException("Failed to get version. HTTP error code: " + responseCode);
        }
    }
}
