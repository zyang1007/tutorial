package p01_creation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadCreateTests {

    @Test
    public void testWithNewThread() {
        Thread t1 = new Thread();  // no target/job for the new thread
        // System.out.println("t1 id:  " + t1.getId());  // error, variable t1 not have been initialized
        t1.start();  // create/initialize a new thread then run the task in the new thread
        System.out.println("t1 id = " + t1.getId() + ",  t1 status: " + t1.getState());  // runnable
        t1.run();  // run the task in current thread/process
        System.out.println("t1 id = " + t1.getId() + ",  t1 status: " + t1.getState());  // terminated
    }

    @Test
    public void testWithNewThreadImplementsRunnable() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("creating a new thread with new Thread() implementing Runnable...");
            }
        });
        t1.start();
        System.out.println("t1 id = " + t1.getId() + ",  t1 status:  " + t1.getState());
        t1.run();
        System.out.println("t1 id = " + t1.getId() + ",  t1 status:  " + t1.getState());
    }

    @Test
    public void testWithNewThreadAndLambda() {
        Thread t1 = new Thread(() -> {
            System.out.println("creating a new thread with new Thread() using lambda...");
        });
        t1.start();
        System.out.println("t1 id = " + t1.getId() + ",  t1 status:  " + t1.getState());
        t1.run();
        System.out.println("t1 id = " + t1.getId() + ",  t1 status:  " + t1.getState());
    }

    @Test
    public void testWithMyThread() {
        Thread t1 = new MyThread();
        t1.start();  // create a new thread and then execute its task
        System.out.println("t1 id = " + t1.getId() + ",  t1 status:  " + t1.getState());
        t1.run();  // call run() again?
        System.out.println("t1 id = " + t1.getId() + ",  t1 status:  " + t1.getState());
    }

    @Test
    public void testWithMyRunnable() {
        Thread t1 = new Thread(new MyRunnable());
        t1.start();
        System.out.println("t1 id = " + t1.getId() + ",  t1 status:  " + t1.getState());
        t1.run();
        System.out.println("t1 id = " + t1.getId() + ",  t1 status:  " + t1.getState());
    }

    @Test
    public void testWithMyCallable() {
        Callable<String> myCallable = new MyCallable();  // Callable.call() has return value

        try {  // Callable.call() throws Exception. need to be handling
            String res = myCallable.call();
            System.out.println(res);
        } catch (Exception e) {
            System.out.println("caught a exception when calling call()!");
        }
    }

    @Test
    public void testMyCallableWithExecutorService() throws InterruptedException, ExecutionException {
        Callable<String> myCallable = new MyCallable();

        // initialize an Executor and a thread pool with size 10
        ExecutorService executor = Executors.newFixedThreadPool(10);

        List<Future<String>> futureList = new ArrayList<>();
        Future<String> future = executor.submit(myCallable);  // submit() returns Future<T>
        futureList.add(future);
        System.out.println("list size:  " + futureList.size());

        Future<String> stringFuture = futureList.get(0);

        for(int i = 0; i < 5; i++) {
            // Future<String> stringFuture = futureList.get(i); // error, Index 1 out of bounds for length 1
//            long id = Thread.currentThread().getId();
//            System.out.println("current thread id = " + id);  // same thread
            Thread.sleep(1000);
            String s = future.get();
            System.out.println(s);
        }

        executor.shutdown();
    }

    @AfterEach
    public void afterEachTest() {
        System.out.println();
    }
}
