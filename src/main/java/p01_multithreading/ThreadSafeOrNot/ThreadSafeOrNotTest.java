package p01_multithreading.ThreadSafeOrNot;

import org.junit.jupiter.api.Test;

/**
 * Thread Safe: implementation is guaranteed to be free of race condition when accessed by
 * multiple thread simultaneously.
 *
 * Conditionally Safe: different threads can access different objects simultaneously, and
 * access to shared data is protected from race conditions.
 *
 * Not Thread Safe: data structures should not be accessed simultaneously by different threads.
 */
public class ThreadSafeOrNotTest {

    @Test
    public void testGlobalCountOfCounter() throws InterruptedException {  // not safe
        int n = 5;
        while (n > 0) {
            new Thread(() -> Counter.setGlobalCount(1)).start();
            new Thread(() -> Counter.setGlobalCount(2)).start();
            new Thread(() -> Counter.setGlobalCount(3)).start();

            System.out.println(Counter.getGlobalCount());
            n--;
        }
    }

    @Test
    public void testCountOfCounter() throws InterruptedException {
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":  " + counter.incrementCount());
        });

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":  " + counter.decrementCount());
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("final count = " + counter.getCount());  // keep changing - not safe
    }

    @Test
    public void testSynchronizedCounterWithOneLocker() throws InterruptedException {
        SynchronizedCounterWithOneLocker counter = new SynchronizedCounterWithOneLocker();

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":  " + counter.incrementCount());
        });

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":  " + counter.decrementCount());
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("final count = " + counter.getCount());  // =0 -> safe
    }

    @Test
    public void testSynchronizedCounterWithTwoLockers() throws InterruptedException {
        SynchronizedCounterWithTwoLockers counter = new SynchronizedCounterWithTwoLockers();

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":  " + counter.incrementCount());
        });

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":  " + counter.decrementCount());
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("final count = " + counter.getCount());  // !=0, keep changing -> not safe
    }

    @Test
    public void testWithSynchronizedMethodCounter() throws InterruptedException {
        SynchronizedMethodCounter counter = new SynchronizedMethodCounter();

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":  " + counter.incrementCount());
        });

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":  " + counter.decrementCount());
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("final count = " + counter.getCount());  // =0, -> safe
    }

    @Test
    public void testWithAtomicCounter() throws InterruptedException {
        AtomicCounter counter = new AtomicCounter();

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":  " + counter.incrementCount());
        });

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":  " + counter.decrementCount());
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("final count = " + counter.getCount());  // =0, but race condition?
    }
}
