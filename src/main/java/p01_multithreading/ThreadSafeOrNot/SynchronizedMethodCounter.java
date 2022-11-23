package p01_multithreading.ThreadSafeOrNot;

public class SynchronizedMethodCounter {
    private Integer count = 0;

    public synchronized int incrementCount() {
        int k = 10000;
        while (k > 0) {
            count++;
            k--;
        }
        return count;
    }

    public synchronized int decrementCount() {
        int k = 10000;
        while (k > 0) {
            count--;
            k--;
        }
        return count;
    }

    public Integer getCount() {
        return count;
    }
}
