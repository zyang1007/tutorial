package builder.demo_bike;

public class BuilderY extends Builders {

    @Override
    public void buildFrame() {
        bike.setFrame("iron frame");
    }

    @Override
    public void buildSeat() {
        bike.setSeat("iron seat");
    }

    @Override
    public Bike createBike() {
        return bike;
    }
}
