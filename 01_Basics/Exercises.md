# 01_Basics - Practice Exercises

This folder contains exercises to practice Java basics: data types, operators, conditionals, scanner input, loops, and return statements.

## 📝 Exercises

### 1. Swap Variables Without a Third Variable (Easy)
- **Problem**: Write a method that swaps the values of two integers `a` and `b` without using a third temporary variable.
- **Goal**: Print the values before and after the swap.
- **Constraints**: $O(1)$ auxiliary space.

### 2. Leap Year Checker (Medium)
- **Problem**: Write a method `isLeapYear(int year)` that determines whether a given year is a leap year.
- **Rules**:
  - A year is a leap year if it is divisible by 4.
  - However, if the year is divisible by 100, it is NOT a leap year, unless it is also divisible by 400.
- **Examples**:
  - `1900` is NOT a leap year (divisible by 100 but not 400).
  - `2000` is a leap year (divisible by 400).
  - `2024` is a leap year (divisible by 4).

### 3. Interactive CLI Calculator (Hard/Challenge)
- **Problem**: Complete a command-line calculator method that continuously takes console inputs from the user:
  - Input format: `<number1> <operator> <number2>` (e.g., `5 + 3`, `10 / 2`).
  - Supports operators: `+`, `-`, `*`, `/`, `%`.
  - Exits when the user enters `"exit"`.
- **Requirements**: Handle division by zero gracefully and continue looping.

---

## 🏃 How to Practice
1. Open [BasicsPractice.java](file:///c:/Users/kausi/Desktop/java/01_Basics/BasicsPractice.java).
2. Complete the methods marked with `TODO`.
3. Run the file to verify if all test assertions pass:
   ```powershell
   javac 01_Basics/BasicsPractice.java
   java basics.BasicsPractice
   ```
4. If stuck, review the solution in [solutions/BasicsSolutions.java](file:///c:/Users/kausi/Desktop/java/01_Basics/solutions/BasicsSolutions.java).
