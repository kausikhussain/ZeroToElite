package advanced.monotonic_stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Problem: Daily Temperatures (LeetCode 739).
 * Difficulty: Medium.
 * Pattern: Monotonic Decreasing Stack with Index Distance Tracking.
 * 
 * Problem Statement:
 * Given an array of integers temperatures represents the daily temperatures, return an array answer
 * such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.
 * If there is no future day for which this is possible, keep answer[i] == 0 instead.
 * 
 * Approach:
 * - Maintain a stack of INDICES in monotonically decreasing temperature order.
 * - When current temperature temperatures[i] > temperatures[stack.peek()]:
 *   We have found the first warmer day for the index at the top of the stack!
 *   Pop prevDay = stack.pop(), and record wait days: answer[prevDay] = i - prevDay.
 *   Continue popping while current temperature is warmer than stack top.
 * - Push current day's index 'i' onto the stack.
 * 
 * Key Insight:
 * By storing INDICES instead of raw temperature values, the stack retains both:
 * 1. The temperature value (via temperatures[idx])
 * 2. The spatial position to compute distance (i - prevDay).
 * 
 * Time Complexity:
 * - O(N): Each day is pushed and popped at most once.
 * 
 * Space Complexity:
 * - O(N): Stack stores at most N indices in the worst case (monotonically decreasing temperatures).
 */
public class DailyTemperatures {

    public static int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) {
            return new int[0];
        }

        int n = temperatures.length;
        int[] answer = new int[n];
        Deque<Integer> stack = new ArrayDeque<>(); // Monotonic decreasing stack of indices

        for (int i = 0; i < n; i++) {
            // While current temperature is strictly warmer than the temperature at top of stack
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevDay = stack.pop();
                answer[prevDay] = i - prevDay; // Days waited
            }
            stack.push(i);
        }

        // Remaining elements in stack have no warmer day ahead (already 0 by default)
        return answer;
    }

    public static void main(String[] args) {
        System.out.println("=== 09_Advanced_DSA: Daily Temperatures Verification ===");

        // Test 1: Standard case
        int[] temps1 = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] res1 = dailyTemperatures(temps1);
        boolean pass1 = Arrays.equals(res1, new int[]{1, 1, 4, 2, 1, 1, 0, 0});
        System.out.println("Test 1: " + Arrays.toString(temps1) + " -> " + Arrays.toString(res1) + " -> " + (pass1 ? "PASS" : "FAIL"));

        // Test 2: Strictly ascending
        int[] temps2 = {30, 40, 50, 60};
        int[] res2 = dailyTemperatures(temps2);
        boolean pass2 = Arrays.equals(res2, new int[]{1, 1, 1, 0});
        System.out.println("Test 2: Strictly Ascending -> " + Arrays.toString(res2) + " -> " + (pass2 ? "PASS" : "FAIL"));

        // Test 3: Strictly descending (no warmer days)
        int[] temps3 = {30, 60, 90};
        int[] res3 = dailyTemperatures(temps3);
        boolean pass3 = Arrays.equals(res3, new int[]{1, 1, 0});
        System.out.println("Test 3: " + Arrays.toString(temps3) + " -> " + Arrays.toString(res3) + " -> " + (pass3 ? "PASS" : "FAIL"));

        if (pass1 && pass2 && pass3) {
            System.out.println("\nAll Daily Temperatures tests PASSED successfully!");
        } else {
            System.err.println("\nOne or more tests FAILED.");
        }
    }
}
