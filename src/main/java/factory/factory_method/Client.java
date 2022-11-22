package factory.factory_method;

public class Client {

    public static void main(String[] args) {
        CoffeeStore store = new CoffeeStore();
        store.setFactory(new AmericanFactory());
        Coffee coffee = store.orderCoffee();
        System.out.println(coffee.getName());

        System.out.println("================");
        CoffeeStore coffeeStore = new CoffeeStore(new LatteFactory());
        System.out.println(coffeeStore.orderCoffee().getName());
    }
}
