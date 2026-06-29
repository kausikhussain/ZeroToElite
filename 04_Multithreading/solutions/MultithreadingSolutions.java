package multithreading.solutions;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 04_Multithreading Practice Solutions
 * Complete reference implementation for multithreading exercises.
 */

// ==========================================
// TASK 1: DELAY PRINTER THREAD
// ==========================================
class DelayThread extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " prints: " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class DelayRunnable implements Runnable {
    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " prints: " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// ==========================================
// TASK 2: PRODUCER-CONSUMER BUFFER
// ==========================================
class MessageBuffer {
    private Integer data = null;

    public synchronized void produce(int val) {
        while (data != null) {
            try {
                wait(); // Wait until data is consumed
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        data = val;
        System.out.println("Produced message: " + val);
        notify(); // Notify consumer
    }

    public synchronized int consume() {
        while (data == null) {
            try {
                wait(); // Wait until data is produced
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        int val = data;
        data = null;
        System.out.println("Consumed message: " + val);
        notify(); // Notify producer
        return val;
    }
}

// ==========================================
// TASK 3: CUSTOM BLOCKING QUEUE
// ==========================================
class CustomBlockingQueue {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public CustomBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void put(int val) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                System.out.println(Thread.currentThread().getName() + " waiting on Full Queue...");
                notFull.await(); // Wait until space is available
            }
            queue.offer(val);
            System.out.println(Thread.currentThread().getName() + " put: " + val + ". Queue: " + queue);
            notEmpty.signal(); // Signal waiting consumers
        } finally {
            lock.unlock();
        }
    }

    public int take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + " waiting on Empty Queue...");
                notEmpty.await(); // Wait until element is available
            }
            int val = queue.poll();
            System.out.println(Thread.currentThread().getName() + " took: " + val + ". Queue: " + queue);
            notFull.signal(); // Signal waiting producers
            return val;
        } finally {
            lock.unlock();
        }
    }
}

public class MultithreadingSolutions {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Running 04_Multithreading Solutions ===");

        // Test Task 1
        System.out.println("--- Task 1: Delay Printing ---");
        Thread t1 = new DelayThread();
        t1.setName("Thread-Subclass");
        Thread t2 = new Thread(new DelayRunnable(), "Runnable-Interface");
        
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();

        // Test Task 2
        System.out.println("\n--- Task 2: Inter-Thread Buffer ---");
        MessageBuffer buffer = new MessageBuffer();
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                buffer.produce(i * 10);
            }
        });
        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                buffer.consume();
            }
        });
        
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();

        // Test Task 3
        System.out.println("\n--- Task 3: Lock-Condition Blocking Queue ---");
        CustomBlockingQueue bq = new CustomBlockingQueue(2); // Capacity = 2

        Thread writer = new Thread(() -> {
            try {
                bq.put(1);
                bq.put(2);
                bq.put(3); // Blocks until reader pulls
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "WriterThread");

        Thread reader = new Thread(() -> {
            try {
                Thread.sleep(1000); // Wait for writer to fill queue
                bq.take();
                bq.take();
                bq.take();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "ReaderThread");

        writer.start();
        reader.start();
        writer.join();
        reader.join();
    }
}
