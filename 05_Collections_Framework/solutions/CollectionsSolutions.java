package collections.solutions;

import java.util.*;

/**
 * 05_Collections_Framework Practice Solutions
 * Complete reference implementation for Collections exercises.
 */

// ==========================================
// TASK 3: LRU CACHE VIA LINKEDHASHMAP
// ==========================================
// In Java, LinkedHashMap maintains a doubly-linked list running through all of its entries.
// By passing true for accessOrder in the constructor, the list is ordered from least-recently accessed
// to most-recently accessed (access-order).
// We override removeEldestEntry to automatically evict the oldest entry when size exceeds capacity.
class LRUCache extends LinkedHashMap<Integer, Integer> {
    private final int capacity;

    public LRUCache(int capacity) {
        // constructor: initialCapacity, loadFactor, accessOrder (true for access-order, false for insertion-order)
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        // Evicts the eldest entry (least recently accessed) when capacity is exceeded
        return size() > capacity;
    }
}

public class CollectionsSolutions {

    public static void main(String[] args) {
        System.out.println("=== Running 05_Collections_Framework Solutions ===");

        // Task 1
        System.out.println("--- Task 1: Word Frequency ---");
        String text = "Java is great. Java is fast, and Java is popular!";
        System.out.println(countWordFrequency(text));

        // Task 2
        System.out.println("\n--- Task 2: Retain Order De-duplicate ---");
        List<Integer> list = Arrays.asList(5, 2, 5, 8, 2, 1);
        System.out.println("Original: " + list);
        System.out.println("De-duplicated: " + removeDuplicatesKeepOrder(list));

        // Task 3
        System.out.println("\n--- Task 3: LRU Cache Verification ---");
        LRUCache cache = new LRUCache(2);
        cache.put(1, 10);
        cache.put(2, 20);
        System.out.println("Get(1): " + cache.get(1)); // returns 10
        cache.put(3, 30); // evicts key 2
        System.out.println("Get(2) (Expected -1): " + cache.get(2)); // returns -1
    }

    /**
     * Splits string, sanitizes case & punctuation, and counts frequency.
     */
    public static Map<String, Integer> countWordFrequency(String text) {
        Map<String, Integer> map = new HashMap<>();
        if (text == null || text.trim().isEmpty()) return map;

        // Split by whitespace and strip common punctuation marks
        String[] words = text.toLowerCase().split("[\\s.,!?;:]+");
        for (String word : words) {
            if (!word.isEmpty()) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
        }
        return map;
    }

    /**
     * De-duplicates in O(n) time using a LinkedHashSet.
     * LinkedHashSet maintains a doubly-linked list through elements, preserving insertion order.
     */
    public static List<Integer> removeDuplicatesKeepOrder(List<Integer> list) {
        if (list == null) return new ArrayList<>();
        // LinkedHashSet removes duplicates in O(1) and retains original sequence
        Set<Integer> set = new LinkedHashSet<>(list);
        return new ArrayList<>(set);
    }
}
