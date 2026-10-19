package advanced.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem: Permutations (LeetCode 46).
 * Difficulty: Medium.
 * Pattern: Backtracking / State Exploration & Swapping.
 * 
 * Problem Statement:
 * Given an array 'nums' of distinct integers, return all possible permutations.
 * You can return the answer in any order.
 * 
 * Approach 1 (State Tracking via Boolean Array):
 * - Maintain a boolean[] 'used' array indicating which elements are currently part of 'path'.
 * - At each recursion level:
 *     - If path.size() == nums.length: all elements have been placed, add copy of path to res.
 *     - For each index i from 0 to nums.length - 1:
 *         - If used[i] is true, continue.
 *         - Choose: used[i] = true, path.add(nums[i]).
 *         - Explore: recurse to fill subsequent positions.
 *         - Backtrack: path.remove(path.size() - 1), used[i] = false.
 * 
 * Approach 2 (In-Place Array Swapping):
 * - Avoids extra space for 'used' and dynamic 'path' list by swapping elements directly in nums.
 * - At index 'start':
 *     - For each index i from 'start' to nums.length - 1:
 *         - Swap nums[start] with nums[i].
 *         - Explore: recurse with start + 1.
 *         - Backtrack: swap nums[start] with nums[i] back to restore original order.
 * 
 * Key Invariant:
 * Every permutation corresponds to an ordered traversal of a decision tree where branching
 * factor decreases from N to 1. State restoration guarantees each choice is isolated.
 * 
 * Time Complexity:
 * - O(N * N!): There are N! unique permutations, and each requires O(N) time to construct.
 * 
 * Space Complexity:
 * - O(N): Recursion call stack depth of N and O(N) auxiliary space for the 'used' / 'path' structure.
 */
public class Permutations {

    /**
     * Approach 1: Backtracking with boolean 'used' state tracking.
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        boolean[] used = new boolean[nums.length];
        backtrack(nums, used, new ArrayList<>(), res);
        return res;
    }

    private static void backtrack(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> res) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }

            // 1. Choose
            used[i] = true;
            path.add(nums[i]);

            // 2. Explore
            backtrack(nums, used, path, res);

            // 3. Un-choose (Backtrack)
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }

    /**
     * Approach 2: Backtracking with In-Place Swapping.
     */
    public static List<List<Integer>> permuteSwap(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        int[] arr = nums.clone();
        backtrackSwap(arr, 0, res);
        return res;
    }

    private static void backtrackSwap(int[] arr, int start, List<List<Integer>> res) {
        if (start == arr.length) {
            List<Integer> permutation = new ArrayList<>(arr.length);
            for (int val : arr) {
                permutation.add(val);
            }
            res.add(permutation);
            return;
        }

        for (int i = start; i < arr.length; i++) {
            swap(arr, start, i);               // 1. Choose via swap
            backtrackSwap(arr, start + 1, res); // 2. Explore next position
            swap(arr, start, i);               // 3. Un-choose (Restore state)
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println("=== 09_Advanced_DSA: Permutations Verification ===");

        // Test 1: Standard case [1, 2, 3] -> 3! = 6 permutations
        int[] nums1 = {1, 2, 3};
        List<List<Integer>> res1 = permute(nums1);
        boolean pass1 = (res1.size() == 6);
        System.out.println("Test 1: Input " + Arrays.toString(nums1) + " -> Count: " + res1.size() + " (Expected 6) -> " + (pass1 ? "PASS" : "FAIL"));
        System.out.println("        Permutations: " + res1);

        // Test 2: Two elements [0, 1] -> 2! = 2 permutations: [[0, 1], [1, 0]]
        int[] nums2 = {0, 1};
        List<List<Integer>> res2 = permute(nums2);
        boolean pass2 = (res2.size() == 2) && res2.contains(List.of(0, 1)) && res2.contains(List.of(1, 0));
        System.out.println("Test 2: Input " + Arrays.toString(nums2) + " -> " + res2 + " -> " + (pass2 ? "PASS" : "FAIL"));

        // Test 3: Single element [1] -> 1! = 1 permutation: [[1]]
        int[] nums3 = {1};
        List<List<Integer>> res3 = permute(nums3);
        boolean pass3 = (res3.size() == 1) && res3.get(0).equals(List.of(1));
        System.out.println("Test 3: Input " + Arrays.toString(nums3) + " -> " + res3 + " -> " + (pass3 ? "PASS" : "FAIL"));

        // Test 4: Equivalence check between 'used' array and in-place swap approach
        List<List<Integer>> swapRes = permuteSwap(nums1);
        boolean pass4 = (res1.size() == swapRes.size());
        System.out.println("Test 4: Equivalence check (Used-Array: " + res1.size() 
                + ", Swap-Based: " + swapRes.size() + ") -> " + (pass4 ? "PASS" : "FAIL"));
    }
}
