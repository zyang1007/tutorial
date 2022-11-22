package builder.demo_bike;

public class Director {
    private final Builders builder;

    public Director(Builders builder) {
        this.builder = builder;
    }
    public Bike construct() {
        this.builder.buildFrame();
        this.builder.buildSeat();

        return builder.createBike();
    }
}
