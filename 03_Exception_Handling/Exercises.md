# 03_Exception_Handling - Practice Exercises

This folder contains exercises to practice exception safety, robust error reporting, try-catch-finally strategies, checked vs unchecked exceptions, custom exceptions, and try-with-resources.

## 📝 Exercises

### 1. Robust Multi-Catch Block (Easy)
- **Problem**: Write a method `parseAndSum(String[] array, int index1, int index2)` that:
  - Accesses values at index1 and index2 in the array.
  - Parses them to integers and returns their sum.
  - Catches `ArrayIndexOutOfBoundsException` and `NumberFormatException` in separate catch blocks (or multi-catch) and displays clean warnings, returning `-1` if an exception occurs.

### 2. Banking Withdrawal Validation (Medium)
- **Problem**: Design custom exception-handling logic for a mock bank:
  - Create a custom checked exception `InsufficientFundsException`.
  - Write a class `BankAccount` with fields `accountNumber` (String) and `balance` (double).
  - Implement a method `withdraw(double amount) throws InsufficientFundsException` that checks if the amount exceeds the balance. If it does, throw your custom exception with an appropriate message (e.g. `"Cannot withdraw $X: Balance is only $Y"`).

### 3. Safe File Copier (Hard/Challenge)
- **Problem**: Write a method `copyFile(String sourcePath, String destPath)` that reads contents from a source file and writes them to a destination file.
- **Constraints**: 
  - Use try-with-resources to ensure `FileReader` / `FileWriter` (or `BufferedReader` / `BufferedWriter`) are closed automatically even if errors occur.
  - Catch and handle any `IOException` (e.g. file not found or write errors) and print a helpful status report.

---

## 🏃 How to Practice
1. Open [ExceptionPractice.java](file:///c:/Users/kausi/Desktop/java/03_Exception_Handling/ExceptionPractice.java).
2. Complete the classes and methods marked with `TODO`.
3. Run the file to verify if the test drivers execute:
   ```powershell
   javac 03_Exception_Handling/ExceptionPractice.java
   java exceptions.ExceptionPractice
   ```
4. If stuck, check the solution in [solutions/ExceptionSolutions.java](file:///c:/Users/kausi/Desktop/java/03_Exception_Handling/solutions/ExceptionSolutions.java).
