package p02_thread_handling;

// The thread who calls Thread.sleep() is going to sleep.
public class SleepTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            System.out.println("Thread t starts...");
            try {
                System.out.println("thread t is going to sleeping for 5 seconds...");
                Thread.sleep(5000);
                System.out.println("Thread t awake...");
            } catch(Exception e) {
                e.printStackTrace();
            }
            System.out.println("Done, Thread t completes its task!");
        });

        System.out.println("--- Main thread starts ---");
        t.start();

        Thread.sleep(3000);  // main thread finish 2 sec earlier than thread t
        // Thread.sleep(6000);  // 1 sec later than thread t
        System.out.println("Main thread is going to sleep for 3 seconds...");
        System.out.println("Main thread done!");
    }
}
