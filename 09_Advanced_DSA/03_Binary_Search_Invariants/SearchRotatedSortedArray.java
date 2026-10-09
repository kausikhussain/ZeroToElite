package advanced.binary_search;

import java.util.Arrays;

/**
 * Problem: Search in Rotated Sorted Array (LeetCode 33).
 * Difficulty: Medium.
 * Pattern: Binary Search with Rotated Subarray Partitioning.
 * 
 * Problem Statement:
 * Given an integer array nums sorted in ascending order (with distinct values) and rotated at
 * an unknown pivot index, return the index of target, or -1 if not found.
 * You must achieve O(log n) runtime complexity.
 * 
 * Approach:
 * - When dividing a rotated sorted array at midpoint 'mid', at least ONE of the two halves
 *   is GUARANTEED to be normally sorted without rotation.
 * - Identify which half is sorted:
 *   1. If nums[low] <= nums[mid]:
 *      The left half [low..mid] is strictly sorted.
 *      Check if target falls within this range: target >= nums[low] && target < nums[mid].
 *      If yes -> high = mid - 1. Else -> low = mid + 1.
 *   2. Else (nums[low] > nums[mid]):
 *      The right half [mid..high] is strictly sorted.
 *      Check if target falls within this range: target > nums[mid] && target <= nums[high].
 *      If yes -> low = mid + 1. Else -> high = mid - 1.
 * 
 * Key Insight:
 * We do NOT need to locate the pivot point first. Evaluating which boundary preserves
 * ordering at each step allows discarding half the search space on every iteration.
 * 
 * Time Complexity:
 * - O(log N): Standard logarithmic binary search.
 * 
 * Space Complexity:
 * - O(1): Constant auxiliary space.
 */
public class SearchRotatedSortedArray {

    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return mid; // Target found
            }

            // Case 1: Left half is normally sorted
            if (nums[low] <= nums[mid]) {
                // Check if target lies within the sorted left half
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            // Case 2: Right half is normally sorted
            else {
                // Check if target lies within the sorted right half
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        return -1; // Not found
    }

    public static void main(String[] args) {
        System.out.println("=== 09_Advanced_DSA: Search in Rotated Sorted Array Verification ===");

        // Test 1: Standard rotated array, target in right half
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int target1 = 0;
        int res1 = search(nums1, target1);
        boolean pass1 = (res1 == 4);
        System.out.println("Test 1: Target 0 in " + Arrays.toString(nums1) + " -> Index " + res1 + " -> " + (pass1 ? "PASS" : "FAIL"));

        // Test 2: Target in left half
        int target2 = 5;
        int res2 = search(nums1, target2);
        boolean pass2 = (res2 == 1);
        System.out.println("Test 2: Target 5 in " + Arrays.toString(nums1) + " -> Index " + res2 + " -> " + (pass2 ? "PASS" : "FAIL"));

        // Test 3: Target not present
        int target3 = 3;
        int res3 = search(nums1, target3);
        boolean pass3 = (res3 == -1);
        System.out.println("Test 3: Target 3 in " + Arrays.toString(nums1) + " -> Index " + res3 + " -> " + (pass3 ? "PASS" : "FAIL"));

        // Test 4: Single element array
        int[] nums4 = {1};
        int res4 = search(nums4, 0);
        boolean pass4 = (res4 == -1);
        System.out.println("Test 4: Target 0 in [1] -> Index " + res4 + " -> " + (pass4 ? "PASS" : "FAIL"));

        if (pass1 && pass2 && pass3 && pass4) {
            System.out.println("\nAll Rotated Sorted Array tests PASSED successfully!");
        } else {
            System.err.println("\nOne or more tests FAILED.");
        }
    }
}
