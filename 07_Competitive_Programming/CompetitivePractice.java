package competitive;

/**
 * 07_Competitive_Programming Practice Template
 * Complete the methods marked with TODO.
 * Run this class to test your implementations.
 */
public class CompetitivePractice {

    public static void main(String[] args) {
        System.out.println("=== Running 07_Competitive_Programming Practice ===");

        // Test Task 1: Contains Duplicate
        System.out.println("Testing Task 1 (Contains Duplicate):");
        int[] arr1 = {1, 2, 3, 1};
        int[] arr2 = {1, 2, 3, 4};
        boolean res1 = containsDuplicate(arr1); // true
        boolean res2 = containsDuplicate(arr2); // false
        if (res1 && !res2) {
            System.out.println("Task 1: PASSED");
        } else {
            System.out.println("Task 1: FAILED");
        }

        // Test Task 2: Longest Substring
        System.out.println("\nTesting Task 2 (Longest Substring):");
        int l1 = lengthOfLongestSubstring("abcabcbb"); // 3
        int l2 = lengthOfLongestSubstring("bbbbb");    // 1
        int l3 = lengthOfLongestSubstring("pwwkew");   // 3
        if (l1 == 3 && l2 == 1 && l3 == 3) {
            System.out.println("Task 2: PASSED");
        } else {
            System.out.println("Task 2: FAILED");
        }

        // Test Task 3: Merge K Sorted Arrays
        System.out.println("\nTesting Task 3 (Merge K Sorted Arrays):");
        int[][] arrays = {
            {1, 4, 5},
            {1, 3, 4},
            {2, 6}
        };
        int[] merged = mergeKSortedArrays(arrays);
        // Expected: [1, 1, 2, 3, 4, 4, 5, 6]
        boolean match = merged != null && merged.length == 8 && merged[2] == 2 && merged[7] == 6;
        if (match) {
            System.out.println("Task 3: PASSED");
        } else {
            System.out.println("Task 3: FAILED");
        }
    }

    /**
     * Task 1: Check if array has duplicates in O(n) time.
     */
    public static boolean containsDuplicate(int[] nums) {
        // TODO: Implement duplicate detection using HashSet.
        return false;
    }

    /**
     * Task 2: Find length of longest substring without repeating characters in O(n).
     */
    public static int lengthOfLongestSubstring(String s) {
        // TODO: Implement sliding window logic.
        return 0;
    }

    /**
     * Task 3: Merge K sorted arrays in O(N log K) time.
     */
    public static int[] mergeKSortedArrays(int[][] arrays) {
        // TODO: Implement using a PriorityQueue (min-heap) holding element nodes.
        return null;
    }
}
