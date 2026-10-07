package dsa;

import java.util.Arrays;

/**
 * Problem: Sort an array of integers in non-decreasing order using Merge Sort.
 * Concept: Divide and Conquer, Stable Sorting, Recursive Tree Decomposition.
 * 
 * Approach:
 * 1. Divide: Recursively split the array into two halves at the midpoint.
 * 2. Conquer: Recursively sort each half until subarrays of length <= 1 are reached (base case).
 * 3. Combine (Merge): Merge the two sorted subarrays back together into sorted order using an auxiliary array.
 * 
 * Key Insight:
 * Unlike QuickSort, MergeSort guarantees O(N log N) time in ALL cases (best, average, worst)
 * because the array is always divided strictly in half. It is also a STABLE sort (preserves the relative
 * order of duplicate keys), which is why it is preferred for sorting linked lists and objects (e.g., Arrays.sort for objects in Java uses TimSort, a hybrid MergeSort).
 * 
 * Time Complexity:
 * - Best Case:    O(N log N) - recursion depth is always log2(N), each level merges N elements.
 * - Average Case: O(N log N)
 * - Worst Case:   O(N log N)
 * 
 * Space Complexity:
 * - O(N) auxiliary space for the temporary merge buffer.
 * - O(log N) space for the recursive call stack.
 */
public class MergeSort {

    /**
     * Public entry point for Merge Sort.
     * Validates input and delegates to recursive helper.
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int[] temp = new int[arr.length]; // Single auxiliary buffer allocated once to optimize memory
        mergeSort(arr, temp, 0, arr.length - 1);
    }

    /**
     * Recursive divide-and-conquer function.
     * 
     * @param arr   the array being sorted
     * @param temp  reusable auxiliary buffer
     * @param left  starting index of the current range
     * @param right ending index of the current range
     */
    private static void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left >= right) {
            return; // Base case: 1 or 0 elements
        }

        int mid = left + (right - left) / 2; // Prevents integer overflow compared to (left + right) / 2

        // Divide & Conquer
        mergeSort(arr, temp, left, mid);
        mergeSort(arr, temp, mid + 1, right);

        // Optimization: If the largest element of the left half is already <= smallest element of the right half,
        // then the two halves are already in sorted order! Skip merge step.
        if (arr[mid] <= arr[mid + 1]) {
            return;
        }

        // Combine
        merge(arr, temp, left, mid, right);
    }

    /**
     * Merges two sorted contiguous subarrays: [left..mid] and [mid+1..right].
     * Maintains stability by choosing left element when arr[i] == arr[j].
     */
    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        // Copy relevant segment to auxiliary buffer
        for (int k = left; k <= right; k++) {
            temp[k] = arr[k];
        }

        int i = left;      // Pointer for left half in temp
        int j = mid + 1;   // Pointer for right half in temp
        int k = left;      // Destination pointer in arr

        // Two-pointer merge comparison
        while (i <= mid && j <= right) {
            // '<=' ensures stability: duplicates from left half are placed first
            if (temp[i] <= temp[j]) {
                arr[k++] = temp[i++];
            } else {
                arr[k++] = temp[j++];
            }
        }

        // Copy any remaining elements from the left half
        // (Note: remaining elements from the right half are already in their correct places)
        while (i <= mid) {
            arr[k++] = temp[i++];
        }
    }

    /**
     * Comprehensive test driver covering boundary conditions and edge cases.
     */
    public static void main(String[] args) {
        System.out.println("=== 08_DSA_Implementations: Merge Sort Verification ===");

        // Test 1: Standard unsorted array
        int[] t1 = {38, 27, 43, 3, 9, 82, 10};
        sort(t1);
        boolean pass1 = Arrays.equals(t1, new int[]{3, 9, 10, 27, 38, 43, 82});
        System.out.println("Test 1 (Standard Unsorted): " + Arrays.toString(t1) + " -> " + (pass1 ? "PASS" : "FAIL"));

        // Test 2: Array with duplicate and negative numbers
        int[] t2 = {-5, 12, 0, -5, 8, -12, 0, 7};
        sort(t2);
        boolean pass2 = Arrays.equals(t2, new int[]{-12, -5, -5, 0, 0, 7, 8, 12});
        System.out.println("Test 2 (Duplicates & Negatives): " + Arrays.toString(t2) + " -> " + (pass2 ? "PASS" : "FAIL"));

        // Test 3: Already sorted array (tests the arr[mid] <= arr[mid+1] skip optimization)
        int[] t3 = {1, 2, 3, 4, 5, 6};
        sort(t3);
        boolean pass3 = Arrays.equals(t3, new int[]{1, 2, 3, 4, 5, 6});
        System.out.println("Test 3 (Already Sorted): " + Arrays.toString(t3) + " -> " + (pass3 ? "PASS" : "FAIL"));

        // Test 4: Reverse sorted array
        int[] t4 = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        sort(t4);
        boolean pass4 = Arrays.equals(t4, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        System.out.println("Test 4 (Reverse Sorted): " + Arrays.toString(t4) + " -> " + (pass4 ? "PASS" : "FAIL"));

        // Test 5: Single element & empty array (boundary edge cases)
        int[] t5a = {42};
        sort(t5a);
        int[] t5b = {};
        sort(t5b);
        boolean pass5 = (t5a[0] == 42 && t5b.length == 0);
        System.out.println("Test 5 (Single & Empty): " + (pass5 ? "PASS" : "FAIL"));

        // Test 6: All identical elements
        int[] t6 = {5, 5, 5, 5, 5};
        sort(t6);
        boolean pass6 = Arrays.equals(t6, new int[]{5, 5, 5, 5, 5});
        System.out.println("Test 6 (All Identical): " + Arrays.toString(t6) + " -> " + (pass6 ? "PASS" : "FAIL"));

        if (pass1 && pass2 && pass3 && pass4 && pass5 && pass6) {
            System.out.println("\nAll Merge Sort tests PASSED successfully!");
        } else {
            System.err.println("\nOne or more Merge Sort tests FAILED.");
        }
    }
}
