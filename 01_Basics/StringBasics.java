package basics;

/**
 * 01_Basics - String Basics
 * This file illustrates String operations in Java, string memory allocation (String Pool vs Heap),
 * common String methods, and StringBuilder vs StringBuffer for mutable strings.
 */
public class StringBasics {

    public static void main(String[] args) {
        System.out.println("=== 1. String Literal vs heap allocation ===");
        // Literal storage in String Constant Pool
        String str1 = "NPTEL - Programming in java";
        String str2 = "NPTEL - Programming in java";

        // Object storage in Heap memory
        String str3 = new String("NPTEL - Programming in java");

        // Reference comparisons
        System.out.println("str1 == str2 (Same reference?): " + (str1 == str2)); // true (points to same object in Pool)
        System.out.println("str1 == str3 (Same reference?): " + (str1 == str3)); // false (points to heap object vs pool object)

        // Value comparisons
        System.out.println("str1.equals(str3) (Same values?): " + str1.equals(str3)); // true

        System.out.println("\n=== 2. Useful String Methods ===");
        System.out.println("String: " + str1);
        System.out.println("Length of string: " + str1.length());
        System.out.println("Character at index 8: " + str1.charAt(8));
        System.out.println("Substring from index 8 to 19: '" + str1.substring(8, 19) + "'");
        System.out.println("Upper Case: " + str1.toUpperCase());
        System.out.println("Contains 'java': " + str1.contains("java"));
        System.out.println("Replace 'NPTEL' with 'ZeroToElite': " + str1.replace("NPTEL", "ZeroToElite"));

        System.out.println("\n=== 3. StringBuilder vs StringBuffer ===");
        // String is immutable, which means any change creates a new object.
        // For frequent string changes, we use StringBuilder (unsynchronized, faster) or StringBuffer (synchronized, thread-safe).

        System.out.println("Using StringBuilder for modifications:");
        StringBuilder sb = new StringBuilder("Zero");
        sb.append(" To");
        sb.append(" Elite");
        System.out.println("StringBuilder result: " + sb.toString());
        sb.reverse();
        System.out.println("StringBuilder reversed: " + sb.toString());
        
        System.out.println("\nUsing StringBuffer for thread-safe modifications:");
        StringBuffer sbf = new StringBuffer("ThreadSafe");
        sbf.append(" Buffer");
        System.out.println("StringBuffer result: " + sbf.toString());
    }
}
