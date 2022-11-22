package factory.without_factory;

class CoffeeStore {

    public Coffee orderCoffee(String type) {
        switch (type.toUpperCase()) {
            case "AMERICAN":
                return new AmericanCoffee();
            case "LATTE":
                return new LatteCoffee();
            default:
                return null;
        }
    }
}
