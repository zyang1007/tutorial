package p08_future;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * interface Future<V>.
 * 1. Future<V>: A Future represents the result of an asynchronous computation. Methods are provided to check if
 * the computation is complete, to wait for its completion, and to retrieve the result of the computation.
 *
 * 2. FutureTask<V>: a cancellable asynchronous computation. This class provides a base implementation of Future, with
 * extra methods to start and cancel a computation.
 */
public class FutureUsageExample {

    @Test
    public void demo_01() {
        AtomicInteger count = new AtomicInteger(0);
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Callable is abstract, cannot be instantiated(with 'new' keyword)
        Callable<Integer> task1 = () -> count.getAndIncrement();
        Callable<Integer> task2 = () -> count.getAndIncrement();
        Callable<Integer> task3 = () -> count.getAndIncrement();

        Future<Integer> future1 = executorService.submit(task1);
        Future<Integer> future2 = executorService.submit(task2);
        Future<Integer> future3 = executorService.submit(task3);

        try {
            System.out.println("future 1:  " + future1.get());
            System.out.println("future 2:  " + future2.get());
            System.out.println("future 3:  " + future3.get());
        } catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void demo_02() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        long startTime = System.currentTimeMillis();

        FutureTask<String> futureTask1 = new FutureTask<>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread() + ":  task 1 is completed!";
        });

        FutureTask<String> futureTask2 = new FutureTask<>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread() + " task 2 is completed!";
        });

        executorService.submit(futureTask1);
        executorService.submit(futureTask2);

        try {
            System.out.println(futureTask1.get());
            System.out.println(futureTask2.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println(">>> Time Costs:  " + (endTime - startTime) + "  milli seconds.");
        System.out.println(Thread.currentThread().getName() + "\t ends.");
        executorService.shutdown();
    }
}
