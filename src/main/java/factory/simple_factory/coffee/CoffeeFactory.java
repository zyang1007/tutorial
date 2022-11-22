package factory.simple_factory.coffee;

public class CoffeeFactory {

    public Coffee produceCoffee_1(String type) {
        if ("american".equalsIgnoreCase(type)) {
            return new AmericanCoffee();
        } else if ("latte".equalsIgnoreCase(type)) {
            return new LatteCoffee();
        } else {
            throw new IllegalArgumentException("The type of coffee you ordered does not exist!");
        }
    }

    public static Coffee produceCoffee_2(String type) {
        if ("american".equalsIgnoreCase(type)) {
            return new AmericanCoffee();
        } else if ("latte".equalsIgnoreCase(type)) {
            return new LatteCoffee();
        } else {
            throw new IllegalArgumentException("The type of coffee you ordered does not exist!");
        }
    }
}
