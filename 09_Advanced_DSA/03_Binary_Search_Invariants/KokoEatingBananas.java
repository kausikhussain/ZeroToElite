package advanced.binary_search;

import java.util.Arrays;

/**
 * Problem: Koko Eating Bananas (LeetCode 875).
 * Difficulty: Medium.
 * Pattern: Binary Search on Answer (Monotonic Feasibility Predicate).
 * 
 * Problem Statement:
 * Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas.
 * The guards have gone and will come back in 'h' hours.
 * Koko can decide her bananas-per-hour eating speed of 'k'. Each hour, she chooses some pile of bananas
 * and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead
 * and will not eat any more bananas during this hour.
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 * 
 * Approach:
 * 1. Define the search space for speed k:
 *    - Minimum possible speed: 1 (cannot eat 0 bananas/hr).
 *    - Maximum necessary speed: max(piles) (eating faster than max(piles) does not reduce hour count below 1 per pile).
 * 2. Feasibility function canFinish(piles, h, speed):
 *    - For each pile p, hours needed = ceil(p / speed) = (p + speed - 1) / speed.
 *    - Sum hours needed. Returns true if totalHours <= h.
 * 3. Binary Search:
 *    - If speed 'mid' is feasible: mid might be the answer, but a slower speed might also work.
 *      Record ans = mid, high = mid - 1.
 *    - If speed 'mid' is too slow: low = mid + 1.
 * 
 * Key Mathematical Insight:
 * The feasibility function is MONOTONIC:
 *   speed: 1, 2, ..., k_min - 1 (FALSE), k_min, k_min + 1, ... (TRUE)
 * Whenever a function transitions monotonically from False to True across an integer range,
 * binary search can locate the first True value in O(log(range)) steps!
 * 
 * Time Complexity:
 * - O(N * log(max(piles))): Evaluating feasibility takes O(N), binary search takes log(max(piles)) iterations.
 * 
 * Space Complexity:
 * - O(1): Constant auxiliary memory.
 */
public class KokoEatingBananas {

    public static int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = 0;
        for (int p : piles) {
            high = Math.max(high, p);
        }

        int minSpeed = high;

        while (low <= high) {
            int midSpeed = low + (high - low) / 2;

            if (canFinish(piles, h, midSpeed)) {
                minSpeed = midSpeed;
                high = midSpeed - 1; // Try to find a smaller feasible speed
            } else {
                low = midSpeed + 1;  // Speed is too slow, must increase
            }
        }

        return minSpeed;
    }

    private static boolean canFinish(int[] piles, int h, int speed) {
        long totalHours = 0; // Use long to prevent integer overflow with large inputs
        for (int p : piles) {
            // Integer ceiling division: (p + speed - 1) / speed
            totalHours += (p + speed - 1) / speed;
            if (totalHours > h) {
                return false; // Early exit if already exceeds allowed hours
            }
        }
        return totalHours <= h;
    }

    public static void main(String[] args) {
        System.out.println("=== 09_Advanced_DSA: Koko Eating Bananas Verification ===");

        // Test 1: piles = [3,6,7,11], h = 8 -> expected speed = 4
        int[] piles1 = {3, 6, 7, 11};
        int h1 = 8;
        int res1 = minEatingSpeed(piles1, h1);
        boolean pass1 = (res1 == 4);
        System.out.println("Test 1: Piles " + Arrays.toString(piles1) + ", h=8 -> Speed: " + res1 + " -> " + (pass1 ? "PASS" : "FAIL"));

        // Test 2: piles = [30,11,23,4,20], h = 5 -> expected speed = 30
        int[] piles2 = {30, 11, 23, 4, 20};
        int h2 = 5;
        int res2 = minEatingSpeed(piles2, h2);
        boolean pass2 = (res2 == 30);
        System.out.println("Test 2: Piles " + Arrays.toString(piles2) + ", h=5 -> Speed: " + res2 + " -> " + (pass2 ? "PASS" : "FAIL"));

        // Test 3: piles = [30,11,23,4,20], h = 6 -> expected speed = 23
        int h3 = 6;
        int res3 = minEatingSpeed(piles2, h3);
        boolean pass3 = (res3 == 23);
        System.out.println("Test 3: Piles " + Arrays.toString(piles2) + ", h=6 -> Speed: " + res3 + " -> " + (pass3 ? "PASS" : "FAIL"));

        if (pass1 && pass2 && pass3) {
            System.out.println("\nAll Koko Eating Bananas tests PASSED successfully!");
        } else {
            System.err.println("\nOne or more tests FAILED.");
        }
    }
}
