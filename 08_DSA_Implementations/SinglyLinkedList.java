package dsa;

/**
 * 08_DSA_Implementations - Singly Linked List
 * Demonstrates node definition, pointer traversal, insertions (front, middle, end),
 * deletions, and display mechanisms.
 */
public class SinglyLinkedList {

    // Nested private Node class for encapsulation
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
     * Inserts a new node at the front of the list.
     * Time Complexity: O(1)
     */
    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        System.out.println("Inserted " + data + " at beginning.");
    }

    /**
     * Inserts a new node at the end of the list.
     * Time Complexity: O(n)
     */
    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            System.out.println("List was empty. Inserted " + data + " as head.");
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
        System.out.println("Inserted " + data + " at end.");
    }

    /**
     * Inserts a node after a specific index (1-based position).
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

        Node newNode = new Node(data);
        Node temp = head;
        for (int i = 1; i < position - 1 && temp != null; i++) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Position out of bounds. Cannot insert " + data);
            return;
        }

        newNode.next = temp.next;
        temp.next = newNode;
        System.out.println("Inserted " + data + " at position " + position + ".");
    }

    /**
     * Deletes the first node containing the specified value.
     * Time Complexity: O(n)
     */
    public void deleteValue(int value) {
        if (head == null) {
            System.out.println("List is empty. Cannot delete " + value);
            return;
        }

        // Case 1: Head node contains the value
        if (head.data == value) {
            head = head.next;
            System.out.println("Deleted head node containing value " + value + ".");
            return;
        }

        // Case 2: Value is in a subsequent node
        Node current = head;
        Node prev = null;
        while (current != null && current.data != value) {
            prev = current;
            current = current.next;
        }

        if (current == null) {
            System.out.println("Value " + value + " not found in the list.");
            return;
        }

        prev.next = current.next;
        System.out.println("Deleted node containing value " + value + ".");
    }

    /**
     * Displays all elements in the list.
     * Time Complexity: O(n)
     */
    public void display() {
        if (head == null) {
            System.out.println("Empty List.");
            return;
        }
        Node temp = head;
        System.out.print("Singly List: ");
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtBeginning(5);
        list.insertAtPosition(3, 15); // Insert 15 at position 3 (between 10 and 20)
        list.display();

        list.deleteValue(10);
        list.display();

        list.deleteValue(5); // delete head
        list.display();
    }
}
