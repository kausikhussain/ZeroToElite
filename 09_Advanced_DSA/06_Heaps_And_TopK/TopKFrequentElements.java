package advanced.heaps;

import java.util.*;

/**
 * Problem: Top K Frequent Elements (LeetCode 347).
 * Difficulty: Medium.
 * Pattern: Frequency Mapping + Min-Heap Size-K / Bucket Sort.
 * 
 * Problem Statement:
 * Given an integer array nums and an integer k, return the k most frequent elements.
 * You may return the answer in any order.
 * 
 * Approaches:
 * 1. Approach 1 (Frequency Map + Min-Heap of size K):
 *    - Count frequencies in a HashMap: O(N).
 *    - Maintain a Min-Heap of numbers ordered by their frequency:
 *      Comparator: (a, b) -> freqMap.get(a) - freqMap.get(b).
 *    - If heap.size() > k: poll the least frequent element.
 *    - Time: O(N log K), Space: O(N + K).
 * 
 * 2. Approach 2 (Bucket Sort - Linear Time O(N)):
 *    - Count frequencies in a HashMap: O(N).
 *    - Group numbers into buckets where index = frequency: List<Integer>[] buckets.
 *    - Since the maximum possible frequency is N, buckets array has size N + 1.
 *    - Traverse buckets from index N down to 1 until k elements are collected.
 *    - Time: O(N), Space: O(N).
 * 
 * Time Complexity:
 * - O(N) using Bucket Sort, or O(N log K) using Min-Heap.
 * 
 * Space Complexity:
 * - O(N): Auxiliary map and bucket list memory.
 */
public class TopKFrequentElements {

    /**
     * Approach 1: Min-Heap bounded at size K.
     * Time Complexity: O(N log K), Space Complexity: O(N + K).
     */
    public static int[] topKFrequentHeap(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        // Step 1: Count element frequencies
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Min-Heap ordered by frequency (least frequent at root)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(freqMap::get));

        for (int num : freqMap.keySet()) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll(); // Evict least frequent
            }
        }

        // Step 3: Extract top k frequent numbers
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll();
        }
        return result;
    }

    /**
     * Approach 2: Bucket Sort.
     * Time Complexity: O(N) strictly linear, Space Complexity: O(N).
     */
    @SuppressWarnings("unchecked")
    public static int[] topKFrequentBucketSort(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        // Step 1: Frequency map
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Bucket array where index represents frequency count (0 to N)
        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        for (int num : freqMap.keySet()) {
            int freq = freqMap.get(num);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(num);
        }

        // Step 3: Gather k most frequent numbers by iterating from highest frequency down
        int[] result = new int[k];
        int index = 0;

        for (int freq = buckets.length - 1; freq >= 0 && index < k; freq--) {
            if (buckets[freq] != null) {
                for (int num : buckets[freq]) {
                    result[index++] = num;
                    if (index == k) break;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("=== 09_Advanced_DSA: Top K Frequent Elements Verification ===");

        // Test 1: Standard case: [1,1,1,2,2,3], k = 2 -> [1, 2]
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;
        int[] res1 = topKFrequentBucketSort(nums1, k1);
        Arrays.sort(res1);
        boolean pass1 = Arrays.equals(res1, new int[]{1, 2});
        System.out.println("Test 1 (Bucket Sort): " + Arrays.toString(res1) + " -> " + (pass1 ? "PASS" : "FAIL"));

        // Test 2: Min-Heap approach test
        int[] res1Heap = topKFrequentHeap(nums1, k1);
        Arrays.sort(res1Heap);
        boolean pass2 = Arrays.equals(res1Heap, new int[]{1, 2});
        System.out.println("Test 2 (Min-Heap): " + Arrays.toString(res1Heap) + " -> " + (pass2 ? "PASS" : "FAIL"));

        // Test 3: Single element array
        int[] nums2 = {42};
        int k2 = 1;
        int[] res3 = topKFrequentBucketSort(nums2, k2);
        boolean pass3 = (res3.length == 1 && res3[0] == 42);
        System.out.println("Test 3 (Single Element): " + Arrays.toString(res3) + " -> " + (pass3 ? "PASS" : "FAIL"));

        if (pass1 && pass2 && pass3) {
            System.out.println("\nAll Top K Frequent Elements tests PASSED successfully!");
        } else {
            System.err.println("\nOne or more tests FAILED.");
        }
    }
}
