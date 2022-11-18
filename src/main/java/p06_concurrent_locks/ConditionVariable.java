package p06_concurrent_locks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionVariable {
    private static TaskQueue taskQueue;
    private static List<Thread> consumerList = new ArrayList<>();

    public static void createProducerThreads(int numOfProducer) throws InterruptedException {
        for (int i = 1; i <= numOfProducer ; i++) {
            Thread producer = new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    String s = "t-" + (int) (Math.random() * 1000);
                    System.out.println("\nAdding task:  " + s);
                    taskQueue.addTask(s);

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            producer.start();
            System.out.println("producer:  "+ producer.getId() + ",  status:  " + producer.getState());
            producer.join();  // main thread will wait for this thread to die
            System.out.println("producer status:  " + producer.getState());
        }
    }

    public static void createConsumerThreads(int numOfConsumer) throws InterruptedException {
        for (int i = 1; i <= numOfConsumer; i++) {
            Thread consumer = new Thread(() -> {
                while(true) {
                    try {
                        String task = taskQueue.getTask();
                        System.out.println("Task: " + task + " is executed!");
                    } catch(Exception e) {
                        // e.printStackTrace();
                        Thread.currentThread().interrupt();
                        return;  // close current thread
                    }
                }
            });
            System.out.println("Consumer Thread " + i + " starts...");
            consumer.start();
            consumerList.add(consumer);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        taskQueue = new TaskQueue();
        createConsumerThreads(5);  // creates 5 threads and add them into the consumerList
        createProducerThreads(2);

        Thread.sleep(3000);
        for (Thread thread : consumerList) {
            thread.interrupt();  // wake up consumers and let them WAITING
            // System.out.println(thread.getId() + ":  " + thread.getState());
        }
        System.out.println("\n--- End of Main() ---");
        // System.exit(0);
    }
}

class TaskQueue extends Thread {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private final Queue<String> queue = new LinkedList<>();

    public void addTask(String s) {
        lock.lock();  // thread acquires the lock, otherwise, lies dormant(sleep) until the lock has been acquired
        try {
            queue.add(s);
            condition.signalAll();  // wakes up all waiting threads
        } finally {
            lock.unlock();  // releases the lock
        }
    }

    public String getTask() throws InterruptedException {
        lock.lock();
        try {
            while(queue.isEmpty()) {
                // current thread goes to sleep and releases the lock until it is signalled/interrupted
                condition.await();
            }
            return queue.remove();
        } finally {
            lock.unlock();
        }
    }

}
