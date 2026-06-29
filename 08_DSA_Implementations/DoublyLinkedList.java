package dsa;

/**
 * 08_DSA_Implementations - Doubly Linked List
 * Demonstrates node definition with prev/next pointers, insertions,
 * and bi-directional traversal (forward and backward).
 */
public class DoublyLinkedList {

    // Nested Node class for Doubly Linked List
    private static class DNode {
        int data;
        DNode prev;
        DNode next;

        DNode(int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    private DNode head;

    /**
     * Inserts at the beginning of the doubly linked list.
     * Time Complexity: O(1)
     */
    public void insertAtBeginning(int data) {
        DNode newNode = new DNode(data);
        if (head == null) {
            head = newNode;
            System.out.println("Inserted " + data + " as head.");
            return;
        }
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
        System.out.println("Inserted " + data + " at beginning.");
    }

    /**
     * Inserts at the end of the doubly linked list.
     * Time Complexity: O(n)
     */
    public void insertAtEnd(int data) {
        DNode newNode = new DNode(data);
        if (head == null) {
            head = newNode;
            System.out.println("Inserted " + data + " as head.");
            return;
        }
        DNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
        newNode.prev = temp;
        System.out.println("Inserted " + data + " at end.");
    }

    /**
     * Inserts a node after a specific position (1-based index).
     * Time Complexity: O(n)
     */
    public void insertAtPosition(int position, int data) {
        if (position < 1) {
            System.out.println("Position must be >= 1.");
            return;
        }
        if (position == 1) {
            insertAtBeginning(data);
            return;
        }

        DNode newNode = new DNode(data);
        DNode temp = head;
        for (int i = 1; i < position - 1 && temp != null; i++) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Position out of bounds. Cannot insert " + data);
            return;
        }

        // Adjust pointers for newNode, temp, and temp.next
        newNode.next = temp.next;
        newNode.prev = temp;
        if (temp.next != null) {
            temp.next.prev = newNode;
        }
        temp.next = newNode;
        System.out.println("Inserted " + data + " at position " + position + ".");
    }

    /**
     * Displays list from head to tail.
     * Time Complexity: O(n)
     */
    public void displayForward() {
        if (head == null) {
            System.out.println("Empty List.");
            return;
        }
        DNode temp = head;
        System.out.print("Forward List: ");
        while (temp != null) {
            System.out.print(temp.data + " <-> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    /**
     * Displays list in reverse (tail to head).
     * Time Complexity: O(n)
     */
    public void displayBackward() {
        if (head == null) {
            System.out.println("Empty List.");
            return;
        }
        DNode temp = head;
        // Move to the last node
        while (temp.next != null) {
            temp = temp.next;
        }
        // Traverse backwards
        System.out.print("Backward List: ");
        while (temp != null) {
            System.out.print(temp.data + " <-> ");
            temp = temp.prev;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList();
        dll.insertAtEnd(10);
        dll.insertAtEnd(20);
        dll.insertAtBeginning(5);
        dll.insertAtPosition(3, 15); // Insert 15 at position 3
        dll.displayForward();
        dll.displayBackward();
    }
}
