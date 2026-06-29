package multithreading;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 04_Multithreading - ReentrantReadWriteLock Demo
 * This file ports and fixes the legacy 'x.java' file.
 * It demonstrates the ReentrantReadWriteLock, which allows multiple reader threads
 * to read shared data concurrently while restricting write access exclusively to a single writer thread.
 */

class SharedResource {
    private int data = 0; // Shared state
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    // Read method - uses Read Lock (shared)
    public void readData() {
        lock.readLock().lock(); // Acquire read lock
        try {
            System.out.println(Thread.currentThread().getName() + " reads: " + data);
            // Simulate reading delay
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.readLock().unlock(); // Release read lock
        }
    }

    // Write method - uses Write Lock (exclusive)
    public void writeData(int value) {
        lock.writeLock().lock(); // Acquire write lock (exclusive)
        try {
            System.out.println(Thread.currentThread().getName() + " WRITES: " + value);
            data = value;
            // Simulate writing delay
            Thread.sleep(80);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.writeLock().unlock(); // Release write lock
        }
    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        // readerTask will continuously read data
        Runnable readerTask = () -> {
            for (int i = 0; i < 3; i++) {
                resource.readData();
            }
        };

        // writerTask will continuously write data
        Runnable writerTask = () -> {
            for (int i = 1; i <= 3; i++) {
                resource.writeData(i);
            }
        };

        // Create reader and writer threads
        Thread reader1 = new Thread(readerTask, "Reader-1");
        Thread reader2 = new Thread(readerTask, "Reader-2");
        Thread writer = new Thread(writerTask, "Writer-Worker");

        // Start threads
        reader1.start();
        reader2.start();
        writer.start();

        // Wait for execution completion
        try {
            reader1.join();
            reader2.join();
            writer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
