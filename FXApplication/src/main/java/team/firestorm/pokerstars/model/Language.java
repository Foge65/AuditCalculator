package team.firestorm.pokerstars.model;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Language {
    static {
        loadProfile();
    }

    private CsvParser csvParser;

    private static void loadProfile() {
        try {
            DetectorFactory.loadProfile("FXApplication/src/main/resources/profiles");
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
