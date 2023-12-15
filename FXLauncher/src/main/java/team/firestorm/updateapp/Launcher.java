package team.firestorm.updateapp;

import lombok.SneakyThrows;

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

    @SneakyThrows
    private static void launchMainApplication() {
        String pathToApp = "AuditCalculator.exe";
        ProcessBuilder processBuilder = new ProcessBuilder(pathToApp);
        processBuilder.start();
    }
}
