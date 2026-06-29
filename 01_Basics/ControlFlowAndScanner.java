package basics;

import java.util.Scanner;

/**
 * 01_Basics - Control Flow and Scanner Input
 * This file demonstrates reading input from the user using Scanner, conditional statements (if-else),
 * loops (for, while), and classic coding exercises.
 */
public class ControlFlowAndScanner {

    public static void main(String[] args) {
        // We use try-with-resources to automatically close the Scanner
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("=== 1. Basic Console Input with Scanner ===");
            System.out.print("Enter your name: ");
            String name = sc.nextLine();
            System.out.print("Enter your age: ");
            int age = sc.nextInt();
            System.out.println("Welcome, " + name + "! You are " + age + " years old.");

            System.out.println("\n=== 2. Conditional Statements (if-else) ===");
            if (age >= 18) {
                System.out.println("Status: You are an adult.");
            } else if (age > 0) {
                System.out.println("Status: You are a minor.");
            } else {
                System.out.println("Status: Invalid age entered.");
            }

            // Simple Interest Calculation with input
            System.out.println("\n=== 3. Simple Interest Calculation ===");
            System.out.print("Enter Principal Amount: ");
            double principal = sc.nextDouble();
            System.out.print("Enter Annual Interest Rate (%): ");
            double rate = sc.nextDouble();
            System.out.print("Enter Time Period (in years): ");
            double time = sc.nextDouble();
            
            double simpleInterest = (principal * rate * time) / 100.0;
            System.out.printf("Simple Interest = $%.2f%n", simpleInterest);

            System.out.println("\n=== 4. Loops: For Loop (Multiplication Table) ===");
            System.out.print("Enter a number to print its multiplication table: ");
            int tableNum = sc.nextInt();
            for (int i = 1; i <= 10; i++) {
                System.out.printf("%d x %d = %d%n", tableNum, i, tableNum * i);
            }

            System.out.println("\n=== 5. Loops: While Loop (Factorial Calculation) ===");
            System.out.print("Enter a positive number to compute its factorial: ");
            int n = sc.nextInt();
            long factorial = computeFactorial(n);
            System.out.printf("Factorial of %d is %d%n", n, factorial);

            System.out.println("\n=== 6. Algorithm: Fibonacci Sequence ===");
            System.out.print("Enter the number of terms for the Fibonacci sequence: ");
            int terms = sc.nextInt();
            printFibonacci(terms);
        }
    }

    /**
     * Computes the factorial of a number using a while loop.
     */
    public static long computeFactorial(int n) {
        long result = 1;
        int count = 1;
        while (count <= n) {
            result *= count;
            count++;
        }
        return result;
    }

    /**
     * Prints the Fibonacci sequence up to specified terms.
     */
    public static void printFibonacci(int terms) {
        int first = 0, second = 1;
        System.out.print("Fibonacci Series up to " + terms + " terms: ");
        for (int i = 1; i <= terms; i++) {
            System.out.print(first + " ");
            int next = first + second;
            first = second;
            second = next;
        }
        System.out.println();
    }
}
