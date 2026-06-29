package features;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 06_Java_8_Features - Lambda Expressions and Streams API
 * This file illustrates functional programming features introduced in Java 8:
 * 1. Lambda Expressions (simplifying anonymous classes)
 * 2. Functional Interfaces (like Predicate, Consumer, and custom ones)
 * 3. Stream API (declarative pipeline operations: filter, map, sorted, collect)
 */

@FunctionalInterface
interface StringOperator {
    String operate(String input); // Single Abstract Method (SAM)
}

public class LambdaAndStreams {

    public static void main(String[] args) {
        System.out.println("=== 1. LAMBDA EXPRESSIONS ===");
        // Traditional way: Anonymous Inner Class
        StringOperator reverseTraditional = new StringOperator() {
            @Override
            public String operate(String input) {
                return new StringBuilder(input).reverse().toString();
            }
        };
        System.out.println("Traditional reverse ('Java'): " + reverseTraditional.operate("Java"));

        // Modern way: Lambda Expression
        StringOperator reverseLambda = (input) -> new StringBuilder(input).reverse().toString();
        System.out.println("Lambda reverse ('Java'): " + reverseLambda.operate("Java"));

        System.out.println("\n=== 2. STREAM API DEMONSTRATION ===");
        List<Product> products = Arrays.asList(
            new Product("Laptop", 1200.0, "Electronics"),
            new Product("Phone", 800.0, "Electronics"),
            new Product("Shirt", 25.0, "Clothing"),
            new Product("Jeans", 50.0, "Clothing"),
            new Product("Blender", 150.0, "Home Appliances")
        );

        System.out.println("Original Products List:");
        products.forEach(p -> System.out.println(p));

        System.out.println("\nStream Pipeline Operations:");
        // Goal: Filter products that are Electronics, sort by price descending, map to names, and collect to list
        List<String> electronicsNames = products.stream()
            .filter(p -> p.getCategory().equals("Electronics"))              // 1. Filter
            .sorted(Comparator.comparingDouble(Product::getPrice).reversed()) // 2. Sort descending
            .map(Product::getName)                                            // 3. Map to name string
            .collect(Collectors.toList());                                    // 4. Collect results

        System.out.println("Sorted Electronics Names: " + electronicsNames);

        // Summarizing statistics using Streams
        double totalElectronicsPrice = products.stream()
            .filter(p -> p.getCategory().equals("Electronics"))
            .mapToDouble(Product::getPrice)
            .sum();
        System.out.println("\nTotal Price of all Electronics: $" + totalElectronicsPrice);

        // Grouping products by Category
        Map<String, List<Product>> groupedByCategory = products.stream()
            .collect(Collectors.groupingBy(Product::getCategory));
        
        System.out.println("\nProducts grouped by category:");
        groupedByCategory.forEach((category, list) -> {
            System.out.println(category + ": " + list.stream().map(Product::getName).collect(Collectors.joining(", ")));
        });
    }

    // Helper product class
    static class Product {
        private String name;
        private double price;
        private String category;

        public Product(String name, double price, String category) {
            this.name = name;
            this.price = price;
            this.category = category;
        }

        public String getName() { return name; }
        public double getPrice() { return price; }
        public String getCategory() { return category; }

        @Override
        public String toString() {
            return String.format("%s (%s) - $%.2f", name, category, price);
        }
    }
}
