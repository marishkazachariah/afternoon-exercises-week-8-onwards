package week_10;


import java.util.concurrent.ExecutorService;

// Exercise 1.1
public class Customer implements Runnable{
    private final BankAccount account;
    private final ExecutorService executorService;
    private final double depositAmount;

    public Customer(BankAccount account, ExecutorService executorService, double depositAmount) {
        this.account = account;
        this.executorService = executorService;
        this.depositAmount = depositAmount;
    }

    @Override
    public void run() {
        System.out.println(account + " is depositing: " + depositAmount);
        executorService.submit(() -> account.deposit(depositAmount));
        System.out.println("New balance: " + account.getBalance());
    }
}
