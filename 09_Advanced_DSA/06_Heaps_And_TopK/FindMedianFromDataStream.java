package advanced.heaps;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Problem: Find Median from Data Stream (LeetCode 295).
 * Difficulty: Hard.
 * Pattern: Two-Heaps Partitioning Pattern (Max-Heap & Min-Heap).
 * 
 * Problem Statement:
 * The median is the middle value in an ordered integer list. If the size of the list is even,
 * there is no middle value, and the median is the mean of the two middle values.
 * Implement the MedianFinder class:
 * - MedianFinder() initializes the MedianFinder object.
 * - void addNum(int num) adds the integer num from the data stream to the data structure.
 * - double findMedian() returns the median of all elements so far.
 * 
 * Approach:
 * - Divide the stream into two halves:
 *   1. maxHeap: Stores the smaller half of numbers (largest among them is at top).
 *   2. minHeap: Stores the larger half of numbers (smallest among them is at top).
 * 
 * Key Invariants Maintained:
 * 1. Value Invariant: Every element in maxHeap <= every element in minHeap.
 * 2. Size Invariant:  maxHeap.size() == minHeap.size() OR maxHeap.size() == minHeap.size() + 1.
 * 
 * Algorithm for addNum(num):
 * - Step 1: Offer num to maxHeap.
 * - Step 2: To enforce Value Invariant, poll the largest from maxHeap and offer it to minHeap.
 * - Step 3: To enforce Size Invariant, if minHeap has more elements than maxHeap,
 *           poll the smallest from minHeap and offer it back to maxHeap.
 * 
 * Algorithm for findMedian():
 * - If total elements is odd: median is maxHeap.peek().
 * - If total elements is even: median is (maxHeap.peek() + minHeap.peek()) / 2.0.
 * 
 * Time Complexity:
 * - addNum():     O(log N) - log-time insertions and rebalancing across two heaps.
 * - findMedian(): O(1) - instant constant-time peak retrieval.
 * 
 * Space Complexity:
 * - O(N): Holds all N numbers partitioned between the two heaps.
 */
public class FindMedianFromDataStream {

    public static class MedianFinder {
        // maxHeap stores the lower/smaller half of numbers
        private final PriorityQueue<Integer> maxHeap;
        // minHeap stores the upper/larger half of numbers
        private final PriorityQueue<Integer> minHeap;

        public MedianFinder() {
            this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            this.minHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            maxHeap.offer(num);

            // Maintain Value Invariant: transfer highest of lower half to upper half
            minHeap.offer(maxHeap.poll());

            // Maintain Size Invariant: maxHeap can have at most 1 more element than minHeap
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }

        public double findMedian() {
            if (maxHeap.isEmpty()) {
                throw new IllegalStateException("No numbers in data stream.");
            }

            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            } else {
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== 09_Advanced_DSA: Find Median from Data Stream Verification ===");

        MedianFinder mf = new MedianFinder();

        // Step 1: Add 1 -> Stream: [1] -> Median: 1.0
        mf.addNum(1);
        double med1 = mf.findMedian();
        boolean pass1 = (med1 == 1.0);
        System.out.println("After adding 1 -> Median: " + med1 + " (Expected 1.0) -> " + (pass1 ? "PASS" : "FAIL"));

        // Step 2: Add 2 -> Stream: [1, 2] -> Median: 1.5
        mf.addNum(2);
        double med2 = mf.findMedian();
        boolean pass2 = (med2 == 1.5);
        System.out.println("After adding 2 -> Median: " + med2 + " (Expected 1.5) -> " + (pass2 ? "PASS" : "FAIL"));

        // Step 3: Add 3 -> Stream: [1, 2, 3] -> Median: 2.0
        mf.addNum(3);
        double med3 = mf.findMedian();
        boolean pass3 = (med3 == 2.0);
        System.out.println("After adding 3 -> Median: " + med3 + " (Expected 2.0) -> " + (pass3 ? "PASS" : "FAIL"));

        // Step 4: Add negative numbers and test even length
        mf.addNum(-1); // Stream: [-1, 1, 2, 3] -> Median: (1 + 2) / 2.0 = 1.5
        double med4 = mf.findMedian();
        boolean pass4 = (med4 == 1.5);
        System.out.println("After adding -1 -> Median: " + med4 + " (Expected 1.5) -> " + (pass4 ? "PASS" : "FAIL"));

        mf.addNum(100); // Stream: [-1, 1, 2, 3, 100] -> Median: 2.0
        double med5 = mf.findMedian();
        boolean pass5 = (med5 == 2.0);
        System.out.println("After adding 100 -> Median: " + med5 + " (Expected 2.0) -> " + (pass5 ? "PASS" : "FAIL"));

        if (pass1 && pass2 && pass3 && pass4 && pass5) {
            System.out.println("\nAll Median from Data Stream tests PASSED successfully!");
        } else {
            System.err.println("\nOne or more tests FAILED.");
        }
    }
}
