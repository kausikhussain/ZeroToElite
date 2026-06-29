package exceptions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 03_Exception_Handling - Try-With-Resources Demo
 * This file illustrates:
 * 1. Traditional Resource Cleanup (using finally block)
 * 2. Modern Resource Cleanup using Try-With-Resources (Java 7+)
 * 3. AutoCloseable interface logic
 */
public class TryWithResourcesDemo {

    public static void main(String[] args) {
        System.out.println("=== 1. Try-With-Resources Demonstration ===");
        // The BufferedReader is automatically closed at the end of the try block,
        // because it implements java.lang.AutoCloseable interface.
        try (BufferedReader br = new BufferedReader(new FileReader("nonexistent_temp.txt"))) {
            String line = br.readLine();
            System.out.println("Line read: " + line);
        } catch (IOException e) {
            System.out.println("Caught IOException during File operations: File not found or unreadable! (" + e.getMessage() + ")");
        }

        System.out.println("\n=== 2. Custom AutoCloseable Resource ===");
        // We can create our own resources that implement AutoCloseable to see this in action
        try (CustomResource resource = new CustomResource()) {
            resource.doWork();
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }

    // Static nested class simulating a database connection or file descriptor resource
    static class CustomResource implements AutoCloseable {
        public CustomResource() {
            System.out.println("CustomResource: Opened/Allocated system resources.");
        }

        public void doWork() {
            System.out.println("CustomResource: Performing operations...");
        }

        @Override
        public void close() throws Exception {
            System.out.println("CustomResource: Automatically Closed and Released resources!");
        }
    }
}
