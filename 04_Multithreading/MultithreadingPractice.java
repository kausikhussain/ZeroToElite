package multithreading;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 04_Multithreading Practice Template
 * Complete the classes and methods marked with TODO.
 * Run this class to test your implementations.
 */

// ==========================================
// TASK 1: DELAY PRINTER THREAD
// ==========================================
// TODO: Define DelayThread extending Thread class
// TODO: Define DelayRunnable implementing Runnable interface

// ==========================================
// TASK 2: PRODUCER-CONSUMER BUFFER
// ==========================================
class MessageBuffer {
    private Integer data = null;

    // TODO: Implement synchronized produce(int value) using wait/notify
    public synchronized void produce(int val) {
        
    }

    // TODO: Implement synchronized consume() returning int using wait/notify
    public synchronized int consume() {
        return -1;
    }
}

// ==========================================
// TASK 3: CUSTOM BLOCKING QUEUE
// ==========================================
class CustomBlockingQueue {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity;
    private final ReentrantLock lock = new ReentrantLock();
    // TODO: Define two Conditions: notFull and notEmpty

    public CustomBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    // TODO: Implement put(int val) using lock, notFull condition and signals
    public void put(int val) throws InterruptedException {
        
    }

    // TODO: Implement take() using lock, notEmpty condition and signals
    public int take() throws InterruptedException {
        return -1;
    }
}

public class MultithreadingPractice {
    public static void main(String[] args) {
        System.out.println("=== Running 04_Multithreading Practice ===");

        // Test Task 1: Delay Printing
        System.out.println("Testing Task 1 (Delay Printer):");
        // TODO: Start DelayThread and DelayRunnable and join them.

        // Test Task 2: Producer-Consumer Buffer
        System.out.println("\nTesting Task 2 (Producer-Consumer Buffer):");
        // TODO: Test calling produce and consume concurrently.

        // Test Task 3: Custom Blocking Queue
        System.out.println("\nTesting Task 3 (Custom Blocking Queue):");
        // TODO: Test putting elements into a full queue, verifying that threads block.
    }
}
