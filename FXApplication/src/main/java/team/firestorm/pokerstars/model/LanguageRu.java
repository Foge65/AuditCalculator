package team.firestorm.pokerstars.model;

import java.time.format.DateTimeFormatter;

public class LanguageRu extends PokerStarsBase implements DateFormat {
    private static final String DATETIMEFORMAT = "dd.MM.yyyy h:mm a";
    private static final String REGISTRATION = "Регистрация в турнире";
    private static final String UNREGISTRATION = "Отмена регистрации в турнире";
    private static final String NETWON = "Победа в турнире";
    private static final String REENTRY = "Ре-ентри в турнире";
    private static final String KNOCKOUT = "Промежуточная выплата в турнире";
    private static final String WITHDRAWAL = "Вывод средств";
    private static final String SENT = "Деньги отправлены";
    private static final String RECEIVED1 = "Деньги получены";
    private static final String RECEIVED2 = "Перевод получен (админ)";
    private static final String DEPOSIT = "Депозит (игрок)";
    private static final String CHEST_REWARD = "Награда из подарка";
    private static final String BUY_CHIPS = "Награда при покупке фишек";
    private static final String CASINO = "Казино: ";
    private static final String[] BONUSES = new String[]{"LC_", "Бонус зачислен"};
    private static final String SEAT_IN_TABLE = "Бай-ин на столе";
    private static final String SEAT_OUT_TABLE = "Покинуть стол";
    private static final String AUTO_REBUY_TABLE = "Авторебай на столе";

    public LanguageRu(CsvParser csvParser) {
        super(csvParser);
    }

    @Override
    public String replaceComma(String string) {
        return string.replace(" ", "").replace(",", ".");
    }

    @Override
    public DateTimeFormatter getFormat() {
        return DateTimeFormatter.ofPattern(DATETIMEFORMAT);
    }

    @Override
    public String getRegistrationString() {
        return REGISTRATION;
    }

    @Override
    public String getUnRegistrationString() {
        return UNREGISTRATION;
    }

    @Override
    public String getNetWonString() {
        return NETWON;
    }

    @Override
    public String getReEntryString() {
        return REENTRY;
    }

    @Override
    public String getKnockOutString() {
        return KNOCKOUT;
    }

    @Override
    public String getWithdrawalString() {
        return WITHDRAWAL;
    }

    @Override
    public String getMoneySentString() {
        return SENT;
    }

    @Override
    public String getMoneyReceivedStringVer1() {
        return RECEIVED1;
    }

    @Override
    public String getMoneyReceivedStringVer2() {
        return RECEIVED2;
    }

    @Override
    public String getDepositString() {
        return DEPOSIT;
    }

    @Override
    public String getChestString() {
        return CHEST_REWARD;
    }

    @Override
    public String getExchangeCoinString() {
        return BUY_CHIPS;
    }

    @Override
    public String getCasinoString() {
        return CASINO;
    }

    @Override
    public String[] getBonuses() {
        return BONUSES;
    }

    @Override
    public String getSeatInTable() {
        return SEAT_IN_TABLE;
    }

    @Override
    public String getSeatOutTable() {
        return SEAT_OUT_TABLE;
    }

    @Override
    public String getAutoRebuyTable() {
        return AUTO_REBUY_TABLE;
    }
}
