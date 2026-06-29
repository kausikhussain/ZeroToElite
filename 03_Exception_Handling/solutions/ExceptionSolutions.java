package exceptions.solutions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 03_Exception_Handling Practice Solutions
 * Complete reference implementation for exception exercises.
 */

// Custom Checked Exception
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

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

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Cannot withdraw $" + amount + ": Balance is only $" + balance);
        }
        balance -= amount;
        System.out.printf("Successfully withdrew $%.2f. New Balance: $%.2f%n", amount, balance);
    }
}

public class ExceptionSolutions {

    public static void main(String[] args) {
        System.out.println("=== Running 03_Exception_Handling Solutions ===");

        // Task 1
        System.out.println("--- Task 1: Multi-Catch ---");
        String[] arr = {"10", "abc", "30"};
        System.out.println("Result (0, 2): " + parseAndSum(arr, 0, 2)); // 40
        System.out.println("Result (0, 1): " + parseAndSum(arr, 0, 1)); // -1
        System.out.println("Result (0, 5): " + parseAndSum(arr, 0, 5)); // -1

        // Task 2
        System.out.println("\n--- Task 2: Custom Bank Exceptions ---");
        BankAccount acc = new BankAccount("SAV-9912", 500.00);
        try {
            acc.withdraw(200.00);
            acc.withdraw(400.00); // Should fail
        } catch (InsufficientFundsException e) {
            System.out.println("Caught Expected Bank Error: " + e.getMessage());
        }

        // Task 3
        System.out.println("\n--- Task 3: Safe File Copy ---");
        // We will create a small temp file to copy
        createTempFile("temp_source.txt", "Line 1: Zero To Elite\nLine 2: Keep Coding!\n");
        copyFile("temp_source.txt", "temp_dest.txt");
    }

    /**
     * Parse elements and catch individual exceptions.
     */
    public static int parseAndSum(String[] array, int index1, int index2) {
        try {
            int num1 = Integer.parseInt(array[index1]);
            int num2 = Integer.parseInt(array[index2]);
            return num1 + num2;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Index out of array bounds! (" + e.getMessage() + ")");
            return -1;
        } catch (NumberFormatException e) {
            System.out.println("Error: Cannot parse non-numeric string to integer! (" + e.getMessage() + ")");
            return -1;
        }
    }

    /**
     * File Copy using Try-With-Resources.
     */
    public static void copyFile(String src, String dest) {
        try (BufferedReader reader = new BufferedReader(new FileReader(src));
             BufferedWriter writer = new BufferedWriter(new FileWriter(dest))) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("File copied successfully from " + src + " to " + dest);
            
        } catch (IOException e) {
            System.out.println("IOException during copy operation: " + e.getMessage());
        }
    }

    private static void createTempFile(String filename, String content) {
        try (FileWriter fw = new FileWriter(filename)) {
            fw.write(content);
        } catch (IOException e) {
            System.out.println("Failed to setup test file: " + e.getMessage());
        }
    }
}
