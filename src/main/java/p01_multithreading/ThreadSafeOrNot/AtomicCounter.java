package p01_multithreading.ThreadSafeOrNot;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    private final AtomicInteger count = new AtomicInteger(0);

    public AtomicInteger incrementCount() {
        int k = 10000;
        while (k > 0) {
            count.getAndIncrement();  // atomic operation
            k--;
        }
        return count;
    }

    public AtomicInteger decrementCount() {
        int k = 10000;
        while (k > 0) {
            count.decrementAndGet();
            k--;
        }
        return count;
    }

    public AtomicInteger getCount() {
        return count;
    }
}
