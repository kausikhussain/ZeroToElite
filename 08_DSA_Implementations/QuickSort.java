package dsa;

import java.util.Arrays;
import java.util.Random;

/**
 * Problem: Sort an array of integers in non-decreasing order using Quick Sort.
 * Concept: Divide and Conquer, In-Place Partitioning, Randomized Pivot Selection.
 * 
 * Approach:
 * 1. Pivot Selection: Pick a pivot element (randomized to prevent O(N^2) worst case on sorted arrays).
 * 2. Partition: Rearrange the array so that elements smaller than the pivot are to the left,
 *    and elements greater than or equal are to the right. The pivot settles in its final sorted index.
 * 3. Recurse: Recursively apply Quick Sort to the left and right partitions.
 * 
 * Key Insight:
 * Quick Sort sorts IN-PLACE without requiring O(N) extra memory like Merge Sort.
 * On modern hardware, it typically outperforms Merge Sort and Heap Sort due to excellent cache locality.
 * However, Quick Sort is UNSTABLE (relative order of identical elements is not preserved).
 * 
 * Partition Schemes Illustrated:
 * 1. Lomuto Partition: Simpler to implement; single pointer scan; swaps more frequently.
 * 2. Hoare Partition: Classic two-pointer inward scan; performs on average 3x fewer swaps than Lomuto.
 * 
 * Time Complexity:
 * - Best Case:    O(N log N) - partitions are evenly split in half.
 * - Average Case: O(N log N) - expected runtime across random inputs.
 * - Worst Case:   O(N^2) - heavily unbalanced splits (e.g. smallest/largest element always chosen as pivot).
 *                   Mitigated to practically O(N log N) using randomized pivot selection.
 * 
 * Space Complexity:
 * - O(log N) auxiliary stack space in best/average case.
 * - O(N) stack space in worst case (avoidable via tail-call elimination).
 */
public class QuickSort {

    private static final Random RNG = new Random();

    /**
     * Public sorting method using Randomized Lomuto Partitioning.
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Partition and obtain pivot's settled final position
            int pivotIndex = randomizedPartition(arr, low, high);

            // Recursively sort subarrays on either side of pivot
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    /**
     * Chooses a random pivot in [low, high], swaps it to high, and partitions.
     * Prevents adversarial inputs (like sorted or reverse-sorted data) from triggering O(N^2).
     */
    private static int randomizedPartition(int[] arr, int low, int high) {
        int randomPivotIdx = low + RNG.nextInt(high - low + 1);
        swap(arr, randomPivotIdx, high);
        return partitionLomuto(arr, low, high);
    }

    /**
     * Lomuto Partition Scheme:
     * - Pivot is at arr[high].
     * - Pointer 'i' marks the boundary of elements <= pivot.
     * - Pointer 'j' explores from low to high - 1.
     */
    public static int partitionLomuto(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1; // Index of smaller element

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        // Place pivot in correct final position
        swap(arr, i + 1, high);
        return i + 1;
    }

    /**
     * Alternative: Hoare Partition Scheme.
     * Two pointers move inwards from opposite ends until they find elements that violate the partition rule.
     * Note: Hoare partition does not necessarily place the pivot at the returned split point.
     */
    public static int partitionHoare(int[] arr, int low, int high) {
        int pivot = arr[low + (high - low) / 2];
        int i = low - 1;
        int j = high + 1;

        while (true) {
            do {
                i++;
            } while (arr[i] < pivot);

            do {
                j--;
            } while (arr[j] > pivot);

            if (i >= j) {
                return j;
            }
            swap(arr, i, j);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Comprehensive test driver covering boundary conditions and edge cases.
     */
    public static void main(String[] args) {
        System.out.println("=== 08_DSA_Implementations: Quick Sort Verification ===");

        // Test 1: Standard unsorted array
        int[] t1 = {10, 7, 8, 9, 1, 5};
        sort(t1);
        boolean pass1 = Arrays.equals(t1, new int[]{1, 5, 7, 8, 9, 10});
        System.out.println("Test 1 (Standard Unsorted): " + Arrays.toString(t1) + " -> " + (pass1 ? "PASS" : "FAIL"));

        // Test 2: Array with duplicate and negative numbers
        int[] t2 = {-3, 4, -1, 4, 0, -3, 8, 2};
        sort(t2);
        boolean pass2 = Arrays.equals(t2, new int[]{-3, -3, -1, 0, 2, 4, 4, 8});
        System.out.println("Test 2 (Duplicates & Negatives): " + Arrays.toString(t2) + " -> " + (pass2 ? "PASS" : "FAIL"));

        // Test 3: Already sorted array (tests randomized pivot resistance to O(N^2))
        int[] t3 = {1, 2, 3, 4, 5, 6, 7};
        sort(t3);
        boolean pass3 = Arrays.equals(t3, new int[]{1, 2, 3, 4, 5, 6, 7});
        System.out.println("Test 3 (Already Sorted): " + Arrays.toString(t3) + " -> " + (pass3 ? "PASS" : "FAIL"));

        // Test 4: Reverse sorted array
        int[] t4 = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        sort(t4);
        boolean pass4 = Arrays.equals(t4, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        System.out.println("Test 4 (Reverse Sorted): " + Arrays.toString(t4) + " -> " + (pass4 ? "PASS" : "FAIL"));

        // Test 5: Single element & empty array
        int[] t5a = {99};
        sort(t5a);
        int[] t5b = {};
        sort(t5b);
        boolean pass5 = (t5a[0] == 99 && t5b.length == 0);
        System.out.println("Test 5 (Single & Empty): " + (pass5 ? "PASS" : "FAIL"));

        // Test 6: All identical elements
        int[] t6 = {7, 7, 7, 7, 7};
        sort(t6);
        boolean pass6 = Arrays.equals(t6, new int[]{7, 7, 7, 7, 7});
        System.out.println("Test 6 (All Identical): " + Arrays.toString(t6) + " -> " + (pass6 ? "PASS" : "FAIL"));

        if (pass1 && pass2 && pass3 && pass4 && pass5 && pass6) {
            System.out.println("\nAll Quick Sort tests PASSED successfully!");
        } else {
            System.err.println("\nOne or more Quick Sort tests FAILED.");
        }
    }
}
