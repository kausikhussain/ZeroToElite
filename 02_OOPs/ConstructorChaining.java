package oops;

/**
 * 02_OOPs - Constructor Chaining, Overloading, and Copy Constructors
 * This file illustrates how constructors initialize objects in Java. It covers:
 * 1. Default constructor
 * 2. Parameterized constructor
 * 3. Constructor overloading
 * 4. Copy constructor
 * 5. Constructor chaining using `this()` (within same class) and `super()` (base class)
 */

class ParentDevice {
    String brand;

    // Parameterized constructor of parent class
    ParentDevice(String brand) {
        this.brand = brand;
        System.out.println("ParentDevice constructor called: Brand set to " + brand);
    }
}

class SmartPhone extends ParentDevice {
    private String model;
    private double price;

    // 1. Default Constructor
    public SmartPhone() {
        // Chaining to parameterized constructor within the same class using this()
        this("Unknown Model", 0.0);
        System.out.println("Default SmartPhone constructor called.");
    }

    // 2. Parameterized Constructor (Overloaded - 2 arguments)
    public SmartPhone(String model, double price) {
        // Chaining to parent class constructor using super() - must be the first statement
        super("Generic Brand");
        this.model = model;
        this.price = price;
        System.out.println("2-arg Parameterized SmartPhone constructor called.");
    }

    // 3. Overloaded Constructor (3 arguments)
    public SmartPhone(String brand, String model, double price) {
        super(brand); // Call parent constructor
        this.model = model;
        this.price = price;
        System.out.println("3-arg Parameterized SmartPhone constructor called.");
    }

    // 4. Copy Constructor - initializes an object using another object of the same class
    public SmartPhone(SmartPhone other) {
        super(other.brand); // Pass the other object's parent field
        this.model = other.model;
        this.price = other.price;
        System.out.println("Copy SmartPhone constructor called.");
    }

    public void displayDetails() {
        System.out.println("Device [Brand: " + brand + ", Model: " + model + ", Price: $" + price + "]");
    }
}

public class ConstructorChaining {
    public static void main(String[] args) {
        System.out.println("=== Creating phone1 using Default Constructor (chains via this() and super()) ===");
        SmartPhone phone1 = new SmartPhone();
        phone1.displayDetails();

        System.out.println("\n=== Creating phone2 using Parameterized Constructor ===");
        SmartPhone phone2 = new SmartPhone("Galaxy S24", 999.99);
        phone2.displayDetails();

        System.out.println("\n=== Creating phone3 using Fully Parameterized Constructor ===");
        SmartPhone phone3 = new SmartPhone("Apple", "iPhone 15 Pro", 1199.99);
        phone3.displayDetails();

        System.out.println("\n=== Creating phone4 (Copy of phone3) using Copy Constructor ===");
        SmartPhone phone4 = new SmartPhone(phone3);
        phone4.displayDetails();
    }
}
