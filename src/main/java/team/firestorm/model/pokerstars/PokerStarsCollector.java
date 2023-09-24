package team.firestorm.model.pokerstars;

import lombok.Getter;

import java.io.File;

public class PokerStarsCollector {
    private final StringProcessor stringProcessor;
    private final LanguageDetection languageDetection;
    @Getter
    private final PokerStarsBuilder pokerStarsBuilder;

    public PokerStarsCollector(File file) {
        stringProcessor = new StringProcessor(file);
        languageDetection = new LanguageDetection(stringProcessor);
        pokerStarsBuilder = new PokerStarsBuilder(languageDetection, stringProcessor);
    }
}
