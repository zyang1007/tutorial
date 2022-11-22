package builder.demo_bike;

public class Client {

    public static void main(String[] args) {
        Director director1 = new Director(new BuilderX());
        Bike bike1 = director1.construct();
        System.out.println(bike1.getFrame());
        System.out.println(bike1.getSeat());

        Director director2 = new Director(new BuilderY());
        Bike bike2 = director2.construct();
        System.out.println(bike2.getFrame());
        System.out.println(bike2.getSeat());
    }
}
