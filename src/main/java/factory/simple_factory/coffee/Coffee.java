package factory.simple_factory.coffee;

public abstract class Coffee {

    public abstract String getName();

    public void addSugar() {
        System.out.println("adding sugar..");
    }

    public void addMilk() {
        System.out.println("adding milk...");
    }
}
