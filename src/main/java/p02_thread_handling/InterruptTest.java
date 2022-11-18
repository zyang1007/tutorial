package p02_thread_handling;

import org.junit.jupiter.api.Test;

// Thread interruption: a signal is sent to a thread, and the thread immediately ends its execution of the
// run() method after receiving the signal.
public class InterruptTest {

    /**
     * Main thread interrupts thread t by calling t.interrupt().
     *
     * Note that the interrupt() method only sends a "signal/interrupt request" to the thread t.
     * It depends on the code whether the thread t will respond immediately.
     */
    @Test
    public void testWithIsInterrupted() throws InterruptedException {
        Thread t = new MyThreadWithIsInterrupted();
        executeHelper(t);
    }

    // statement of the while loop is always true, so thread t will not be interrupted.
    @Test
    public void testWithWhileTrue() throws InterruptedException {
        Thread t = new WhileTrue();
        executeHelper(t);
    }

    // InterruptedException is caught, so sleep interrupted.
    @Test
    public void testInterruptSleep() throws InterruptedException {
        Thread t = new SleepForever();
        executeHelper(t);
    }

    private void executeHelper(Thread t) throws InterruptedException {
        t.start();
        Thread.sleep(10);  // main thread sleep for 10 milli sec

        System.out.println("sending a interrupt request to thread t...");
        t.interrupt();  // t is interrupted
        t.join();  // main thread is stopped, and waiting for thread t returning

        System.out.println("--- ends ---\n");
    }

}

class MyThreadWithIsInterrupted extends Thread {
    @Override
    public void run() {
        int n = 0;
        while(!isInterrupted()) {
            n++;
            System.out.println("n = " + n);
        }
    }
}

class WhileTrue extends Thread {
    @Override
    public void run() {
        int n = 0;
        while(true) {
            n++;
            System.out.println("n: " + n);
        }
    }
}

class SleepForever extends Thread {
    @Override
    public void run() {
        // int n = 0;
        try {
            Thread.sleep(5000 * 1000);
        } catch(InterruptedException e) {
            System.out.println("thread t is interrupted!");
            e.printStackTrace();
        }
    }
}
