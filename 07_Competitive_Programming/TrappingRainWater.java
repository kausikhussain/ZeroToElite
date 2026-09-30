package competitive;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 07_Competitive_Programming - Trapping Rain Water (LeetCode 42)
 *
 * Problem: Given 'n' non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it can trap after raining.
 *
 * This file provides three industry-standard solutions:
 * 1. Two-Pointer Approach (Optimal: O(N) time, O(1) space)
 * 2. Dynamic Programming / Prefix-Suffix Max Arrays (O(N) time, O(N) space)
 * 3. Monotonic Decreasing Stack Approach (O(N) time, O(N) space)
 */
public class TrappingRainWater {

    public static void main(String[] args) {
        System.out.println("=== Trapping Rain Water Demonstrations ===");

        // Test Case 1: Standard elevation map
        int[] height1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Elevation Map 1: " + Arrays.toString(height1));
        System.out.println("Trapped Water (Two Pointer): " + trapTwoPointer(height1)); // Expected: 6
        System.out.println("Trapped Water (DP Prefix/Suffix): " + trapDP(height1));     // Expected: 6
        System.out.println("Trapped Water (Monotonic Stack): " + trapStack(height1));   // Expected: 6

        // Test Case 2: Deep valley elevation
        System.out.println("\nElevation Map 2 (Deep Valley):");
        int[] height2 = {4, 2, 0, 3, 2, 5};
        System.out.println("Input: " + Arrays.toString(height2));
        System.out.println("Trapped Water (Two Pointer): " + trapTwoPointer(height2)); // Expected: 9
        System.out.println("Trapped Water (DP Prefix/Suffix): " + trapDP(height2));     // Expected: 9

        // Test Case 3: Flat or monotonic terrain (no water trapped)
        System.out.println("\nElevation Map 3 (Monotonic / Flat):");
        int[] height3 = {1, 2, 3, 4, 5};
        System.out.println("Input: " + Arrays.toString(height3));
        System.out.println("Trapped Water: " + trapTwoPointer(height3)); // Expected: 0
    }

    /**
     * 1. Two-Pointer Approach (Optimal)
     *
     * Intuition:
     * Water trapped above any bar is bounded by min(maxLeft, maxRight) - height[i].
     * If height[left] < height[right], water trapped at 'left' is determined purely by leftMax,
     * because we already know a taller or equal wall exists somewhere to its right (height[right]).
     *
     * Time Complexity: O(N) - single pass from both ends
     * Space Complexity: O(1) auxiliary space
     */
    public static int trapTwoPointer(int[] height) {
        if (height == null || height.length < 3) return 0;

        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int totalWater = 0;

        while (left < right) {
            if (height[left] <= height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    totalWater += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    totalWater += rightMax - height[right];
                }
                right--;
            }
        }

        return totalWater;
    }

    /**
     * 2. Dynamic Programming (Prefix and Suffix Max)
     *
     * Precomputes maximum height to the left and right for every index.
     * Water trapped at index i = max(0, min(leftMax[i], rightMax[i]) - height[i]).
     *
     * Time Complexity: O(N) - three sequential passes
     * Space Complexity: O(N) auxiliary arrays
     */
    public static int trapDP(int[] height) {
        if (height == null || height.length < 3) return 0;

        int n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        // Fill leftMax array
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        // Fill rightMax array
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        // Accumulate trapped water
        int totalWater = 0;
        for (int i = 0; i < n; i++) {
            totalWater += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return totalWater;
    }

    /**
     * 3. Monotonic Stack Approach
     *
     * Maintains indices of bars in decreasing order of height.
     * When encountering a taller bar, water is computed horizontally row-by-row.
     *
     * Time Complexity: O(N) - each index pushed and popped at most once
     * Space Complexity: O(N) for stack
     */
    public static int trapStack(int[] height) {
        if (height == null || height.length < 3) return 0;

        Deque<Integer> stack = new ArrayDeque<>();
        int totalWater = 0;

        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int bottomIndex = stack.pop();
                if (stack.isEmpty()) break;

                int leftIndex = stack.peek();
                int boundedHeight = Math.min(height[leftIndex], height[i]) - height[bottomIndex];
                int distance = i - leftIndex - 1;

                totalWater += distance * boundedHeight;
            }
            stack.push(i);
        }

        return totalWater;
    }
}
