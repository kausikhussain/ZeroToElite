package competitive.solutions;

import java.util.*;

/**
 * 07_Competitive_Programming Practice Solutions
 * Complete reference implementation for competitive coding exercises.
 */
public class CompetitiveSolutions {

    public static void main(String[] args) {
        System.out.println("=== Running 07_Competitive_Programming Solutions ===");

        // Task 1
        int[] arr1 = {1, 2, 3, 1};
        System.out.println("Contains Duplicate: " + containsDuplicate(arr1));

        // Task 2
        System.out.println("Longest Substring length ('abcabcbb'): " + lengthOfLongestSubstring("abcabcbb"));

        // Task 3
        int[][] arrays = {
            {1, 4, 5},
            {1, 3, 4},
            {2, 6}
        };
        int[] merged = mergeKSortedArrays(arrays);
        System.out.println("Merged K Sorted Arrays: " + Arrays.toString(merged));

        // Task 4
        int[] subArr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Maximum Subarray Sum: " + maxSubArray(subArr)); // 6

        // Task 5
        int[] elevations = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Trapped Rain Water: " + trapRainWater(elevations)); // 6
    }

    /**
     * Time Complexity: O(n), Space Complexity: O(n)
     */
    public static boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return true; // Duplicate found
            }
        }
        return false;
    }

    /**
     * Sliding Window approach:
     * - Expand window by moving 'right' pointer.
     * - If duplicate character, contract window by shifting 'left' pointer.
     * Time Complexity: O(n), Space Complexity: O(min(m, n)) where m is size of alphabet.
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) return 0;
        int maxLen = 0;
        int left = 0;
        Set<Character> chars = new HashSet<>();

        for (int right = 0; right < s.length(); right++) {
            char currChar = s.charAt(right);
            // Shrink window from the left until the duplicate character is removed
            while (chars.contains(currChar)) {
                chars.remove(s.charAt(left));
                left++;
            }
            chars.add(currChar);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    // Node class to track array positions inside PriorityQueue
    private static class QueueNode implements Comparable<QueueNode> {
        int value;
        int arrayIndex;
        int elementIndex;

        public QueueNode(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }

        @Override
        public int compareTo(QueueNode other) {
            return Integer.compare(this.value, other.value); // Min-heap comparison
        }
    }

    /**
     * Merges K sorted arrays using a Min-Heap.
     * Time Complexity: O(N log K) where N is total elements, K is number of arrays.
     * Space Complexity: O(K) for PriorityQueue.
     */
    public static int[] mergeKSortedArrays(int[][] arrays) {
        if (arrays == null || arrays.length == 0) return new int[0];

        PriorityQueue<QueueNode> minHeap = new PriorityQueue<>();
        int totalElements = 0;

        // Initialize minHeap with the first element of each non-empty array
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] != null && arrays[i].length > 0) {
                minHeap.offer(new QueueNode(arrays[i][0], i, 0));
                totalElements += arrays[i].length;
            }
        }

        int[] result = new int[totalElements];
        int index = 0;

        while (!minHeap.isEmpty()) {
            QueueNode curr = minHeap.poll();
            result[index++] = curr.value;

            // If the array from which this node came has more elements, push the next one
            int nextElemIndex = curr.elementIndex + 1;
            if (nextElemIndex < arrays[curr.arrayIndex].length) {
                minHeap.offer(new QueueNode(arrays[curr.arrayIndex][nextElemIndex], curr.arrayIndex, nextElemIndex));
            }
        }

        return result;
    }

    /**
     * Task 4: Kadane's Algorithm for Maximum Subarray Sum.
     * Time Complexity: O(N), Space Complexity: O(1)
     */
    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int currentSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    /**
     * Task 5: Trapping Rain Water using Two-Pointer Approach.
     * Time Complexity: O(N), Space Complexity: O(1)
     */
    public static int trapRainWater(int[] height) {
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
}
