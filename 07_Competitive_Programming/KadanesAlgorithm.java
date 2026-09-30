package competitive;

import java.util.Arrays;

/**
 * 07_Competitive_Programming - Kadane's Algorithm & Maximum Subarray Problems
 *
 * Problem: Given an integer array 'nums', find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 *
 * This file covers three standard interview variations:
 * 1. Standard Kadane's Algorithm (Handles all negative numbers safely)
 * 2. Kadane's with Subarray Indices (Finds exact start and end of max subarray)
 * 3. Maximum Circular Subarray Sum (Handles circular wrap-around subarrays)
 *
 * Complexity:
 * - Time Complexity: O(N) single pass through the array.
 * - Space Complexity: O(1) auxiliary space.
 */
public class KadanesAlgorithm {

    public static void main(String[] args) {
        System.out.println("=== 1. Standard Kadane's Algorithm ===");
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Input Array: " + Arrays.toString(nums1));
        System.out.println("Max Subarray Sum: " + maxSubArray(nums1)); // Expected: 6 (Subarray: [4, -1, 2, 1])

        System.out.println("\n=== 2. All-Negative Elements Case ===");
        int[] nums2 = {-5, -3, -8, -2, -9};
        System.out.println("Input Array: " + Arrays.toString(nums2));
        System.out.println("Max Subarray Sum: " + maxSubArray(nums2)); // Expected: -2

        System.out.println("\n=== 3. Max Subarray with Start & End Indices ===");
        int[] res = maxSubArrayWithIndices(nums1);
        System.out.println("Max Sum: " + res[0] + " from index " + res[1] + " to " + res[2]);
        System.out.print("Optimal Subarray: [");
        for (int i = res[1]; i <= res[2]; i++) {
            System.out.print(nums1[i] + (i < res[2] ? ", " : ""));
        }
        System.out.println("]");

        System.out.println("\n=== 4. Maximum Circular Subarray Sum ===");
        int[] circularNums = {5, -3, 5};
        System.out.println("Input Array: " + Arrays.toString(circularNums));
        System.out.println("Max Circular Subarray Sum: " + maxSubarrayCircular(circularNums)); // Expected: 10 (wrap-around [5, 5])
    }

    /**
     * Standard Kadane's Algorithm.
     * Computes the maximum contiguous subarray sum in a single linear pass.
     *
     * Invariant:
     * - currentSum: Maximum subarray sum ending at current index.
     * - maxSum: Overall maximum subarray sum found so far.
     *
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty.");
        }

        int currentSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // Either extend the previous subarray or start a new one from nums[i]
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    /**
     * Kadane's algorithm extending to track the [start, end] indices of the optimal subarray.
     * Returns int[] {maxSum, startIndex, endIndex}.
     */
    public static int[] maxSubArrayWithIndices(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty.");
        }

        int currentSum = nums[0];
        int maxSum = nums[0];
        int start = 0;
        int end = 0;
        int tempStart = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > currentSum + nums[i]) {
                currentSum = nums[i];
                tempStart = i; // Reset subarray start
            } else {
                currentSum += nums[i];
            }

            if (currentSum > maxSum) {
                maxSum = currentSum;
                start = tempStart;
                end = i;
            }
        }

        return new int[]{maxSum, start, end};
    }

    /**
     * Finds the maximum subarray sum in a circular array (LeetCode 918).
     * The optimal circular sum can either be:
     * 1. A non-wrapping contiguous subarray (standard Kadane's).
     * 2. A wrapping subarray spanning the ends, equivalent to totalSum - minimumSubarraySum.
     *
     * Special case: If all elements are negative, totalSum == minSum, so return standard maxSum.
     *
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public static int maxSubarrayCircular(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int totalSum = 0;
        int maxKadane = nums[0], currMax = 0;
        int minKadane = nums[0], currMin = 0;

        for (int x : nums) {
            totalSum += x;

            // Standard Kadane for maximum subarray
            currMax = Math.max(x, currMax + x);
            maxKadane = Math.max(maxKadane, currMax);

            // Inverted Kadane for minimum subarray
            currMin = Math.min(x, currMin + x);
            minKadane = Math.min(minKadane, currMin);
        }

        // If all elements are negative, maxKadane is the maximum element (negative)
        // totalSum - minKadane would yield 0 (empty set), which is invalid since subarray must be non-empty.
        if (maxKadane < 0) {
            return maxKadane;
        }

        return Math.max(maxKadane, totalSum - minKadane);
    }
}
