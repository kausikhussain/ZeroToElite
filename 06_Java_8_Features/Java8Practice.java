package features;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 06_Java_8_Features Practice Template
 * Complete the classes and methods marked with TODO.
 * Run this class to test your implementations.
 */

class Transaction {
    int id;
    double amount;
    int year;

    public Transaction(int id, double amount, int year) {
        this.id = id;
        this.amount = amount;
        this.year = year;
    }
}

class Employee {
    String name;
    String department;
    double salary;

    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
}

public class Java8Practice {

    public static void main(String[] args) {
        System.out.println("=== Running 06_Java_8_Features Practice ===");

        // Test Task 1: Stream Filter
        System.out.println("Testing Task 1 (Filter and Uppercase):");
        List<String> words = List.of("apple", "banana", "apricot", "avocado", "cherry");
        List<String> res1 = filterAndUppercase(words, "ap");
        System.out.println("Result: " + res1);
        // Expected: [APPLE, APRICOT]
        if (res1 != null && res1.size() == 2 && res1.get(1).equals("APRICOT")) {
            System.out.println("Task 1: PASSED");
        } else {
            System.out.println("Task 1: FAILED");
        }

        // Test Task 2: Transaction Sum
        System.out.println("\nTesting Task 2 (Transaction Sum):");
        List<Transaction> txs = List.of(
            new Transaction(1, 100.0, 2024),
            new Transaction(2, 250.0, 2023),
            new Transaction(3, 150.0, 2024)
        );
        double total2024 = sumTransactionAmountForYear(txs, 2024);
        System.out.println("Total 2024: " + total2024); // Expected 250.0
        if (total2024 == 250.0) {
            System.out.println("Task 2: PASSED");
        } else {
            System.out.println("Task 2: FAILED");
        }

        // Test Task 3: Employee Grouping
        System.out.println("\nTesting Task 3 (Highest Paid by Department):");
        List<Employee> emps = List.of(
            new Employee("Alice", "Engineering", 80000),
            new Employee("Bob", "Engineering", 95000),
            new Employee("Charlie", "HR", 60000),
            new Employee("David", "HR", 65000)
        );
        Map<String, Employee> highestPaid = highestPaidByDepartment(emps);
        if (highestPaid != null && highestPaid.get("Engineering").name.equals("Bob") && highestPaid.get("HR").name.equals("David")) {
            System.out.println("Task 3: PASSED");
        } else {
            System.out.println("Task 3: FAILED");
        }
    }

    /**
     * Task 1: Filter words starting with prefix, convert to uppercase, and sort.
     */
    public static List<String> filterAndUppercase(List<String> words, String prefix) {
        // TODO: Implement stream filter, map, sorted, collect.
        return null;
    }

    /**
     * Task 2: Sum up transaction amounts matching the targetYear.
     */
    public static double sumTransactionAmountForYear(List<Transaction> transactions, int targetYear) {
        // TODO: Implement stream filter, mapToDouble, sum.
        return 0.0;
    }

    /**
     * Task 3: Group employees by department and return a mapping of department -> highest paid employee.
     */
    public static Map<String, Employee> highestPaidByDepartment(List<Employee> employees) {
        // TODO: Implement Collectors.groupingBy with nested reducer.
        return null;
    }
}
