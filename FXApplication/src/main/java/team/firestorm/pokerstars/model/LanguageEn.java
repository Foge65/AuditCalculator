package team.firestorm.pokerstars.model;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LanguageEn extends PokerStarsBase implements DateFormat {
    private static final String DATETIMEFORMAT = "yyyy/MM/dd h:mm a";
    private static final String REGISTRATION = "Tournament Registration";
    private static final String UNREGISTRATION = "Tournament Unregistration";
    private static final String NETWON = "Tournament Won";
    private static final String REENTRY = "Tournament Re-entry";
    private static final String KNOCKOUT = "Reward: Knockout Bounty";
    private static final String INTERIM_PAYOUT = "Tournament Interim Payout";
    private static final String WITHDRAWAL = "Withdrawal";
    private static final String SENT = "Real Money Transfer Sent";
    private static final String[] RECEIVED = new String[]{
            "Real Money Transfer Received",
            "Transfer Received (Admin)"
    };
    private static final String DEPOSIT = "Deposit (Player)";
    private static final String CHEST_REWARD = "Chest Reward";
    private static final String BUY_CHIPS = "Buy Chips";
    private static final String CASINO = "Casino: ";
    private static final String[] BONUSES = new String[]{
            "LC_",
            "Good Will Credit",
            "Bonus credit",
            "Tournament Prize Adjustment",
            "ICE Challenge Reward (Cash)",
            "Conversion from StarsCoin",
    };
    private static final String SEAT_IN_TABLE = "Table Buy In";
    private static final String SEAT_OUT_TABLE = "Leave Table";
    private static final String AUTO_REBUY_TABLE = "Table Auto Rebuy";

    public LanguageEn() {
        super();
    }

    @Override
    public String replaceComma(String string) {
        return string.replace(",", "");
    }

    @Override
    public DateTimeFormatter getFormat() {
        return DateTimeFormatter.ofPattern(DATETIMEFORMAT, Locale.US);
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
    public String getWonString() {
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
    public String getInterimString() {
        return INTERIM_PAYOUT;
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
    public String[] getMoneyReceivedString() {
        return RECEIVED;
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
