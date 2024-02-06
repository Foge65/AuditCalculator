package team.firestorm.pokerstars.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public class DefaultBonus {
    private final Map<String, BigDecimal> bonus;

    public DefaultBonus(Model model) {
        bonus = new HashMap<>(model.getSumBonusSpin());
    }
}
