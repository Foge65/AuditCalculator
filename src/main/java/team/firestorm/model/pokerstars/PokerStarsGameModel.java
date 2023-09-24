package team.firestorm.model.pokerstars;

import javafx.beans.property.ObjectProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Getter
@AllArgsConstructor
public class PokerStarsGameModel {
    private ObjectProperty<LocalDateTime> date = dateProperty();
    private Set<String> game;
    private Map<String, Integer> countRegistration;
    private Map<String, BigDecimal> sumRegistration;
    private Map<String, Integer> countUnregistration;
    private Map<String, BigDecimal> sumUnregistration;
    private Map<String, BigDecimal> sumNetWon;
    private Map<String, BigDecimal> sumRegistrationForTMoney;
    private Map<String, Integer> countRegistrationByTicket;
    private Map<String, BigDecimal> sumProfit;
    private Map<String, BigDecimal> sumBonus;

    public PokerStarsGameModel(Set<String> gameLimit) {
        this.game = gameLimit;
    }

    public ObjectProperty<LocalDateTime> dateProperty() {
        return date;
    }

}
