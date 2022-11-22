package factory.simple_factory.coffee;

public class Buyer {

    public static void main(String[] args) {
        CoffeeStore coffeeStore = new CoffeeStore();
        Coffee coffee = coffeeStore.orderCoffee("latte");
        System.out.println(coffee.getName());

        System.out.println("------------------------------");
        Coffee anotherCoffee = coffeeStore.orderCoffee("american");
        System.out.println(anotherCoffee.getName());
    }
}
