package factory.without_factory;

class Client {

    public static void main(String[] args) {
        CoffeeStore store = new CoffeeStore();

        Coffee coffee = store.orderCoffee("american");
        System.out.println(coffee.getName());
        coffee.addSugar();

        System.out.println("================");
        Coffee anotherCoffee = store.orderCoffee("latte");
        System.out.println(anotherCoffee.getName());
        anotherCoffee.addMilk();
    }
}
