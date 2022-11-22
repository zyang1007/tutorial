package proxy.jdk_proxy;

public class TrainStation implements SellTickets {
    @Override
    public void sell() {
        System.out.println("train station(2) is selling tickets...");
    }
}
