package team.firestorm.updateapp;

public class Launcher {
    public static void main(String[] args) {
        Updater updater = new Updater();
        String serverVersion = updater.fetchVersionFromServer();
        String localVersion = updater.currentVersionFromFile();
        if (!serverVersion.equals(localVersion)) {
            updater.downloadUpdate();
            updater.updateVersionInFile(serverVersion);
            updater.replaceExe();
            updater.deleteTmpFile();
        }
    }
}
