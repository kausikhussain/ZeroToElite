package collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 05_Collections_Framework Practice Template
 * Complete the classes and methods marked with TODO.
 * Run this class to test your implementations.
 */

// ==========================================
// TASK 3: LRU CACHE DEFINITION
// ==========================================
class LRUCache {
    // TODO: Define fields and double linked list/hashmap structures.

    public LRUCache(int capacity) {
        // TODO: Initialize fields
    }

    public int get(int key) {
        // TODO: Return value if key exists, otherwise -1. Update key to most recently used.
        return -1;
    }

    public void put(int key, int value) {
        // TODO: Insert or update key-value. Evict LRU element if capacity exceeded.
    }
}

public class CollectionsPractice {

    public static void main(String[] args) {
        System.out.println("=== Running 05_Collections_Framework Practice ===");

        // Test Task 1: Word Count
        System.out.println("Testing Task 1 (Word Frequency):");
        String text = "Java is great. Java is fast, and Java is popular!";
        Map<String, Integer> freq = countWordFrequency(text);
        System.out.println("Frequencies: " + freq);
        if (freq != null && freq.get("java") == 3 && freq.get("is") == 3) {
            System.out.println("Task 1: PASSED");
        } else {
            System.out.println("Task 1: FAILED");
        }

        // Test Task 2: Order-retaining De-duplication
        System.out.println("\nTesting Task 2 (De-duplicate order):");
        List<Integer> list = new ArrayList<>();
        list.add(5); list.add(2); list.add(5); list.add(8); list.add(2); list.add(1);
        List<Integer> unique = removeDuplicatesKeepOrder(list);
        System.out.println("Unique Elements: " + unique);
        // Expected: [5, 2, 8, 1]
        if (unique != null && unique.size() == 4 && unique.get(2) == 8) {
            System.out.println("Task 2: PASSED");
        } else {
            System.out.println("Task 2: FAILED");
        }

        // Test Task 3: LRU Cache
        System.out.println("\nTesting Task 3 (LRU Cache):");
        LRUCache cache = new LRUCache(2);
        cache.put(1, 10);
        cache.put(2, 20);
        int r1 = cache.get(1);    // returns 10
        cache.put(3, 30);         // evicts key 2 (since key 1 was recently accessed)
        int r2 = cache.get(2);    // returns -1 (evicted)
        cache.put(4, 40);         // evicts key 1
        int r3 = cache.get(1);    // returns -1 (evicted)
        int r4 = cache.get(3);    // returns 30
        int r5 = cache.get(4);    // returns 40

        if (r1 == 10 && r2 == -1 && r3 == -1 && r4 == 30 && r5 == 40) {
            System.out.println("Task 3: PASSED");
        } else {
            System.out.println("Task 3: FAILED");
        }
    }

    /**
     * Task 1: Word Frequency Counter (Case insensitive, strip punctuation)
     */
    public static Map<String, Integer> countWordFrequency(String text) {
        // TODO: Implement frequency mapping.
        return null;
    }

    /**
     * Task 2: Remove duplicates while maintaining original insertion order.
     */
    public static List<Integer> removeDuplicatesKeepOrder(List<Integer> list) {
        // TODO: Implement de-duplication in O(n) retaining order.
        return null;
    }
}
