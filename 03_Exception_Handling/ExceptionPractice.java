package exceptions;

/**
 * 03_Exception_Handling Practice Template
 * Complete the classes and methods marked with TODO.
 * Run this class to test your implementations.
 */

// ==========================================
// TASK 2: CUSTOM BANK EXCEPTION
// ==========================================
// TODO: Define a custom checked exception named InsufficientFundsException

class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    // TODO: Implement withdraw method which throws InsufficientFundsException if balance is insufficient
    public void withdraw(double amount) {
        
    }
}

public class ExceptionPractice {

    public static void main(String[] args) {
        System.out.println("=== Running 03_Exception_Handling Practice ===");

        // Test Task 1: Multi-Catch
        System.out.println("Testing Task 1 (Multi-Catch):");
        String[] arr = {"10", "abc", "30"};
        int res1 = parseAndSum(arr, 0, 2); // 40
        int res2 = parseAndSum(arr, 0, 1); // error: NumberFormat, returns -1
        int res3 = parseAndSum(arr, 0, 5); // error: IndexOutOfBounds, returns -1

        if (res1 == 40 && res2 == -1 && res3 == -1) {
            System.out.println("Task 1: PASSED");
        } else {
            System.out.println("Task 1: FAILED");
        }

        // Test Task 2: Banking Exception
        System.out.println("\nTesting Task 2 (Custom Bank Exceptions):");
        BankAccount account = new BankAccount("SAV-9912", 500.0);
        // TODO: Test calling withdraw(100.0) (should work) and withdraw(600.0) (should throw InsufficientFundsException)
    }

    /**
     * Task 1: Parse and sum elements from the array.
     */
    public static int parseAndSum(String[] array, int index1, int index2) {
        // TODO: Implement parsing, summing, and multiple catch blocks.
        return -1;
    }

    /**
     * Task 3: Safe File Copy using try-with-resources.
     */
    public static void copyFile(String src, String dest) {
        // TODO: Implement copy logic using BufferedReader/BufferedWriter in try-with-resources
    }
}
