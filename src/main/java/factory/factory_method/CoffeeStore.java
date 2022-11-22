package factory.factory_method;

import factory.simple_factory.coffee.CoffeeFactory;

public class CoffeeStore {
    private Factory factory;

    // no parameter constructor
    public CoffeeStore() {
    }

    public CoffeeStore(Factory factory) {
        this.factory = factory;
    }

    // setter
    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public Coffee orderCoffee() {
        Coffee coffee = factory.createCoffee();
        coffee.addMilk();
        coffee.addSugar();

        return coffee;
    }
}
