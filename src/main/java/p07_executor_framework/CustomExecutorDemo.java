package p07_executor_framework;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * java.util.concurrent.CountDownLatch - class CountDownLatch extends Object
 * A synchronization aid that allows one or more threads to wait until a set of operations being performed in
 * other threads completes.
 */
public class CustomExecutorDemo {

    @Test
    public void test_01() {  // test with ExecutorService and Thread pool
        int size = 20;
        int taskNumber = 1000;

        CountDownLatch latch = new CountDownLatch(taskNumber);
        ExecutorService executorService = Executors.newFixedThreadPool(size);

        for(int i = 0; i < taskNumber; i++) {
            MyTask task = new MyTask(latch);
            executorService.execute(task);
        }
        executorService.shutdown();

        try {
            latch.await();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_02() {  // test with MyExecutor
        int size = 20, taskNum = 1000;
        CountDownLatch latch = new CountDownLatch(taskNum);
        MyExecutor myExecutor = new MyExecutor(20);

        for (int i = 0; i < taskNum; i++) {
            MyTask myTask =  new MyTask(latch);
            myExecutor.execute(myTask);
        }
        myExecutor.shutdown();

        try {
            latch.await();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class MyTask implements Runnable {
    CountDownLatch latch;
    private static final AtomicInteger COUNT = new AtomicInteger(1);

    public MyTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":  " + ", count:  " + COUNT.getAndIncrement());
        latch.countDown();
    }
}