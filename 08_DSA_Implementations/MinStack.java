package dsa;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EmptyStackException;

/**
 * Problem: Design a stack that supports push, pop, top, and retrieving the minimum element in O(1) time. (LeetCode 155)
 * Concept: Auxiliary State Tracking, Invariant Preservation, Monotonic Property.
 * 
 * Approaches:
 * 1. Approach 1 (Standard & Safe): Two Parallel Stacks
 *    - mainStack: Stores all pushed values.
 *    - minStack:  Stores the minimum element seen up to the corresponding height.
 *    - Invariant: peek() on minStack always reflects the minimum of all active elements in mainStack.
 * 
 * 2. Approach 2 (Node / Linked Representation):
 *    - Each Node holds: int val, int currentMin, Node next.
 *    - Eliminates synchronization between two stacks and stores minimum directly with each state.
 * 
 * 3. Approach 3 (Space-Optimized Value Encoding - Interview Follow-up):
 *    - Uses a single primitive stack and encodes values via diff = 2 * val - min.
 *    - Note: Can overflow with extreme 32-bit integers if using int instead of long.
 * 
 * Time Complexity:
 * - push(val): O(1)
 * - pop():      O(1)
 * - top():      O(1)
 * - getMin():   O(1)
 * 
 * Space Complexity:
 * - O(N) auxiliary space.
 */
public class MinStack {

    // =========================================================================
    // IMPLEMENTATION 1: Two Parallel Stacks (Primary Standard Approach)
    // =========================================================================
    public static class ParallelMinStack {
        private final Deque<Integer> mainStack = new ArrayDeque<>();
        private final Deque<Integer> minStack = new ArrayDeque<>();

        public void push(int val) {
            mainStack.push(val);
            // If minStack is empty, val is the new min.
            // Otherwise, min is min(val, currentMin).
            // Using '<=' allows duplicate minimums to be tracked accurately.
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }

        public void pop() {
            if (mainStack.isEmpty()) {
                throw new EmptyStackException();
            }
            int removed = mainStack.pop();
            // If the popped element is the current minimum, remove it from minStack as well
            if (removed == minStack.peek()) {
                minStack.pop();
            }
        }

        public int top() {
            if (mainStack.isEmpty()) {
                throw new EmptyStackException();
            }
            return mainStack.peek();
        }

        public int getMin() {
            if (minStack.isEmpty()) {
                throw new EmptyStackException();
            }
            return minStack.peek();
        }

        public boolean isEmpty() {
            return mainStack.isEmpty();
        }

        public int size() {
            return mainStack.size();
        }
    }

    // =========================================================================
    // IMPLEMENTATION 2: Linked Node with Embedded State (Elegant OOP Approach)
    // =========================================================================
    public static class NodeMinStack {
        private static class Node {
            final int val;
            final int min;
            final Node next;

            Node(int val, int min, Node next) {
                this.val = val;
                this.min = min;
                this.next = next;
            }
        }

        private Node head = null;

        public void push(int val) {
            if (head == null) {
                head = new Node(val, val, null);
            } else {
                head = new Node(val, Math.min(val, head.min), head);
            }
        }

        public void pop() {
            if (head == null) throw new EmptyStackException();
            head = head.next;
        }

        public int top() {
            if (head == null) throw new EmptyStackException();
            return head.val;
        }

        public int getMin() {
            if (head == null) throw new EmptyStackException();
            return head.min;
        }

        public boolean isEmpty() {
            return head == null;
        }
    }

    /**
     * Comprehensive test driver covering edge cases:
     * - Interleaved pushes and pops
     * - Duplicate minimum values
     * - Negative numbers
     * - Empty stack exceptions
     */
    public static void main(String[] args) {
        System.out.println("=== 08_DSA_Implementations: MinStack Verification ===");

        ParallelMinStack stack = new ParallelMinStack();

        // Step 1: Push sequence: [-2, 0, -3]
        stack.push(-2);
        stack.push(0);
        stack.push(-3);
        System.out.println("Pushed: -2, 0, -3");

        // Step 2: Check getMin -> should be -3
        int min1 = stack.getMin();
        System.out.println("Current Min: " + min1 + " (Expected: -3)");
        boolean pass1 = (min1 == -3);

        // Step 3: Pop top element (-3)
        stack.pop();
        System.out.println("Popped top element (-3)");

        // Step 4: Top should now be 0
        int top1 = stack.top();
        System.out.println("Top: " + top1 + " (Expected: 0)");
        boolean pass2 = (top1 == 0);

        // Step 5: getMin should revert back to -2
        int min2 = stack.getMin();
        System.out.println("Current Min: " + min2 + " (Expected: -2)");
        boolean pass3 = (min2 == -2);

        // Step 6: Test duplicate minimums
        stack.push(-2);
        stack.push(-2);
        boolean pass4 = (stack.getMin() == -2);
        stack.pop();
        boolean pass5 = (stack.getMin() == -2); // Ensure second -2 is still tracked!
        stack.pop();
        boolean pass6 = (stack.getMin() == -2);

        // Step 7: Test Node-based implementation
        NodeMinStack nodeStack = new NodeMinStack();
        nodeStack.push(5);
        nodeStack.push(2);
        nodeStack.push(10);
        nodeStack.push(1);
        boolean pass7 = (nodeStack.getMin() == 1);
        nodeStack.pop(); // pops 1
        boolean pass8 = (nodeStack.getMin() == 2);

        if (pass1 && pass2 && pass3 && pass4 && pass5 && pass6 && pass7 && pass8) {
            System.out.println("\nAll MinStack tests PASSED successfully!");
        } else {
            System.err.println("\nOne or more MinStack tests FAILED.");
        }
    }
}
