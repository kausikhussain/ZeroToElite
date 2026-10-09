package dsa;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem: Implement a generic Hash Table from scratch with Separate Chaining.
 * Concept: Hash Function Mapping, Collision Resolution, Load Factor Threshold, Dynamic Rehashing.
 * 
 * Key Principles:
 * 1. Hash Distribution:
 *    Hash code is converted to a non-negative bucket index:
 *      index = (key.hashCode() & 0x7fffffff) % capacity
 *    (Bitwise AND with 0x7fffffff clears the 32-bit sign bit to prevent negative indices).
 * 
 * 2. Collision Resolution via Separate Chaining:
 *    Each bucket contains a linked list of Entry nodes. When two distinct keys hash to the same bucket,
 *    they are chained together.
 * 
 * 3. Load Factor and Rehashing (lambda = size / capacity):
 *    Default load factor threshold is 0.75.
 *    Why 0.75? Under random hash codes, bucket lengths follow a Poisson distribution.
 *    At lambda = 0.75, the probability of finding a long bucket chain is vanishingly small (~0.00000006 for length 8),
 *    offering the optimal mathematical balance between space utilization and constant-time O(1) performance.
 *    When size exceeds threshold, capacity doubles, and all active entries are rehashed into the new table.
 * 
 * Time Complexity:
 * - put(K, V):         Average O(1), Worst Case O(N) when all keys collide into one bucket.
 * - get(K):            Average O(1), Worst Case O(N).
 * - remove(K):         Average O(1), Worst Case O(N).
 * - containsKey(K):    Average O(1), Worst Case O(N).
 * - rehash():          O(N) executed amortized across insertions.
 * 
 * Space Complexity:
 * - O(N + M) where N is number of key-value pairs and M is table capacity.
 */
public class HashTable<K, V> {

    private static final int DEFAULT_INITIAL_CAPACITY = 8;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    @SuppressWarnings("unchecked")
    private Entry<K, V>[] buckets = (Entry<K, V>[]) new Entry[DEFAULT_INITIAL_CAPACITY];
    private int size = 0;
    private final double loadFactor;

