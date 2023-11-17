package week_10;


import java.util.concurrent.ExecutorService;

// Exercise 1.1
public class Customer implements Runnable{
    private final BankAccountDetails account;
    private final BankAccountTransactions bankAccountTransactions;
    private final ExecutorService executorService;
    private final double depositAmount;

    public Customer(BankAccountDetails account, ExecutorService executorService, double depositAmount) {
        this.account = account;
        this.bankAccountTransactions = new BankAccountTransactions(account);
        this.executorService = executorService;
        this.depositAmount = depositAmount;
    }

    @Override
    public void run() {
        System.out.println(account + " is depositing: " + depositAmount);
        executorService.submit(() -> bankAccountTransactions.deposit(account, depositAmount));
        System.out.println("New balance: " + account.getBalance());
    }
}
