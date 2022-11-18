package p04_deadlock;

/**
 * If deadlock situation will not occur, the two values would keep changing.
 *
 * Solution of avoiding deadlock: the order in which threads acquire locks must be consistent;
 * in other words, all threads acquiring lock_A first and then acquiring lock_B.
 */
public class DeadLockExample {

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[] {
          new Thread(() -> {
              while(true) {
                  Counter.incrementBy(2);
              }
          }),
          new Thread(() -> {
              while(true) {
                  Counter.decrementBy(2);
              }
          })
        };

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads){
            t.join();
        }

        System.out.println(" --- main() ends ---");
    }
}

class Counter {  // locker
    public static final Object LOCK_A = new Object();
    public static final Object LOCK_B = new Object();
    private static int value = 0;
    private static int another = 0;

    public static void incrementBy(int num ) {
        synchronized (LOCK_A) {
            value += num;
            synchronized (LOCK_B) {
                another += num;
            }
        }
        System.out.println("value = " + value + ",  another = " + another);
    }

    public static void decrementBy(int num) {
        synchronized (LOCK_B) {
            value -= num;
            synchronized (LOCK_A) {
                another -= num;
            }
        }
        System.out.println("value = " + value + ",  another = " + another);
    }
}
