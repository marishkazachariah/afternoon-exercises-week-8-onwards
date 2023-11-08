package week_10;

public class Main {
    public static void main(String[] args) {
        // Exercise 1.1
        Bank bank = new Bank();
        bank.createAccount(400, 2342321);
        bank.createAccount(330, 6453345);
        bank.executeCustomerTransactions();     // Cached Thread Pool

        // Exercise 1.2 - Fixed Thread Pool
        BankFixedThreadPool fixedThreadBank = new BankFixedThreadPool();
        fixedThreadBank.executeCustomerTransactions();
    }
}
