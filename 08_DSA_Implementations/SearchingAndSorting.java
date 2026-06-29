package dsa;

import java.util.Arrays;

/**
 * 08_DSA_Implementations - Searching and Sorting Algorithms
 * This class provides clean, documented implementations of fundamental algorithms:
 * 1. Searching: Linear Search (O(n)), Binary Search (O(log n) - requires sorted array)
 * 2. Sorting: Bubble Sort (O(n^2)), Selection Sort (O(n^2))
 */
public class SearchingAndSorting {

    public static void main(String[] args) {
        System.out.println("=== 1. SEARCHING ALGORITHMS ===");
        int[] searchArr = {10, 20, 30, 40, 50, 60};
        int key = 40;
        System.out.println("Input Array: " + Arrays.toString(searchArr));
        
        int linIndex = linearSearch(searchArr, key);
        System.out.println("Linear Search: Element " + key + " found at index: " + linIndex);

        int binIndex = binarySearch(searchArr, key);
        System.out.println("Binary Search: Element " + key + " found at index: " + binIndex);

        System.out.println("\n=== 2. SORTING ALGORITHMS ===");
        int[] unsorted1 = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Unsorted 1: " + Arrays.toString(unsorted1));
        bubbleSort(unsorted1);
        System.out.println("Bubble Sorted: " + Arrays.toString(unsorted1));

        int[] unsorted2 = {29, 10, 14, 37, 13, 2, 8};
        System.out.println("\nUnsorted 2: " + Arrays.toString(unsorted2));
        selectionSort(unsorted2);
        System.out.println("Selection Sorted: " + Arrays.toString(unsorted2));
    }

    /**
     * Linear Search: Scans every element sequentially.
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static int linearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i; // Found
            }
        }
        return -1; // Not found
    }

    /**
     * Binary Search: Iteratively divides search space in half.
     * Array must be sorted beforehand.
     * Time Complexity: O(log n), Space Complexity: O(1)
     */
    public static int binarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2; // Prevents overflow

            if (arr[mid] == key) {
                return mid; // Found
            } else if (key < arr[mid]) {
                high = mid - 1; // Left half
            } else {
                low = mid + 1; // Right half
            }
        }
        return -1; // Not found
    }

    /**
     * Bubble Sort: Repeatedly steps through the list, compares adjacent elements,
     * and swaps them if they are in the wrong order.
     * Time Complexity: O(n^2) worst/average, O(n) best. Space Complexity: O(1)
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap adjacent elements
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no elements were swapped in the inner loop, array is sorted
            if (!swapped) break;
        }
    }

    /**
     * Selection Sort: Finds the minimum element from the unsorted part and
     * puts it at the beginning.
     * Time Complexity: O(n^2), Space Complexity: O(1)
     */
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            // Find the minimum element in unsorted array
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            // Swap the found minimum element with the first element
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }
}
