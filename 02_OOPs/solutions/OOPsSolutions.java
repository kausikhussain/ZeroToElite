package oops.solutions;

/**
 * 02_OOPs Practice Solutions
 * Complete reference implementation for OOP exercises.
 */

// ==========================================
// TASK 1: BOOK & AUTHOR RELATIONSHIP
// ==========================================
class Author {
    String name;
    String email;

    public Author(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

class Book {
    String title;
    Author author;
    double price;

    public Book(String title, Author author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public String getFormattedDetails() {
        return "Book: " + title + " By " + author.name + " (" + author.email + ") - $" + price;
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
        System.out.print("ID: " + id + ", Title: " + title);
    }
}

class BookItem extends LibraryItem {
    String author;
    int pageCount;

    public BookItem(int id, String title, String author, int pageCount) {
        super(id, title);
        this.author = author;
        this.pageCount = pageCount;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println(" | Type: Book, Author: " + author + ", Pages: " + pageCount);
    }
}

class DVDItem extends LibraryItem {
    String director;
    int durationMinutes;

    public DVDItem(int id, String title, String director, int durationMinutes) {
        super(id, title);
        this.director = director;
        this.durationMinutes = durationMinutes;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println(" | Type: DVD, Director: " + director + ", Duration: " + durationMinutes + " mins");
    }
}

// ==========================================
// TASK 3: PAYMENT GATEWAY INTERFACE
// ==========================================
interface PaymentProcessor {
    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentProcessor {
    String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean processPayment(double amount) {
        if (cardNumber == null || cardNumber.length() != 16) {
            System.out.println("Payment Failed: Invalid credit card number. Must be 16 digits.");
            return false;
        }
        double fee = amount * 0.02;
        double total = amount + fee;
        System.out.printf("Processed Credit Card Payment: $%.2f + $%.2f (2%% Fee) = Total $%.2f%n", amount, fee, total);
        return true;
    }
}

class PayPalPayment implements PaymentProcessor {
    String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public boolean processPayment(double amount) {
        if (email == null || !email.contains("@")) {
            System.out.println("Payment Failed: Invalid PayPal email address.");
            return false;
        }
        System.out.printf("Processed PayPal Payment: $%.2f for account %s%n", amount, email);
        return true;
    }
}

class CryptoPayment implements PaymentProcessor {
    String walletAddress;

    public CryptoPayment(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    @Override
    public boolean processPayment(double amount) {
        if (walletAddress == null || !walletAddress.startsWith("0x")) {
            System.out.println("Payment Failed: Invalid crypto wallet address. Must start with '0x'.");
            return false;
        }
        double gasFee = 0.05;
        double total = amount + gasFee;
        System.out.printf("Processed Crypto Payment: $%.2f + $%.2f (Gas Fee) = Total $%.2f%n", amount, gasFee, total);
        return true;
    }
}

public class OOPsSolutions {
    public static void main(String[] args) {
        System.out.println("=== Running 02_OOPs Solutions ===");

        // Test Task 1
        System.out.println("--- Task 1: Relationships ---");
        Author author = new Author("Kenneth H. Rosen", "rosen@math.org");
        Book book = new Book("Discrete Mathematics", author, 29.99);
        System.out.println(book.getFormattedDetails());

        // Test Task 2
        System.out.println("\n--- Task 2: Inheritance & Overriding ---");
        LibraryItem item1 = new BookItem(101, "Algorithms", "Thomas H. Cormen", 1312);
        LibraryItem item2 = new DVDItem(202, "Inception", "Christopher Nolan", 148);
        item1.displayInfo();
        item2.displayInfo();

        // Test Task 3
        System.out.println("\n--- Task 3: Polymorphic Interfaces ---");
        PaymentProcessor p1 = new CreditCardPayment("1234567890123456");
        PaymentProcessor p2 = new PayPalPayment("user@example.com");
        PaymentProcessor p3 = new CryptoPayment("0x8e8a6f3b");
        
        // Dynamic method dispatch in action
        p1.processPayment(100.00);
        p2.processPayment(45.50);
        p3.processPayment(500.00);
        
        // Test failures
        System.out.println("\n--- Testing Failures ---");
        PaymentProcessor p4 = new CreditCardPayment("1234");
        PaymentProcessor p5 = new PayPalPayment("invalidemail");
        p4.processPayment(10.00);
        p5.processPayment(20.00);
    }
}
