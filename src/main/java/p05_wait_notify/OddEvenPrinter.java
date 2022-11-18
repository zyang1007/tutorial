package p05_wait_notify;

public class OddEvenPrinter {
    private static final Object monitor = new Object();
    private static int value = 1;

    public static void main(String[] args) throws InterruptedException {
//        PrintRunnable printThread = new PrintRunnable();
//        new Thread(printThread).start();
//        new Thread(printThread).start();

        // same as above
        Thread t1 = new Thread(new PrintRunnable());
        Thread t2 = new Thread(new PrintRunnable());
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("--- main() ends ---");
    }


    protected static class PrintRunnable implements Runnable {
        @Override
        public void run() {
            synchronized (monitor) {
                while (value <= 10) {
                    System.out.println(Thread.currentThread().getName() + ",  value: " + value);
                    value++;
                    monitor.notify();  // wake up/notify other threads

                        try {
                            if (value < 11) {
                                monitor.wait();  // current thread release lock and go to wait/sleep
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
            }
        }
    }

}
