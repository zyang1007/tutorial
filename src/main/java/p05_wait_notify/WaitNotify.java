package p05_wait_notify;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class WaitNotify {

    public static void main(String[] args) throws InterruptedException {
        TaskQueue taskQueue = new TaskQueue();
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(() -> {
               while(true) {
                   try {
                       String s = taskQueue.getTask();
                       System.out.println("executing task:  " + s);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                       return;
                   }
               }
            });
            t.start();
            System.out.println(t.getName() + " starts...");
            threads.add(t);
        }

        for(int i = 0; i < 10; i++) {
            int num = i;
            Thread suppler = new Thread(() -> {
                String s = "t-" + Math.random();
                System.out.println("adding task (" + num + "):  " + s);
                taskQueue.addTask(s);
                try {
                    Thread.sleep(1000);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            });
            suppler.start();
            suppler.join();
        }

        Thread.sleep(3000);  // main thread sleeps for 3 sec
        for(Thread t : threads) {  // main thread wakes up and sends interrupt requests to other threads
            t.interrupt();
        }

        Thread.sleep(3000);
        System.out.println("\n--- End of Main() ---");
    }
}

class TaskQueue {
    Queue<String> queue = new LinkedList<>();

    public synchronized void addTask(String str) {
        this.queue.add(str);
        this.notifyAll();
    }

    public synchronized String getTask() throws InterruptedException {
        while(queue.isEmpty()) {
            this.wait();
        }

        return queue.remove();
    }
}
