package team.firestorm.model.pokerstars;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;

public class LanguageDetection {
    private StringProcessor StringProcessor;

    public LanguageDetection(StringProcessor stringProcessor) {
        this.StringProcessor = stringProcessor;
    }

    public static void loadProfile() {
        try {
            DetectorFactory.loadProfile("src/main/resources/profiles");
        } catch (LangDetectException e) {
            e.printStackTrace();
        }
    }

    public String detect() {
        String firstLine = StringProcessor.getFirstString();
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
