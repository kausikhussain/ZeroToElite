package advanced.heaps;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Problem: Kth Largest Element in an Array (LeetCode 215).
 * Difficulty: Medium.
 * Pattern: Min-Heap Size-K Invariant & QuickSelect In-Place Partitioning.
 * 
 * Problem Statement:
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * 
 * Approaches Implemented:
 * 1. Approach 1 (Min-Heap of Size K):
 *    - Maintain a min-heap bounded at size 'k'.
 *    - For each element in nums:
 *      * Push into heap.
 *      * If heap.size() > k, poll the smallest element out.
 *    - After processing all N elements, the root of the min-heap contains the kth largest element!
 *    - Time: O(N log K), Space: O(K).
 * 
 * 2. Approach 2 (QuickSelect - Average O(N)):
 *    - Based on QuickSort partitioning.
 *    - If pivot settles at index targetIndex = (nums.length - k):
 *      Found the answer!
 *    - If pivot < targetIndex: search right partition.
 *    - If pivot > targetIndex: search left partition.
 *    - Time: Average O(N), Worst O(N^2), Space: O(1).
 * 
 * Key Insight:
 * To find the K LARGEST elements, maintain a MIN-HEAP of size K.
 * The min-heap continually evicts the smallest among the top candidates, guaranteeing
 * that only the K largest survive at the end, with the Kth largest residing at the top.
 */
public class KthLargestElement {

    private static final Random RNG = new Random();

    /**
     * Approach 1: Min-Heap of bounded size K.
     * Time Complexity: O(N log K), Space Complexity: O(K).
     */
    public static int findKthLargestHeap(int[] nums, int k) {
        if (nums == null || nums.length < k || k <= 0) {
            throw new IllegalArgumentException("Invalid input or k value.");
        }

        // Java PriorityQueue is a Min-Heap by default
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);

        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll(); // Evict smallest element, preserving only top k largest
            }
        }

        return minHeap.peek();
    }

    /**
     * Approach 2: QuickSelect (Hoare Selection Algorithm).
     * Time Complexity: Average O(N), Worst O(N^2), Space Complexity: O(1).
     */
    public static int findKthLargestQuickSelect(int[] nums, int k) {
        if (nums == null || nums.length < k || k <= 0) {
            throw new IllegalArgumentException("Invalid input or k value.");
        }

        int targetIndex = nums.length - k; // In sorted order, kth largest is at index (n - k)
        return quickSelect(nums, 0, nums.length - 1, targetIndex);
    }

    private static int quickSelect(int[] nums, int low, int high, int targetIndex) {
        if (low == high) {
            return nums[low];
        }

        int pivotIndex = randomizedPartition(nums, low, high);

        if (pivotIndex == targetIndex) {
            return nums[pivotIndex];
        } else if (pivotIndex < targetIndex) {
            return quickSelect(nums, pivotIndex + 1, high, targetIndex);
        } else {
            return quickSelect(nums, low, pivotIndex - 1, targetIndex);
        }
    }

    private static int randomizedPartition(int[] nums, int low, int high) {
        int randomPivot = low + RNG.nextInt(high - low + 1);
        swap(nums, randomPivot, high);

        int pivot = nums[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (nums[j] <= pivot) {
                i++;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println("=== 09_Advanced_DSA: Kth Largest Element Verification ===");

        // Test 1: Standard case: [3,2,1,5,6,4], k = 2 -> expected 5
        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int k1 = 2;
        int resHeap1 = findKthLargestHeap(nums1, k1);
        int resQS1 = findKthLargestQuickSelect(nums1.clone(), k1);
        boolean pass1 = (resHeap1 == 5 && resQS1 == 5);
        System.out.println("Test 1: Input " + Arrays.toString(nums1) + ", k=" + k1 + " -> Heap: " + resHeap1 + ", QS: " + resQS1 + " -> " + (pass1 ? "PASS" : "FAIL"));

        // Test 2: Array with duplicates: [3,2,3,1,2,4,5,5,6], k = 4 -> expected 4
        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k2 = 4;
        int resHeap2 = findKthLargestHeap(nums2, k2);
        int resQS2 = findKthLargestQuickSelect(nums2.clone(), k2);
        boolean pass2 = (resHeap2 == 4 && resQS2 == 4);
        System.out.println("Test 2: Input " + Arrays.toString(nums2) + ", k=" + k2 + " -> Heap: " + resHeap2 + ", QS: " + resQS2 + " -> " + (pass2 ? "PASS" : "FAIL"));

        // Test 3: k = 1 (Maximum element)
        int[] nums3 = {7, 10, 4, 3, 20, 15};
        int k3 = 1;
        int resHeap3 = findKthLargestHeap(nums3, k3);
        int resQS3 = findKthLargestQuickSelect(nums3.clone(), k3);
        boolean pass3 = (resHeap3 == 20 && resQS3 == 20);
        System.out.println("Test 3: k=1 Maximum -> Heap: " + resHeap3 + ", QS: " + resQS3 + " -> " + (pass3 ? "PASS" : "FAIL"));

        if (pass1 && pass2 && pass3) {
            System.out.println("\nAll Kth Largest Element tests PASSED successfully!");
        } else {
            System.err.println("\nOne or more tests FAILED.");
        }
    }
}
