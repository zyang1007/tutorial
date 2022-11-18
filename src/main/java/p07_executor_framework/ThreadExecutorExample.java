package p07_executor_framework;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadExecutorExample {

    public static void main(String[] args) throws InterruptedException {
        MyExecutor myExecutor = new MyExecutor();
        MyTask myTask = new MyTask();

        myExecutor.execute(myTask);
        Thread.sleep(1000);
        System.out.println("--- End of main() ---");
    }

    static class MyExecutor implements Executor {
        @Override
        public void execute(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println("executing my task...");
        }
    }
}


