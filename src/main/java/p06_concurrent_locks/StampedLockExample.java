package p06_concurrent_locks;

import java.util.concurrent.locks.StampedLock;

/**
 * Optimistic locking - allows multiple users to attempt to update the same record without informing the
 * users that others are also attempting to update the record.
 */
public class StampedLockExample {

    public static void main(String[] args) {
        Point point = new Point();
        Thread writeThread = new Thread(() -> {
            for(int i = 0; i < 3; i++) {
                point.move(100, 200);
            }
            System.out.println("Write thread done!");
        });

        Thread readThread = new Thread(() -> System.out.println(point.distanceFromOrigin()));
        writeThread.start();  // create and run the Write-thread

        try {
            Thread.sleep(1000);  // main thread sleeps for 1 sec
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        readThread.start();  // create and run the Read-thread
    }
}

class Point {
    private final StampedLock stampedLock = new StampedLock();
    private double x = 0.0;
    private double y = 0.0;

    public void move(double deltaX, double deltaY) {
        long stamp = stampedLock.writeLock();  // Exclusively acquires the write-lock, method returns a write-stamp
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            stampedLock.unlockWrite(stamp);  // releases the exclusive lock if the lock state matches the stamp
        }
    }

    public double distanceFromOrigin() {
        // tryOptimisticRead() method returns a stamp that can later be validated, or zero if exclusively locked.
        long stamp = stampedLock.tryOptimisticRead();
        double currentX = x, currentY = y;  // values of x and y may be being modified by others

        if(!stampedLock.validate(stamp)) {  // check if any Write occur after optimistic-read
            stamp = stampedLock.readLock();  // choose Pessimistic locking
            try {
                currentX = x;
                currentY = y;
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }

        return Math.sqrt(currentX * currentX + currentY * currentY);
    }
}
