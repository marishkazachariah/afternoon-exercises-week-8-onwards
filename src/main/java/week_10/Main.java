package week_10;

import java.util.concurrent.ExecutionException;

public class Main {
    // Exercise 1.2 & 2.2
    public static void main(String[] args) {
        BankAccount account = new BankAccount(150, 234234);
        int customerAmount = 8;

        for (int i = 0; i < customerAmount; i++) {
            Thread customerThread = new Thread(() -> {
                account.deposit(100);
                account.withdraw(50);
            });
            customerThread.start();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Final balance: " + account.getBalance());

        // Exercise 3.2
        Bank bank = new Bank();
        BankAccount account1 = new BankAccount(321, 234234);
        BankAccount account2 = new BankAccount(200, 234234);

        int numTransactions = 5;

        for (int i = 0; i < numTransactions; i++) {
            Thread transactionThread = new Thread(() -> {
                bank.getAccount(234234);
            });
            transactionThread.start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Final balance in account1: " + account1.getBalance());
        System.out.println("Final balance in account2: " + account2.getBalance());
    }
}