    public HashTable() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    @SuppressWarnings("unchecked")
    public HashTable(int initialCapacity, double loadFactor) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("Initial capacity must be >= 1.");
        }
        if (loadFactor <= 0.0 || Double.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Load factor must be positive.");
        }
        this.buckets = (Entry<K, V>[]) new Entry[initialCapacity];
        this.loadFactor = loadFactor;
        this.size = 0;
    }

    /**
     * Associates the specified value with the specified key in this hash table.
     * Returns previous value associated with key, or null if key was new.
     */
    public V put(K key, V value) {
        int index = getBucketIndex(key);
        Entry<K, V> current = buckets[index];

        // Search if key already exists in this chain
        while (current != null) {
            if (keysEqual(current.key, key)) {
                V oldValue = current.value;
                current.value = value; // Update value
                return oldValue;
            }
            current = current.next;
        }

        // Key not found: prepend new node to bucket chain
        buckets[index] = new Entry<>(key, value, buckets[index]);
        size++;

        // Trigger dynamic rehashing if load factor threshold exceeded
        if ((double) size / buckets.length >= loadFactor) {
            rehash();
        }

        return null;
    }

    /**
     * Retrieves the value associated with the specified key.
     * Returns null if key is not found.
     */
    public V get(K key) {
        int index = getBucketIndex(key);
        Entry<K, V> current = buckets[index];

        while (current != null) {
            if (keysEqual(current.key, key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Removes the mapping for the specified key if present.
     * Returns removed value or null if key was not present.
     */
    public V remove(K key) {
        int index = getBucketIndex(key);
        Entry<K, V> current = buckets[index];
        Entry<K, V> prev = null;

        while (current != null) {
            if (keysEqual(current.key, key)) {
                if (prev == null) {
                    buckets[index] = current.next; // Head removed
                } else {
                    prev.next = current.next; // Inner node removed
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    /**
     * Checks if this hash table contains the given key.
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return buckets.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns a list of all keys currently in the hash table.
     */
    public List<K> keySet() {
        List<K> keys = new ArrayList<>(size);
        for (Entry<K, V> head : buckets) {
            Entry<K, V> curr = head;
            while (curr != null) {
                keys.add(curr.key);
                curr = curr.next;
            }
        }
        return keys;
    }

    private int getBucketIndex(K key) {
        if (key == null) return 0;
        return (key.hashCode() & 0x7fffffff) % buckets.length;
    }

    private boolean keysEqual(K k1, K k2) {
        if (k1 == null) return k2 == null;
        return k1.equals(k2);
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        int newCapacity = buckets.length * 2;
        Entry<K, V>[] newBuckets = (Entry<K, V>[]) new Entry[newCapacity];

        // Redistribute all existing entries to the new bucket array
        for (Entry<K, V> head : buckets) {
            Entry<K, V> current = head;
            while (current != null) {
                Entry<K, V> next = current.next;

                int newIndex = (current.key == null) ? 0 : (current.key.hashCode() & 0x7fffffff) % newCapacity;
                current.next = newBuckets[newIndex];
                newBuckets[newIndex] = current;

                current = next;
            }
        }
        this.buckets = newBuckets;
    }

    /**
     * Comprehensive test driver verifying insertion, collision handling, updates,
     * deletions, and automatic capacity expansion.
     */
    public static void main(String[] args) {
        System.out.println("=== 08_DSA_Implementations: HashTable Verification ===");

        HashTable<String, Integer> map = new HashTable<>(4, 0.75); // Starts with capacity 4, threshold = 3

        // Test 1: Insert initial entries
        map.put("Apple", 10);
        map.put("Banana", 20);
        map.put("Cherry", 30);
        System.out.println("Inserted 3 items. Size: " + map.size() + ", Capacity: " + map.capacity());
        boolean pass1 = (map.size() == 3 && map.get("Banana") == 20);

        // Test 2: Trigger Rehashing by inserting 4th item (3 / 4 >= 0.75 threshold)
        map.put("Date", 40);
        System.out.println("Inserted 4th item. Size: " + map.size() + ", Capacity after rehash: " + map.capacity());
        boolean pass2 = (map.capacity() >= 8 && map.get("Date") == 40 && map.get("Apple") == 10);

        // Test 3: Value update for existing key
        Integer oldVal = map.put("Apple", 99);
        boolean pass3 = (oldVal == 10 && map.get("Apple") == 99 && map.size() == 4);
        System.out.println("Updated Apple value to 99 (Old: " + oldVal + ") -> " + (pass3 ? "PASS" : "FAIL"));

        // Test 4: Removal
        Integer removed = map.remove("Banana");
        boolean pass4 = (removed == 20 && map.get("Banana") == null && !map.containsKey("Banana") && map.size() == 3);
        System.out.println("Removed Banana -> " + (pass4 ? "PASS" : "FAIL"));

        // Test 5: Bulk insertion to stress-test collision handling & multi-level rehashing
        for (int i = 0; i < 50; i++) {
            map.put("Key" + i, i * 100);
        }
        boolean pass5 = (map.size() == 53 && map.get("Key25") == 2500 && map.get("Cherry") == 30);
        System.out.println("Bulk 50 items inserted. Final Size: " + map.size() + ", Final Capacity: " + map.capacity() + " -> " + (pass5 ? "PASS" : "FAIL"));

        // Test 6: Null key handling
        map.put(null, 9999);
        boolean pass6 = (map.get(null) == 9999 && map.containsKey(null));
        System.out.println("Null key support -> " + (pass6 ? "PASS" : "FAIL"));

        if (pass1 && pass2 && pass3 && pass4 && pass5 && pass6) {
            System.out.println("\nAll HashTable tests PASSED successfully!");
        } else {
            System.err.println("\nOne or more HashTable tests FAILED.");
        }
    }
}
