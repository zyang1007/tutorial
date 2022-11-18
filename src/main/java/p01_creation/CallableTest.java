package p01_creation;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;

public class CallableTest {
    final int n = 10;

    @Test
    public void testCallable() {
        Callable<Integer> sumTask = new Callable<>() {  // anonymous class
            // Note that interface Callable.call() returns Future<T>
            // here, we override it to return a Integer.
            @Override
            public Integer call() throws Exception {
                int sum = 0;
                for (int i = 1; i < n; i++) {
                    sum += i;
                    Thread.sleep(500);
                    System.out.println(sum);
                }
                return sum;
            }
        };

        try{
            Integer call = sumTask.call();
            // System.out.println(call);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println();
        }

    }

    @Test
    public void testCallableWithLambda() throws Exception {
        Callable<Double> task = () -> {
            double sum = 0.0;
            for (int i = 0; i < n; i++) {
                sum += i;
                Thread.sleep(500);
                System.out.println(sum);
            }
            return sum;
        };

        task.call();
    }


}
