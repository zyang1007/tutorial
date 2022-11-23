package p01_multithreading;

public class NumberPrinter {
    private static int count = 1;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(() -> printNumber());
            thread.start();
            thread.join();
        }

//        Thread t1 = new Thread(() -> printNumber());
//        Thread t2 = new Thread(() -> printNumber());
//        Thread t3 = new Thread(() -> printNumber());
//
//        t1.start();
//        t2.start();
//        t3.start();

        System.out.println("\n---- End of main() ----");
    }

    private static synchronized void printNumber() {
        int n = 3;
        while (n > 0) {
            System.out.println(Thread.currentThread().getName() + ":  " + count);
            count++;
            n--;

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // NumberPrinter.class.notify();
    }
}
