package advanced.two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem: 3Sum (LeetCode 15).
 * Difficulty: Medium.
 * Pattern: Sorting + Opposite-Direction Two Pointers with Duplicate Avoidance.
 * 
 * Problem Statement:
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that
 * i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * Notice that the solution set must not contain duplicate triplets.
 * 
 * Approach:
 * 1. Sort the array in non-decreasing order.
 * 2. Iterate index i from 0 to n - 3:
 *    - If nums[i] > 0, break early (since array is sorted, remaining elements cannot sum to 0).
 *    - Skip duplicates: if i > 0 && nums[i] == nums[i - 1], continue.
 *    - Set two pointers: left = i + 1, right = n - 1.
 *    - Target to find is: -nums[i].
 *    - While left < right:
 *      * If nums[left] + nums[right] == -nums[i]:
 *        Add triplet [nums[i], nums[left], nums[right]] to results.
 *        Advance left and decrement right while skipping duplicate values.
 *      * If sum < -nums[i]: left++
 *      * If sum > -nums[i]: right--
 * 
 * Key Insight:
 * The primary challenge in 3Sum is duplicate avoidance without using heavy HashSet conversions.
 * By sorting beforehand and advancing pointers past identical consecutive elements,
 * duplicate triplets are filtered out with zero auxiliary space overhead.
 * 
 * Time Complexity:
 * - O(N log N) for sorting + O(N^2) for nested two-pointer sweeps = O(N^2) overall.
 * 
 * Space Complexity:
 * - O(1) auxiliary space (excluding the output list and sort stack).
 */
public class ThreeSum {

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }

        // Step 1: Sort the array
        Arrays.sort(nums);

        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            // Early exit: if smallest element in triplet is > 0, sum cannot be 0
            if (nums[i] > 0) break;

            // Skip duplicate values for outer pointer
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = n - 1;
            int target = -nums[i];

            while (left < right) {
                int sum = nums[left] + nums[right];

                if (sum == target) {
                    result.add(List.of(nums[i], nums[left], nums[right]));

                    // Skip duplicate elements on left
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // Skip duplicate elements on right
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("=== 09_Advanced_DSA: 3Sum Verification ===");

        // Test 1: Standard case with multiple triplets
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> res1 = threeSum(nums1);
        System.out.println("Test 1: Input " + Arrays.toString(nums1) + " -> Result: " + res1);
        boolean pass1 = res1.size() == 2 && 
                        res1.contains(List.of(-1, -1, 2)) && 
                        res1.contains(List.of(-1, 0, 1));

        // Test 2: All zeros
        int[] nums2 = {0, 0, 0, 0};
        List<List<Integer>> res2 = threeSum(nums2);
        System.out.println("Test 2: Input [0, 0, 0, 0] -> Result: " + res2);
        boolean pass2 = res2.size() == 1 && res2.get(0).equals(List.of(0, 0, 0));

        // Test 3: No valid triplet
        int[] nums3 = {0, 1, 1};
        List<List<Integer>> res3 = threeSum(nums3);
        System.out.println("Test 3: Input [0, 1, 1] -> Result: " + res3);
        boolean pass3 = res3.isEmpty();

        if (pass1 && pass2 && pass3) {
            System.out.println("\nAll 3Sum tests PASSED successfully!");
        } else {
            System.err.println("\nOne or more 3Sum tests FAILED.");
        }
    }
}
