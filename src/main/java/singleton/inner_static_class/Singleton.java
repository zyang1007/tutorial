package singleton.inner_static_class;

public class Singleton {
    private Singleton(){}  // private constructor prevents external access

    public static Singleton getInstance() {  // getter
        return SingletonHolder.INSTANCE;
    }

    // inner static class
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
}
