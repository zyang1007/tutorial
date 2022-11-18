package p10_thread_local;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalExample {

    public static void main(String[] args) {
        Mydata mydata = new Mydata();
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        try {
            for (int i = 0; i < 3; i++) {  // creates worker threads
                executorService.submit(() -> {  // submits tasks for execution
                    for (int j = 0; j < 5; j++) {
                        Integer beforeAddOne = mydata.threadLocalField.get();
                        mydata.addOne();
                        Integer afterAddOne = mydata.threadLocalField.get();
                        System.out.println("before:  " + beforeAddOne + ",  after:  " + afterAddOne);
                    }
                    // removes the current thread's value for this thread-local variable
                    mydata.threadLocalField.remove();
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}

class Mydata {
    ThreadLocal<Integer> threadLocalField = ThreadLocal.withInitial(() -> 0);

    public void addOne() {
        threadLocalField.set(threadLocalField.get() + 1);
    }
}
