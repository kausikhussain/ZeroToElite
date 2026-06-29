package competitive;

import java.util.Arrays;

/**
 * 07_Competitive_Programming - Common Array Problems (TCS NQT Prep)
 * This file covers three standard array interview questions with optimal solutions:
 * 1. Find the Largest Element in an Array (Linear scan)
 * 2. Reverse an Array In-Place (Two-pointer swap technique)
 * 3. Maximum Product of a Triplet (Single-pass scan for min/max values)
 */
public class ArrayProblems {

    public static void main(String[] args) {
        System.out.println("=== 1. Find the Largest Element ===");
        int[] arr1 = {3, 5, 7, 2, 8};
        System.out.println("Input Array: " + Arrays.toString(arr1));
        System.out.println("Max Element: " + findMax(arr1));

        System.out.println("\n=== 2. Reverse an Array In-Place ===");
        int[] arr2 = {1, 2, 3, 4, 5};
        System.out.println("Original: " + Arrays.toString(arr2));
        reverseInPlace(arr2);
        System.out.println("Reversed: " + Arrays.toString(arr2));

        System.out.println("\n=== 3. Maximum Product of a Triplet ===");
        // Test case showing negative values (as two large negative numbers multiply to a positive)
        int[] arr3 = {-10, -10, 5, 2, 3, 0}; 
        System.out.println("Input Array: " + Arrays.toString(arr3));
        System.out.println("Max Triplet Product: " + maxTripletProduct(arr3)); // Should be (-10 * -10 * 5) = 500
    }

    /**
     * Finds the maximum element in a single pass.
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static int findMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    /**
     * Reverses the array elements in-place using two pointers.
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static void reverseInPlace(int[] arr) {
        if (arr == null) return;
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            // Swap elements
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * Computes maximum product of a triplet in single pass.
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static long maxTripletProduct(int[] arr) {
        if (arr.length < 3) {
            throw new IllegalArgumentException("Array must contain at least 3 elements.");
        }

        // We need the three largest values and the two smallest values
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;

        for (int val : arr) {
            // Track 3 maximums
            if (val > max1) {
                max3 = max2;
                max2 = max1;
                max1 = val;
            } else if (val > max2) {
                max3 = max2;
                max2 = val;
            } else if (val > max3) {
                max3 = val;
            }

            // Track 2 minimums
            if (val < min1) {
                min2 = min1;
                min1 = val;
            } else if (val < min2) {
                min2 = val;
            }
        }

        // Either (largest * second largest * third largest) or (smallest * second smallest * largest)
        long product1 = (long) max1 * max2 * max3;
        long product2 = (long) min1 * min2 * max1;

        return Math.max(product1, product2);
    }
}
