package advanced.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem: Subsets (LeetCode 78).
 * Difficulty: Medium.
 * Pattern: Backtracking / Power Set Generation (Include / Exclude Decision Tree).
 * 
 * Problem Statement:
 * Given an integer array 'nums' of unique elements, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 * 
 * Approach 1 (Classic Backtracking):
 * - At every step, the current path represents a valid subset, so add a copy of 'path' to 'res'.
 * - Iterate from 'start' index to 'nums.length - 1':
 *     1. Choose: add nums[i] to path.
 *     2. Explore: recurse with backtrack(i + 1, path).
 *     3. Un-choose (Backtrack): remove the last element from path to restore state.
 * 
 * Approach 2 (Iterative Cascading):
 * - Start with an initial empty subset: [[]].
 * - For each number x in nums:
 *     Take all existing subsets in the output, clone them, add x to the clone, and append to output.
 * 
 * Key Invariant:
 * Passing 'start' as 'i + 1' prevents re-evaluating preceding elements, eliminating duplicates
 * and maintaining subset order without sorting.
 * 
 * Time Complexity:
 * - O(N * 2^N): There are 2^N total subsets, and each subset requires up to O(N) time to copy into the result list.
 * 
 * Space Complexity:
 * - O(N): Maximum recursion stack depth is N. The path list uses O(N) space.
 */
public class Subsets {

    /**
     * Approach 1: Classic Backtracking.
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) {
            return res;
        }
        backtrack(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private static void backtrack(int[] nums, int start, List<Integer> path, List<List<Integer>> res) {
        // Every node in the decision tree represents a valid subset
        res.add(new ArrayList<>(path));

        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);                         // 1. Choose
            backtrack(nums, i + 1, path, res);        // 2. Explore
            path.remove(path.size() - 1);             // 3. Un-choose (Backtrack)
        }
    }

    /**
     * Approach 2: Iterative Cascading.
     */
    public static List<List<Integer>> subsetsCascading(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>()); // Base empty set

        if (nums == null) {
            return res;
        }

        for (int num : nums) {
            int n = res.size();
            for (int i = 0; i < n; i++) {
                List<Integer> subset = new ArrayList<>(res.get(i));
                subset.add(num);
                res.add(subset);
            }
        }

        return res;
    }

    /**
     * Approach 3: Bitmask Representation.
     * Each integer from 0 to (2^N - 1) corresponds to a unique bitmask.
     */
    public static List<List<Integer>> subsetsBitmask(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) {
            return res;
        }

        int total = 1 << nums.length; // 2^N
        for (int mask = 0; mask < total; mask++) {
            List<Integer> subset = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if ((mask & (1 << i)) != 0) {
                    subset.add(nums[i]);
                }
            }
            res.add(subset);
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println("=== 09_Advanced_DSA: Subsets (Power Set) Verification ===");

        // Test 1: Standard case [1, 2, 3] -> 2^3 = 8 subsets
        int[] nums1 = {1, 2, 3};
        List<List<Integer>> res1 = subsets(nums1);
        boolean pass1 = (res1.size() == 8);
        System.out.println("Test 1: Input " + Arrays.toString(nums1) + " -> Count: " + res1.size() + " (Expected 8) -> " + (pass1 ? "PASS" : "FAIL"));
        System.out.println("        Subsets: " + res1);

        // Test 2: Single element [0] -> 2^1 = 2 subsets: [[], [0]]
        int[] nums2 = {0};
        List<List<Integer>> res2 = subsets(nums2);
        boolean pass2 = (res2.size() == 2) && res2.contains(List.of()) && res2.contains(List.of(0));
        System.out.println("Test 2: Input " + Arrays.toString(nums2) + " -> " + res2 + " -> " + (pass2 ? "PASS" : "FAIL"));

        // Test 3: Empty input [] -> 1 subset [[]]
        int[] nums3 = {};
        List<List<Integer>> res3 = subsets(nums3);
        boolean pass3 = (res3.size() == 1 && res3.get(0).isEmpty());
        System.out.println("Test 3: Input " + Arrays.toString(nums3) + " -> " + res3 + " -> " + (pass3 ? "PASS" : "FAIL"));

        // Test 4: Algorithmic equivalence check (Backtracking vs Cascading vs Bitmask)
        List<List<Integer>> cascadingRes = subsetsCascading(nums1);
        List<List<Integer>> bitmaskRes = subsetsBitmask(nums1);
        boolean pass4 = (res1.size() == cascadingRes.size()) && (res1.size() == bitmaskRes.size());
        System.out.println("Test 4: Equivalence check (Backtracking: " + res1.size() 
                + ", Cascading: " + cascadingRes.size() 
                + ", Bitmask: " + bitmaskRes.size() + ") -> " + (pass4 ? "PASS" : "FAIL"));
    }
}
