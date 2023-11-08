package week_10;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// Exercise 3
class BankWithFuture {
    private final List<BankAccount> accounts = new ArrayList<>();
    private final ExecutorService executorService;

    BankWithFuture() {
        this.executorService = Executors.newCachedThreadPool();
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

    public Future<Void> executeCustomerTransactionAsync(int accountNumber, double depositAmount) {
        Callable<Void> transactionTask = () -> {
            BankAccount account = getAccount(accountNumber);
            if (account != null) {
                account.deposit(depositAmount);
            }
            return null;
        };
        return executorService.submit(transactionTask);
    }

    public void shutdownExecutor() {
        executorService.shutdown();
    }
}