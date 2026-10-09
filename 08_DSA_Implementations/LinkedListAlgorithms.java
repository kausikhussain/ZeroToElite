package dsa;

/**
 * Problem: Essential Linked List pointer algorithms and interview patterns.
 * Concept: Fast & Slow Pointers (Floyd's Tortoise and Hare), Dummy Node Technique, In-Place Relinking.
 * 
 * Algorithms Implemented:
 * 1. Find Middle Node: Fast pointer moves 2x speed, Slow moves 1x speed.
 * 2. Cycle Detection (hasCycle): Determines if a cycle exists in O(1) space.
 * 3. Cycle Start Node (detectCycleStart): Finds the exact node where cycle begins (Floyd's Phase 2).
 * 4. Merge Two Sorted Lists: Merges two sorted lists in-place in O(1) auxiliary space using a dummy node.
 * 5. Remove N-th Node From End: Single-pass removal using two pointers spaced N steps apart.
 * 
 * Key Mathematical Insight (Floyd's Cycle Start Proof):
 * Let L1 = distance from head to cycle start.
 * Let L2 = distance from cycle start to meeting point.
 * Let C  = length of the cycle.
 * Total distance slow traveled: d(slow) = L1 + L2
 * Total distance fast traveled: d(fast) = L1 + L2 + n * C  (for some integer n >= 1)
 * Since fast moves at 2x speed:
 *   2 * (L1 + L2) = L1 + L2 + n * C
 *   L1 + L2 = n * C
 *   L1 = n * C - L2
 * Therefore, advancing one pointer from the head and one from the meeting point at 1x speed
 * causes them to collide exactly at the cycle start after traveling distance L1!
 * 
 * Time Complexity:
 * - O(N) for all algorithms.
 * 
 * Space Complexity:
 * - O(1) auxiliary space for all algorithms (in-place pointer manipulation).
 */
