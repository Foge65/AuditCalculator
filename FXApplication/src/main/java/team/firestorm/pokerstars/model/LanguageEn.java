package team.firestorm.pokerstars.model;

import java.time.format.DateTimeFormatter;

public class LanguageEn extends PokerStarsBase implements DateFormat {
    private static final String DATETIMEFORMAT = "yyyy/MM/dd h:mm a";
    private static final String REGISTRATION = "Tournament Registration";
    private static final String UNREGISTRATION = "Tournament Unregistration";
    private static final String NETWON = "Tournament Won";
    private static final String REENTRY = "Tournament Re-entry";
    private static final String KNOCKOUT = "Reward: Knockout Bounty";
    private static final String WITHDRAWAL = "Withdrawal";
    private static final String SENT = "Real Money Transfer Sent";
    private static final String RECEIVED1 = "Real Money Transfer Received";
    private static final String RECEIVED2 = "Transfer Received (Admin)";
    private static final String DEPOSIT = "Deposit (Player)";
    private static final String CHEST_REWARD = "Chest Reward";
    private static final String BUY_CHIPS = "Buy Chips";
    private static final String CASINO = "Casino: ";

    public LanguageEn(CsvParser csvParser) {
        super(csvParser);
    }

    @Override
    public String replaceComma(String string) {
        return string.replace(",", "");
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
}
