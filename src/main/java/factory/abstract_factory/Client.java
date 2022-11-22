package factory.abstract_factory;

public class Client {

    public static void main(String[] args) {
        DessertFactory dessertFactory1 = new AmericanFactory();

        Coffee coffee1 = dessertFactory1.createCoffee();
        Dessert dessert1 = dessertFactory1.createDessert();

        System.out.println(coffee1.getName());
        coffee1.addSugar();
        dessert1.show();

        System.out.println("======================");

        DessertFactory dessertFactory2 = new ItalyFactory();
        Coffee coffee2 = dessertFactory2.createCoffee();

        System.out.println(coffee2.getName());
        coffee2.addMilk();

        dessertFactory2.createDessert().show();
    }
}
