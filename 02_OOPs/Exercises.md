# 02_OOPs - Practice Exercises

This folder contains exercises to practice Object-Oriented Programming (OOP) concepts in Java: encapsulation, relationships (Composition/Association), inheritance, method overriding, and interfaces/polymorphism.

## 📝 Exercises

### 1. Book & Author Relationship (Easy)
- **Problem**: Create a relationship between two classes: `Author` and `Book`.
  - `Author` has: `name` (String), `email` (String).
  - `Book` has: `title` (String), `author` (`Author`), `price` (double).
- **Goal**: Instantiate a `Book` with an associated `Author`, and write a method to display the details in the format: `"Book: Title By AuthorName (AuthorEmail) - $Price"`.

### 2. Library Management System (Medium)
- **Problem**: Implement a basic Library system:
  - Create a base class `LibraryItem` with: `id` (int), `title` (String).
  - Create a subclass `BookItem` that extends `LibraryItem` and adds: `author` (String), `pageCount` (int).
  - Create a subclass `DVDItem` that extends `LibraryItem` and adds: `director` (String), `durationMinutes` (int).
- **Goal**: Override a method `displayInfo()` in both subclasses to print their unique attributes alongside the base parameters, showcasing runtime polymorphism.

### 3. Payment Gateway Interface (Hard/Challenge)
- **Problem**: Design a payment processing simulation utilizing interfaces and abstraction:
  - Create an interface `PaymentProcessor` defining `processPayment(double amount)`.
  - Create three classes that implement `PaymentProcessor`:
    - `CreditCardPayment`: Requires a 16-digit card number and charges a flat 2% fee on top of the transaction.
    - `PayPalPayment`: Requires an email address and verifies if it contains `@`.
    - `CryptoPayment`: Requires a wallet address (must start with `"0x"`) and charges a gas fee of $0.05.
- **Goal**: Instantiate a collection of payment methods and process payments polymorphically.

---

## 🏃 How to Practice
1. Open [OOPsPractice.java](file:///c:/Users/kausi/Desktop/java/02_OOPs/OOPsPractice.java).
2. Complete the classes and methods marked with `TODO`.
3. Run the file to verify if the test drivers execute:
   ```powershell
   javac 02_OOPs/OOPsPractice.java
   java oops.OOPsPractice
   ```
4. If stuck, check the solution in [solutions/OOPsSolutions.java](file:///c:/Users/kausi/Desktop/java/02_OOPs/solutions/OOPsSolutions.java).
