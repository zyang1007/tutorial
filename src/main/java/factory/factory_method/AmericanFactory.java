package factory.factory_method;

public class AmericanFactory implements Factory {
    @Override
    public Coffee createCoffee() {
        return new AmericanCoffee();
    }
}
