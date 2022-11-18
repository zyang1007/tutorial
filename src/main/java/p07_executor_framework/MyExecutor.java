package p07_executor_framework;

import java.util.*;
import java.util.concurrent.Executor;

public class MyExecutor implements Executor {

    private static final int MAX_WORKER_NUMBERS = 30;  // max worker size
    private final Deque<Runnable> taskQueue = new LinkedList<>();  // tasks queue
    private final List<Worker> workerList = Collections.synchronizedList(new ArrayList<>());  // worker list

    // initialize thread pool
    public MyExecutor(int num) {
        initializedWorker(Math.min(num, MAX_WORKER_NUMBERS));
    }

    // initialize workers
    public void initializedWorker(int num) {
        for(int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workerList.add(worker);
            new Thread(worker).start();  // starts worker thread
        }
    }

    // close the thread pool
    public void shutdown() {
        for(Worker worker : workerList) {
            worker.shutdown();
        }
    }

    // add worker
    public void addWorkers(int num) {
        synchronized (taskQueue) {
            if (num + workerList.size() > MAX_WORKER_NUMBERS) {
                num = MAX_WORKER_NUMBERS - workerList.size();
            }
            initializedWorker(num);
        }
    }

    // decreases worker
    public void removeWorker(int num) {
        synchronized(taskQueue) {
            if (num >= workerListSize()) {
                throw new IllegalArgumentException("exceed current worker size!");
            }
            for (int i = 0; i < num; i++) {
                Worker worker = workerList.get(i);
                if (worker != null) {
                    worker.shutdown();
                    workerList.remove(i);
                }
            }
        }
    }

    public int workerListSize() {
        return workerList.size();
    }

    @Override
    public void execute(Runnable task) {
        if(task == null) {
            throw new NullPointerException();
        }

        synchronized (taskQueue) {  // add task into task queue and notify worker
            taskQueue.addLast(task);
            taskQueue.notify();
        }
    }


    class Worker implements Runnable {
        private volatile boolean running = true;

        @Override
        public void run() {
            Runnable task = null;

            while(running || task != null) {
                if(task != null) {
                    task.run();
                }
                synchronized (taskQueue) {
                    if(taskQueue.isEmpty() && running) {  // executes all tasks in the taskQueue until no more available
                        try {
                            taskQueue.wait();
                        } catch(InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;  // return when current thread is interrupted
                        }
                    }
                    task = taskQueue.isEmpty() ? null : taskQueue.removeFirst();
                }
            }
        }

        public void shutdown() {
            running = false;
            synchronized(taskQueue) {
                taskQueue.notify();  // notify work to shut down
            }
        }
    }
}

