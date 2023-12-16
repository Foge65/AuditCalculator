package team.firestorm.updateapp;

import java.io.IOException;

public class Launcher {
    public static void main(String[] args) {
        update();
    }

    private static void update() {
        Updater updater = new Updater();
        String serverVersion = updater.fetchVersionFromServer();
        String localVersion = updater.currentVersionFromFile();
        if (!serverVersion.equals(localVersion)) {
            updater.downloadUpdate();
            updater.updateVersionInFile(serverVersion);
            updater.replaceExe();
            updater.deleteTmpFile();
            launchMainApplication();
        } else {
            launchMainApplication();
        }
    }

    private static void launchMainApplication() {
        String pathToApp = "app.exe";
        ProcessBuilder processBuilder = new ProcessBuilder(pathToApp);
        try {
            processBuilder.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
