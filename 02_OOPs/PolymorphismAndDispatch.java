package oops;

/**
 * 02_OOPs - Polymorphism and Dynamic Method Dispatch
 * This file illustrates:
 * 1. Compile-time Polymorphism (Method Overloading)
 * 2. Runtime Polymorphism (Method Overriding)
 * 3. Dynamic Method Dispatch (Runtime Polymorphism in action)
 * 4. A clean polymorphic design refactoring of the age-comparison code from legacy main.java
 */

// --- 1. Compile-Time Polymorphism (Method Overloading) ---
class MathOperations {
    // Overloaded method: 2 parameters
    public int multiply(int a, int b) {
        return a * b;
    }

    // Overloaded method: 3 parameters
    public int multiply(int a, int b, int c) {
        return a * b * c;
    }

    // Overloaded method: double parameters
    public double multiply(double a, double b) {
        return a * b;
    }
}

// --- 2 & 3. Runtime Polymorphism & Dynamic Method Dispatch ---
class Vehicle {
    void run() {
        System.out.println("Vehicle is running.");
    }
}

class Car extends Vehicle {
    @Override
    void run() {
        System.out.println("Car is running safely with a speed of 60 km/h.");
    }
}

class Bike extends Vehicle {
    @Override
    void run() {
        System.out.println("Bike is running fast with a speed of 80 km/h.");
    }
}

// --- 4. Clean Refactoring of Legacy Human Age Comparison ---
// Legacy code used `instanceof` and explicit casting in Human class:
// `if (x1 instanceof Man && x2 instanceof Woman) { Man m = (Man)x1; ... }`
// The clean polymorphic approach puts 'age' in the parent class or uses a polymorphic method,
// eliminating typecasting completely and keeping code extensible.

abstract class Human {
    private String name;
    private String gender;

    public Human(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    // Polymorphic method to get age (to be implemented by concrete classes, or kept in parent)
    public abstract int getAge();

    // Polymorphic age comparison - works for ANY subclass of Human without casting!
    public void compareAge(Human other) {
        if (this.getAge() > other.getAge()) {
            System.out.println(this.getName() + " (" + this.getAge() + ") is older than " + 
                               other.getName() + " (" + other.getAge() + ").");
        } else if (this.getAge() < other.getAge()) {
            System.out.println(other.getName() + " (" + other.getAge() + ") is older than " + 
                               this.getName() + " (" + this.getAge() + ").");
        } else {
            System.out.println(this.getName() + " and " + other.getName() + " are of the same age.");
        }
    }
}

class Man extends Human {
    private int age;

    public Man(String name, int age) {
        super(name, "Male");
        this.age = age;
    }

    @Override
    public int getAge() {
        return this.age;
    }
}

class Woman extends Human {
    private int age;

    public Woman(String name, int age) {
        super(name, "Female");
        this.age = age;
    }

    @Override
    public int getAge() {
        return this.age;
    }
}

public class PolymorphismAndDispatch {
    public static void main(String[] args) {
        System.out.println("=== 1. Compile-Time Polymorphism (Method Overloading) ===");
        MathOperations math = new MathOperations();
        System.out.println("Multiply 2 ints: " + math.multiply(4, 5));
        System.out.println("Multiply 3 ints: " + math.multiply(4, 5, 2));
        System.out.println("Multiply 2 doubles: " + math.multiply(4.5, 2.0));

        System.out.println("\n=== 2. Dynamic Method Dispatch (Runtime Polymorphism) ===");
        // Parent reference holding child objects
        Vehicle v1 = new Car(); 
        Vehicle v2 = new Bike();

        v1.run(); // Calls Car's run method at runtime
        v2.run(); // Calls Bike's run method at runtime

        System.out.println("\n=== 3. Clean Polymorphic Age Comparison (Refactored main.java) ===");
        Human jai = new Man("Jai", 25);
        Human kelly = new Woman("Kelly", 15);

        // Jai compares age with Kelly - no typecasting, fully extensible
        jai.compareAge(kelly);
    }
}
