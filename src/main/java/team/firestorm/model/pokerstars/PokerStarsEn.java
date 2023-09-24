package team.firestorm.model.pokerstars;

public class PokerStarsEn extends PokerStarsBase {
    private static final String REGISTRATION = "Tournament Registration";
    private static final String UNREGISTRATION = "Tournament Unregistration";
    private static final String WON = "Tournament Won";
    private static final String REENTRY = "Tournament Re-entry";
    private static final String KNOCKOUT = "Reward: Knockout Bounty";
    private static final String WITHDRAWAL = "Withdrawal";
    private static final String SENT = "Real Money Transfer Sent";
    private static final String RECEIVED1 = "Real Money Transfer Received";
    private static final String RECEIVED2 = "Transfer Received (Admin)";
    private static final String DEPOSIT = "Deposit (Player)";

    public PokerStarsEn(StringProcessor stringProcessor) {
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
        return string.replace(",", "");
    }

}
