package p06_concurrent_locks;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

// implements atomic operation with compareAndSet(CAS) method by self
public class AtomicOperation {

    /**
     * CAS (compare-and-set or compare-and-swap) is an atomic instruction used in multithreading to
     * achieve synchronization. It compares the contents of a memory location with a given value and, only if
     * they are the same, modifies the contents of that memory location to a new given value.
     *
     */
    public int incrementAndGet(AtomicInteger id) {
        int previous, next;
        do {
            previous = id.get();
            next = previous + 1;
        } while(!id.compareAndSet(previous, next));

        return next;
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicOperation atomicOperation = new AtomicOperation();
        AtomicInteger id = new AtomicInteger(0);

        System.out.println("--- test with self-implemented method ---");
        for (int i = 0; i < 5; i++) {
            int next1 = atomicOperation.incrementAndGet(id);
            System.out.println("next1:  " + next1);
            Thread.sleep(300);
        }


        System.out.println("\n--- test with built-in method ---");
        IdGenerator idGenerator = new IdGenerator();
        for (int i = 0; i < 5; i++) {
            long next2 = idGenerator.getNextId();
            System.out.println("next2:  " + next2);
            Thread.sleep(300);
        }

    }
}

// uses new keyword to create an existing AtomicInteger, AtomicLong, AtomicBoolean, AtomicIntegerArray, etc.
class IdGenerator {
    AtomicLong id = new AtomicLong(0);

    public long getNextId() {
        return id.incrementAndGet();
    }
}


