package week_10;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Bank bank = new Bank();
        bank.createAccount(400, 2342321);
        bank.createAccount(330, 6453345);
        bank.executeCustomerTransactions();     // Cached Thread Pool

        // Fixed Thread Pool
        BankFixedThreadPool fixedThreadBank = new BankFixedThreadPool();
        fixedThreadBank.createAccount(400, 2342321);
        fixedThreadBank.createAccount(330, 6453345);
        fixedThreadBank.executeCustomerTransactions();

        callBankWithFuture();
    }

    public static void callBankWithFuture() throws ExecutionException, InterruptedException {
        BankWithFuture bankWithFuture = new BankWithFuture();
        bankWithFuture.createAccount(400, 2342321);
        bankWithFuture.createAccount(330, 6453345);

        Future<Void> transaction1 = bankWithFuture.executeCustomerTransactionAsync(2342321, 50);
        Future<Void> transaction2 = bankWithFuture.executeCustomerTransactionAsync(6453345, 50);

        transaction1.get();
        transaction2.get();

        bankWithFuture.shutdownExecutor();
    }
}
