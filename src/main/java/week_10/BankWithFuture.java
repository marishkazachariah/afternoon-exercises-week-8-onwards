package week_10;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class BankWithFuture {
    private final List<BankAccountDetails> accounts = new ArrayList<>();
    private BankAccountTransactions bankAccountTransactions;
    private final ExecutorService executorService;

    BankWithFuture() {
        this.executorService = Executors.newCachedThreadPool();
    }


    public void createAccount(double initialBalance, int accountNumber) {
        BankAccountDetails account = new BankAccountDetails(initialBalance, accountNumber);
        this.bankAccountTransactions = new BankAccountTransactions(account);
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

    public Future<Void> executeCustomerTransactionAsync(int accountNumber, double depositAmount) {
        Callable<Void> transactionTask = () -> {
            BankAccountDetails account = getAccount(accountNumber);
            if (account != null) {
                bankAccountTransactions.deposit(account, depositAmount);
            }
            return null;
        };
        return executorService.submit(transactionTask);
    }

    public void shutdownExecutor() {
        executorService.shutdown();
    }
}