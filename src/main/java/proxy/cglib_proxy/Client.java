package proxy.cglib_proxy;

public class Client {

    public static void main(String[] args) {
        ProxyFactory factory = new ProxyFactory();
        TrainStation proxyObj = factory.getProxyObject();
        proxyObj.sell();
    }
}
