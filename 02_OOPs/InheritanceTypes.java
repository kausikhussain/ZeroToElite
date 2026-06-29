package oops;

/**
 * 02_OOPs - Types of Inheritance in Java
 * This file illustrates the different inheritance models supported by Java:
 * 1. Single Inheritance (Class extends one Class)
 * 2. Multilevel Inheritance (Class extends a subclass)
 * 3. Hierarchical Inheritance (Multiple classes extend the same base class)
 * 4. Multiple Inheritance via Interfaces (Java does not support multiple class inheritance due to the Diamond Problem, but supports it via interfaces)
 */

// --- Base Class ---
class Animal {
    void eat() {
        System.out.println("This animal eats food.");
    }
}

// --- 1. Single Inheritance (Dog extends Animal) ---
class Dog extends Animal {
    void bark() {
        System.out.println("The dog barks.");
    }
}

// --- 2. Multilevel Inheritance (Puppy extends Dog, which extends Animal) ---
class Puppy extends Dog {
    void weep() {
        System.out.println("The puppy weeps.");
    }
}

// --- 3. Hierarchical Inheritance (Cat extends Animal, just like Dog did) ---
class Cat extends Animal {
    void meow() {
        System.out.println("The cat meows.");
    }
}

// --- 4. Multiple Inheritance Simulation using Interfaces ---
// The Diamond Problem occurs if class A has method foo(), class B and C inherit from A and override foo(),
// and class D inherits from B and C. If D calls foo(), it's ambiguous which version to use.
// Java resolves this by not allowing multiple class inheritance, but allowing class to implement multiple interfaces.

interface CanRun {
    void run();
}

interface CanSwim {
    void swim();
}

// Amphibian implements both CanRun and CanSwim interfaces
class Duck extends Animal implements CanRun, CanSwim {
    @Override
    public void run() {
        System.out.println("Duck waddles on land (runs).");
    }

    @Override
    public void swim() {
        System.out.println("Duck swims in water.");
    }
}

public class InheritanceTypes {
    public static void main(String[] args) {
        System.out.println("=== 1. Single Inheritance ===");
        Dog dog = new Dog();
        dog.eat();  // Inherited from Animal
        dog.bark(); // Defined in Dog

        System.out.println("\n=== 2. Multilevel Inheritance ===");
        Puppy puppy = new Puppy();
        puppy.eat();  // Inherited from Animal
        puppy.bark(); // Inherited from Dog
        puppy.weep(); // Defined in Puppy

        System.out.println("\n=== 3. Hierarchical Inheritance ===");
        Cat cat = new Cat();
        cat.eat();  // Inherited from Animal
        cat.meow(); // Defined in Cat

        System.out.println("\n=== 4. Multiple Inheritance (via Interfaces) ===");
        Duck duck = new Duck();
        duck.eat();  // Inherited from Animal class
        duck.run();  // Implemented from CanRun interface
        duck.swim(); // Implemented from CanSwim interface
    }
}
