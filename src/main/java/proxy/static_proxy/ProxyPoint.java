package proxy.static_proxy;

public class ProxyPoint implements SellTickets {

    private final TrainStation trainStation = new TrainStation();

    @Override
    public void sell() {
        System.out.println("proxy point charges service fee!");
        trainStation.sell();
    }
}
