package team.firestorm.model.pokerstars;

public class PokerStarsRu extends PokerStarsBase {
    private static final String REGISTRATION = "Регистрация в турнире";
    private static final String UNREGISTRATION = "Отмена регистрации в турнире";
    private static final String WON = "Победа в турнире";

    // TODO : Найти аудит на русском, где есть повторные входы
    private static final String REENTRY = "";
    private static final String KNOCKOUT = "";
    private static final String WITHDRAWAL = "Вывод средств";
    private static final String SENT = "Деньги отправлены";
    private static final String RECEIVED1 = "Деньги получены";
    private static final String RECEIVED2 = "Перевод получен (админ)";
    private static final String DEPOSIT = "Депозит (игрок)";

    public PokerStarsRu(StringProcessor stringProcessor) {
        super(stringProcessor);
    }

    @Override
    public String getRegistration() {
        return REGISTRATION;
    }

    @Override
    public String getUnregistration() {
        return UNREGISTRATION;
    }

    @Override
    public String getWon() {
        return WON;
    }

    @Override
    public String getReEntry() {
        return REENTRY;
    }

    @Override
    public String getKnockOut() {
        return KNOCKOUT;
    }

    @Override
    public String getWithdrawal() {
        return WITHDRAWAL;
    }

    @Override
    public String getMoneySent() {
        return SENT;
    }

    @Override
    public String getMoneyReceived1() {
        return RECEIVED1;
    }

    @Override
    public String getMoneyReceived2() {
        return RECEIVED2;
    }

    @Override
    public String getDeposit() {
        return DEPOSIT;
    }

    @Override
    public String replaceComma(String string) {
        return string.replace(" ", "").replace(",", ".");
    }

}
