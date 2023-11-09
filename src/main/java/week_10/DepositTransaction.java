package week_10;

// Exercise 1.2
public class DepositTransaction implements Transaction{
    @Override
    public void process(BankAccountDetails account, double amount) {
        if (amount > 0 && amount <= account.getBalance()) {
            account.setBalance(account.getAccountNumber() - amount);
        }
    }
}
