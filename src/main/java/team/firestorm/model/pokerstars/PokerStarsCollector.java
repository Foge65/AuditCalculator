package team.firestorm.model.pokerstars;

import lombok.Getter;

import java.io.File;

public class PokerStarsCollector {
    private StringProcessor stringProcessor;
    private LanguageDetection languageDetection;
    @Getter
    private PokerStarsBuilder pokerStarsBuilder;

    public PokerStarsCollector(File file) {
        stringProcessor = new StringProcessor(file);
        languageDetection = new LanguageDetection(stringProcessor);
        pokerStarsBuilder = new PokerStarsBuilder(languageDetection, stringProcessor);
    }
}
