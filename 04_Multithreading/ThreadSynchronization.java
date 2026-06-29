package multithreading;

/**
 * 04_Multithreading - Thread Synchronization
 * This file illustrates:
 * 1. Thread creation (extending Thread class & implementing Runnable interface)
 * 2. Race Conditions (multiple threads modifying shared state)
 * 3. Thread Synchronization using 'synchronized' methods to maintain data consistency
 */

class BankAccount {
    private int balance;

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    /**
     * Synchronized method ensures only one thread can execute this method at a time
     * for a given BankAccount instance. This prevents race conditions.
     */
    public synchronized void withdraw(String accountHolder, int amount) {
        System.out.println(accountHolder + " is trying to withdraw Rs." + amount);
        
        if (amount > balance) {
            System.out.println("Insufficient funds for " + accountHolder + ". Balance: Rs." + balance);
        } else {
            System.out.println(accountHolder + " successfully withdrew Rs." + amount);
            // Simulate processing time to highlight potential race conditions if unsynchronized
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            balance -= amount;
        }
        System.out.println("Remaining balance: Rs." + balance + "\n");
    }
}

class AccountHolder implements Runnable {
    private BankAccount account;
    private String name;
    private int amount;

    public AccountHolder(BankAccount account, String name, int amount) {
        this.account = account;
        this.name = name;
        this.amount = amount;
    }

    @Override
    public void run() {
        account.withdraw(name, amount);
    }
}

public class ThreadSynchronization {
    public static void main(String[] args) {
        System.out.println("=== Starting Thread Synchronization Demo ===");
        System.out.println("Initial Balance: Rs. 5000");
        BankAccount sharedAccount = new BankAccount(5000);

        // Creating threads using the Runnable interface
        Thread customer1 = new Thread(new AccountHolder(sharedAccount, "Customer 1", 3000));
        Thread customer2 = new Thread(new AccountHolder(sharedAccount, "Customer 2", 3000));

        // Start both threads. Since withdraw() is synchronized, Customer 2 will wait
        // until Customer 1 finishes, avoiding double-withdrawal (which would result in a negative balance).
        customer1.start();
        customer2.start();

        // Wait for threads to finish
        try {
            customer1.join();
            customer2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("=== Demo Finished ===");
    }
}
