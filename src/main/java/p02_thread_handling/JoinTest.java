package p02_thread_handling;

// Child thread calls join() method will make the Main thread to stop and wait.
public class JoinTest {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            System.out.println("thread starts... ");
            try {
                System.out.println("thread t is doing its task...");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread t ends!");
        });

        System.out.println("--- main starts ---");

        t.start();
        try {
            System.out.println("main thread stops and waits for thread t completing its task...");
            t.join();  // main thread waits for thread t end. main thread's status is Timed Waiting.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("--- main ends ---");
    }
}
