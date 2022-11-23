package p01_multithreading.ThreadSafeOrNot;

/**
 * Not thread safe:
 * 1. globalCount is static, shared and can be accessed by all class objects;
 * 2. each objects has its own 'count'(doesn't share), but if there are more than one method to modify it,
 * and those methods are executed by different threads then it is not thread safe.
 */
public class Counter {
    private Integer count = 0;
    private static Integer globalCount = 0;

    public int incrementCount() {
        int k = 10000;
        while (k > 0) {
            count++;
            k--;
        }
        return count;
    }

    public int decrementCount() {
        int k = 10000;
        while (k > 0) {
            count--;
            k--;
        }
        return count;
    }

    // getters and setters
    public Integer getCount() {
        return count;
    }

    public static Integer getGlobalCount() {
        return globalCount;
    }

    public static void setGlobalCount(Integer globalCount) {
        Counter.globalCount = globalCount;
    }
}
