package advanced.monotonic_stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Problem: Largest Rectangle in Histogram (LeetCode 84).
 * Difficulty: Hard.
 * Pattern: Monotonic Increasing Stack (Boundary Finding).
 * 
 * Problem Statement:
 * Given an array of integers heights representing the histogram's bar height where the width of
 * each bar is 1, return the area of the largest rectangle in the histogram.
 * 
 * Approach:
 * - A rectangle of height h = heights[i] can extend:
 *   - Leftward until the first bar strictly shorter than heights[i] (left boundary).
 *   - Rightward until the first bar strictly shorter than heights[i] (right boundary).
 * - A MONOTONIC INCREASING stack of indices tracks left boundaries naturally:
 *   - While current bar heights[i] < heights[stack.peek()]:
 *     The bar at stack.peek() has reached its right smaller boundary (which is index 'i')!
 *     Pop heightIndex = stack.pop().
 *     Height of rectangle = heights[heightIndex].
 *     Left boundary = stack.isEmpty() ? -1 : stack.peek().
 *     Width of rectangle = i - leftBoundary - 1.
 *     Area = height * width.
 *     Update maxArea = max(maxArea, Area).
 *   - Push current index 'i'.
 * - By extending the loop up to index n (with virtual height 0 at index n), all remaining
 *   bars in the stack are cleanly resolved without duplicate cleanup loops.
 * 
 * Key Mathematical Insight:
 * Brute force considers every pair of bars in O(N^2).
 * The monotonic stack computes the maximal rectangle for EACH bar as the bottleneck height
 * in a single linear pass because the left and right smaller bounds are discovered simultaneously!
 * 
 * Time Complexity:
 * - O(N): Each bar index is pushed and popped exactly once.
 * 
 * Space Complexity:
 * - O(N): Stack stores at most N indices.
 */
public class LargestRectangleInHistogram {

    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int n = heights.length;
        int maxArea = 0;
        Deque<Integer> stack = new ArrayDeque<>(); // Monotonic increasing stack of indices

        // Loop runs from 0 to n; at i = n, virtual height 0 forces remaining elements to pop
        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];

            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int poppedIndex = stack.pop();
                int height = heights[poppedIndex];

                // If stack is empty, popped bar was smaller than all elements to its left (width = i)
                int leftBound = stack.isEmpty() ? -1 : stack.peek();
                int width = i - leftBound - 1;

                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println("=== 09_Advanced_DSA: Largest Rectangle in Histogram Verification ===");

        // Test 1: Standard histogram: [2, 1, 5, 6, 2, 3] -> expected max area = 10 (bars [5, 6] with height 5)
        int[] h1 = {2, 1, 5, 6, 2, 3};
        int res1 = largestRectangleArea(h1);
        boolean pass1 = (res1 == 10);
        System.out.println("Test 1: " + Arrays.toString(h1) + " -> Max Area: " + res1 + " -> " + (pass1 ? "PASS" : "FAIL"));

        // Test 2: Uniform heights: [2, 4] -> expected 4
        int[] h2 = {2, 4};
        int res2 = largestRectangleArea(h2);
        boolean pass2 = (res2 == 4);
        System.out.println("Test 2: [2, 4] -> Max Area: " + res2 + " -> " + (pass2 ? "PASS" : "FAIL"));

        // Test 3: Monotonically increasing heights: [1, 2, 3, 4, 5] -> expected 9 (3 * 3 = 9)
        int[] h3 = {1, 2, 3, 4, 5};
        int res3 = largestRectangleArea(h3);
        boolean pass3 = (res3 == 9);
        System.out.println("Test 3: [1, 2, 3, 4, 5] -> Max Area: " + res3 + " -> " + (pass3 ? "PASS" : "FAIL"));

        // Test 4: Single bar: [7] -> expected 7
        int[] h4 = {7};
        int res4 = largestRectangleArea(h4);
        boolean pass4 = (res4 == 7);
        System.out.println("Test 4: [7] -> Max Area: " + res4 + " -> " + (pass4 ? "PASS" : "FAIL"));

        if (pass1 && pass2 && pass3 && pass4) {
            System.out.println("\nAll Largest Rectangle in Histogram tests PASSED successfully!");
        } else {
            System.err.println("\nOne or more tests FAILED.");
        }
    }
}
