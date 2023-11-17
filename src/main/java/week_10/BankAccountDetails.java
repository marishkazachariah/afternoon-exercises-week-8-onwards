package week_10;

public class BankAccountDetails {
    private double balance;
    private final int accountNumber;

    public BankAccountDetails(double balance, int accountNumber) {
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}
