package advanced.monotonic_stack;

import java.util.*;

/**
 * Problem: Next Greater Element (LeetCode 496 & 503).
 * Difficulty: Medium.
 * Pattern: Monotonic Decreasing Stack.
 * 
 * Concept:
 * A monotonic stack maintains elements in either monotonically increasing or decreasing order.
 * To find the "Next Greater Element" to the right of each element, maintain a MONOTONIC DECREASING stack:
 * - Elements inside the stack represent pending values waiting for a greater number to appear.
 * - When current number nums[i] > stack.peek():
 *   nums[i] is the NEXT GREATER ELEMENT for all smaller elements on the top of the stack.
 *   Pop them and record their answers.
 * - Push current element (or its index).
 * 
 * Circular Array Variation (LeetCode 503):
 * - Simulate circular traversal by running the loop for 2 * N iterations and indexing using i % N.
 * - Elements remaining on the stack after 2N iterations have no greater element in the circle (default to -1).
 * 
 * Time Complexity:
 * - O(N): Each element is pushed and popped at most once.
 * 
 * Space Complexity:
 * - O(N): Space for stack and result mappings.
 */
public class NextGreaterElement {

    /**
     * Next Greater Element for standard array with circular wrap-around (LeetCode 503).
     */
    public static int[] nextGreaterElementsCircular(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);

        Deque<Integer> stack = new ArrayDeque<>(); // Stores indices of elements

        // Traverse array twice to simulate circular wrap-around
        for (int i = 0; i < 2 * n; i++) {
            int currentNum = nums[i % n];

            // Resolve next greater element for all smaller items currently in stack
            while (!stack.isEmpty() && nums[stack.peek()] < currentNum) {
                int resolvedIndex = stack.pop();
                result[resolvedIndex] = currentNum;
            }

            // Only push indices during the first pass (i < n)
            if (i < n) {
                stack.push(i);
            }
        }

        return result;
    }

    /**
     * Next Greater Element I (LeetCode 496): Find next greater in nums2 for elements of nums1.
     */
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nextGreaterMap = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();

        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peek() < num) {
                nextGreaterMap.put(stack.pop(), num);
            }
            stack.push(num);
        }

        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = nextGreaterMap.getOrDefault(nums1[i], -1);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("=== 09_Advanced_DSA: Next Greater Element Verification ===");

        // Test 1: Standard circular next greater (LeetCode 503)
        // [1, 2, 1] -> [2, -1, 2]
        int[] circ1 = {1, 2, 1};
        int[] res1 = nextGreaterElementsCircular(circ1);
        boolean pass1 = Arrays.equals(res1, new int[]{2, -1, 2});
        System.out.println("Test 1 (Circular [1, 2, 1]): " + Arrays.toString(res1) + " -> " + (pass1 ? "PASS" : "FAIL"));

        // Test 2: Circular array with descending elements: [5, 4, 3, 2, 1] -> [-1, 5, 5, 5, 5]
        int[] circ2 = {5, 4, 3, 2, 1};
        int[] res2 = nextGreaterElementsCircular(circ2);
        boolean pass2 = Arrays.equals(res2, new int[]{-1, 5, 5, 5, 5});
        System.out.println("Test 2 (Circular Descending): " + Arrays.toString(res2) + " -> " + (pass2 ? "PASS" : "FAIL"));

        // Test 3: Subarray mapping (LeetCode 496): nums1 = [4,1,2], nums2 = [1,3,4,2]
        int[] n1 = {4, 1, 2};
        int[] n2 = {1, 3, 4, 2};
        int[] res3 = nextGreaterElement(n1, n2);
        boolean pass3 = Arrays.equals(res3, new int[]{-1, 3, -1});
        System.out.println("Test 3 (Next Greater I): " + Arrays.toString(res3) + " -> " + (pass3 ? "PASS" : "FAIL"));

        if (pass1 && pass2 && pass3) {
            System.out.println("\nAll Next Greater Element tests PASSED successfully!");
        } else {
            System.err.println("\nOne or more tests FAILED.");
        }
    }
}
