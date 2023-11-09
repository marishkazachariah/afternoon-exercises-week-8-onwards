package week_10;

// Exercise 1.2
public interface Transaction {
    // updated implementation
    void process(BankAccountDetails account, double amount);

    // old implementation
    // void deposit(double amount);
    // void withdraw(double amount);
}
