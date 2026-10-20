package advanced.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem: Combination Sum (LeetCode 39).
 * Difficulty: Medium.
 * Pattern: Backtracking / Unbounded Candidate Selection with Target Pruning.
 * 
 * Problem Statement:
 * Given an array of distinct integers 'candidates' and a target integer 'target', return a list
 * of all unique combinations of 'candidates' where the chosen numbers sum to 'target'.
 * The same number may be chosen from 'candidates' an unlimited number of times.
 * Two combinations are unique if the frequency of at least one of the chosen numbers is different.
 * 
 * Approach (Backtracking with Early Pruning):
 * - Sort 'candidates' ascending. This allows immediate loop termination (pruning) when candidates[i] > target.
 * - In backtrack(candidates, target, start, path, res):
 *     - If target == 0: Found a valid combination, add copy of path to res.
 *     - For index i from start to candidates.length - 1:
 *         - If candidates[i] > target: BREAK (all subsequent numbers are >= candidates[i] and will exceed target).
 *         - 1. Choose: path.add(candidates[i]).
 *         - 2. Explore: recurse with (target - candidates[i]) and start index 'i' (allowing unbounded reuse of current number).
 *         - 3. Un-choose (Backtrack): path.remove(path.size() - 1).
 * 
 * Key Invariant:
 * Passing index 'i' (not 'i + 1') permits picking the current candidate multiple times,
 * while passing 'start' ensures we never look back at earlier candidates, strictly avoiding duplicate combinations.
 * 
 * Time Complexity:
 * - O(N^(T/M)): Where T is target, M is the minimal value in candidates, and N is candidate count.
 *   Sorting takes O(N log N).
 * 
 * Space Complexity:
 * - O(T / M): Maximum recursion depth occurs when repeatedly picking the smallest element M.
 */
public class CombinationSum {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0 || target <= 0) {
            return res;
        }

        // Sorting is crucial for early pruning
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private static void backtrack(int[] candidates, int target, int start, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // Early pruning: Since array is sorted, if current candidate exceeds target, all subsequent candidates will too
            if (candidates[i] > target) {
                break;
            }

            path.add(candidates[i]);                             // 1. Choose
            backtrack(candidates, target - candidates[i], i, path, res); // 2. Explore (i allows reuse)
            path.remove(path.size() - 1);                        // 3. Un-choose (Backtrack)
        }
    }

    public static void main(String[] args) {
        System.out.println("=== 09_Advanced_DSA: Combination Sum Verification ===");

        // Test 1: Standard case candidates = [2, 3, 6, 7], target = 7
        int[] c1 = {2, 3, 6, 7};
        int t1 = 7;
        List<List<Integer>> res1 = combinationSum(c1, t1);
        boolean pass1 = res1.size() == 2 
                && res1.contains(List.of(2, 2, 3)) 
                && res1.contains(List.of(7));
        System.out.println("Test 1: candidates " + Arrays.toString(c1) + ", target " + t1 + " -> " + res1 + " -> " + (pass1 ? "PASS" : "FAIL"));

        // Test 2: Multiple combinations candidates = [2, 3, 5], target = 8
        int[] c2 = {2, 3, 5};
        int t2 = 8;
        List<List<Integer>> res2 = combinationSum(c2, t2);
        boolean pass2 = res2.size() == 3
                && res2.contains(List.of(2, 2, 2, 2))
                && res2.contains(List.of(2, 3, 3))
                && res2.contains(List.of(3, 5));
        System.out.println("Test 2: candidates " + Arrays.toString(c2) + ", target " + t2 + " -> " + res2 + " -> " + (pass2 ? "PASS" : "FAIL"));

        // Test 3: No valid combination candidates = [2], target = 1
        int[] c3 = {2};
        int t3 = 1;
        List<List<Integer>> res3 = combinationSum(c3, t3);
        boolean pass3 = res3.isEmpty();
        System.out.println("Test 3: candidates " + Arrays.toString(c3) + ", target " + t3 + " -> " + res3 + " -> " + (pass3 ? "PASS" : "FAIL"));

        // Test 4: Single element repeated candidates = [1], target = 2
        int[] c4 = {1};
        int t4 = 2;
        List<List<Integer>> res4 = combinationSum(c4, t4);
        boolean pass4 = res4.size() == 1 && res4.contains(List.of(1, 1));
        System.out.println("Test 4: candidates " + Arrays.toString(c4) + ", target " + t4 + " -> " + res4 + " -> " + (pass4 ? "PASS" : "FAIL"));
    }
}
