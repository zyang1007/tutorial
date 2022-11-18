package p08_future;

import com.sun.security.jgss.GSSUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureMethodsTest {

    /**
     * Using CompletableFuture as a simple Future.
     *
     * boolean complete(T value) - If not already completed, sets the value returned by get() and related
     * method to the given value.
     *
     * T get() - Waits if necessary for this future to complete, and then returns its result.
     */
    @Test
    public void test_01() throws InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.submit(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("executing tasking...");
            completableFuture.complete("Hello?");
            System.out.println("task was completed!");
        });

        for (int i = 0; i < 5; i++) {
            try {
                System.out.println("Task was done:  " + completableFuture.isDone());
                System.out.println(completableFuture.get() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier) - returns a new CompletableFuture
     * that is asynchronously completed by a task running in the ForkJoinPool.commonPool()
     * with the value obtained by calling the given Supplier.
     *
     * staticCompletableFuture<Void> runAsync(Runnable runnable) - returns a new CompletableFuture that
     * is asynchronously completed by a task running in the ForkJoinPool.commonPool() after
     * it runs the given action.
     */
    @Test
    public void test_02() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + ":  Hello 1");
            return "Hello 1";
        });

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread() + ":  Hello 2");
        });

        try {
            System.out.println("voidCompletableFuture.isDone():  " + voidCompletableFuture.isDone());
            Thread.sleep(1000);
            System.out.println("voidCompletableFuture.isDone():  " + voidCompletableFuture.isDone());

            System.out.println(stringCompletableFuture.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Using thenApply() method process the result, and returns a Future that holds a value returned by a function.
     *
     * <U> CompletableFuture<U>  thenApply(Function<? super T, ? extends U> fn) - returns a new CompletionStage
     * that, when this stage completes normally, is executed with this stage's result as the argument
     * to the supplied function.
     */
    @Test
    public void test_03() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> finalResult = stringCompletableFuture.thenApply(str -> str + " world!");

        try {
            System.out.println(finalResult.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The thenAccept() method receives a Consumer(no return) and passes it the result of the computation.
     *
     * CompletableFuture<Void>  thenAccept(Consumer<? super T> action) - returns a new CompletionStage that, when
     * this stage completes normally, is executed with this stage's result as the argument to the supplied action.
     */
    @Test
    public void test_04() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<Void> finalResult = stringCompletableFuture
                .thenAccept(str -> System.out.println("Last stage's result:  " + str));

        try {
            finalResult.get();  // no return value -> null
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * If we neither need the value of the computation, nor want to return some value at the end if the chain,
     * then we can pass a Runnable lambda to the thenRun() method.
     */
    @Test
    public void test_05() {
        CompletableFuture<String> firstStage = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<Void> secondStage = firstStage.thenRun(() -> System.out.println("second stage completed!"));
        CompletableFuture<Void> lastStage = secondStage.thenRun(() -> System.out.println("All stages completed!"));

        try {
            lastStage.get();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Using thenCompose() method to chain two Futures sequentially.
     * The thenApply() and the thenCompose() methods closely relate to the map and flatmap methods of Stream.
     */
    @Test
    public void test_06() throws InterruptedException {
        CompletableFuture<String> thenCompose = CompletableFuture
                .supplyAsync(() -> "Hello")
                .thenCompose(res -> CompletableFuture.supplyAsync(() -> res + " World!"));

        CompletableFuture<CompletableFuture<String>> thenApply = CompletableFuture
                .supplyAsync(() -> "Good")
                .thenApply(e -> CompletableFuture.supplyAsync(() -> e + "Bye!"));

        try {
            System.out.println(thenCompose.get());

            // System.out.println(thenApply.get());  // java.util.concurrent.CompletableFuture@179ece50
            System.out.println(thenApply.get().get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Using the thenCombine() method to execute two independent Futures and do something with their
     * results, then return a final result.
     *
     * <U, V> CompletableFuture<V> thenCombine(CompletionStage<? extends U> other, BiFunction<...> fn)
     */
    @Test
    public void test_07() {
        CompletableFuture<String> combineFutures = CompletableFuture.supplyAsync(() -> {
            System.out.println("step 1...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("step 2...");
            return "Good";
        }).thenCombine(
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("step 3...");
                    return "  Bye!";})
                ,(s1, s2) -> s1 + s2
        );

        try {
            System.out.println(combineFutures.get());  // notice the printout order
            combineFutures.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Using the thenAcceptBoth() to execute two independent Futures and do something with their results,
     * but not passing any result.
     */
    @Test
    public void test_08() {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> "Good")
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> " Morning!"), (a, b) -> System.out.println(a + b));

        try {
            System.out.println("future.isDone():  " + future.isDone());
            System.out.println(future.get());
        } catch(InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * public static CompletableFuture<Void> allOf(CompletableFuture<?>... cfs) - returns a new CompletableFuture
     * that is completed when all the given CompletableFuture completes.
     */
    @Test
    public void test_09() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> " beautiful ");
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "world!");

        CompletableFuture<Void> allCompleted = CompletableFuture.allOf(future1, future2, future3);

        try {
            allCompleted.get();  // waits for all given CompletableFuture to complete
            Assertions.assertTrue(allCompleted.isDone());
            // Assertions.assertTrue(allCompleted.isCompletedExceptionally());

            Assertions.assertTrue(future1.isDone());
            Assertions.assertTrue(future2.isDone());
            Assertions.assertTrue(future3.isDone());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * T join() - returns the result value when complete, or throws an (unchecked) exception if completed exceptionally.
     *
     * Similar to the get(), but get() method does not handle exceptions, so it can be used with Stream.map().
     */
    @Test
    public void test_10() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Good ");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "morning");
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "fresh air!");

        // String res = Stream.of(future1, future2)
        // .map(CompletableFuture::get)
        // .collect(Collectors.joining(" "));  // unhandled exception warning

        String joinString = Stream.of(future1, future2, future3)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));

        // same as above
        List<CompletableFuture<String>> futureList = Arrays.asList(future1, future2, future3);
        String combineStr = futureList.stream().map(CompletableFuture::join).collect(Collectors.joining(" "));

        try {
            System.out.println("joinString:  " + joinString);
            System.out.println("combineStr:  " + combineStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Methods can be used to handle exceptions.
     * 1. handle(BiFunction<? super T, Throwable, ? extends  U> fn);
     * 2. whenComplete(BiConsumer<? super T, ? super Throwable> action);
     * 3. exceptionally(Function<Throwable, ? extends T> fn);
     */
    @Test
    public void test_11() {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("step 1");
            System.out.println("> adding 1...\n");
            return 1;
        }).handle((res, e) -> {
            System.out.println("step 2");
            System.out.println("res before adding 2:  " + res);

            int i = 3 / 0;  // divide by 0, Exception occur
            System.out.println("adding 2...\n");  // not continue to execute, assigns res with null
            return res + 2;
        }).handle((res, throwable) -> {
            System.out.println("step 3");
            System.out.println("res: " + res);
            System.out.println("> adding 3...\n");
            return res + 3;
        }).whenComplete((res, e) -> {
            System.out.println("Task is completed!");
            if (e == null) {  // prints out final result if no error occurred
                System.out.println("final result:  " + res);
            }
        }).exceptionally(e -> {  // executes only if exception occurred
            System.out.println("check runtime error...");
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        });

        try {
            System.out.println("\nintegerCompletableFuture.get(): " + integerCompletableFuture.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
