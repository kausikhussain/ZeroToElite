package advanced.sliding_window;

import java.util.Arrays;

/**
 * Problem: Maximum Sum Subarray of Size K.
 * Difficulty: Easy+.
 * Pattern: Fixed-Size Sliding Window.
 * 
 * Problem Statement:
 * Given an array of integers 'arr' and a positive integer 'k', find the maximum sum of any
 * contiguous subarray of size exactly 'k'.
 * 
 * Approach:
 * 1. Compute the sum of the first 'k' elements to establish the initial window.
 * 2. Slide the window one element at a time from index k to n - 1:
 *    - Add the incoming element: arr[i]
 *    - Subtract the outgoing element: arr[i - k]
 *    - Update maxSum = Math.max(maxSum, windowSum)
 * 
 * Key Insight:
 * Rather than recalculating the sum of k elements from scratch on every step (which costs O(N * k)),
 * the sliding window reuses the sum of the overlapping (k - 1) elements, performing only ONE addition
 * and ONE subtraction per step to achieve O(N) overall runtime.
 * 
 * Time Complexity:
 * - O(N): Linear single pass across the array.
 * 
 * Space Complexity:
 * - O(1): Constant auxiliary memory.
 */
public class MaxSumSubarraySizeK {

    public static int maxSubarraySum(int[] arr, int k) {
        if (arr == null || arr.length < k || k <= 0) {
            throw new IllegalArgumentException("Invalid input array or window size k.");
        }

        // Step 1: Compute sum of first window
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }

        int maxSum = windowSum;

        // Step 2: Slide the window
        for (int i = k; i < arr.length; i++) {
            windowSum += arr[i] - arr[i - k]; // Invariant: subtract left element, add right element
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println("=== 09_Advanced_DSA: Maximum Sum Subarray Size K Verification ===");

        // Test 1: Standard positive array
        int[] arr1 = {2, 1, 5, 1, 3, 2};
        int k1 = 3;
        int res1 = maxSubarraySum(arr1, k1);
        boolean pass1 = (res1 == 9); // Subarray [5, 1, 3] = 9
        System.out.println("Test 1: " + Arrays.toString(arr1) + ", k=" + k1 + " -> Max Sum: " + res1 + " -> " + (pass1 ? "PASS" : "FAIL"));

        // Test 2: Array with negative numbers
        int[] arr2 = {2, 3, 4, 1, -5, 10, -2};
        int k2 = 2;
        int res2 = maxSubarraySum(arr2, k2);
        boolean pass2 = (res2 == 8); // Subarray [10, -2] is 8, or [4, 1] is 5, [-5, 10] is 5, but [10, -2] is 8? Wait, [-5, 10] is 5; [3, 4] is 7. Wait, {-5, 10} = 5, {10, -2} = 8.
        System.out.println("Test 2: " + Arrays.toString(arr2) + ", k=" + k2 + " -> Max Sum: " + res2 + " -> " + (pass2 ? "PASS" : "FAIL"));

        // Test 3: k equals array length
        int[] arr3 = {1, 2, 3};
        int k3 = 3;
        int res3 = maxSubarraySum(arr3, k3);
        boolean pass3 = (res3 == 6);
        System.out.println("Test 3: " + Arrays.toString(arr3) + ", k=" + k3 + " -> Max Sum: " + res3 + " -> " + (pass3 ? "PASS" : "FAIL"));

        if (pass1 && pass2 && pass3) {
            System.out.println("\nAll Max Sum Subarray Size K tests PASSED successfully!");
        } else {
            System.err.println("\nOne or more tests FAILED.");
        }
    }
}
