package week_10;

import java.util.ArrayList;
import java.util.List;

// Exercise 3.1
class Bank {
    private final List<BankAccount> accounts = new ArrayList<>();

    public void createAccount(double initialBalance, int accountNumber) {
        BankAccount account = new BankAccount(initialBalance, accountNumber);
        accounts.add(account);
    }

    public BankAccount getAccount(int accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }
}