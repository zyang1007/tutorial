package proxy.static_proxy;

public class TrainStation implements SellTickets {
    @Override
    public void sell() {
        System.out.println("train station(1) is selling tickets...");
    }
}
