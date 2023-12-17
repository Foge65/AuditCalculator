package team.firestorm;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorLog {
    public static void errorsToFile() {
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            try (PrintWriter printWriter = new PrintWriter(new FileWriter("Errors.log", true))) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime localDateTime = LocalDateTime.now();
                printWriter.println("=== Uncaught Exception === " + dateTimeFormatter.format(localDateTime));
                throwable.printStackTrace(printWriter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
