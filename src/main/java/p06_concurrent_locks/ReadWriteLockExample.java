package p06_concurrent_locks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Pessimistic locking - ReadWriteLock
 * 1. only one thread is allowed to Write and other threads can neither write nor Read;
 * 2. allows multiple thread to Read when no thread is writing;
 *
 * Suitable situation: data is read by a large number of threads and modified by only a few threads.
 */

public class ReadWriteLockExample {

    private static List<Thread> readThreads = new ArrayList<>();
    private static List<Thread> writeThreads = new ArrayList<>();
    private static Counter counter = new Counter();

    public static void createWriteThreads(int numOfThread) {
        for(int i = 1; i <= numOfThread; i++) {
            Thread wThread = new Thread(() -> {
                for (int j = 0; j < counter.size; j++) {
                    counter.increment(j);
                }
            });
            wThread.start();
            writeThreads.add(wThread);
        }
    }

    public static void createReadThreads(int numOfThread) throws InterruptedException {
        for(int i = 1; i <= numOfThread; i++) {
            Thread thread = new Thread(() -> {
                int[] counts = counter.getCounts();
                for (int num: counts) {
                    System.out.println("num:  " + num);
                }
                System.out.println();
            });
            thread.start();
            readThreads.add(thread);
            thread.join();  // main thread waits for this thread to finish its task and die, then creates next rThread
        }
    }

    public static void main(String[] args) throws InterruptedException {
        createReadThreads(3);
        createWriteThreads(2);

        // Thread.sleep(1000);  // give more time to wThread to finish task
        createReadThreads(1);

        Thread.sleep(3000);
//        for (Thread t : readThreads) {
//            t.interrupt();
//        }
        System.out.println("--- End of Main() ---");
    }

}

class Counter {
    protected final int size = 5;
    private final int[] counts = new int[size];
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock rlock = readWriteLock.readLock();
    private final Lock wlock = readWriteLock.writeLock();


    public void increment(int index) {
        wlock.lock();
        try {
            counts[index] += 1;
        } finally {
            wlock.unlock();
        }
    }

    public int[] getCounts() {
        rlock.lock();
        try {
            return Arrays.copyOf(counts, counts.length);
        } finally {
            rlock.unlock();
        }
    }
}
