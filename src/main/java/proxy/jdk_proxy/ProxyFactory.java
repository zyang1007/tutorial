package proxy.jdk_proxy;

import java.lang.reflect.Proxy;

public class ProxyFactory {

    private final TrainStation trainStation = new TrainStation();

    public SellTickets getProxyObject() {
        SellTickets proxy = (SellTickets) Proxy.newProxyInstance(
                trainStation.getClass().getClassLoader(),
                trainStation.getClass().getInterfaces(),
                (proxyObject, method, args) -> {
                    System.out.println("JDK dynamic proxy method");
                    return method.invoke(trainStation, args);
                }
        );

        return proxy;
    }
}
