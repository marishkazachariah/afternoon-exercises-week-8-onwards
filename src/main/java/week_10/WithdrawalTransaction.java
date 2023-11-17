package week_10;

// Exercise 1.2
public class WithdrawalTransaction implements Transaction{
    @Override
    public void process(BankAccountDetails account, double amount) {
        if (amount < 0) {
            System.out.println("Invalid withdrawal amount");
        } else if(amount > account.getBalance()) {
            System.out.println("Insufficient balance");
        } else {
            account.setBalance(account.getBalance() - amount);
        }
    }
}
