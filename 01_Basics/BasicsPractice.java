package basics;

/**
 * 01_Basics Practice Exercises Template
 * Complete the methods below marked with TODO.
 * Run this class to test your implementations.
 */
public class BasicsPractice {

    public static void main(String[] args) {
        System.out.println("=== Running 01_Basics Practice Tests ===");

        // Test Task 1: Swap Variables
        System.out.println("Testing Task 1 (Swap Variables):");
        int[] vals = {5, 10};
        System.out.println("Before: a = " + vals[0] + ", b = " + vals[1]);
        swapWithoutTemp(vals);
        System.out.println("After: a = " + vals[0] + ", b = " + vals[1]);
        if (vals[0] == 10 && vals[1] == 5) {
            System.out.println("Task 1: PASSED");
        } else {
            System.out.println("Task 1: FAILED");
        }

        // Test Task 2: Leap Year Check
        System.out.println("\nTesting Task 2 (Leap Year Checker):");
        boolean test1 = isLeapYear(2000); // true
        boolean test2 = isLeapYear(1900); // false
        boolean test3 = isLeapYear(2024); // true
        boolean test4 = isLeapYear(2023); // false
        
        if (test1 && !test2 && test3 && !test4) {
            System.out.println("Task 2: PASSED");
        } else {
            System.out.println("Task 2: FAILED");
        }

        // Note: Task 3 (Interactive CLI Calculator) is designed to run in console.
        // You can run it manually in main to verify.
        System.out.println("\nTask 3 can be run manually in terminal.");
    }

    /**
     * Task 1: Swap the elements in the 2-element array without using a third variable.
     * @param vals vals[0] is 'a', vals[1] is 'b'
     */
    public static void swapWithoutTemp(int[] vals) {
        // TODO: Implement swap logic using addition/subtraction or bitwise XOR operations.
    }

    /**
     * Task 2: Return true if the year is a leap year, otherwise false.
     */
    public static boolean isLeapYear(int year) {
        // TODO: Implement leap year logic using modulus operators and conditions.
        return false;
    }

    /**
     * Task 3: Interactive CLI Calculator
     * Take inputs continuously using Scanner: "<num1> <operator> <num2>"
     * Exit if the line is "exit".
     */
    public static void runCalculator() {
        // TODO: Implement the interactive CLI calculator.
    }
}
