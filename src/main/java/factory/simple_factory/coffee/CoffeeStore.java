package factory.simple_factory.coffee;

public class CoffeeStore {

    public Coffee orderCoffee(String type) {
        // Coffee coffee1 = CoffeeFactory.produceCoffee_2(type);  // for static produceCoffee()
        Coffee coffee2 = new CoffeeFactory().produceCoffee_1(type); // for non-static produceCoffee()

        coffee2.addMilk();
        coffee2.addSugar();

        return coffee2;
    }
}
