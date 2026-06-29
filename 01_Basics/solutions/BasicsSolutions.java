package basics.solutions;

import java.util.Scanner;

/**
 * 01_Basics Practice Solutions
 * Complete reference implementation for basics exercises.
 */
public class BasicsSolutions {

    public static void main(String[] args) {
        System.out.println("=== Running 01_Basics Solutions ===");

        // Task 1: Swap
        int[] vals = {5, 10};
        swapWithoutTemp(vals);
        System.out.println("Swap Result -> a: " + vals[0] + ", b: " + vals[1]);

        // Task 2: Leap Year
        System.out.println("Is 2000 leap? " + isLeapYear(2000));
        System.out.println("Is 1900 leap? " + isLeapYear(1900));

        // Un-comment to test the interactive calculator
        // runCalculator();
    }

    /**
     * Swap variables using addition and subtraction.
     * a = a + b
     * b = a - b (which equals original a)
     * a = a - b (which equals original b)
     */
    public static void swapWithoutTemp(int[] vals) {
        if (vals == null || vals.length < 2) return;
        vals[0] = vals[0] + vals[1]; // a becomes (a + b)
        vals[1] = vals[0] - vals[1]; // b becomes (a + b) - b = a
        vals[0] = vals[0] - vals[1]; // a becomes (a + b) - a = b
    }

    /**
     * Leap Year Check logic.
     */
    public static boolean isLeapYear(int year) {
        if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else {
            return year % 4 == 0;
        }
    }

    /**
     * Continuous CLI calculator with error handling.
     */
    public static void runCalculator() {
        System.out.println("CLI Calculator Started. Enter expression (e.g. '5 + 3') or type 'exit' to quit:");
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                String input = sc.nextLine().trim();
                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting calculator. Goodbye!");
                    break;
                }

                String[] tokens = input.split("\\s+");
                if (tokens.length != 3) {
                    System.out.println("Invalid format. Use: <number> <operator> <number>");
                    continue;
                }

                try {
                    double num1 = Double.parseDouble(tokens[0]);
                    String op = tokens[1];
                    double num2 = Double.parseDouble(tokens[2]);
                    double result = 0;
                    boolean valid = true;

                    switch (op) {
                        case "+":
                            result = num1 + num2;
                            break;
                        case "-":
                            result = num1 - num2;
                            break;
                        case "*":
                            result = num1 * num2;
                            break;
                        case "/":
                            if (num2 == 0) {
                                System.out.println("Error: Division by zero.");
                                valid = false;
                            } else {
                                result = num1 / num2;
                            }
                            break;
                        case "%":
                            if (num2 == 0) {
                                System.out.println("Error: Division by zero.");
                                valid = false;
                            } else {
                                result = num1 % num2;
                            }
                            break;
                        default:
                            System.out.println("Unsupported operator: " + op);
                            valid = false;
                    }

                    if (valid) {
                        System.out.println("Result: " + result);
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Invalid numbers. Make sure to input valid floats/integers.");
                }
            }
        }
    }
}
