package dsa;

/**
 * 08_DSA_Implementations - Circular Linked List
 * Demonstrates a circular singly linked list where the tail node links back
 * to the head node, preventing null terminations.
 */
public class CircularLinkedList {

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;

    /**
     * Inserts a new node at the end of the circular linked list.
     * Time Complexity: O(n)
     */
    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        // Case 1: Empty list
        if (head == null) {
            head = newNode;
            newNode.next = head; // Point to itself
            System.out.println("Inserted " + data + " as initial circular node.");
            return;
        }

        // Case 2: Non-empty list
        Node temp = head;
        // Traverse until we reach the node pointing back to head (the current tail)
        while (temp.next != head) {
            temp = temp.next;
        }
        temp.next = newNode;
        newNode.next = head; // Link back to head to maintain circular property
        System.out.println("Inserted " + data + " at circular end.");
    }

    /**
     * Displays all circular nodes in the list once.
     * Time Complexity: O(n)
     */
    public void display() {
        if (head == null) {
            System.out.println("Circular List is empty.");
            return;
        }
        Node temp = head;
        System.out.print("Circular List: ");
        do {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        } while (temp != head);
        System.out.println("(back to head: " + head.data + ")");
    }

    public static void main(String[] args) {
        CircularLinkedList cll = new CircularLinkedList();
        cll.insertAtEnd(10);
        cll.insertAtEnd(20);
        cll.insertAtEnd(30);
        cll.insertAtEnd(40);
        cll.display();
    }
}
