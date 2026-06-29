package dsa;

/**
 * 08_DSA_Implementations - Simple Queue & Circular Queue (Array-based)
 * This file illustrates how to construct queues using standard arrays. It covers:
 * 1. Simple Queue (First-In-First-Out, with front/rear trackers)
 * 2. Circular Queue (Overcoming space issues of simple queues by wrapping around using modulo arithmetic)
 */
public class Queues {

    // ==========================================
    // 1. SIMPLE QUEUE IMPLEMENTATION
    // ==========================================
    public static class SimpleQueue {
        private final int[] arr;
        private final int capacity;
        private int front;
        private int rear;

        public SimpleQueue(int size) {
            this.capacity = size;
            this.arr = new int[capacity];
            this.front = 0;
            this.rear = 0;
        }

        public boolean isFull() {
            return rear == capacity;
        }

        public boolean isEmpty() {
            return front == rear;
        }

        public void enqueue(int data) {
            if (isFull()) {
                System.out.println("SimpleQueue: Overflow! Cannot enqueue " + data);
                return;
            }
            arr[rear++] = data;
            System.out.println("SimpleQueue: Enqueued " + data);
        }

        public int dequeue() {
            if (isEmpty()) {
                System.out.println("SimpleQueue: Underflow! Cannot dequeue.");
                return -1;
            }
            int val = arr[front++];
            System.out.println("SimpleQueue: Dequeued " + val);
            return val;
        }

        public void display() {
            if (isEmpty()) {
                System.out.println("SimpleQueue is empty.");
                return;
            }
            System.out.print("SimpleQueue elements: ");
            for (int i = front; i < rear; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    // ==========================================
    // 2. CIRCULAR QUEUE IMPLEMENTATION
    // ==========================================
    public static class CircularQueue {
        private final int[] arr;
        private final int capacity;
        private int front;
        private int rear;

        public CircularQueue(int size) {
            this.capacity = size;
            this.arr = new int[capacity];
            this.front = -1;
            this.rear = -1;
        }

        public boolean isFull() {
            return (rear + 1) % capacity == front;
        }

        public boolean isEmpty() {
            return front == -1;
        }

        public void enqueue(int data) {
            if (isFull()) {
                System.out.println("CircularQueue: Overflow! Cannot enqueue " + data);
                return;
            }
            if (isEmpty()) {
                front = 0;
            }
            rear = (rear + 1) % capacity; // Wrap around using modulo
            arr[rear] = data;
            System.out.println("CircularQueue: Enqueued " + data);
        }

        public int dequeue() {
            if (isEmpty()) {
                System.out.println("CircularQueue: Underflow! Cannot dequeue.");
                return -1;
            }
            int val = arr[front];
            if (front == rear) {
                // Queue becomes empty after this dequeue
                front = -1;
                rear = -1;
            } else {
                front = (front + 1) % capacity; // Wrap around front pointer
            }
            System.out.println("CircularQueue: Dequeued " + val);
            return val;
        }

        public void display() {
            if (isEmpty()) {
                System.out.println("CircularQueue is empty.");
                return;
            }
            System.out.print("CircularQueue elements: ");
            int i = front;
            while (true) {
                System.out.print(arr[i] + " ");
                if (i == rear) break;
                i = (i + 1) % capacity;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== 1. Simple Queue Demo ===");
        SimpleQueue sq = new SimpleQueue(3);
        sq.enqueue(10);
        sq.enqueue(20);
        sq.enqueue(30);
        sq.enqueue(40); // Overflow
        sq.display();
        sq.dequeue();
        sq.display();

        System.out.println("\n=== 2. Circular Queue Demo ===");
        CircularQueue cq = new CircularQueue(3);
        cq.enqueue(10);
        cq.enqueue(20);
        cq.enqueue(30);
        cq.enqueue(40); // Overflow
        cq.display();
        cq.dequeue();
        // Since it's circular, we can now enqueue another element (space is recycled!)
        cq.enqueue(40); 
        cq.display();
    }
}
