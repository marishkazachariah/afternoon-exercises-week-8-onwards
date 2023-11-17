package week_10;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class BankFixedThreadPool {
    private final List<BankAccountDetails> accounts = new ArrayList<>();
    private final ExecutorService executorService;

    BankFixedThreadPool() {
        this.executorService = Executors.newFixedThreadPool(6);
    }


    public void createAccount(double initialBalance, int accountNumber) {
        BankAccountDetails account = new BankAccountDetails(initialBalance, accountNumber);
        accounts.add(account);
    }

    public BankAccountDetails getAccount(int accountNumber) {
        for (BankAccountDetails account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    public void executeCustomerTransactions() {
        for(BankAccountDetails account : accounts) {
            int transactionAmount = 125;
            Customer customer = new Customer(account, executorService, transactionAmount);
            executorService.submit(customer);
        }
        executorService.shutdown();
    }
}