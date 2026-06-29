package exceptions;

/**
 * 03_Exception_Handling - Exception Handling Demo
 * This file illustrates exception handling in Java:
 * 1. Unchecked Exceptions (RuntimeExceptions - like ArithmeticException)
 * 2. Try-Catch-Finally block
 * 3. Checked Exceptions and the 'throws' keyword
 * 4. Creating and throwing a custom Exception
 */

// Custom Checked Exception
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

public class ExceptionHandlingDemo {

    public static void main(String[] args) {
        System.out.println("=== 1. Unchecked Exception (ArithmeticException) ===");
        try {
            int a = 10;
            int b = 0;
            int result = a / b; // Throws ArithmeticException at runtime
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Caught Unchecked Exception: Division by zero is not allowed! (" + e.getMessage() + ")");
        } finally {
            System.out.println("Finally block executed: This block always runs regardless of exception.");
        }

        System.out.println("\n=== 2. Multiple Catch Blocks ===");
        try {
            String str = null;
            System.out.println("Length: " + str.length()); // Throws NullPointerException
        } catch (ArithmeticException e) {
            System.out.println("Caught ArithmeticException.");
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException: Attempted to reference null object! (" + e.getMessage() + ")");
        } catch (Exception e) {
            System.out.println("Caught general Exception: " + e.getMessage());
        }

        System.out.println("\n=== 3. Custom Exception & 'throw'/'throws' ===");
        try {
            // Under 18 will throw our custom checked Exception
            checkEligibility(15);
        } catch (InvalidAgeException e) {
            System.out.println("Caught Custom Checked Exception: " + e.getMessage());
        }

        try {
            System.out.println("\nChecking eligibility for age 20...");
            checkEligibility(20);
        } catch (InvalidAgeException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }

    /**
     * Method that checks eligibility.
     * Declares that it throws a custom checked exception (InvalidAgeException) using 'throws'.
     */
    public static void checkEligibility(int age) throws InvalidAgeException {
        if (age < 18) {
            // Throwing our custom exception using 'throw'
            throw new InvalidAgeException("Age " + age + " is below the required voting age of 18!");
        } else {
            System.out.println("Access Granted: You are eligible to vote.");
        }
    }
}
