package dsa;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Problem: Implement a generic Resizable Dynamic Array from scratch (mimicking java.util.ArrayList).
 * Concept: Contiguous Memory Allocation, Geometric Array Doubling, Amortized Analysis O(1).
 * 
 * Key Insights:
 * 1. Why geometric doubling (factor of 2)?
 *    If capacity grows by a fixed constant (e.g. +10):
 *      Total copy operations for N elements = 10 + 20 + 30 + ... + N = O(N^2). Average per insert = O(N).
 *    If capacity doubles (1 -> 2 -> 4 -> 8 -> ... -> N):
 *      Total copy operations for N elements = 1 + 2 + 4 + ... + N/2 < N.
 *      Amortized cost per insertion = (N work) / (N insertions) = O(1) constant time!
 * 2. Downsizing: When elements decrease to <= capacity / 4, shrink capacity by half to reclaim memory,
 *    avoiding thrashing at the boundary (hysteresis).
 * 
 * Time Complexity:
 * - Append (add):     Amortized O(1), Worst Case O(N) when resizing occurs.
 * - Access (get):     O(1) - direct pointer offset in contiguous memory.
 * - Update (set):     O(1).
 * - Insert at index:  O(N) - requires shifting elements right.
 * - Remove at index:  O(N) - requires shifting elements left.
 * 
 * Space Complexity:
 * - O(N) where N is the current capacity.
 */
public class DynamicArray<T> implements Iterable<T> {

    private static final int DEFAULT_INITIAL_CAPACITY = 4;

    @SuppressWarnings("unchecked")
    private T[] data = (T[]) new Object[DEFAULT_INITIAL_CAPACITY];
    private int size = 0;

    public DynamicArray() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public DynamicArray(int initialCapacity) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("Initial capacity must be at least 1.");
        }
        this.data = (T[]) new Object[initialCapacity];
        this.size = 0;
    }

    /**
     * Appends an element to the end of the array.
     * Amortized O(1) time complexity.
     */
    public void add(T element) {
        if (size == data.length) {
            resize(data.length * 2);
        }
        data[size++] = element;
    }

    /**
     * Inserts an element at the specified index, shifting subsequent elements right.
     * O(N) time complexity.
     */
    public void add(int index, T element) {
        checkIndexForAdd(index);
        if (size == data.length) {
            resize(data.length * 2);
        }
        // Shift elements right by one position
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    /**
     * Retrieves element at specified index.
     * O(1) time complexity.
     */
    public T get(int index) {
        checkIndex(index);
        return data[index];
    }

    /**
     * Updates element at specified index and returns the old value.
     * O(1) time complexity.
     */
    public T set(int index, T element) {
        checkIndex(index);
        T oldVal = data[index];
        data[index] = element;
        return oldVal;
    }

    /**
     * Removes element at specified index, shifting subsequent elements left.
     * O(N) time complexity.
     */
    public T remove(int index) {
        checkIndex(index);
        T removedVal = data[index];

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(data, index + 1, data, index, numMoved);
        }
        data[--size] = null; // Prevent memory leak (allow GC)

        // Shrink capacity if size drops to <= 1/4 of capacity (prevents thrashing)
        if (size > 0 && size <= data.length / 4 && data.length / 2 >= DEFAULT_INITIAL_CAPACITY) {
            resize(data.length / 2);
        }

        return removedVal;
    }

    /**
     * Checks if array contains the given value.
     * O(N) time complexity.
     */
    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    /**
     * Returns the index of the first occurrence of the specified element, or -1 if not found.
     */
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? data[i] == null : element.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        Arrays.fill(data, 0, size, null);
        size = 0;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        T[] newData = (T[]) new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                return data[cursor++];
            }
        };
    }

    @Override
    public String toString() {
        if (size == 0) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Comprehensive test driver verifying dynamic resizing, shifting, and bounds.
     */
    public static void main(String[] args) {
        System.out.println("=== 08_DSA_Implementations: Dynamic Array Verification ===");

        DynamicArray<Integer> arr = new DynamicArray<>(2);
        System.out.println("Initial capacity: " + arr.capacity() + ", size: " + arr.size());

        // Test 1: Append elements triggering automatic capacity doubling
        arr.add(10);
        arr.add(20);
        System.out.println("After adding 10, 20 -> capacity: " + arr.capacity() + ", elements: " + arr);
        boolean pass1 = (arr.capacity() == 2 && arr.size() == 2);

        arr.add(30); // Triggers resize to 4
        System.out.println("After adding 30 -> capacity: " + arr.capacity() + ", elements: " + arr);
        boolean pass2 = (arr.capacity() == 4 && arr.size() == 3);

        arr.add(40);
        arr.add(50); // Triggers resize to 8
        System.out.println("After adding 40, 50 -> capacity: " + arr.capacity() + ", elements: " + arr);
        boolean pass3 = (arr.capacity() == 8 && arr.size() == 5);

        // Test 2: Insert at specific index
        arr.add(2, 25); // Insert 25 at index 2
        System.out.println("After insert 25 at index 2: " + arr);
        boolean pass4 = (arr.get(2) == 25 && arr.get(3) == 30 && arr.size() == 6);

        // Test 3: Remove at index and shrink capacity
        int removed = arr.remove(2); // Remove 25
        System.out.println("Removed: " + removed + ", array: " + arr);
        boolean pass5 = (removed == 25 && arr.size() == 5);

        // Remove more to trigger shrink
        arr.remove(0); // 10
        arr.remove(0); // 20
        arr.remove(0); // 30
        arr.remove(0); // 40
        System.out.println("After multiple removals -> capacity: " + arr.capacity() + ", size: " + arr.size() + ", elements: " + arr);
        boolean pass6 = (arr.capacity() <= 4 && arr.size() == 1);

        // Test 4: Bounds checking exceptions
        boolean pass7 = false;
        try {
            arr.get(99);
        } catch (IndexOutOfBoundsException e) {
            pass7 = true;
        }

        if (pass1 && pass2 && pass3 && pass4 && pass5 && pass6 && pass7) {
            System.out.println("\nAll Dynamic Array tests PASSED successfully!");
        } else {
            System.err.println("\nOne or more Dynamic Array tests FAILED.");
        }
    }
}
