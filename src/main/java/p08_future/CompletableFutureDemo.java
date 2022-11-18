package p08_future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * class CompletableFuture<T> implements interface Future<V>, CompletionStage<T>
 * - a Future that may be explicitly completed(setting its value and status), and may be used as a CompletionStage,
 * supporting dependent functions and actions that trigger upon its completion.
 */
public class CompletableFutureDemo {
    static List<NetMall> netMallList = Arrays.asList(
            new NetMall("jd"),
            new NetMall("dangdang"),
            new NetMall("taobap"),
            new NetMall("pdd"),
            new NetMall("tmall")
    );

    public static List<String> getPrice(List<NetMall> list, String productName) {
        return list.stream().map(mall ->
                String.format(productName + " in %s price is %.2f",
                        mall.getMallName(),
                        mall.computePrice(productName)))
                .collect(Collectors.toList());

        // return list.stream().map(e -> e.computePrice(productName)).map(String::valueOf).collect(Collectors.toList());
    }

    public static List<String> getPriceByCompletableFuture(List<NetMall> list, String productName) {
        return list.stream().map(mall ->
                CompletableFuture.supplyAsync(() -> String.format(productName + " in %s price is %.2f",
                        mall.getMallName(),
                        mall.computePrice(productName))))
                .collect(Collectors.toList()).stream().map(s -> s.join()).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        // synchronous method
        List<String> list1 = getPrice(netMallList, "mysql");

        for (String element : list1) {
            System.out.println(element);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Time Costs:  " + (endTime - startTime) + " milli seconds.");
        System.out.println("---------------------------");

        startTime = System.currentTimeMillis();
        // asynchronous method
        List<String> list2 = getPriceByCompletableFuture(netMallList, "mysql");
        for (String e : list2) {
            System.out.println(e);
        }

        endTime = System.currentTimeMillis();
        System.out.println("Time Costs:  " + (endTime - startTime) + " milli seconds.");
    }
}

class NetMall {
    private String mallName;

    public NetMall(String mallName) {
        this.mallName = mallName;
    }

    public String getMallName() {
        return mallName;
    }

    public double computePrice(String productName) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }
}
