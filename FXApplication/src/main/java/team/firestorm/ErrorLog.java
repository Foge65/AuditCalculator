package team.firestorm;

import java.io.FileWriter;
import java.io.PrintWriter;

public class ErrorLog {
    public static void errorsToFile() {
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            try (PrintWriter printWriter = new PrintWriter(new FileWriter("errors.log", true))) {
                printWriter.println("=== Uncaught Exception ===");
                throwable.printStackTrace(printWriter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
