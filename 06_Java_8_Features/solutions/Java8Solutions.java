package features.solutions;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 06_Java_8_Features Practice Solutions
 * Complete reference implementation for Java 8 Stream/Lambda exercises.
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

    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return name + " ($" + salary + ")";
    }
}

public class Java8Solutions {

    public static void main(String[] args) {
        System.out.println("=== Running 06_Java_8_Features Solutions ===");

        // Task 1
        List<String> words = List.of("apple", "banana", "apricot", "avocado", "cherry");
        System.out.println("Filter 'ap': " + filterAndUppercase(words, "ap"));

        // Task 2
        List<Transaction> txs = List.of(
            new Transaction(1, 100.0, 2024),
            new Transaction(2, 250.0, 2023),
            new Transaction(3, 150.0, 2024)
        );
        System.out.println("Sum for 2024: " + sumTransactionAmountForYear(txs, 2024));

        // Task 3
        List<Employee> emps = List.of(
            new Employee("Alice", "Engineering", 80000),
            new Employee("Bob", "Engineering", 95000),
            new Employee("Charlie", "HR", 60000),
            new Employee("David", "HR", 65000)
        );
        System.out.println("Highest paid by department: " + highestPaidByDepartment(emps));
    }

    /**
     * Java 8 Stream Pipeline for string filtration and mapping.
     */
    public static List<String> filterAndUppercase(List<String> words, String prefix) {
        if (words == null) return new ArrayList<>();
        return words.stream()
            .filter(w -> w.startsWith(prefix))
            .map(String::toUpperCase)
            .sorted()
            .collect(Collectors.toList());
    }

    /**
     * Java 8 Stream reduction for summation.
     */
    public static double sumTransactionAmountForYear(List<Transaction> transactions, int targetYear) {
        if (transactions == null) return 0.0;
        return transactions.stream()
            .filter(t -> t.year == targetYear)
            .mapToDouble(t -> t.amount)
            .sum();
    }

    /**
     * Advanced grouping using Collectors.toMap with merge function.
     * We map key as department, value as Employee, and merge duplicates by picking
     * the one with higher salary.
     */
    public static Map<String, Employee> highestPaidByDepartment(List<Employee> employees) {
        if (employees == null) return new HashMap<>();
        return employees.stream()
            .collect(Collectors.toMap(
                Employee::getDepartment,
                Function.identity(),
                BinaryOperator.maxBy(Comparator.comparingDouble(Employee::getSalary))
            ));
    }
}
