package factory.factory_method;

public class LatteFactory implements Factory {
    @Override
    public Coffee createCoffee() {
        return new LatteCoffee();
    }
}
