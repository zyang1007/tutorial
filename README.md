## Thread

### Creation
- implements Runnable and override run();
- implements Callable and override call();
- allocates a new Thread Object: Thread t = new Thread(Runnable target);
- creates a class extends Thread, e.g. class MyThread extends Thread: Thread t = new MyThread(Runnable target);

**Remember:** Runnable and Callable are functional interfaces, so we can use lambda for them.

### Basic Thread Handling and Communication
1. `t.start()` - causes this thread to begin execution. The JVM calls the run() method of this thread.
1. `t.join()` - waits for this thread to die.
2. `t.interrupt()` - interrupts this thread (note: cannot interrupt while(true) ).
3. `Thread.sleep(long millis)` - causes the current thread to sleep for the specifued number of milliseconds.
4. `setDaemon` - marks this thread as either a daemon thread or a user thread. The JVM exits when the only threads running are all daemon threads.

More: `setName(String name)`, `getId()`, `getName()`, `getState()`, `getPriority()` `setPriority(int newPriority)`, etc.

### Executor Framework
- Executor life-cycle: running, shutting down, and terminated.
#### interface Executor
1. contains one abstract method `void execute(Runnable r)` - executes the given Runnable at sometime in the future.
2. useage example:
```
Runnable task = () -> System.out.println("Hi");
        Executor executor = (r) -> {
	    r.run();  // direct execute
            // new Thread(r).start();  // thead per task executor
        };
        executor.execute(task);
```

#### interface ExecutorService
1. extends `Executor`, provides methods to manage termination and methods that can produce a `Future` for tracking progress of one or more asynchronous tasks.
2. `void execute(Runnable r)` method inherited from interface Executor;
3. `Future<?> submit(Runnable task)` - submits a Runnable task for execution and returns a Future representing that task.
4. `<T> Future<T> submit(Callable<T> task)` - submit a value-returning task for execution and returns a Future representing the pending results of the task.
5. `void shutdown()` - initiates an orderly shutdown in whcih previously submitted tasks are executed, but no new tasks will be accepted.
6. `<T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)` - executes the given tasks, returning a list of Futures holding their status and results when all complete.
7. More methods: `isShutdown()`, `isTerminated()`, `awaitTermination()`, `invokeAny()`, etc.

**Note:** 
1. The `shutdown()` method will allow previously submitted tasks to execute before terminating, while the `shutdownNow()` method prevents waiting tasks from starting and attemps to stop currently executing tasks.
2. Method `submit()` extends base method `Executor.execute(Runnable)` by creating and returning a Future that can be used to cancel execution and/or wait for completion.

#### class Executors and Thread Pool
- class `Executor` extends `Object` - has utility (static) methods for(to create) `Executor`, `ExecutorService`, `ScheduleExecutorService`, `ThreadFactory`, and `Callable` classes defined in this package. Most commonly used methods are:
1. `newCachedThreadPool()` - creates a thread pool that creates new threads as needed, but will reuse previously constructed threads when they are available.
2. `newFixedThreadPool(int nThreads)` - creates a thread pool that reuses a fixed number of threads operating off a shared unbounded queue.
3. `newSingleThreadExecutor()` - creates an Executor that uses a single worker thread operating off an unbounded queue.
4. More methods: `defaultThreadFactory()`, `newScheduledThreadPool(int size)`, `callable(Runnable task)`, etc.

- Thread Pool - consists of homogenous worker threads that are assigned to execute tasks. Once a worker thread completes its task, it is returned to the pool. Usually, thread pools are bound to a (task)queue from which tasks are dequeued for execution by worker threads. Using a thread pool alleviates from the ails of manual creation of threads.
