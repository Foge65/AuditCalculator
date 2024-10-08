package team.firestorm.pokerstars.model;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import lombok.AllArgsConstructor;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

@AllArgsConstructor
public class Language {
    static {
        loadProfile();
    }

    private CsvParser csvParser;

    private static void loadProfile() {
        try {
            loadProfileForApp();
        } catch (LangDetectException e1) {
            try {
                loadProfileForTest();
            } catch (LangDetectException | URISyntaxException e2) {
                e2.printStackTrace();
            }
        }
    }

    private static void loadProfileForApp() throws LangDetectException {
        DetectorFactory.loadProfile("profiles");
    }

    public static void loadProfileForTest() throws LangDetectException, URISyntaxException {
        URL resourceUrl = Language.class.getResource("/profiles");
        if (resourceUrl != null) {
            DetectorFactory.loadProfile(new File(resourceUrl.toURI()));
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
