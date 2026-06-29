package multithreading;

/**
 * 04_Multithreading - Wait and Notify Demo (Inter-Thread Communication)
 * This file resolves the syntax and structural errors of the legacy 'lin_syn.java'.
 * It demonstrates how threads can communicate and coordinate execution stages using
 * Object's wait() and notify() methods inside synchronized contexts.
 */

class CalculatorThread extends Thread {
    int total = 0;

    @Override
    public void run() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " starts calculation...");
            for (int i = 1; i <= 10; i++) {
                total += i;
                try {
                    Thread.sleep(50); // Simulate some work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println(Thread.currentThread().getName() + " calculation complete. Notifying waiting threads...");
            // notify() wakes up a single thread that is waiting on this object's monitor.
            this.notify();
        }
    }
}

public class WaitNotifyDemo {
    public static void main(String[] args) {
        CalculatorThread calculator = new CalculatorThread();
        calculator.setName("Calculator-Worker");
        calculator.start();

        // The main thread needs to wait for the calculator thread to finish its calculation.
        // We synchronize on the calculator object to acquire its monitor before calling wait().
        synchronized (calculator) {
            try {
                System.out.println("Main thread is waiting for " + calculator.getName() + " to finish...");
                // wait() causes the current thread to release the lock and wait until another thread invokes notify() on this object.
                calculator.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Main thread was interrupted.");
            }

            System.out.println("Main thread notified! Calculated Total: " + calculator.total);
        }
    }
}
