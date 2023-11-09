package week_10;

public class BankAccountTransactions{

    private final BankAccountDetails account;

    public BankAccountTransactions(BankAccountDetails account) {
        this.account = account;
    }

    public synchronized void deposit(BankAccountDetails account, double amount) {
        double balance = account.getBalance();

        if (amount <= 0) {
            System.err.println("Invalid deposit amount");
        } else {
            balance += amount;
            System.out.println("New balance: $" + balance);
        }
    }

    public synchronized void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
        }else {
            System.out.println("Insufficient balance");
        }
        double balance = account.getBalance() - amount;
        account.setBalance(balance);
    }
}
