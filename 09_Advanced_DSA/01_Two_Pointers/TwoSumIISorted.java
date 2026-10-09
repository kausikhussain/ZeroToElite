package advanced.two_pointers;

import java.util.Arrays;

/**
 * Problem: Two Sum II - Input Array Is Sorted (LeetCode 167).
 * Difficulty: Medium.
 * Pattern: Opposite-Direction Two Pointers on Sorted Arrays.
 * 
 * Problem Statement:
 * Given a 1-indexed array of integers 'numbers' that is already sorted in non-decreasing order,
 * find two numbers such that they add up to a specific 'target' number.
 * Return the indices of the two numbers, 1-indexed, as an integer array [index1, index2].
 * 
 * Approach:
 * - Initialize two pointers: left = 0, right = numbers.length - 1.
 * - Calculate currentSum = numbers[left] + numbers[right].
 * - If currentSum == target: found the answer, return [left + 1, right + 1].
 * - If currentSum < target: to increase the sum, advance left pointer (left++).
 * - If currentSum > target: to decrease the sum, decrement right pointer (right--).
 * 
 * Key Insight:
 * The sorted invariant guarantees that moving 'left' strictly increases the candidate sum,
 * and moving 'right' strictly decreases the candidate sum. This eliminates the need for
 * an O(N) hash map or O(N^2) nested loops.
 * 
 * Time Complexity:
 * - O(N): Pointers move towards each other; at most N steps.
 * 
 * Space Complexity:
 * - O(1): Constant auxiliary space.
 */
public class TwoSumIISorted {

    public static int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) {
            return new int[]{-1, -1};
        }

        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int currentSum = numbers[left] + numbers[right];

            if (currentSum == target) {
                return new int[]{left + 1, right + 1}; // 1-indexed result
            } else if (currentSum < target) {
                left++;
            } else {
                right--;
            }
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        System.out.println("=== 09_Advanced_DSA: Two Sum II (Sorted Array) Verification ===");

        // Test 1: Standard case
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] res1 = twoSum(nums1, target1);
        boolean pass1 = Arrays.equals(res1, new int[]{1, 2});
        System.out.println("Test 1: Input " + Arrays.toString(nums1) + ", Target 9 -> " + Arrays.toString(res1) + " -> " + (pass1 ? "PASS" : "FAIL"));

        // Test 2: Negative numbers
        int[] nums2 = {-3, 3, 4, 90};
        int target2 = 0;
        int[] res2 = twoSum(nums2, target2);
        boolean pass2 = Arrays.equals(res2, new int[]{1, 2});
        System.out.println("Test 2: Input " + Arrays.toString(nums2) + ", Target 0 -> " + Arrays.toString(res2) + " -> " + (pass2 ? "PASS" : "FAIL"));

        // Test 3: Duplicate elements
        int[] nums3 = {5, 25, 75};
        int target3 = 100;
        int[] res3 = twoSum(nums3, target3);
        boolean pass3 = Arrays.equals(res3, new int[]{2, 3});
        System.out.println("Test 3: Input " + Arrays.toString(nums3) + ", Target 100 -> " + Arrays.toString(res3) + " -> " + (pass3 ? "PASS" : "FAIL"));

        if (pass1 && pass2 && pass3) {
            System.out.println("\nAll Two Sum II tests PASSED successfully!");
        } else {
            System.err.println("\nOne or more Two Sum II tests FAILED.");
        }
    }
}
