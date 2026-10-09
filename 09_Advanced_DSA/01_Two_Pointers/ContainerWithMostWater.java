package advanced.two_pointers;

import java.util.Arrays;

/**
 * Problem: Container With Most Water (LeetCode 11).
 * Difficulty: Medium.
 * Pattern: Greedy Two Pointers from Outermost Boundaries.
 * 
 * Problem Statement:
 * You are given an integer array height of length n. There are n vertical lines drawn such that
 * the two endpoints of the ith line are (i, 0) and (i, height[i]).
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 * Return the maximum amount of water a container can store.
 * 
 * Approach:
 * - Place two pointers at the extreme ends: left = 0, right = height.length - 1.
 * - Current area is determined by: width * min(height[left], height[right]), where width = right - left.
 * - Track maxArea = max(maxArea, currentArea).
 * - Greedy Choice:
 *   If height[left] < height[right], advance left++ (move shorter line).
 *   Else, decrement right-- (move shorter line).
 * 
 * Key Mathematical Insight:
 * The area is constrained by the SHORTER of the two boundaries.
 * If we move the taller line inward, the width decreases, but the maximum possible height is STILL
 * bounded by the shorter line, which guarantees the area can only decrease or stay smaller.
 * The ONLY possibility of finding a larger area is to discard the shorter line in hopes of finding a taller boundary!
 * 
 * Time Complexity:
 * - O(N): Single pass inward until pointers meet.
 * 
 * Space Complexity:
 * - O(1): Only constant auxiliary variables.
 */
public class ContainerWithMostWater {

    public static int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }

        int left = 0;
        int right = height.length - 1;
        int maxWater = 0;

        while (left < right) {
            int width = right - left;
            int currentHeight = Math.min(height[left], height[right]);
            int currentArea = width * currentHeight;

            maxWater = Math.max(maxWater, currentArea);

            // Always discard the shorter boundary
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxWater;
    }

    public static void main(String[] args) {
        System.out.println("=== 09_Advanced_DSA: Container With Most Water Verification ===");

        // Test 1: Standard case
        int[] h1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int res1 = maxArea(h1);
        boolean pass1 = (res1 == 49); // min(8, 7) * 7 = 49
        System.out.println("Test 1: Input " + Arrays.toString(h1) + " -> Max Water: " + res1 + " -> " + (pass1 ? "PASS" : "FAIL"));

        // Test 2: Two equal elements
        int[] h2 = {1, 1};
        int res2 = maxArea(h2);
        boolean pass2 = (res2 == 1);
        System.out.println("Test 2: Input [1, 1] -> Max Water: " + res2 + " -> " + (pass2 ? "PASS" : "FAIL"));

        // Test 3: Descending heights
        int[] h3 = {4, 3, 2, 1, 4};
        int res3 = maxArea(h3);
        boolean pass3 = (res3 == 16); // min(4, 4) * 4 = 16
        System.out.println("Test 3: Input " + Arrays.toString(h3) + " -> Max Water: " + res3 + " -> " + (pass3 ? "PASS" : "FAIL"));

        if (pass1 && pass2 && pass3) {
            System.out.println("\nAll Container With Most Water tests PASSED successfully!");
        } else {
            System.err.println("\nOne or more Container With Most Water tests FAILED.");
        }
    }
}
