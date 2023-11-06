package week_10;

// Exercise 1.1
public class Customer implements Runnable{
    private final BankAccount account;

    public Customer(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        account.deposit(100);
    }
}
