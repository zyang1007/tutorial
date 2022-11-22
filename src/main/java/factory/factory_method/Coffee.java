package factory.factory_method;

public abstract class Coffee {
    abstract String getName();

    public void addSugar() {
        System.out.println("adding sugar...");
    }

    public void addMilk() {
        System.out.println("adding milk...");
    }
}
