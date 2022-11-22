package builder.demo_bike;

public class BuilderX extends Builders {

    @Override
    public void buildFrame() {
        bike.setFrame("carbon frame");
    }

    @Override
    public void buildSeat() {
        bike.setSeat("carbon seat");
    }

    @Override
    public Bike createBike() {
        return bike;
    }
}
