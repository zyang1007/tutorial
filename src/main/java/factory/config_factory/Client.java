package factory.config_factory;

public class Client {

    public static void main(String[] args) {
        Coffee coffee = CoffeeFactory.createCoffee("american");
        System.out.println(coffee.getName());
        coffee.addSugar();

        System.out.println("=======================");
        Coffee coffee1 = CoffeeFactory.createCoffee("latte");
        coffee1.addMilk();
        System.out.println(coffee1.getName());
    }
}
