package p06_concurrent_locks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

    @Test
    public void testCounterReentrantWithSingleThread() {
        CounterReentrant counterReentrant = new CounterReentrant();

        Thread writeThread = new Thread(() -> {
            int n = 15;
            while(n > 0) {
                counterReentrant.increment(2);
                System.out.println("Write thread adds 2 to the count:  " + counterReentrant.getCount());
                n--;
            }
        });
        writeThread.start();
    }

    @Test
    public void testCounterReentrantWithMultiThreads() throws InterruptedException {
        CounterReentrant counterReentrant = new CounterReentrant();

        for (int i = 0; i < 5; i++) {  // 5 threads
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 3; j++) {  // each thread execute increment(2) 3 times, so totally 15 times
                    counterReentrant.increment(2);
                    System.out.println("Write thread adds 2 to the count:  " + counterReentrant.getCount());
                }
            });
            thread.start();
        }

        Thread.sleep(1000);  // main thread sleep for a sec
        System.out.println("counterReentrant.getCount():  " + counterReentrant.getCount());
    }

    @Test
    public void testWithCounterSynchronizedWithSingleThread() throws InterruptedException {
        CounterSynchronized counterSynchronized = new CounterSynchronized();
        Thread t1 = new Thread(() -> {
            int n = 15;
            while(n > 0) {
                counterSynchronized.increment(3);
                System.out.println("thread adds 3 into count:  " + counterSynchronized.getCount());
                n--;
            }
        });
        t1.start();
    }

    @Test
    public void testWithCounterSynchronizedWithMultiThreads() throws InterruptedException {
        CounterSynchronized counterSynchronized = new CounterSynchronized();

        for(int i = 0; i < 5; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    counterSynchronized.increment(3);
                    System.out.println("t adds 3 into count:  " + counterSynchronized.getCount());
                }
            });
            t.start();
            // t.join();  // main thread waits for current thread to die, so this ensures threads executes in order
        }

        Thread.sleep(1000);
        System.out.println("counterSynchronized.getCount():  " + counterSynchronized.getCount());
    }

    @BeforeEach
    public void beforeEachTest() { System.out.println("--- Test Starts ---"); }
}

class CounterSynchronized {
    private int count;

    public void increment(int n) {
        synchronized (this) {
            count += n;
        }
    }

    public int getCount() {
        return count;
    }
}

class CounterReentrant {
    private final Lock locker = new ReentrantLock();
    private int count;

    public void increment(int n) {
        locker.lock();
        try {
            count += n;
        } finally {
            locker.unlock();
        }
    }

    public int getCount() {
        return count;
    }
}
