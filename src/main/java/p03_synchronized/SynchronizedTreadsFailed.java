package p03_synchronized;

// Use an object as a lock.
// Note: different threads use different locks/objects to access the same variable causes failure.
public class SynchronizedTreadsFailed {

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[]{
                new IncrementThread(),
                new DecrementThread(),
                new IncrementThread2(),
                new DecrementThread2()
        };

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t: threads) {
            t.join();
        }

        // studentCount always change because if the different locks
        System.out.println("Counter.studentCount:  " + Counter.studentCount);
        // teacherCount always is 0 because of the same lock
        System.out.println("Counter.teacherCount:  " + Counter.teacherCount);
    }

}

class Counter {
    public static final Object LOCK_STUDENT = new Object();
    public static final Object LOCK_TEACHER = new Object();
    public static final Object LOCK_COMMON = new Object();
    public static int studentCount = 0;
    public static int teacherCount = 0;
}

class IncrementThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Counter.LOCK_STUDENT) {
                Counter.studentCount++;
            }
        }
    }
}

class DecrementThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Counter.LOCK_TEACHER) {
                Counter.studentCount--;
            }
        }
    }
}

class IncrementThread2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Counter.LOCK_COMMON) {
                Counter.teacherCount++;
            }
        }
    }
}

class DecrementThread2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Counter.LOCK_COMMON) {
                Counter.teacherCount--;
            }
        }
    }
}
