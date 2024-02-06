package team.firestorm.pokerstars.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public class DefaultProfit {
    private final Map<String, BigDecimal> profit;

    public DefaultProfit(Model model) {
        profit = new HashMap<>(model.getSumProfitSpin());
    }
}
