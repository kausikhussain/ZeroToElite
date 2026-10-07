package dsa;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Problem: Implement a Binary Min-Heap and Max-Heap from scratch using an array.
 * Concept: Complete Binary Tree, Implicit Array Indexing, Sift-Up, Sift-Down, O(N) Build-Heap, Heap Sort.
 * 
 * Array Representation of a Complete Binary Tree (0-indexed):
 * - Parent index of node i:      (i - 1) / 2
 * - Left child index of node i:  2 * i + 1
 * - Right child index of node i: 2 * i + 2
 * 
 * Key Invariants:
 * 1. Shape Invariant: Every level is completely filled except possibly the last, which is filled from left to right.
 * 2. Min-Heap Invariant: arr[parent] <= arr[child] for every non-root node.
 * 3. Max-Heap Invariant: arr[parent] >= arr[child] for every non-root node.
 * 
 * Why is buildHeap O(N) and not O(N log N)?
 * - Bottom-up approach starts sift-down from index (N/2 - 1) to 0.
 * - Leaves (N/2 nodes) do 0 work.
 * - Nodes at height 1 (N/4 nodes) sift down at most 1 step.
 * - Total work = sum(h = 0 to log N) [ (N / 2^(h+1)) * h ] = N * sum(h / 2^(h+1)) = N * 1 = O(N).
 * 
 * Time Complexity:
 * - peek():        O(1)
 * - insert():      O(log N) (sift-up)
 * - extractMin():  O(log N) (sift-down)
 * - buildHeap():   O(N) linear time
 * - heapSort():    O(N log N) in-place with O(1) auxiliary space
 * 
 * Space Complexity:
 * - O(N) for the underlying heap array.
 */
public class BinaryHeap {

    // =========================================================================
    // 1. MIN-HEAP IMPLEMENTATION
    // =========================================================================
    public static class MinHeap {
        private int[] heap;
        private int size;
        private int capacity;

        public MinHeap(int initialCapacity) {
            this.capacity = initialCapacity;
            this.heap = new int[capacity];
            this.size = 0;
        }

        /**
         * Linear-time O(N) heap construction from an existing array.
         */
        public static MinHeap buildFrom(int[] array) {
            MinHeap minHeap = new MinHeap(array.length);
            System.arraycopy(array, 0, minHeap.heap, 0, array.length);
            minHeap.size = array.length;

            // Start from the last non-leaf node down to the root
            for (int i = (minHeap.size / 2) - 1; i >= 0; i--) {
                minHeap.siftDown(i);
            }
            return minHeap;
        }

        public void insert(int val) {
            if (size == capacity) {
                ensureCapacity();
            }
            heap[size] = val;
            siftUp(size);
            size++;
        }

        public int peek() {
            if (size == 0) throw new NoSuchElementException("Heap is empty.");
            return heap[0];
        }

        public int extractMin() {
            if (size == 0) throw new NoSuchElementException("Heap is empty.");
            int minVal = heap[0];

            // Move the last element to the root and sift down
            heap[0] = heap[size - 1];
            size--;
            if (size > 0) {
                siftDown(0);
            }
            return minVal;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private void siftUp(int i) {
            while (i > 0) {
                int parent = (i - 1) / 2;
                if (heap[i] < heap[parent]) {
                    swap(i, parent);
                    i = parent;
                } else {
                    break;
                }
            }
        }

        private void siftDown(int i) {
            while (2 * i + 1 < size) {
                int left = 2 * i + 1;
                int right = 2 * i + 2;
                int smallest = left;

                if (right < size && heap[right] < heap[left]) {
                    smallest = right;
                }

                if (heap[smallest] < heap[i]) {
                    swap(i, smallest);
                    i = smallest;
                } else {
                    break;
                }
            }
        }

        private void swap(int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }

        private void ensureCapacity() {
            capacity *= 2;
            heap = Arrays.copyOf(heap, capacity);
        }
    }

    // =========================================================================
    // 2. IN-PLACE HEAP SORT (Using Max-Heap property)
    // =========================================================================

    /**
     * Sorts an array in non-decreasing order in O(N log N) time and O(1) extra space.
     */
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;

        // Step 1: Build Max-Heap bottom-up in O(N)
        for (int i = (n / 2) - 1; i >= 0; i--) {
            siftDownMax(arr, n, i);
        }

        // Step 2: Extract elements from max heap one by one (O(N log N))
        for (int i = n - 1; i > 0; i--) {
            // Move current root (maximum element) to end of array
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Restore max heap property on reduced heap of size i
            siftDownMax(arr, i, 0);
        }
    }

    private static void siftDownMax(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            siftDownMax(arr, n, largest);
        }
    }

    /**
     * Comprehensive test driver verifying heap operations and heap sort.
     */
    public static void main(String[] args) {
        System.out.println("=== 08_DSA_Implementations: Binary Heap & HeapSort Verification ===");

        // Test 1: MinHeap element extraction ordering
        MinHeap minHeap = new MinHeap(4);
        int[] insertValues = {15, 10, 20, 17, 8, 25, 5};
        for (int v : insertValues) {
            minHeap.insert(v);
        }
        System.out.println("Inserted values into MinHeap: " + Arrays.toString(insertValues));

        int[] extracted = new int[insertValues.length];
        for (int i = 0; i < insertValues.length; i++) {
            extracted[i] = minHeap.extractMin();
        }
        System.out.println("Extracted in order: " + Arrays.toString(extracted));
        boolean pass1 = Arrays.equals(extracted, new int[]{5, 8, 10, 15, 17, 20, 25});

        // Test 2: O(N) buildHeap verification
        int[] raw = {40, 10, 30, 5, 70, 20};
        MinHeap builtHeap = MinHeap.buildFrom(raw);
        boolean pass2 = (builtHeap.peek() == 5);
        System.out.println("buildHeap root: " + builtHeap.peek() + " (Expected: 5) -> " + (pass2 ? "PASS" : "FAIL"));

        // Test 3: HeapSort on arbitrary array (with negatives and duplicates)
        int[] unsorted = {12, 11, -5, 13, 5, 6, 7, -5, 0};
        heapSort(unsorted);
        System.out.println("HeapSorted array: " + Arrays.toString(unsorted));
        boolean pass3 = Arrays.equals(unsorted, new int[]{-5, -5, 0, 5, 6, 7, 11, 12, 13});

        if (pass1 && pass2 && pass3) {
            System.out.println("\nAll Binary Heap and HeapSort tests PASSED successfully!");
        } else {
            System.err.println("\nOne or more Heap tests FAILED.");
        }
    }
}
