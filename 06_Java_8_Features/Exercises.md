# 06_Java_8_Features - Practice Exercises

This folder contains exercises to practice functional programming concepts in Java: Lambda expressions, functional interfaces, and stream processing pipelines.

## 📝 Exercises

### 1. Stream Prefix Filtering (Easy)
- **Problem**: Write a method `filterAndUppercase(List<String> words, String prefix)` that:
  - Takes a list of strings and a prefix string.
  - Uses Java 8 streams to filter elements starting with the prefix.
  - Converts the matching elements to upper case and sorts them alphabetically.
  - Returns the list of processed strings.

### 2. Transaction Summation by Year (Medium)
- **Problem**: Complete the mapping and reduction calculation:
  - You are given a class `Transaction` with fields: `id` (int), `amount` (double), and `year` (int).
  - Implement `sumTransactionAmountForYear(List<Transaction> transactions, int targetYear)` that returns the total sum of amounts for transactions matching the target year.
  - **Constraint**: Must be written as a single, fluid Java 8 stream statement.

### 3. Employee Salary Department Grouping (Hard/Challenge)
- **Problem**: Perform advanced data grouping using streams:
  - Create a class `Employee` with: `name` (String), `department` (String), and `salary` (double).
  - Implement `highestPaidByDepartment(List<Employee> employees)` that returns a `Map<String, Employee>` mapping each department name to its highest-paid employee.
  - **Hint**: Use `Collectors.groupingBy` combined with `Collectors.reducing` or `Collectors.maxBy`.

---

## 🏃 How to Practice
1. Open [Java8Practice.java](file:///c:/Users/kausi/Desktop/java/06_Java_8_Features/Java8Practice.java).
2. Complete the classes and methods marked with `TODO`.
3. Run the file to verify if the test drivers execute:
   ```powershell
   javac 06_Java_8_Features/Java8Practice.java
   java features.Java8Practice
   ```
4. If stuck, check the solution in [solutions/Java8Solutions.java](file:///c:/Users/kausi/Desktop/java/06_Java_8_Features/solutions/Java8Solutions.java).
