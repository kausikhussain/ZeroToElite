package basics;

/**
 * 01_Basics - Variables and Operators
 * This file serves as an educational guide on Java variables, data types, arithmetic operators,
 * increment/decrement nuances, and basic method return statements.
 */
public class VariablesAndOperators {

    public static void main(String[] args) {
        System.out.println("=== 1. Variables and Data Types ===");
        // Declaring and initializing primitives
        int age = 20;
        double salary = 50000.50;
        char grade = 'A';
        boolean isLearning = true;
        
        System.out.println("Age (int): " + age);
        System.out.println("Salary (double): " + salary);
        System.out.println("Grade (char): " + grade);
        System.out.println("Is Learning (boolean): " + isLearning);

        // Variable Updation
        System.out.println("\n=== 2. Variable Updation ===");
        int score = 100;
        System.out.println("Initial score: " + score);
        score = score + 20; // adding 20
        System.out.println("Updated score: " + score);
        score += 10; // compound assignment
        System.out.println("Score after compound addition (score += 10): " + score);

        System.out.println("\n=== 3. Arithmetic Operations ===");
        int a = 15;
        int b = 4;
        
        System.out.println("a = " + a + ", b = " + b);
        System.out.println("Sum (a + b): " + (a + b));
        System.out.println("Difference (a - b): " + (a - b));
        System.out.println("Product (a * b): " + (a * b));
        System.out.println("Quotient (a / b) (Integer Division): " + (a / b));
        System.out.println("Remainder / Modulo (a % b): " + (a % b));

        // Percentage and Geometry calculations (Ported & corrected from legacy files)
        double totalMarks = 500;
        double securedMarks = 425;
        double percentage = (securedMarks / totalMarks) * 100;
        System.out.println("Percentage calculation: " + percentage + "%");

        // Area of a circle (Corrected from legacy 'volofcircle' naming)
        double radius = 4.0;
        double circleArea = Math.PI * radius * radius;
        System.out.println("Area of circle (radius = " + radius + "): " + circleArea);

        // Volume of a cylinder
        double height = 5.0;
        double cylinderVolume = Math.PI * radius * radius * height;
        System.out.println("Volume of cylinder (r = " + radius + ", h = " + height + "): " + cylinderVolume);

        System.out.println("\n=== 4. Increment and Decrement Operators ===");
        int x = 5;
        System.out.println("Initial x: " + x);
        
        // Post-increment: Value is used first, then incremented
        System.out.println("Post-increment (x++): " + (x++)); 
        System.out.println("Value of x after post-increment: " + x);

        // Pre-increment: Value is incremented first, then used
        System.out.println("Pre-increment (++x): " + (++x));
        System.out.println("Value of x after pre-increment: " + x);

        // Decrement operations
        int y = 5;
        System.out.println("Initial y: " + y);
        System.out.println("Post-decrement (y--): " + (y--));
        System.out.println("Pre-decrement (--y): " + (--y));

        System.out.println("\n=== 5. Return Statements ===");
        int sumResult = add(10, 25);
        System.out.println("Result of calling add(10, 25) method: " + sumResult);
    }

    /**
     * Helper method to demonstrate parameters and return statements.
     * @param num1 First number
     * @param num2 Second number
     * @return Sum of num1 and num2
     */
    public static int add(int num1, int num2) {
        return num1 + num2; // Exits the method and returns the calculated value
    }
}
