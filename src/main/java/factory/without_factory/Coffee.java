package factory.without_factory;

abstract class Coffee {
    abstract String getName();

    public void addSugar() {
        System.out.println("adding sugar...");
    }

    public void addMilk() {
        System.out.println("adding milk...");
    }
}
