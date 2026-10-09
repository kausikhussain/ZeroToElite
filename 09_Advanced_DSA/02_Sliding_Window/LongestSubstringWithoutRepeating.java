package advanced.sliding_window;

import java.util.Arrays;

/**
 * Problem: Longest Substring Without Repeating Characters (LeetCode 3).
 * Difficulty: Medium.
 * Pattern: Variable-Size Sliding Window with Last-Seen Index Map.
 * 
 * Problem Statement:
 * Given a string s, find the length of the longest substring without duplicate characters.
 * 
 * Approach:
 * - Maintain a window [left, right] of characters without duplicates.
 * - Track the last seen index of each character using an integer array of size 128 (ASCII table).
 * - For each character c at index right:
 *   - If c was previously seen at prevIndex >= left:
 *     A duplicate exists within the active window!
 *     Jump left = prevIndex + 1 (instantly skip past the duplicate character).
 *   - Update last seen index of c: lastSeen[c] = right.
 *   - Update maxLen = Math.max(maxLen, right - left + 1).
 * 
 * Key Insight:
 * Rather than shrinking 'left' one character at a time using a Set (which takes up to 2N iterations),
 * storing the exact last seen index allows 'left' to jump directly past the repeating character in O(1) time.
 * 
 * Time Complexity:
 * - O(N): The right pointer traverses the string exactly once.
 * 
 * Space Complexity:
 * - O(1): Fixed auxiliary table of 128 integers for ASCII characters.
 */
public class LongestSubstringWithoutRepeating {

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        // Stores the last seen index of each ASCII character (initialized to -1)
        int[] lastSeen = new int[128];
        Arrays.fill(lastSeen, -1);

        int maxLen = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // If character was seen within the current window, contract left boundary
            if (lastSeen[c] >= left) {
                left = lastSeen[c] + 1;
            }

            lastSeen[c] = right; // Update last seen index
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println("=== 09_Advanced_DSA: Longest Substring Without Repeating Verification ===");

        // Test 1: Standard case: "abcabcbb" -> 3 ("abc")
        String s1 = "abcabcbb";
        int res1 = lengthOfLongestSubstring(s1);
        boolean pass1 = (res1 == 3);
        System.out.println("Test 1: \"" + s1 + "\" -> Longest: " + res1 + " -> " + (pass1 ? "PASS" : "FAIL"));

        // Test 2: All identical characters: "bbbbb" -> 1 ("b")
        String s2 = "bbbbb";
        int res2 = lengthOfLongestSubstring(s2);
        boolean pass2 = (res2 == 1);
        System.out.println("Test 2: \"" + s2 + "\" -> Longest: " + res2 + " -> " + (pass2 ? "PASS" : "FAIL"));

        // Test 3: Substring in the middle: "pwwkew" -> 3 ("wke")
        String s3 = "pwwkew";
        int res3 = lengthOfLongestSubstring(s3);
        boolean pass3 = (res3 == 3);
        System.out.println("Test 3: \"" + s3 + "\" -> Longest: " + res3 + " -> " + (pass3 ? "PASS" : "FAIL"));

        // Test 4: Empty string
        String s4 = "";
        int res4 = lengthOfLongestSubstring(s4);
        boolean pass4 = (res4 == 0);
        System.out.println("Test 4: \"\" -> Longest: " + res4 + " -> " + (pass4 ? "PASS" : "FAIL"));

        if (pass1 && pass2 && pass3 && pass4) {
            System.out.println("\nAll Longest Substring tests PASSED successfully!");
        } else {
            System.err.println("\nOne or more tests FAILED.");
        }
    }
}
