# 04_Multithreading - Practice Exercises

This folder contains exercises to practice multi-threaded programming, runnable tasks, synchronization, thread coordination, and lock utilities in Java.

## 📝 Exercises

### 1. Delay Printer Thread (Easy)
- **Problem**: Create a thread that prints integers `1` to `5` with a 500ms sleep delay between each value.
- **Goal**: Implement this task twice:
  1. By subclassing `Thread` (class `DelayThread`).
  2. By implementing the `Runnable` interface (class `DelayRunnable`).
- **Run**: Start both threads and verify they print in an interleaved manner.

### 2. Simple Producer-Consumer Coordination (Medium)
- **Problem**: Implement thread coordination using `wait()` and `notify()`:
  - Create a class `Buffer` that holds a single integer resource (like a message slot).
  - Implement a synchronized `produce(int value)` method that blocks (waits) if the slot is occupied, writes a value, and notifies the consumer.
  - Implement a synchronized `consume()` method that blocks if the slot is empty, reads the value, and notifies the producer.

### 3. Thread-Safe Custom Blocking Queue (Hard/Challenge)
- **Problem**: Construct a custom thread-safe `BlockingQueue` using explicit locks:
  - Utilize `java.util.concurrent.locks.ReentrantLock` and its companion `Condition`.
  - Create a queue of a fixed capacity (e.g. `size = 3`).
  - Implement `put(int val)` that blocks (awaits on a `notFull` condition) if the queue is full, inserts an element, and signals `notEmpty`.
  - Implement `take()` that blocks (awaits on a `notEmpty` condition) if the queue is empty, extracts an element, and signals `notFull`.

---

## 🏃 How to Practice
1. Open [MultithreadingPractice.java](file:///c:/Users/kausi/Desktop/java/04_Multithreading/MultithreadingPractice.java).
2. Complete the classes and methods marked with `TODO`.
3. Run the file to verify if the test drivers execute:
   ```powershell
   javac 04_Multithreading/MultithreadingPractice.java
   java multithreading.MultithreadingPractice
   ```
4. If stuck, check the solution in [solutions/MultithreadingSolutions.java](file:///c:/Users/kausi/Desktop/java/04_Multithreading/solutions/MultithreadingSolutions.java).
