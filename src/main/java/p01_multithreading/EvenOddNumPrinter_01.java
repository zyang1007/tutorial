package p01_multithreading;

public class EvenOddNumPrinter_01 {
    private final int MAX = 10;
    private int count = 1;

    public void printOddNum() throws InterruptedException {
        synchronized (this) {
            while (count < MAX) {
                while (count % 2 == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Thread.sleep(500);  // for observation
                System.out.println(Thread.currentThread().getName() + ":  " + count);
                count++;
                notify();
            }
        }
    }

    public void printEvenNum() throws InterruptedException {
        synchronized (this) {
            while (count <= MAX) {
                while (count % 2 == 1) {
                    try {
                        wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Thread.sleep(500);  // for observation
                System.out.println(Thread.currentThread().getName() + ":  " + count);
                count++;
                notify();
            }
        }
    }

    public static void main(String[] args) {
        EvenOddNumPrinter_01 printer = new EvenOddNumPrinter_01();

        Thread t1 = new Thread(() -> {
            try {
                printer.printOddNum();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                printer.printEvenNum();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();
    }

}
