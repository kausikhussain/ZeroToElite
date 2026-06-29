package competitive;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 07_Competitive_Programming - Two Sum Solution (TCS NQT Prep)
 * 
 * Problem: Given an array of integers 'chests' and a 'target' sum, find the indices
 * of two numbers such that they add up to the target.
 * 
 * Optimal Approach: One-pass HashMap.
 * - Time Complexity: O(n) where n is the number of elements (hash table lookup takes O(1) time).
 * - Space Complexity: O(n) to store key-value mappings of elements and their indices.
 */
public class TwoSum {
    public static void main(String[] args) {
        // Using try-with-resources for input safety
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter number of elements:");
            int n = sc.nextInt();

            int[] chests = new int[n];
            System.out.println("Enter elements of array:");
            for (int i = 0; i < n; i++) {
                chests[i] = sc.nextInt();
            }

            System.out.println("Enter target sum:");
            int target = sc.nextInt();

            // Hash map: Key = Number value, Value = Index of that number
            HashMap<Integer, Integer> map = new HashMap<>();
            boolean found = false;

            for (int i = 0; i < n; i++) {
                int complement = target - chests[i];

                // If the complement is already in the map, we found the pair
                if (map.containsKey(complement)) {
                    System.out.println("Indices: " + map.get(complement) + " " + i);
                    found = true;
                    break;
                }

                // Otherwise, store current value and its index
                map.put(chests[i], i);
            }

            if (!found) {
                System.out.println("No matching pair found.");
            }
        }
    }
}
