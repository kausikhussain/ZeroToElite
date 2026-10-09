package advanced.sliding_window;

import java.util.Arrays;

/**
 * Problem: Minimum Size Subarray Sum (LeetCode 209).
 * Difficulty: Medium.
 * Pattern: Dynamic Shrinking Sliding Window.
 * 
 * Problem Statement:
 * Given an array of positive integers nums and a positive integer target, return the minimal length
 * of a contiguous subarray [nums[l], nums[l+1], ..., nums[r-1], nums[r]] of which the sum is greater than
 * or equal to target. If there is no such subarray, return 0 instead.
 * 
 * Approach:
 * - Two pointers: left = 0, right traverses from 0 to n - 1.
 * - Add nums[right] to currentSum.
 * - While currentSum >= target (window constraint satisfied):
 *   - Record current length: minLen = min(minLen, right - left + 1).
 *   - Shrink window from the left: currentSum -= nums[left], then left++.
 * 
 * Key Insight:
 * All elements are POSITIVE integers. This property guarantees monotonicity:
 * expanding 'right' monotonically increases the sum, and advancing 'left' monotonically decreases the sum.
 * Because each element is added by 'right' once and subtracted by 'left' at most once,
 * the two pointers make at most 2N total operations, guaranteeing strictly O(N) runtime.
 * 
 * Time Complexity:
 * - O(N): Amortized linear time.
 * 
 * Space Complexity:
 * - O(1): Constant auxiliary variables.
 */
public class MinimumSizeSubarraySum {

    public static int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0 || target <= 0) {
            return 0;
        }

        int minLen = Integer.MAX_VALUE;
        int currentSum = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];

            // Contract the window from left as long as the sum condition is satisfied
            while (currentSum >= target) {
                minLen = Math.min(minLen, right - left + 1);
                currentSum -= nums[left];
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public static void main(String[] args) {
        System.out.println("=== 09_Advanced_DSA: Minimum Size Subarray Sum Verification ===");

        // Test 1: Standard case: target = 7, nums = [2,3,1,2,4,3] -> output 2 ([4, 3])
        int[] nums1 = {2, 3, 1, 2, 4, 3};
        int target1 = 7;
        int res1 = minSubArrayLen(target1, nums1);
        boolean pass1 = (res1 == 2);
        System.out.println("Test 1: Target " + target1 + ", " + Arrays.toString(nums1) + " -> Min Len: " + res1 + " -> " + (pass1 ? "PASS" : "FAIL"));

        // Test 2: Single element equals target: target = 4, nums = [1,4,4] -> output 1 ([4])
        int[] nums2 = {1, 4, 4};
        int target2 = 4;
        int res2 = minSubArrayLen(target2, nums2);
        boolean pass2 = (res2 == 1);
        System.out.println("Test 2: Target " + target2 + ", " + Arrays.toString(nums2) + " -> Min Len: " + res2 + " -> " + (pass2 ? "PASS" : "FAIL"));

        // Test 3: Total sum less than target: target = 11, nums = [1,1,1,1,1,1,1,1] -> output 0
        int[] nums3 = {1, 1, 1, 1, 1, 1, 1, 1};
        int target3 = 11;
        int res3 = minSubArrayLen(target3, nums3);
        boolean pass3 = (res3 == 0);
        System.out.println("Test 3: Target " + target3 + ", " + Arrays.toString(nums3) + " -> Min Len: " + res3 + " -> " + (pass3 ? "PASS" : "FAIL"));

        if (pass1 && pass2 && pass3) {
            System.out.println("\nAll Minimum Size Subarray Sum tests PASSED successfully!");
        } else {
            System.err.println("\nOne or more tests FAILED.");
        }
    }
}
