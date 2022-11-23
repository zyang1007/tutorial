package p01_multithreading.ThreadSafeOrNot;

public class SynchronizedCounterWithTwoLockers {
    private Integer count = 0;
    final Object locker1 = new Object();
    final Object locker2 = new Object();

    public int incrementCount() {
        int k = 10000;
        while (k > 0) {
            synchronized (locker1) {
                count++;
            }
            k--;
        }
        return count;
    }

    public int decrementCount() {
        int k = 10000;
        while (k > 0) {
            synchronized (locker2) {
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
