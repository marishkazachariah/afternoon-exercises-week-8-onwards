package week_10;

// Exercise 2.1
public class BankAccount {
    private double balance;
    private final int accountNumber;

    public BankAccount(double balance, int accountNumber) {
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public synchronized void deposit(int amount) {
        if (amount <= 0) {
            System.err.println("Invalid deposit amount");
        } else {
            balance += amount;
            System.out.println("New balance: $" + balance);
        }
    }

    public synchronized void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
        }else {
            System.out.println("Insufficient balance");
        }
    }
}
