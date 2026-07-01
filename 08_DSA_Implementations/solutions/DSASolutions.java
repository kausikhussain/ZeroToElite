package dsa.solutions;

import java.util.*;

/**
 * 08_DSA_Implementations Practice Solutions
 * Complete reference implementation for DSA exercises.
 */
public class DSASolutions {

    // ==========================================
    // TASK 1: Static Array Stack Solution
    // ==========================================
    public static class ArrayStack {
        private int[] arr;
        private int top;
        private int capacity;

        public ArrayStack(int capacity) {
            this.capacity = capacity;
            this.arr = new int[capacity];
            this.top = -1;
        }

        public void push(int val) {
            if (isFull()) {
                System.out.println("Stack Overflow. Cannot push " + val);
                return;
            }
            arr[++top] = val;
        }

        public int pop() {
            if (isEmpty()) {
                System.out.println("Stack Underflow. Cannot pop.");
                return -1;
            }
            return arr[top--];
        }

        public int peek() {
            if (isEmpty()) {
                return -1;
            }
            return arr[top];
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public boolean isFull() {
            return top == capacity - 1;
        }
    }

    // ==========================================
    // TASK 2: Singly Linked List Node and Reversal
    // ==========================================
    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Reverses a singly linked list in-place.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static Node reverseLinkedList(Node head) {
        Node prev = null;
        Node current = head;
        Node next = null;

        while (current != null) {
            next = current.next; // Store next node
            current.next = prev; // Reverse the link
            prev = current;      // Move pointers one step forward
            current = next;
        }
        return prev; // New head of reversed list
    }

    // ==========================================
    // TASK 3: Student Sorting Solution
    // ==========================================
    public static class Student implements Comparable<Student> {
        public String name;
        public double cgpa;
        public int age;

        public Student(String name, double cgpa, int age) {
            this.name = name;
            this.cgpa = cgpa;
            this.age = age;
        }

        /**
         * Sort primarily by cgpa descending, secondarily by age ascending.
         * Using Comparable.
         */
        @Override
        public int compareTo(Student other) {
            // Primarily by cgpa descending
            if (Double.compare(other.cgpa, this.cgpa) != 0) {
                return Double.compare(other.cgpa, this.cgpa);
            }
            // Secondarily by age ascending
            return Integer.compare(this.age, other.age);
        }

        @Override
        public String toString() {
            return name + " (" + cgpa + ", " + age + ")";
        }
    }

    public static void sortStudents(List<Student> students) {
        Collections.sort(students);
    }

    // ==========================================
    // Test Driver
    // ==========================================
    public static void main(String[] args) {
        System.out.println("=== Running 08_DSA_Implementations Solutions ===");

        // Test Task 1: Static Array Stack
        System.out.println("Testing Task 1 (Array Stack):");
        ArrayStack stack = new ArrayStack(3);
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40); // should print overflow

        boolean test1 = stack.isFull();
        boolean test2 = (stack.peek() == 30);
        boolean test3 = (stack.pop() == 30);
        boolean test4 = (stack.pop() == 20);
        boolean test5 = !stack.isEmpty();
        boolean test6 = (stack.pop() == 10);
        boolean test7 = stack.isEmpty();
        
        if (test1 && test2 && test3 && test4 && test5 && test6 && test7) {
            System.out.println("Task 1: PASSED");
        } else {
            System.out.println("Task 1: FAILED");
        }

        // Test Task 2: Linked List Reversal
        System.out.println("\nTesting Task 2 (Linked List Reversal):");
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);

        Node reversed = reverseLinkedList(head);
        
        boolean link1 = (reversed != null && reversed.data == 3);
        boolean link2 = (reversed != null && reversed.next != null && reversed.next.data == 2);
        boolean link3 = (reversed != null && reversed.next.next != null && reversed.next.next.data == 1);
        boolean link4 = (reversed != null && reversed.next.next.next == null);

        if (link1 && link2 && link3 && link4) {
            System.out.println("Task 2: PASSED");
        } else {
            System.out.println("Task 2: FAILED");
        }

        // Test Task 3: Student Sorting
        System.out.println("\nTesting Task 3 (Student Sorting):");
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", 3.8, 22));
        students.add(new Student("Bob", 3.9, 21));
        students.add(new Student("Charlie", 3.8, 20));

        sortStudents(students);

        boolean sort1 = students.get(0).name.equals("Bob"); // highest cgpa (3.9)
        boolean sort2 = students.get(1).name.equals("Charlie"); // same cgpa (3.8), younger age (20)
        boolean sort3 = students.get(2).name.equals("Alice"); // same cgpa (3.8), older age (22)

        if (sort1 && sort2 && sort3) {
            System.out.println("Task 3: PASSED");
        } else {
            System.out.println("Task 3: FAILED");
            System.out.println("Actual output order: " + students);
        }
    }
}
