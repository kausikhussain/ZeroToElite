package dsa;

import java.util.*;

/**
 * 08_DSA_Implementations Practice Template
 * Complete the implementations marked with TODO.
 * Run this class to test your implementations.
 */
public class DSAPractice {

    // ==========================================
    // TASK 1: Static Array Stack
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
            // TODO: Implement push. Print overflow message if full.
        }

        public int pop() {
            // TODO: Implement pop. Return -1 if empty.
            return -1;
        }

        public int peek() {
            // TODO: Implement peek. Return -1 if empty.
            return -1;
        }

        public boolean isEmpty() {
            // TODO: Implement isEmpty.
            return true;
        }

        public boolean isFull() {
            // TODO: Implement isFull.
            return false;
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

    public static Node reverseLinkedList(Node head) {
        // TODO: Reverse the linked list in-place. Return the new head.
        return head;
    }

    // ==========================================
    // TASK 3: Comparable/Comparator for Student Sorting
    // ==========================================
    public static class Student {
        public String name;
        public double cgpa;
        public int age;

        public Student(String name, double cgpa, int age) {
            this.name = name;
            this.cgpa = cgpa;
            this.age = age;
        }

        @Override
        public String toString() {
            return name + " (" + cgpa + ", " + age + ")";
        }
    }

    public static void sortStudents(List<Student> students) {
        // TODO: Sort the students list.
        // Primarily by cgpa descending, secondarily by age ascending.
    }

    // ==========================================
    // Test Driver
    // ==========================================
    public static void main(String[] args) {
        System.out.println("=== Running 08_DSA_Implementations Practice ===");

        // Test Task 1: Static Array Stack
        System.out.println("Testing Task 1 (Array Stack):");
        ArrayStack stack = new ArrayStack(3);
        stack.push(10);
        stack.push(20);
        stack.push(30);
        
        boolean test1 = stack.isFull();
        boolean test2 = (stack.peek() == 30);
        boolean test3 = (stack.pop() == 30);
        boolean test4 = (stack.pop() == 20);
        boolean test5 = !stack.isEmpty();
        boolean test6 = (stack.pop() == 10);
        boolean test7 = stack.isEmpty();

        // Check if all actions matched TODO skeletons
        // (For practice template, we expect these might initially fail until solved)
        System.out.println("Template values initialized. Complete your implementations!");

        // Test Task 2: Linked List Reversal
        System.out.println("\nTesting Task 2 (Linked List Reversal):");
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);

        Node reversedHead = reverseLinkedList(head);
        
        // Test Task 3: Student Sorting
        System.out.println("\nTesting Task 3 (Student Sorting):");
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", 3.8, 22));
        students.add(new Student("Bob", 3.9, 21));
        students.add(new Student("Charlie", 3.8, 20));

        sortStudents(students);
    }
}
