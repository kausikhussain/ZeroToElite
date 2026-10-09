package advanced.binary_search;

import java.util.Arrays;

/**
 * Problem: Find First and Last Position of Element in Sorted Array / Lower & Upper Bound (LeetCode 34).
 * Difficulty: Medium.
 * Pattern: Binary Search Boundary Invariants.
 * 
 * Problem Statement:
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending
 * position of a given target value. If target is not found in the array, return [-1, -1].
 * You must write an algorithm with O(log n) runtime complexity.
 * 
 * Approach:
 * - Standard binary search stops as soon as nums[mid] == target.
 * - To find the FIRST occurrence:
 *   When nums[mid] == target, record mid as candidate answer, but keep searching LEFT (high = mid - 1).
 * - To find the LAST occurrence:
 *   When nums[mid] == target, record mid as candidate answer, but keep searching RIGHT (low = mid + 1).
 * 
 * Key Invariant:
 * The search space [low, high] strictly maintains the boundary.
 * Mid calculation uses low + (high - low) / 2 to prevent integer overflow.
 * 
 * Time Complexity:
 * - O(log N): Two independent binary searches, each taking logarithmic time.
 * 
 * Space Complexity:
 * - O(1): Constant auxiliary space.
 */
public class BinarySearchBounds {

    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        int first = findBound(nums, target, true);
        if (first == -1) {
            return new int[]{-1, -1}; // Target does not exist in array
        }
        int last = findBound(nums, target, false);

        return new int[]{first, last};
    }

    /**
     * Binary search helper to find the boundary index.
     * @param isFirst if true, searches for first occurrence; if false, searches for last.
     */
    public static int findBound(int[] nums, int target, boolean isFirst) {
        int low = 0;
        int high = nums.length - 1;
        int boundIndex = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                boundIndex = mid;
                if (isFirst) {
                    high = mid - 1; // Narrow search to left half
                } else {
                    low = mid + 1;  // Narrow search to right half
                }
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return boundIndex;
    }

    public static void main(String[] args) {
        System.out.println("=== 09_Advanced_DSA: Binary Search Bounds Verification ===");

        // Test 1: Multiple occurrences
        int[] nums1 = {5, 7, 7, 8, 8, 10};
        int target1 = 8;
        int[] res1 = searchRange(nums1, target1);
        boolean pass1 = Arrays.equals(res1, new int[]{3, 4});
        System.out.println("Test 1: Target 8 in " + Arrays.toString(nums1) + " -> " + Arrays.toString(res1) + " -> " + (pass1 ? "PASS" : "FAIL"));

        // Test 2: Target not present
        int target2 = 6;
        int[] res2 = searchRange(nums1, target2);
        boolean pass2 = Arrays.equals(res2, new int[]{-1, -1});
        System.out.println("Test 2: Target 6 in " + Arrays.toString(nums1) + " -> " + Arrays.toString(res2) + " -> " + (pass2 ? "PASS" : "FAIL"));

        // Test 3: Single occurrence
        int[] nums3 = {1};
        int target3 = 1;
        int[] res3 = searchRange(nums3, target3);
        boolean pass3 = Arrays.equals(res3, new int[]{0, 0});
        System.out.println("Test 3: Target 1 in [1] -> " + Arrays.toString(res3) + " -> " + (pass3 ? "PASS" : "FAIL"));

        // Test 4: All elements identical to target
        int[] nums4 = {2, 2, 2, 2, 2};
        int target4 = 2;
        int[] res4 = searchRange(nums4, target4);
        boolean pass4 = Arrays.equals(res4, new int[]{0, 4});
        System.out.println("Test 4: Target 2 in [2,2,2,2,2] -> " + Arrays.toString(res4) + " -> " + (pass4 ? "PASS" : "FAIL"));

        if (pass1 && pass2 && pass3 && pass4) {
            System.out.println("\nAll Binary Search Bounds tests PASSED successfully!");
        } else {
            System.err.println("\nOne or more tests FAILED.");
        }
    }
}
