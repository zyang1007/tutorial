package p01_multithreading.ThreadSafeOrNot;

public class SynchronizedCounterWithOneLocker {
    private Integer count = 0;
    final Object locker = new Object();

    public int incrementCount() {
        int k = 10000;
        while (k > 0) {
            synchronized (locker) {
                count++;
            }
            k--;
        }
        return count;
    }

    public int decrementCount() {
        int k = 10000;
        while (k > 0) {
            synchronized (locker) {
                count--;
            }
            k--;
        }
        return count;
    }

    public Integer getCount() {
        return count;
    }
}
