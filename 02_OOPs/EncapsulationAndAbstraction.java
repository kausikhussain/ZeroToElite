package oops;

/**
 * 02_OOPs - Encapsulation and Abstraction
 * This file illustrates:
 * 1. Encapsulation: Data hiding using private access modifiers, controlled access via getters/setters,
 *    and incorporating data validation (e.g., preventing negative salaries).
 * 2. Abstraction: Hiding implementation details using Abstract Classes and Interfaces.
 */

// ==========================================
// 1. ENCAPSULATION DEMONSTRATION
// ==========================================
class Employee {
    private String name;
    private double salary; // Private variable - hidden from external access

    public Employee(String name, double salary) {
        this.name = name;
        setSalary(salary); // Use setter to enforce validation during initialization
    }

    // Getter for Name
    public String getName() {
        return name;
    }

    // Setter for Name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for Salary
    public double getSalary() {
        return salary;
    }

    // Setter for Salary with validation
    public void setSalary(double salary) {
        if (salary >= 0) {
            this.salary = salary;
        } else {
            System.out.println("Warning: Salary cannot be negative. Setting salary to $0.");
            this.salary = 0;
        }
    }
}

// ==========================================
// 2. ABSTRACTION DEMONSTRATION
// ==========================================

// An interface defines a contract of behavior
interface Printable {
    void printReport(); // Public abstract method by default
}

// An abstract class provides a partial implementation and enforces a template
abstract class Document {
    private String title;

    public Document(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    // Abstract method - subclasses must implement this
    abstract void processContent();

    // Concrete method - inherited as is by subclasses
    public void displayHeader() {
        System.out.println("--- Document Header: " + title + " ---");
    }
}

// PDFDocument inherits from Document and implements Printable interface
class PDFDocument extends Document implements Printable {
    private int pageSize;

    public PDFDocument(String title, int pageSize) {
        super(title);
        this.pageSize = pageSize;
    }

    @Override
    void processContent() {
        System.out.println("Processing PDF layout and text content for " + getTitle() + "...");
    }

    @Override
    public void printReport() {
        System.out.println("Printing PDF document '" + getTitle() + "' (" + pageSize + " pages) to system printer.");
    }
}

public class EncapsulationAndAbstraction {
    public static void main(String[] args) {
        System.out.println("=== 1. Encapsulation ===");
        Employee emp = new Employee("Alice", 75000);
        System.out.println(emp.getName() + "'s initial salary: $" + emp.getSalary());

        // Attempting to set negative salary (Validation prevents this)
        System.out.println("Attempting to set salary to -$100...");
        emp.setSalary(-100);
        System.out.println(emp.getName() + "'s current salary: $" + emp.getSalary());

        // Valid update
        System.out.println("Updating salary to $80000...");
        emp.setSalary(80000);
        System.out.println(emp.getName() + "'s updated salary: $" + emp.getSalary());

        System.out.println("\n=== 2. Abstraction (Abstract Class & Interface) ===");
        // Document doc = new Document("Abstract Doc"); // Compile Error: Document is abstract and cannot be instantiated
        
        PDFDocument myPdf = new PDFDocument("Q2 Financials", 15);
        myPdf.displayHeader();     // Concrete method from Document class
        myPdf.processContent();    // Overridden abstract method from Document class
        myPdf.printReport();       // Overridden method from Printable interface
    }
}
