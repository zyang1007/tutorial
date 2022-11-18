package p02_thread_handling;

/**
 * Daemon threads are low-priority threads whose role is only to provide services to user threads (non-daemon threads).
 * Because the daemon thread provides services for the user thread,
 * the daemon thread is only required when the user thread is running.
 *
 * Also, once all user threads have finished running, the daemon thread cannot prevent the JVM from exiting;
 * That is, the program will exit when only the daemon thread is left.
 *
 * Daemon threads are executing in the background, a typical example is the Garbage Collection mechanism of JVM.
 *
 * Note: daemon thread should not hold any resources that need to be closed, such as open files. Because when
 * the JVM exists, daemon thread does not have any chance to close the file, which will cause data loss.
 */
public class DaemonThreadExample {

    public static void main(String[] args) {
        Thread t1 = new Thread(new People(), "Regular_Thread");
        Thread t2 = new Thread(new God(), "Daemon_Thread");
        t2.setDaemon(true);  // set the t2 to a daemon thread

        t2.start();
        t1.start();

        System.out.println("Main ends!");
    }
}

class People implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Thread " + Thread.currentThread().getName() + " is alive");
        }

        System.out.println("Thread " + Thread.currentThread().getName() + "ends!");
    }
}

class God implements Runnable{

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
            } catch(Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " is watching...");
        }
    }
}

