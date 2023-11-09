package week_10;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Exercise 1.2
class BankFixedThreadPool {
    private final List<BankAccount> accounts = new ArrayList<>();
    private final ExecutorService executorService;

    BankFixedThreadPool() {
        this.executorService = Executors.newFixedThreadPool(6);
    }


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

    public void executeCustomerTransactions() {
        for(BankAccount account : accounts) {
            int transactionAmount = 125;
            Customer customer = new Customer(account, executorService, transactionAmount);
            executorService.submit(customer);
        }
        executorService.shutdown();
    }
}