public class LinkedListAlgorithms {

    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 1. Finds the middle node of a singly linked list.
     * If the list has an even number of nodes, returns the second middle node.
     * Time Complexity: O(N), Space Complexity: O(1)
     */
    public static ListNode findMiddle(ListNode head) {
        if (head == null) return null;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 2. Detects if a cycle exists in the linked list using Floyd's Tortoise and Hare.
     * Time Complexity: O(N), Space Complexity: O(1)
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true; // Cycle detected
            }
        }
        return false; // Reached end of list -> No cycle
    }

    /**
     * 3. Finds the exact node where the cycle begins (LeetCode 142).
     * Returns null if no cycle exists.
     * Time Complexity: O(N), Space Complexity: O(1)
     */
    public static ListNode detectCycleStart(ListNode head) {
        if (head == null || head.next == null) return null;

        ListNode slow = head;
        ListNode fast = head;
        boolean cycleExists = false;

        // Phase 1: Determine if cycle exists and find meeting point
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                cycleExists = true;
                break;
            }
        }

        if (!cycleExists) return null;

        // Phase 2: Find cycle start node
        // Move one pointer to head, keep other at meeting point, advance both by 1 step
        ListNode ptr1 = head;
        ListNode ptr2 = slow;

        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        return ptr1; // Intersection point is the start of the cycle
    }

    /**
     * 4. Merges two sorted linked lists into one sorted list in-place (LeetCode 21).
     * Time Complexity: O(N + M), Space Complexity: O(1)
     */
    public static ListNode mergeTwoSortedLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1); // Simplifies edge case handling at head
        ListNode current = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        // Attach any remaining nodes
        current.next = (l1 != null) ? l1 : l2;

        return dummy.next;
    }

    /**
     * 5. Removes the N-th node from the end of the list in a single pass (LeetCode 19).
     * Time Complexity: O(N), Space Complexity: O(1)
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0) return head;

        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy;
        ListNode slow = dummy;

        // Move fast ahead by n + 1 steps to create a gap of n nodes between fast and slow
        for (int i = 0; i <= n; i++) {
            if (fast == null) return head; // n is larger than list size
            fast = fast.next;
        }

        // Move fast to the end, maintaining the gap
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // slow is now positioned right before the node to be removed
        if (slow.next != null) {
            slow.next = slow.next.next;
        }

        return dummy.next;
    }

    // Helper: Convert array to linked list
    public static ListNode createList(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        for (int v : arr) {
            curr.next = new ListNode(v);
            curr = curr.next;
        }
        return dummy.next;
    }

    // Helper: Convert list to string for display
    public static String toListString(ListNode head) {
        StringBuilder sb = new StringBuilder();
        ListNode curr = head;
        int count = 0;
        while (curr != null && count < 20) { // Limit to 20 nodes to prevent infinite loop on cycles
            sb.append(curr.val);
            if (curr.next != null) sb.append(" -> ");
            curr = curr.next;
            count++;
        }
        return sb.toString();
    }

    /**
     * Comprehensive test driver covering normal and edge cases.
     */
    public static void main(String[] args) {
        System.out.println("=== 08_DSA_Implementations: LinkedList Algorithms Verification ===");

        // Test 1: Find middle of odd-length list: [1, 2, 3, 4, 5] -> middle is 3
        ListNode oddList = createList(new int[]{1, 2, 3, 4, 5});
        ListNode mid1 = findMiddle(oddList);
        boolean pass1 = (mid1 != null && mid1.val == 3);
        System.out.println("Test 1 (Middle of Odd List): Expected 3, Got " + (mid1 != null ? mid1.val : null) + " -> " + (pass1 ? "PASS" : "FAIL"));

        // Test 2: Find middle of even-length list: [1, 2, 3, 4] -> second middle is 3
        ListNode evenList = createList(new int[]{1, 2, 3, 4});
        ListNode mid2 = findMiddle(evenList);
        boolean pass2 = (mid2 != null && mid2.val == 3);
        System.out.println("Test 2 (Middle of Even List): Expected 3, Got " + (mid2 != null ? mid2.val : null) + " -> " + (pass2 ? "PASS" : "FAIL"));

        // Test 3: Cycle Detection & Cycle Start Node: [3 -> 2 -> 0 -> -4 -> points back to 2]
        ListNode cHead = new ListNode(3);
        ListNode c2 = new ListNode(2);
        ListNode c0 = new ListNode(0);
        ListNode cNeg4 = new ListNode(-4);
        cHead.next = c2;
        c2.next = c0;
        c0.next = cNeg4;
        cNeg4.next = c2; // Cycle back to node with value 2

        boolean cycleDetected = hasCycle(cHead);
        ListNode cycleStart = detectCycleStart(cHead);
        boolean pass3 = (cycleDetected && cycleStart == c2);
        System.out.println("Test 3 (Cycle Detection & Start): Detected=" + cycleDetected + ", StartVal=" + (cycleStart != null ? cycleStart.val : null) + " -> " + (pass3 ? "PASS" : "FAIL"));

        // Test 4: Merge Two Sorted Lists: [1, 2, 4] and [1, 3, 4] -> [1, 1, 2, 3, 4, 4]
        ListNode l1 = createList(new int[]{1, 2, 4});
        ListNode l2 = createList(new int[]{1, 3, 4});
        ListNode merged = mergeTwoSortedLists(l1, l2);
        String mergedStr = toListString(merged);
        boolean pass4 = mergedStr.equals("1 -> 1 -> 2 -> 3 -> 4 -> 4");
        System.out.println("Test 4 (Merge Sorted Lists): " + mergedStr + " -> " + (pass4 ? "PASS" : "FAIL"));

        // Test 5: Remove N-th node from end: [1, 2, 3, 4, 5], remove 2nd from end (4) -> [1, 2, 3, 5]
        ListNode listToTrim = createList(new int[]{1, 2, 3, 4, 5});
        ListNode trimmed = removeNthFromEnd(listToTrim, 2);
        String trimmedStr = toListString(trimmed);
        boolean pass5 = trimmedStr.equals("1 -> 2 -> 3 -> 5");
        System.out.println("Test 5 (Remove 2nd from End): " + trimmedStr + " -> " + (pass5 ? "PASS" : "FAIL"));

        // Test 6: Remove head (1st node in 1-element list)
        ListNode singleList = createList(new int[]{42});
        ListNode trimmedSingle = removeNthFromEnd(singleList, 1);
        boolean pass6 = (trimmedSingle == null);
        System.out.println("Test 6 (Remove Head from Single): " + (pass6 ? "PASS" : "FAIL"));

        if (pass1 && pass2 && pass3 && pass4 && pass5 && pass6) {
            System.out.println("\nAll LinkedList Algorithm tests PASSED successfully!");
        } else {
            System.err.println("\nOne or more LinkedList tests FAILED.");
        }
    }
}
