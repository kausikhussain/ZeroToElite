package collections;

import java.util.*;

/**
 * 05_Collections_Framework - Java Collections Framework Overview
 * This file serves as an educational walkthrough of the most commonly used collections in Java:
 * 1. List (ArrayList, LinkedList) - Ordered collection allowing duplicates.
 * 2. Set (HashSet, TreeSet) - Unordered/Ordered collection prohibiting duplicates.
 * 3. Map (HashMap, TreeMap) - Key-Value pair mappings.
 * 4. Queue (PriorityQueue, ArrayDeque) - FIFO or priority-based processing.
 */
public class CollectionsDemo {

    public static void main(String[] args) {
        System.out.println("=== 1. LIST DEMONSTRATION ===");
        // ArrayList: backed by dynamic array, O(1) random access, O(n) insertion/deletion (shifting elements)
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Python");
        list.add("Java"); // Duplicates allowed
        list.add("C++");
        System.out.println("ArrayList Elements: " + list);
        System.out.println("Element at index 1: " + list.get(1));

        // LinkedList: doubly-linked list, O(n) random access, O(1) addition/deletion at boundaries
        List<String> linkedList = new LinkedList<>(list);
        linkedList.add(0, "Go"); // Quick insert at front
        System.out.println("LinkedList Elements: " + linkedList);

        System.out.println("\n=== 2. SET DEMONSTRATION ===");
        // HashSet: backed by HashMap, O(1) insertion, retrieval, and deletion. No duplicate entries. Unordered.
        Set<String> set = new HashSet<>();
        set.add("Apple");
        set.add("Banana");
        set.add("Apple"); // Duplicate ignored
        set.add("Orange");
        System.out.println("HashSet Elements (Unordered, Unique): " + set);

        // TreeSet: backed by Red-Black tree, O(log n) operations. Elements sorted in natural order.
        Set<String> treeSet = new TreeSet<>(set);
        System.out.println("TreeSet Elements (Sorted naturally, Unique): " + treeSet);

        System.out.println("\n=== 3. MAP DEMONSTRATION ===");
        // HashMap: key-value storage, O(1) average lookup/insertion. Null keys and values allowed. Unordered.
        Map<Integer, String> map = new HashMap<>();
        map.put(101, "Alice");
        map.put(102, "Bob");
        map.put(103, "Charlie");
        map.put(102, "David"); // Overwrites key 102
        System.out.println("HashMap Entries (Key -> Value): " + map);
        System.out.println("Value for Key 101: " + map.get(101));

        // TreeMap: key-value storage sorted by keys, O(log n) operations.
        Map<Integer, String> treeMap = new TreeMap<>(map);
        System.out.println("TreeMap Entries (Sorted by Key): " + treeMap);

        System.out.println("\n=== 4. QUEUE DEMONSTRATION ===");
        // Queue (LinkedList wrapper): FIFO (First-In-First-Out)
        Queue<String> queue = new LinkedList<>();
        queue.offer("Task 1");
        queue.offer("Task 2");
        queue.offer("Task 3");
        
        System.out.println("Queue: " + queue);
        System.out.println("Polled (Removed from front): " + queue.poll()); // Removes Task 1
        System.out.println("Peek (Next in line): " + queue.peek()); // Pooks at Task 2
        System.out.println("Queue after operations: " + queue);
        
        // PriorityQueue: elements processed according to natural ordering or comparator
        Queue<Integer> pq = new PriorityQueue<>();
        pq.offer(40);
        pq.offer(10);
        pq.offer(30);
        System.out.println("\nPriorityQueue elements (automatically sorted by priority):");
        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " "); // Prints 10, 30, 40
        }
        System.out.println();
    }
}
