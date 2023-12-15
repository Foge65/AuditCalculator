package team.firestorm.pokerstars.model;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.net.URL;
import java.nio.file.Paths;

@AllArgsConstructor
public class Language {
    static {
        loadProfile();
    }

    private CsvParser csvParser;

    @SneakyThrows
    private static void loadProfile() {
        try {
            URL resource = Language.class.getClassLoader().getResource("profiles");
            DetectorFactory.loadProfile(Paths.get(resource.toURI()).toAbsolutePath().toString());
        } catch (LangDetectException e) {
            e.printStackTrace();
        }
    }

    public String detect() {
        String firstLine = csvParser.getFirstString();
        String language = null;
        try {
            Detector detector = DetectorFactory.create();
            detector.append(firstLine);
            language = detector.detect();
        } catch (LangDetectException e) {
            e.printStackTrace();
        }
        return language;
    }
}
