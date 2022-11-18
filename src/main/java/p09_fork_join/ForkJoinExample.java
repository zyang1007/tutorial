package p09_fork_join;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * ForkJoin framework provides tools to help speed up parallel processing by attempting to use all available
 * processor cores. It accomplishes this through a Divide and Conquer approach.
 *
 * In practice, this means that the framework first "forks", recursively breaking the task into smaller
 * independent subtasks until they are simple enough to run asynchronously.
 * After that, the "join" part begins. The results of all subtasks are recursively joined into a single result.
 * In the case of a task returns void, the program simply waits until every subtask runs.
 *
 * To provide effective parallel execution, the fork/join framework uses a pool of threads called the
 * ForkJoinPool. This pool manages worker threads of type ForkJoinWorkerThread.
 */
public class ForkJoinExample {

    @Test
    public void demo_01() {
        ForkJoinTask myForkJoinTask = new MyForkJoinTask(1, 10);
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        Future<Integer> future = forkJoinPool.submit(myForkJoinTask);

        try {
            System.out.println("> Final result:  " + future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void demo_02() throws ExecutionException, InterruptedException {
        ForkJoinTask task = new ForkJoinTask() {
            private final int threadHold = 5;
            private int first = 1, last = 5;

            @Override
            public Object getRawResult() {
                return null;
            }

            @Override
            protected void setRawResult(Object value) {
                // codes you want
            }

            @Override
            protected boolean exec() {
                int res = 0;
                for (int i = first; i <= last; i++) {
                    System.out.println("result:  " + res);
                    res += i;
                }

                System.out.println(res);
                return true;
            }
        };

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        Future<Integer> future = forkJoinPool.submit(task);
        System.out.println(">> Final res:  " + future.get());
    }
}

class MyForkJoinTask extends RecursiveTask<Integer> {
    private final int threadHold = 5;
    private int first;
    private int last;

    public MyForkJoinTask(int first, int last) {
        this.first = first;
        this.last = last;
    }

    @Override
    protected Integer compute() {
        int result = 0;
        if (last - first <= threadHold) {
            // small task, directly calculate
            for (int i = first; i <= last; i++) {
                System.out.println("result:  " + result);
                result += i;
            }
        } else {
            // big task, divide into small tasks
            int middle = first + (last - first) / 2;
            ForkJoinTask leftTask = new MyForkJoinTask(first, middle);
            ForkJoinTask rightTask = new MyForkJoinTask(middle, last);

            leftTask.fork();
            rightTask.fork();
            result = ((Integer) leftTask.join()) + ((Integer) rightTask.join());
        }

        return result;
    }
}
