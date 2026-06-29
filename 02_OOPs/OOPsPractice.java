package oops;

/**
 * 02_OOPs Practice Exercises Template
 * Complete the classes and methods marked with TODO.
 * Run this class to test your implementations.
 */

// ==========================================
// TASK 1: BOOK & AUTHOR RELATIONSHIP
// ==========================================
class Author {
    // TODO: Add fields name and email. Add constructor.
}

class Book {
    // TODO: Add fields title, author (Author), and price. Add constructor.
    
    public String getFormattedDetails() {
        // TODO: Return "Book: Title By AuthorName (AuthorEmail) - $Price"
        return "";
    }
}

// ==========================================
// TASK 2: LIBRARY MANAGEMENT SYSTEM
// ==========================================
class LibraryItem {
    int id;
    String title;

    public LibraryItem(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public void displayInfo() {
        System.out.println("ID: " + id + ", Title: " + title);
    }
}

class BookItem extends LibraryItem {
    // TODO: Add author (String) and pageCount (int). Add constructor.
    // TODO: Override displayInfo() to print subclass attributes as well.
    public BookItem(int id, String title) {
        super(id, title);
    }
}

class DVDItem extends LibraryItem {
    // TODO: Add director (String) and durationMinutes (int). Add constructor.
    // TODO: Override displayInfo() to print subclass attributes as well.
    public DVDItem(int id, String title) {
        super(id, title);
    }
}

// ==========================================
// TASK 3: PAYMENT GATEWAY INTERFACE
// ==========================================
interface PaymentProcessor {
    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentProcessor {
    // TODO: Add fields cardNumber (String) and implement processPayment(double amount)
    // Credit card payment requires a 16-digit card number and charges a 2% fee.
    @Override
    public boolean processPayment(double amount) {
        return false;
    }
}

class PayPalPayment implements PaymentProcessor {
    // TODO: Add email (String). PayPal payment requires a valid email (must contain '@').
    @Override
    public boolean processPayment(double amount) {
        return false;
    }
}

class CryptoPayment implements PaymentProcessor {
    // TODO: Add walletAddress (String). Wallet address must start with "0x".
    @Override
    public boolean processPayment(double amount) {
        return false;
    }
}

public class OOPsPractice {
    public static void main(String[] args) {
        System.out.println("=== Running 02_OOPs Practice ===");

        // Test Task 1: Relationships
        System.out.println("Task 1 (Relationships) implementation can be validated here.");

        // Test Task 2: Library inheritance
        System.out.println("\nTask 2 (Library Items):");
        LibraryItem item1 = new BookItem(1, "Zero To Elite Java Guide");
        LibraryItem item2 = new DVDItem(2, "Advanced Java Live Streams");
        item1.displayInfo();
        item2.displayInfo();

        // Test Task 3: Payment Interfaces
        System.out.println("\nTask 3 (Payment Processor Gateway) implementation can be validated here.");
    }
}
